package com.service.dao.customer;

import com.service.dao.DatabaseUtil;
import com.service.domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.Optional;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DatabaseUtil.class})
public class CustomerDAOTest {

    private CustomerDAO customerDAO;

    @Before
    public void initMocks() {
        customerDAO = new CustomerDAOImpl();

        mockStatic(DatabaseUtil.class);
    }

    @Test
    public void createCustomer_customer_insertCustomerIntoDb() throws Exception {
        Integer id = 2000;
        Customer customer = new Customer(id);

        customerDAO.createCustomer(customer);

        verifyStatic(VerificationModeFactory.times(1));
    }

    @Test
    public void getCustomerById_validId_returnCustomer() {
        Integer id = 2000;
        Customer customer = new Customer(id);

        when(DatabaseUtil.selectObject(Customer.class, id)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerDAO.getCustomerById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getCustomerId());
    }

    @Test
    public void getCustomerById_invalidId_returnOptionalEmpty() {
        Integer id = 2000;

        when(DatabaseUtil.selectObject(Customer.class, id)).thenReturn(Optional.empty());

        Optional<Customer> result = customerDAO.getCustomerById(id);

        assertFalse(result.isPresent());
    }

}
