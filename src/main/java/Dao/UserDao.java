package DAO;

import JPAUtils.JpaUtil;
import entitys.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDao {
    private EntityManager em;

    public UserDao() {
        this.em = JpaUtil.getEntityManager();
    }

    public UsersEntity create(UsersEntity entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.persist(entity);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
        return entity;
    }

    public UsersEntity update(UsersEntity entity) throws Exception {
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            throw e;
        }
        return entity;
    }


    public List<UsersEntity> all() {
        String jpql = "SELECT obj FROM UsersEntity obj where obj.status=1";
        TypedQuery<UsersEntity> query = this.em.createQuery(jpql, UsersEntity.class);
        List<UsersEntity> ds = query.getResultList();
        return ds;
    }

    public UsersEntity findByID(int id) {
        UsersEntity entity = this.em.find(UsersEntity.class, id);
        return entity;
    }

    public UsersEntity findByEmail(String email) {
        String jpql = "SELECT obj FROM UsersEntity obj " + "WHERE obj.email = :email";
        TypedQuery<UsersEntity> query = this.em.createQuery(jpql, UsersEntity.class);
        query.setParameter("email", email);
        List<UsersEntity> result = query.getResultList();
        if (result.isEmpty()){
            return null;
        }
        return result.get(0);
    }

}
