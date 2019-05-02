package com.service.services.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccountTransactionServiceHealthCheck extends HealthCheck {

    private final OkHttpClient client;
    private final HttpUrl path;

    public AccountTransactionServiceHealthCheck() {
        client = new OkHttpClient.Builder().build();
        //Valid account id. Should return successful response.
        path = HttpUrl.parse("http://localhost:8080/account/10012");
    }

    @Override
    protected Result check() throws Exception {
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return Result.healthy();
        }
        return Result.unhealthy("code: %s - body: %s", response.code(), response.body().string());
    }



}