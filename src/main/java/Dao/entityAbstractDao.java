package Dao;

import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class entityAbstractDao<T> {
    public static final EntityManager em = JpaUtil.getEntityManager();
    public T findById(Class<T> tClass,Integer id){
        return this.em.find(tClass,id);
    }
    public List<T> finAll(Class<T> tClass){
        String nameEntity = tClass.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(nameEntity).append(" o ORDER BY o.dateOrder DESC");
        TypedQuery query = this.em.createQuery(sql.toString(),tClass);
        return query.getResultList();
    }
    public T create(T entity) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(entity);
            this.em.getTransaction().commit();
            System.out.println("Thêm thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            System.out.println("không thể thêm đối tượng " + entity.getClass().getSimpleName() + " trong Database");
            throw new RuntimeException(e);
        }
    }

    public T update(T entity) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
            System.out.println("Update thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            System.out.println("Cannot update entity " + entity.getClass().getSimpleName() + " to Database");
            throw new RuntimeException(e);
        }
    }

    public T delete(T entity) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(entity);
            this.em.getTransaction().commit();
            System.out.println("Delete thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
            System.out.println("Cannot delete entity " + entity.getClass().getSimpleName() + " to Database");
            throw new RuntimeException(e);
        }
    }
}
