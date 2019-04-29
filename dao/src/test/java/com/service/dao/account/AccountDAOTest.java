package com.service.dao.account;

import com.service.dao.DatabaseUtil;
import com.service.domain.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DatabaseUtil.class)
public class AccountDAOTest {

    @Mock
    SessionFactory sessionFactory;

    @Mock
    Transaction transaction;

    @InjectMocks
    AccountDAOImpl accountDAO;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void createAccount_validAccount_saveToDatabase() {
//        Account account = new Account(1002, "40-30-34");
//
//        mockStatic(DatabaseUtil.class);
//        PowerMockito.when(DatabaseUtil.getSessionFactory()).thenReturn(sessionFactory);
//        when(session.beginTransaction()).thenReturn(transaction);
//
//        accountDAO.createAccount(account);
//    }



}
