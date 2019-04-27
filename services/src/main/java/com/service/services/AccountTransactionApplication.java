package com.service.services;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // nothing to do yet
    }

}