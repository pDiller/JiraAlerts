package io.reflectoring.jiraalerts.application.testsetup;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.mock.MockHomePage;

public class TestApplication extends AuthenticatedWebApplication {

	private final Object mockRepositoryObject;

	public TestApplication(Object mockRepositoryObject) {
		this.mockRepositoryObject = mockRepositoryObject;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return null;
	}

	@Override
	protected void init() {
		super.init();

		getResourceSettings().setLocalizer(new MockLocalizer());

		DefaultInjector mockInjector = new DefaultInjector(new MockFieldValueFactory(mockRepositoryObject));
		getComponentInstantiationListeners().add(mockInjector);
		mockInjector.bind(this);
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return TestSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return MockHomePage.class;
	}
}
