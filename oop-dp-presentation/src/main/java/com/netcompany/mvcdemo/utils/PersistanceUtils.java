package com.netcompany.mvcdemo.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersistanceUtils {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/contacts";
    public static final String USERNAME = "contacts";
    public static final String PASSWORD = "contacts";

    public static void main(String[] args)
    {
        Connection connection = null;

        try
        {
            String driver = ((args.length > 0) ? args[0] : DRIVER);
            String url = ((args.length > 1) ? args[1] : URL);
            String username = ((args.length > 2) ? args[2] : USERNAME);
            String password = ((args.length > 3) ? args[3] : PASSWORD);

            connection = getConnection(driver, url, username, password);

            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("product: " + metaData.getDatabaseProductName());
            System.out.println("version: " + metaData.getDatabaseProductVersion());
            System.out.println("major  : " + metaData.getDatabaseMajorVersion());
            System.out.println("minor  : " + metaData.getDatabaseMinorVersion());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(connection);
        }
    }


    public static Connection getConnection(String driver, String url, String username, String password) throws ClassNotFoundException, SQLException
    {
        Connection connection = null;

        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);

        return connection;
    }

    public static void close(Connection connection)
    {
        try
        {
            if (connection != null)
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement)
    {
        try
        {
            if (statement != null)
            {
                statement.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet)
    {
        try
        {
            if (resultSet != null)
            {
                resultSet.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection connection)
    {
        try
        {
            if (connection != null)
            {
                connection.rollback();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
