package com.wjh.ssm.service.impl;

import com.wjh.ssm.dao.IPermissionDao;
import com.wjh.ssm.domain.Permission;
import com.wjh.ssm.service.IPermissionService;
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
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
       return permissionDao.findAll();
    }

    @Override
    public void save(Permission p) {
        UUID uuid = new UUID(50,20);
        DateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(new Date());
        p.setId(uuid.toString()+format1);
        permissionDao.save(p);
    }
}
