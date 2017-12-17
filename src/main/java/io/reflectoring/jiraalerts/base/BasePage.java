package io.reflectoring.jiraalerts.base;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {

	public BasePage() {

	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.render(CssHeaderItem.forUrl("font-awesome/css/font-awesome.css"));
		response.render(CssHeaderItem.forUrl("css/styles.css"));
		response.render(JavaScriptHeaderItem.forUrl("js/jquery-slim.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("js/popper.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("bootstrap/js/bootstrap.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("js/main.js"));
	}
}
