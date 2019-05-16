package com.deng.service;

import com.deng.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DengShenglin
 **/


public interface UserService {

    //查询所有用户
    public List<User> findAll();

    //登录校验
    public String VerifyRegister(User user);

    //用户名校验
    public User UserNameExist(String username);

    //姓名查找用户
    public User findUserByName(String username);

    //用户注册
    public User register(User user);

    //用户密码修改
    public User passwordChange(User user,String newpassword);

}
