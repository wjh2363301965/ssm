package com.wjh.ssm.domain;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermission {
    @Select("select * from permission where id in (select from role_permission where roleId=#{id})")
    public List<Permission> findPermissionById(String id);
}
