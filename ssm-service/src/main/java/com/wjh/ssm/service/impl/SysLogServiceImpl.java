package com.wjh.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.wjh.ssm.dao.ISysLogDao;
import com.wjh.ssm.domain.SysLog;
import com.wjh.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

   @Autowired
   private ISysLogDao iSysLogDao;

    @Override
    public void save(SysLog s) throws Exception {
        iSysLogDao.save(s);
    }

    @Override
    public List<SysLog> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return iSysLogDao.findAll();
    }
}
