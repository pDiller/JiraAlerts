package io.reflectoring.jiraalerts.application;

import org.apache.wicket.mock.MockApplication;
import org.springframework.stereotype.Component;

import de.agilecoders.wicket.core.Bootstrap;

@Component
public class TestApplication extends MockApplication {

	private final Object mockRepositoryObject;

	public TestApplication(Object mockRepositoryObject) {
		this.mockRepositoryObject = mockRepositoryObject;
	}

	@Override
	protected void init() {
		super.init();

		getResourceSettings().setLocalizer(new MockLocalizer());

		DefaultInjector mockInjector = new DefaultInjector(new MockFieldValueFactory(mockRepositoryObject));
		getComponentInstantiationListeners().add(mockInjector);
		mockInjector.bind(this);

		Bootstrap.install(this);
	}
}
