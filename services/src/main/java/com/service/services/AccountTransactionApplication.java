package com.service.services;

import com.service.dto.AccountDTO;
import com.service.dto.Currency;
import com.service.dto.CustomerDTO;
import com.service.services.healthcheck.AccountTransactionServiceHealthCheck;
import com.service.services.resources.AccountResource;
import com.service.services.resources.CustomerResource;
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
        final AccountResource accountResource = new AccountResource();
        final CustomerResource customerResource = new CustomerResource();
        setUpMockData(accountResource, customerResource);
        environment.jersey().register(accountResource);
        environment.jersey().register(customerResource);
    }

    private void setUpMockData(AccountResource accountResource, CustomerResource customerResource) {
        AccountDTO accountDTO1 = new AccountDTO(10012, "40-20-10", 32020d, 500d, Timestamp.valueOf("2019-02-23 10:10:10.0"), Currency.GBP);
        AccountDTO accountDTO2 = new AccountDTO(10014, "56-60-55", 100234d, 1000d, Timestamp.valueOf("2019-03-20 10:10:10.0"), Currency.GBP);

        accountResource.createAccount(accountDTO1);
        accountResource.createAccount(accountDTO2);

        CustomerDTO customerDTO1 = new CustomerDTO(400, "John", "Black", "Test Address 1", "07798435677", Timestamp.valueOf("2019-02-23 10:10:10.0"));
        CustomerDTO customerDTO2 = new CustomerDTO(403, "Sarah", "White", "Test Address 2", "07813769927", Timestamp.valueOf("2019-03-20 10:10:10.0"));

        customerResource.createCustomer(customerDTO1);
        customerResource.createCustomer(customerDTO2);
    }



}