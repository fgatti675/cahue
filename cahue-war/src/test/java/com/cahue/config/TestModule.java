package com.cahue.config;

import com.cahue.auth.UserAuthenticationService;
import com.cahue.config.persistence.MySQLDataSource;
import com.cahue.resources.SpotsResource;
import com.cahue.resources.TestHelper;
import com.google.inject.AbstractModule;
import com.google.inject.servlet.RequestScoped;
import org.jukito.TestScope;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {

        bindScope(RequestScoped.class, TestScope.SINGLETON);

        bind(MySQLDataSource.class).to(TestDataSource.class);

        bind(TestHelper.class);
        bind(SpotsResource.class);
        bind(UserAuthenticationService.class);
    }

}
