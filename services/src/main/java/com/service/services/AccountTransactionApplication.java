package com.service.services;

import com.service.dto.AccountDTO;
import com.service.dto.Currency;
import com.service.services.healthcheck.AccountTransactionServiceHealthCheck;
import com.service.services.resources.AccountResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.sql.Timestamp;

public class AccountTransactionApplication extends Application<AccountTransactionConfiguration> {

    public static void main(String[] args) throws Exception {
        new AccountTransactionApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<AccountTransactionConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(AccountTransactionConfiguration configuration,
                    Environment environment) {
        setUpResources(environment);
        final AccountTransactionServiceHealthCheck healthCheck = new AccountTransactionServiceHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

    private void setUpResources(Environment environment) {
        final AccountResource resource = new AccountResource();
        setUpMockData(resource);
        environment.jersey().register(resource);
    }

    private void setUpMockData(AccountResource resource) {
        AccountDTO accountDTO1 = new AccountDTO(10012, "40-20-10", 32020d, 500d, Timestamp.valueOf("2019-02-23 10:10:10.0"), Currency.GBP);
        AccountDTO accountDTO2 = new AccountDTO(10014, "56-60-55", 100234d, 1000d, Timestamp.valueOf("2019-03-20 10:10:10.0"), Currency.GBP);

        resource.createAccount(accountDTO1);
        resource.createAccount(accountDTO2);
    }



}