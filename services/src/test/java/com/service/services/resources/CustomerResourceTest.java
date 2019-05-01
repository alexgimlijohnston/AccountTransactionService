package com.service.services.resources;

import com.service.businesslogic.customer.CustomerService;
import com.service.businesslogic.customer.CustomerServiceImpl;
import com.service.dto.CustomerDTO;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerResourceTest {

    private CustomerService customerService;

    private CustomerResource customerResource;

    @Before
    public void setup() {
        customerService = mock(CustomerServiceImpl.class);
        customerResource = new CustomerResource(customerService);
    }

    @Test
    public void getCustomer_customerThatExists_returnCustomerAndOkStatusCode() {
        Integer customerId = 100;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerId);

        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(customerDTO));

        Response response = customerResource.getCustomer(customerId);

        assertEquals(response.getStatus(),200);
        assertEquals(response.getEntity(),customerDTO);
    }

    @Test
    public void getCustomer_customerThatDoesNotExist_returnNotFoundStatusCode() {
        Integer customerId = 100;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerId);

        when(customerService.getCustomerById(customerId)).thenReturn(Optional.empty());

        Response response = customerResource.getCustomer(customerId);

        assertEquals(response.getStatus(),404);
        assertEquals(response.getEntity(),String.format("Unable to find customer with id &d", customerId));
    }

    @Test
    public void createCustomer_customerWasAbleToBeCreated_returnOkStatusCode() {
        Integer customerId = 100;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerId);

        Response response = customerResource.createCustomer(customerDTO);

        assertEquals(response.getStatus(),200);
        assertEquals(response.getEntity(),String.format("Customer with id %d was successfully created", customerDTO.getCustomerId()));
    }

    @Test
    public void createCustomer_customerWasNotAbleToBeCreated_returnBadRequestStatusCode() throws Exception {
        Integer customerId = 100;
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerId);

        doThrow(new Exception()).when(customerService).createCustomer(customerDTO);

        Response response = customerResource.createCustomer(customerDTO);

        assertEquals(response.getStatus(),400);
        assertEquals(response.getEntity(),String.format("Customer with id %d could not be created", customerDTO.getCustomerId()));
    }

}
