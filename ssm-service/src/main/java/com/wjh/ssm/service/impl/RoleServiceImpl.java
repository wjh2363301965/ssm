package com.wjh.ssm.service.impl;

import com.wjh.ssm.dao.IRolesDao;
import com.wjh.ssm.domain.Role;
import com.wjh.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRolesDao rolesDao;

    @Override
    public List<Role> findAll() throws Exception {
        return rolesDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        UUID uuid = new UUID(50,25);
        role.setId(uuid.toString());
       rolesDao.save(role);
    }
}
