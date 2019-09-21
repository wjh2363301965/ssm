package com.wjh.ssm.service;

import com.wjh.ssm.domain.Role;

import java.util.List;

public interface IRoleService {


    public List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;
}
