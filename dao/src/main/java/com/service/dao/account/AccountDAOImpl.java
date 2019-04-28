package com.service.dao.account;

import com.service.domain.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.service.dao.DbConfig.getInMemoryDatabase;

public class AccountDAOImpl implements AccountDAO {


    public Account getAccountById(Integer id) {
        Connection connection = getInMemoryDatabase();
        String sql = "SELECT * FROM Account WHERE ID = "+ id;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("id"));
                account.setAccountHolderName(rs.getString("name"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
