package com.service.services;

import com.service.dao.DbConfig;
import com.service.services.healthcheck.AccountTransactionServiceHealthCheck;
import com.service.services.resources.AccountResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import static com.service.dao.DbConfig.getInMemoryDatabase;

public class AccountTransactionApplication extends Application<AccountTransactionConfiguration> {

    public static void main(String[] args) throws Exception {
        new AccountTransactionApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<AccountTransactionConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AccountTransactionConfiguration configuration,
                    Environment environment) {
        setUpDatabase();
        setUpResources(environment);
        final AccountTransactionServiceHealthCheck healthCheck = new AccountTransactionServiceHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

    private void setUpResources(Environment environment) {
        final AccountResource resource = new AccountResource();
        environment.jersey().register(resource);
    }

    private void setUpDatabase() {
        getInMemoryDatabase();
    }


}