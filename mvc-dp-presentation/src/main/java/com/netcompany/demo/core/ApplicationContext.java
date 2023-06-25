package com.netcompany.demo.core;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.netcompany.demo.dto.Identity;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
    private Console console;
    private Connection connection;
    private Identity identity;
    private final Map<Class, AbstractController<?>> controllerMap;
    private boolean isTerminatingApplication = false;

    public ApplicationContext(Console console, MysqlDataSource dataSource) throws SQLException {
        this.console = console;
        this.connection = dataSource.getConnection();
        this.controllerMap = new HashMap<>();
    }

    public Console getConsole() {
        return console;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Identity getIdentity() {
        return identity;
    }

    public Map<Class, AbstractController<?>> getControllerMap() {
        return controllerMap;
    }

    public <T extends AbstractController<?>> void setControllerInstance(Class<T> clazz, T instance) {
        this.controllerMap.put(clazz, instance);
    }

    public boolean isTerminatingApplication() {
        return isTerminatingApplication;
    }

    public void setTerminatingApplication(boolean terminatingApplication) {
        isTerminatingApplication = terminatingApplication;
    }
}
