package com.service.services.resources;

import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public AccountResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public String sayHello(@QueryParam("name") Optional<String> name) {
        //final String value = String.format(template, name.orElse(defaultName));
        return "test";
    }
}