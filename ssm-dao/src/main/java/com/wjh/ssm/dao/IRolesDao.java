package com.wjh.ssm.dao;

import com.wjh.ssm.domain.Permission;
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

    @Insert("insert into role values(#{id},#{roleName},#{roleDesc})")
    void save(Role role) throws  Exception;


    @Select("select * from role where id=#{id}")
    Role findRoleById(String id);


    @Select("select * from permission where id not in (select PermissionId from role_permission where roleId = #{id})")
    List<Permission> findAllPermission(String id);

    @Insert("insert into role_permission values (#{p},#{roleid})")
    void addPermissionToRole(@Param("roleid") String roleId,@Param("p") String  p);




/*   @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
   @Results({
           @Result(id = true, property = "id", column = "id"),
           @Result(property = "roleName", column = "roleName"),
           @Result(property = "roleDesc", column = "roleDesc"),
           @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.wjh.ssm.dao.IPermissionDao.findPermissionByRoleId"))
   })
   public List<Role> findRoleByUserId(String userId) throws Exception;*/
}
