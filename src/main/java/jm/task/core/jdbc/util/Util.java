package jm.task.core.jdbc.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/newDataBase";
    private static final String USER = "root";
    private static final String PASSWORD = "Maksum073!";

    private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.info("Успешное подключение к базе данных!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка подключения к базе данных!", e);
        }
        return connection;
    }
}
