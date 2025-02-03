//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private static final Logger LOGGER = Logger.getLogger(UserDaoJDBCImpl.class.getName());
//    private final Connection connection = Util.getConnection();
//
//    public UserDaoJDBCImpl() {
//    }
//
//    @Override
//    public void createUsersTable() {
//        String query = "CREATE TABLE IF NOT EXISTS users (" +
//                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
//                "name VARCHAR(45)," +
//                "lastName VARCHAR(45)," +
//                "age TINYINT)";
//        try (Connection connection = Util.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.executeUpdate();
//            LOGGER.info("Таблица создана");
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Ошибка при создании таблицы", e);
//        }
//    }
//
//    @Override
//    public void dropUsersTable() {
//        String query = "DROP TABLE IF EXISTS users";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.executeUpdate();
//            LOGGER.info("Таблица удалена (если она существовала)");
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Ошибка при удалении таблицы", e);
//        }
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, name);
//            statement.setString(2, lastName);
//            statement.setByte(3, age);
//            statement.executeUpdate();
//            LOGGER.info("User " + name + " добавлен в базу данных");
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Ошибка при добавлении пользователя в базу данных", e);
//        }
//    }
//
//    @Override
//    public void removeUserById(long id) {
//        String query = "DELETE FROM users WHERE id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setLong(1, id);
//            statement.executeUpdate();
//            LOGGER.info("Пользователь с id=" + id + " удалён");
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Ошибка при удалении пользователя", e);
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//        String query = "SELECT * FROM users";
//        try (Connection connection = Util.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                String name = resultSet.getString("name");
//                String lastName = resultSet.getString("lastName");
//                byte age = resultSet.getByte("age");
//                users.add(new User(name, lastName, age));
//            }
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Ошибка при извлечении пользователей", e);
//        }
//        return users;
//    }
//
//    @Override
//    public void cleanUsersTable() {
//        String query = "TRUNCATE TABLE users";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.executeUpdate();
//            LOGGER.info("Таблица очищена");
//        } catch (SQLException e) {
//            LOGGER.log(Level.SEVERE, "Ошибка при очистке таблицы", e);
//        }
//    }
//}