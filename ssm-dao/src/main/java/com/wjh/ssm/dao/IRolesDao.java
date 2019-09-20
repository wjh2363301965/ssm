package com.wjh.ssm.dao;

import com.wjh.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRolesDao {

    @Select("SELECT * FROM role WHERE id IN (SELECT roleId FROM users_role WHERE userId=#{Id})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.wjh.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId (String Id) throws Exception;


    @Select("select * from role")
    List<Role> findAll() throws  Exception;




/*   @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
   @Results({
           @Result(id = true, property = "id", column = "id"),
           @Result(property = "roleName", column = "roleName"),
           @Result(property = "roleDesc", column = "roleDesc"),
           @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.wjh.ssm.dao.IPermissionDao.findPermissionByRoleId"))
   })
   public List<Role> findRoleByUserId(String userId) throws Exception;*/
}
