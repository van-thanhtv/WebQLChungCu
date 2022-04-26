package Dao;

import entitys.UsersEntity;

import java.util.List;

public class userDao extends entityAbstractDao<UsersEntity>{
    public UsersEntity findById(Integer id){
        return super.findById(UsersEntity.class,id);
    }
    public List<UsersEntity> finAll(){
        return super.finAll(UsersEntity.class);
    }
    public UsersEntity add (UsersEntity entity){
        return super.create(entity);
    }
    public UsersEntity edit (UsersEntity entity){
        return super.update(entity);
    }
    public UsersEntity delete (UsersEntity entity){
        return super.delete(entity);
    }
}
