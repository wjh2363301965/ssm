package com.wjh.ssm.service;

import com.wjh.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll() throws  Exception;

    void save(Permission p);
}
