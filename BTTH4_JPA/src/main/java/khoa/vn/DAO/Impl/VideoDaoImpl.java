package khoa.vn.DAO.Impl;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import khoa.vn.Configs.JPA_Config;
import khoa.vn.DAO.VideoDao;
import khoa.vn.Entity.Video;
import khoa.vn.Entity.User;

public class VideoDaoImpl implements VideoDao {

    @Override
    public List<Video> findAll() {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            return em.createQuery("SELECT v FROM Video v", Video.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Video findById(int id) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            return em.find(Video.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(Video video) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(video);
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
            Video video = em.find(Video.class, id);
            if (video != null) {
                em.remove(video);
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
    public List<Video> search(String keyword) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            TypedQuery<Video> query = em.createQuery(
                "SELECT v FROM Video v WHERE v.title LIKE :kw", Video.class
            );
            query.setParameter("kw", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Video> findByUser(User user) {
        EntityManager em = JPA_Config.getEntityManager();
        try {
            TypedQuery<Video> query = em.createQuery(
                "SELECT v FROM Video v WHERE v.user.id = :uid", Video.class
            );
            query.setParameter("uid", user.getId());
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
