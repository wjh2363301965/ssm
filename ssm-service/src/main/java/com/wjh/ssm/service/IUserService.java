package com.wjh.ssm.service;

import com.wjh.ssm.domain.Role;
import com.wjh.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService{


    List<UserInfo> findAll();

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String userId, String[] roleId);
}
