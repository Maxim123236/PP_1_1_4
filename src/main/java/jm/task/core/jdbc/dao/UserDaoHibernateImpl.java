package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoHibernateImpl.class.getName());
    private static final SessionFactory sessionFactory = Util.getSessionFactory();

@Override
public void createUsersTable() {
    try (Session session = Util.getSessionFactory().openSession()) {
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "lastName VARCHAR(255), " +
                "age TINYINT)").executeUpdate();
        transaction.commit();
        LOGGER.info("Таблица Users успешно создана или уже существует.");
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Ошибка при создании таблицы Users", e);
    }
}

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            transaction.commit();
            LOGGER.info("Таблица Users успешно удалена.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ошибка при удалении таблицы Users", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            LOGGER.info("Пользователь " + name + " " + lastName + " успешно добавлен в базу данных.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ошибка при добавлении пользователя", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                LOGGER.info("Пользователь с ID " + id + " успешно удален.");
            } else {
                LOGGER.warning("Пользователь с ID " + id + " не найден.");
            }
            transaction.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ошибка при удалении пользователя по ID", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).list();
            LOGGER.info("Список пользователей успешно получен.");
            return users;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ошибка при получении списка пользователей", e);
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            LOGGER.info("Таблица Users успешно очищена.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ошибка при очистке таблицы Users", e);
        }
    }

}
