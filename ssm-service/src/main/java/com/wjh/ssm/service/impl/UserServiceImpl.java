package com.wjh.ssm.service.impl;

import com.wjh.ssm.dao.IUserDao;
import com.wjh.ssm.domain.Role;
import com.wjh.ssm.domain.UserInfo;
import com.wjh.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;

        try {
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(), getAugetAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(), userInfo.getStatus()==0?  false : true,true,true,true,getAugetAuthority(userInfo.getRoles()));


        return user;
    }

    private List<SimpleGrantedAuthority> getAugetAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;
    }

}
