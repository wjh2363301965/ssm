package com.wjh.ssm.service.impl;

import com.wjh.ssm.dao.IRolesDao;
import com.wjh.ssm.domain.Permission;
import com.wjh.ssm.domain.Role;
import com.wjh.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        UUID uuid = new UUID(50, 25);
        DateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());

        role.setId(uuid.toString()+format1);
        rolesDao.save(role);
    }

    @Override
    public Role findRoleById(String id) {
        return rolesDao.findRoleById(id);
    }

    @Override
    public List<Permission> findAllPermission(String id) {
        return rolesDao.findAllPermission(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String p : ids) {
            rolesDao.addPermissionToRole(roleId,p);
        }
    }
}
