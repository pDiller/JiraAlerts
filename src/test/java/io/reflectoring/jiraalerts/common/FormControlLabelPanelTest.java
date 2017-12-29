package io.reflectoring.jiraalerts.common;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class FormControlLabelPanelTest {

	private static final String TEST_LABEL = "label";
	private static final String TEST_VALUE = "value";

	private WicketTester wicketTester = new WicketTester();

	@Test
	public void rendersSuccessfull() throws Exception {
		wicketTester.startComponentInPage(new FormControlLabelPanel("panel", Model.of(TEST_VALUE), Model.of(TEST_LABEL)));

		wicketTester.assertComponent("panel:label", Label.class);
		wicketTester.assertComponent("panel:input", Label.class);
	}

	@Test
	public void valueModelIsBoundToInputLabel() throws Exception {
		wicketTester.startComponentInPage(new FormControlLabelPanel("panel", Model.of(TEST_VALUE), Model.of(TEST_LABEL)));

		wicketTester.assertModelValue("panel:input", TEST_VALUE);
	}

	@Test
	public void labelModelIsBoundToLabel() throws Exception {
		wicketTester.startComponentInPage(new FormControlLabelPanel("panel", Model.of(TEST_VALUE), Model.of(TEST_LABEL)));

		wicketTester.assertModelValue("panel:label", TEST_LABEL);
	}
}
