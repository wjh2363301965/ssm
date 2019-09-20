package com.wjh.ssm.dao;

import com.wjh.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

/*    @Select("select * from permission where id in (select permissionId from role_permission where roleId={id})")
    public List<Permission> findPermissionById(String id);*/
@Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
public List<Permission> findPermissionByRoleId(String id) throws Exception;
}
