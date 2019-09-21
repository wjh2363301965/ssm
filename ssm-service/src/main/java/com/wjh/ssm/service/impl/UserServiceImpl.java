package com.wjh.ssm.service.impl;

import com.wjh.ssm.dao.IRolesDao;
import com.wjh.ssm.dao.IUserDao;
import com.wjh.ssm.domain.Role;
import com.wjh.ssm.domain.UserInfo;
import com.wjh.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;

        try {
            userInfo = userDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* for (Role role : userInfo.getRoles()) {
            System.out.println(role);
        }*/

//        User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(), getAugetAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus()==0?  false : true,true,true,true,getAugetAuthority(userInfo.getRoles()));


        return user;
    }

    private List<SimpleGrantedAuthority> getAugetAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> list = userDao.findAll();
    return list;
    }

    @Override
    public void save(UserInfo userInfo) throws Exception{
       UUID uuid = new UUID(100,50);

       userInfo.setId(uuid.toString());
       userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
      return   userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String id) {
       return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleId) {

        for (String s : roleId) {
            userDao.addRoleToUser(userId,s);

        }


    }
}
