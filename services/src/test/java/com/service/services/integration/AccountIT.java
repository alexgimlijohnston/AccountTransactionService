package com.service.services.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.common.enums.Currency;
import com.service.dto.AccountDTO;
import com.service.services.AccountTransactionApplication;
import com.service.services.AccountTransactionConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.ClassRule;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountIT {

    @ClassRule
    public static final DropwizardAppRule<AccountTransactionConfiguration> RULE =
            new DropwizardAppRule<>(AccountTransactionApplication.class, resourceFilePath("test-config.yaml"));

    @Test
    public void givenAValidAccountId_whenGetEndpointIsInvoked_thenReturnAccount() throws IOException {
        String id = "10012";
        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        WebTarget webTarget = client.target("http://localhost:8080/").path("account").path(id);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        String account = response.readEntity(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        AccountDTO accountDTO = objectMapper.readValue(account, AccountDTO.class);

        assertNotNull(accountDTO);
        assertEquals(String.valueOf(accountDTO.getAccountId()), id);
        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void givenAnInvalidAccountId_whenGetEndpointIsInvoked_thenReturnNotFoundCode() {
        String id = "10013";
        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        WebTarget webTarget = client.target("http://localhost:8080/").path("account").path(id);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        String account = response.readEntity(String.class);

        String expectedError = String.format("Unable to find account with id %s", id);
        assertEquals(account, expectedError);
        assertEquals(response.getStatus(), 404);
    }

}
