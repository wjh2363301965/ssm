package com.wjh.ssm.service.impl;

import com.wjh.ssm.dao.IRolesDao;
import com.wjh.ssm.domain.Role;
import com.wjh.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRolesDao rolesDao;

    @Override
    public List<Role> findAll() throws Exception {
        return rolesDao.findAll();
    }
}
