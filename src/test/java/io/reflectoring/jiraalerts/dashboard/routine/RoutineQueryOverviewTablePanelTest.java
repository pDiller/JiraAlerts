package io.reflectoring.jiraalerts.dashboard.routine;

import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reflectoring.jiraalerts.application.testsetup.TestApplication;
import io.reflectoring.jiraalerts.application.testsetup.TestSession;

@RunWith(MockitoJUnitRunner.class)
public class RoutineQueryOverviewTablePanelTest {

	private static final long USER_ID = 1337;

	@Mock
	private RoutineQueryService routineQueryServiceMock;

	private WicketTester wicketTester;

	@Before
	public void setUp() throws Exception {
		wicketTester = new WicketTester(new TestApplication(this));
		TestSession.get().setRoles(new Roles("administrator"));
		wicketTester.startComponentInPage(new RoutineQueryOverviewTablePanel("panel", Model.of(new RoutineQueryDTO()), USER_ID));
	}

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.assertComponent("panel:routineQueryTable", RoutineQueryOverviewTable.class);
		wicketTester.assertComponent("panel:showAllRoutinesLink", BookmarkablePageLink.class);
	}

	@Test
	public void clickLinkForNewRoutineCreationOpensPage() throws Exception {
		wicketTester.clickLink("panel:showAllRoutinesLink");

		wicketTester.assertRenderedPage(RoutineQueriesDetailPage.class);
	}
}
