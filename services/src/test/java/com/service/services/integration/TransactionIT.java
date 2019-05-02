package com.service.services.integration;

import com.service.common.enums.Currency;
import com.service.dto.TransactionDTO;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static junit.framework.TestCase.assertEquals;

public class TransactionIT {

    @ClassRule
    public static final DropwizardAppRule<AccountTransactionConfiguration> RULE =
            new DropwizardAppRule<>(AccountTransactionApplication.class, resourceFilePath("test-config.yaml"));

    @Test
    public void givenAValidAccount_whenPostEndpointIsInvoked_thenTransferFunds() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(10012);
        transactionDTO.setReceiverAccountId(10014);
        transactionDTO.setAmount(new BigDecimal(20));
        transactionDTO.setCurrency(Currency.GBP);

        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        WebTarget webTarget = client.target("http://localhost:8080/").path("transaction");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.json(transactionDTO));

        String account = response.readEntity(String.class);

        String expectedMessage = String.format("Transferred %f%s from account %d to account %d",
                transactionDTO.getAmount().setScale(2, RoundingMode.HALF_UP), transactionDTO.getCurrency().getName(),
                transactionDTO.getSenderAccountId(), transactionDTO.getReceiverAccountId());
        assertEquals(expectedMessage, account);
        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void givenASenderAccountWithInsufficientFunds_whenPostEndpointIsInvoked_thenDoNotTransferFunds() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(10012);
        transactionDTO.setReceiverAccountId(10014);
        transactionDTO.setAmount(new BigDecimal(60000));
        transactionDTO.setCurrency(Currency.GBP);

        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        WebTarget webTarget = client.target("http://localhost:8080/").path("transaction");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.json(transactionDTO));

        String account = response.readEntity(String.class);

        String expectedMessage = String.format("Account %d has insufficient funds", transactionDTO.getSenderAccountId());
        assertEquals(expectedMessage, account);
        assertEquals(response.getStatus(), 400);
    }

    @Test
    public void givenSenderAccountThatDoesNotExist_whenPostEndpointIsInvoked_thenDoNotTransferFunds() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(1000);
        transactionDTO.setReceiverAccountId(10014);
        transactionDTO.setAmount(new BigDecimal(10));
        transactionDTO.setCurrency(Currency.GBP);

        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        WebTarget webTarget = client.target("http://localhost:8080/").path("transaction");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.json(transactionDTO));

        String account = response.readEntity(String.class);

        String expectedMessage = String.format("Account %d does not exist", transactionDTO.getSenderAccountId());
        assertEquals(expectedMessage, account);
        assertEquals(response.getStatus(), 404);
    }

    @Test
    public void givenASenderAccountWithEnoughOverdraft_whenPostEndpointIsInvoked_thenTransferFunds() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(10014);
        transactionDTO.setReceiverAccountId(10012);
        transactionDTO.setAmount(new BigDecimal(100300));
        transactionDTO.setCurrency(Currency.EUR);

        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        WebTarget webTarget = client.target("http://localhost:8080/").path("transaction");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.json(transactionDTO));

        String account = response.readEntity(String.class);

        String expectedMessage = String.format("Transferred %f%s from account %d to account %d",
                transactionDTO.getAmount().setScale(2, RoundingMode.HALF_UP), transactionDTO.getCurrency().getName(),
                transactionDTO.getSenderAccountId(), transactionDTO.getReceiverAccountId());
        assertEquals(expectedMessage, account);
        assertEquals(response.getStatus(), 200);
    }

}
