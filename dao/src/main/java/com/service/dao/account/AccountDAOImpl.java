package com.service.dao.account;

import com.service.domain.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static com.service.dao.DbConfig.getInMemoryDatabase;

public class AccountDAOImpl implements AccountDAO {

    private static final String SELECT_FROM_ACCOUNT_WHERE_ID = "SELECT * FROM Account WHERE accountId = ";


    public Optional<Account> getAccountById(Integer id) {
        try {
            Connection connection = getInMemoryDatabase();
            String sql = SELECT_FROM_ACCOUNT_WHERE_ID + id;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return getAccount(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<Account> getAccount(ResultSet rs){
        try {
            if(rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("accountId"));
                account.setBalance(rs.getDouble("balance"));
                return Optional.of(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
