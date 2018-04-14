package io.reflectoring.jiraalerts.dashboard.routine;

import static io.reflectoring.jiraalerts.routine.RoutineQueryState.ACTIVE;
import static io.reflectoring.jiraalerts.routine.RoutineQueryState.NOT_ACTIVE;
import static org.mockito.Mockito.verify;

import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;

@RunWith(MockitoJUnitRunner.class)
public class RoutineActionPanelTest {

	private static final long ROUTINE_ID = 11833;

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	private WicketTester wicketTester;
	private RoutineQueryDTO routineQueryDTO = new RoutineQueryDTO();

	@Before
	public void setUp() {
		wicketTester = new WicketTester(new TestApplication(this));
		routineQueryDTO.setId(ROUTINE_ID);
	}

	@Test
	public void render() {
		wicketTester.startComponentInPage(new RoutineActionPanel("panel", new Model<>(routineQueryDTO)));
		wicketTester.assertComponent("panel:editRoutineQueryLink", BookmarkablePageLink.class);
		wicketTester.assertInvisible("panel:activateRoutineQueryLink");
		wicketTester.assertInvisible("panel:deactivateRoutineQueryLink");
	}

	@Test
	public void rendersActivationLinkWhenDeactivated() throws Exception {
		routineQueryDTO.setRoutineQueryState(NOT_ACTIVE);
		wicketTester.startComponentInPage(new RoutineActionPanel("panel", new Model<>(routineQueryDTO)));
		wicketTester.assertComponent("panel:activateRoutineQueryLink", AjaxFallbackLink.class);
		wicketTester.assertInvisible("panel:deactivateRoutineQueryLink");
	}

	@Test
	public void rendersDeactivationLinkWhenActivated() throws Exception {
		routineQueryDTO.setRoutineQueryState(ACTIVE);
		wicketTester.startComponentInPage(new RoutineActionPanel("panel", new Model<>(routineQueryDTO)));
		wicketTester.assertComponent("panel:deactivateRoutineQueryLink", AjaxFallbackLink.class);
		wicketTester.assertInvisible("panel:activateRoutineQueryLink");
	}

	@Test
	public void clickOnDeactivationCallsService() throws Exception {
		routineQueryDTO.setRoutineQueryState(ACTIVE);
		wicketTester.startComponentInPage(new RoutineActionPanel("panel", new Model<>(routineQueryDTO)));
		wicketTester.clickLink("panel:deactivateRoutineQueryLink");

		verify(routineQueryServiceMock).deactivateRoutineQuery(routineQueryDTO);
	}

	@Test
	public void clickOnActivationCallsService() throws Exception {
		routineQueryDTO.setRoutineQueryState(NOT_ACTIVE);
		wicketTester.startComponentInPage(new RoutineActionPanel("panel", new Model<>(routineQueryDTO)));
		wicketTester.clickLink("panel:activateRoutineQueryLink");

		verify(routineQueryServiceMock).activateRoutineQuery(routineQueryDTO);
	}
}
