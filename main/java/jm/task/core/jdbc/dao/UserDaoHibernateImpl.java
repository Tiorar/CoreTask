package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Transaction;
import org.hibernate.Session;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private jm.task.core.jdbc.util.Util Util;
    private jm.task.core.jdbc.model.User;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id BIGINT AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age TINYINT, PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();

    }

    @Override
    public void dropUsersTable() {
            Transaction transaction = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
            Transaction transaction = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                User user = new User(name, lastName, age);
                session.save(user);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
            Transaction transaction = null;
            try (Session session = Util.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                User user = session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                }
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
            try (Session session = Util.getSessionFactory().openSession()) {
                return session.createQuery("FROM User", User.class).list();
            }
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }
}
