package khoa.vn.DAO.Impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import khoa.vn.Configs.JPA_Config;
import khoa.vn.DAO.UserDao;
import khoa.vn.Entity.User;

public class UserDaoImpl implements UserDao {

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close(); // quan trọng: đóng em
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public User findById(int id) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(User user) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> search(String keyword) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username LIKE :kw", User.class
            );
            query.setParameter("kw", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public User findByUsername(String username) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class
            );
            query.setParameter("username", username);
            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }
}
