package io.reflectoring.jiraalerts.shared.wickettests;

import de.agilecoders.wicket.core.Bootstrap;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestApplication extends MockApplication {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    protected void init() {
        super.init();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));

        Bootstrap.install(this);
    }
}
