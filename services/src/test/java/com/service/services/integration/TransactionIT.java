package com.service.services.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.common.enums.Currency;
import com.service.domain.Account;
import com.service.dto.AccountDTO;
import com.service.dto.TransactionDTO;
import com.service.services.AccountTransactionApplication;
import com.service.services.AccountTransactionConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.ClassRule;
import org.junit.Test;

import javax.validation.constraints.NotNull;
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
    public void givenAValidAccount_whenPostEndpointIsInvoked_thenCreateAccount() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSenderAccountId(100012);
        transactionDTO.setReceiverAccountId(100014);
        transactionDTO.setAmount(new BigDecimal(20));
        transactionDTO.setCurrency(Currency.GBP);

        Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
        WebTarget webTarget = client.target("http://localhost:8080/").path("transaction").path("transfer");

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
