package com.service.services;

import com.service.dto.AccountDTO;
import com.service.common.enums.Currency;
import com.service.dto.CustomerDTO;
import com.service.services.healthcheck.AccountTransactionServiceHealthCheck;
import com.service.services.resources.AccountResource;
import com.service.services.resources.CustomerResource;
import com.service.services.resources.TransactionResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;

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
        final TransactionResource transactionResource = new TransactionResource();
        setUpMockData(accountResource, customerResource);
        environment.jersey().register(accountResource);
        environment.jersey().register(customerResource);
        environment.jersey().register(transactionResource);
    }

    private void setUpMockData(AccountResource accountResource, CustomerResource customerResource) {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        AccountDTO accountDTO1 = new AccountDTO(10012, "40-20-10", new BigDecimal(32020), new BigDecimal(500), format.parseDateTime("2019-03-18T20:40:00"), Currency.GBP);
        AccountDTO accountDTO2 = new AccountDTO(10014, "56-60-55", new BigDecimal(100234), new BigDecimal(1000), format.parseDateTime("2019-03-19T20:40:00"), Currency.EUR);
//
        accountResource.createAccount(accountDTO1);
        accountResource.createAccount(accountDTO2);

//        CustomerDTO customerDTO1 = new CustomerDTO(400, "John", "Black", "Test Address 1", "07798435677", format.parseDateTime("2019-03-18T20:40:00"));
//        CustomerDTO customerDTO2 = new CustomerDTO(403, "Sarah", "White", "Test Address 2", "07813769927", format.parseDateTime("2019-03-19T20:40:00"));
//
//        customerResource.createCustomer(customerDTO1);
//        customerResource.createCustomer(customerDTO2);
    }



}