package Dao;

import JPAUtils.JpaUtil;
import entitys.FloorEntity;
import entitys.TypecontractEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class typeDao {
    private EntityManager em;

    public typeDao() {
        this.em = JpaUtil.getEntityManager();
    }


    public List<TypecontractEntity> all(){
        String jpql="SELECT obj from TypecontractEntity obj";
        TypedQuery<TypecontractEntity> query =this.em.createQuery(jpql,TypecontractEntity.class);
        List<TypecontractEntity> list=query.getResultList();
        return list;
    }
    public TypecontractEntity findByID(int id){
        TypecontractEntity entity=this.em.find(TypecontractEntity.class,id);
        return entity;
    }

}
