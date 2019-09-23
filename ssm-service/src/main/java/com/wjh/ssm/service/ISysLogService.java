package com.wjh.ssm.service;

import com.wjh.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog s) throws Exception;

    List<SysLog> findAll(Integer page,Integer size) throws Exception;
}
