package com.wjh.ssm.dao;


import com.wjh.ssm.domain.Role;
import com.wjh.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {



    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.wjh.ssm.dao.IRolesDao.findRoleByUserId"))

    })
    public UserInfo findByUsername(String username) throws Exception;

  /*  @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.wjh.ssm.dao.IRolesDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;*/


    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;


    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.wjh.ssm.dao.IRolesDao.findRoleByUserId"))
    })
    UserInfo findById(String id);


    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    List<Role> findOtherRoles(String id);


    @Insert("insert into users_role values(#{userId},#{s})")
    void addRoleToUser(@Param("userId") String userId,@Param("s") String s);
}
