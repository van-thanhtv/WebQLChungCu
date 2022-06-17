package Dao;

import JPAUtils.JpaUtil;
import entitys.Customer;
import entitys.UsersEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class customerDao {
    private EntityManager em;

    public customerDao() {
        this.em = JpaUtil.getEntityManager();
    }

    public Customer create(Customer entity) throws Exception {
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

    public Customer update(Customer entity) throws Exception {
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


    public List<Customer> all() {
        String jpql = "SELECT obj FROM Customer obj where obj.status=true ";
        TypedQuery<Customer> query = this.em.createQuery(jpql, Customer.class);
        List<Customer> ds = query.getResultList();
        return ds;
    }

    public Customer findByID(int id) {
        Customer entity = this.em.find(Customer.class, id);
        return entity;
    }

}
