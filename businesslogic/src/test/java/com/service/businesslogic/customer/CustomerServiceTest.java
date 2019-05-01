package com.service.businesslogic.customer;

import com.service.dao.customer.CustomerDAO;
import com.service.dao.customer.CustomerDAOImpl;
import com.service.domain.Customer;
import com.service.dto.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import java.util.Optional;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CustomerServiceTest {

    private CustomerService customerService;

    private CustomerDAO customerDAO;

    private CustomerMapper customerMapper;

    @Before
    public void setup() {
        customerDAO = mock(CustomerDAOImpl.class);
        customerMapper = mock(CustomerMapper.class);

        customerService = new CustomerServiceImpl(customerDAO, customerMapper);
    }

    @Test
    public void getCustomerById_validId_returnMappedCustomerDto() {
        Integer customerId = 100;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerId);

        when(customerDAO.getCustomerById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.mapCustomerToCustomerDTO(customer)).thenReturn(customerDTO);

        Optional<CustomerDTO> returned = customerService.getCustomerById(customerId);

        assertTrue(returned.isPresent());
        assertEquals(returned.get(), customerDTO);
    }

    @Test
    public void getCustomerById_invalidId_returnOptionalEmpty() {
        Integer customerId = 100;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        when(customerDAO.getCustomerById(customerId)).thenReturn(Optional.empty());

        Optional<CustomerDTO> returned = customerService.getCustomerById(customerId);

        assertFalse(returned.isPresent());
    }

    @Test
    public void createCustomer_validCustomerDto_mapAndCreateCustomer() throws Exception {
        Integer customerId = 100;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customerId);

        when(customerMapper.mapCustomerDTOToCustomer(customerDTO)).thenReturn(customer);

        customerService.createCustomer(customerDTO);

        verify(customerDAO, times(1)).createCustomer(customer);
    }

}