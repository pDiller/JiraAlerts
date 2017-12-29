package io.reflectoring.jiraalerts.application.testsetup;

import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.injection.IFieldValueFactory;
import org.apache.wicket.injection.Injector;

/**
 * Fills fields with @Inject in wicket components.
 */
public class DefaultInjector extends Injector implements IComponentInstantiationListener {

	private IFieldValueFactory factory;

	public DefaultInjector(IFieldValueFactory factory) {
		this.factory = factory;
	}

	@Override
	public void onInstantiation(org.apache.wicket.Component component) {
		inject(component);
	}

	@Override
	public void inject(Object object) {
		inject(object, factory);
	}
}
