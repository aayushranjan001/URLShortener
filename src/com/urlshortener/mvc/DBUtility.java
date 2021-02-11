package com.urlshortener.mvc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
    String dbUrl;
    String user;
    String password;

    DBUtility(String dbUrl, String user, String password)
    {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    public Connection establishConnection() throws SQLException
    {
    	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(dbUrl,user,password);
    }
}
