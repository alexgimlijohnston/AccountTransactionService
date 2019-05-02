package com.service.services.healthcheck;

import com.codahale.metrics.health.HealthCheck;

public class AccountTransactionServiceHealthCheck extends HealthCheck {

    public AccountTransactionServiceHealthCheck() {
    }

    @Override
    protected Result check() throws Exception {
//        if (!saying.contains("TEST")) {
//            return Result.unhealthy("template doesn't include a name");
//        }
        return Result.healthy();
    }
}