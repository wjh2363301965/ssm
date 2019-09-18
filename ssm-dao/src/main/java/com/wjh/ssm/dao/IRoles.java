package com.wjh.ssm.dao;

import com.wjh.ssm.domain.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRoles {

    @Select("SELECT * FROM role WHERE id IN (SELECT roleId FROM users_role WHERE userId=#{id})")
  /*  @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.wjh.ssm.IPermission.findPermissionById"))
    })*/
    public List<Role> findRolesById( String id);
}
