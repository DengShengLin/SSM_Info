package com.deng.service.impl;

import com.deng.dao.UserDao;
import com.deng.domain.User;
import com.deng.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DengShenglin
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    //Test





    //查询全部用户
    @Override
    public List<User> findAll() {
        System.out.println("业务层。查询所有用户信息");

        return userDao.findAll();
    }


    //校验登录
    @Override
    public String VerifyRegister(User user) {
        System.out.println("业务层。校验用户登录信息中。。。");
        //
        if (user.getUsername() != null && !"".equals(user.getUsername())) {
            if (userDao.findUserByName(user.getUsername()) == null) {
                //用户名存在
                return "0";
            }
            User euser = userDao.findUserByName(user.getUsername());
            String username = euser.getUsername();
            String password = euser.getPassword();

            if (user.getUsername().equals(euser.getUsername())) {
                if (user.getPassword().equals(euser.getPassword())) {
                    //密码正确
                    return "1";
                } else {
                    //密码错误
                    return "0";
                }
            } else {
                //用户名不存在
                return "0";
            }
        }
        //用户名为空
        return "0";
    }


    //用户名校验
    @Override
    public User UserNameExist(String username) {
        User user = userDao.findUserByName(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    //用户名查找用户
    @Override
    public User findUserByName(String username) {

        if (userDao.findUserByName(username) == null || "".equals(userDao.findUserByName(username))) {
            return null;
        } else {
            return userDao.findUserByName(username);
        }
    }

    //用户注册添加用户
    @Override
    public User register(User user) {

        if(user.getUsername()==null || "".equals(user.getUsername())){
            return null;
        }else if (userDao.findUserByName(user.getUsername()) != null){
            return null;
        }
        int i = userDao.saveUser(user);
        System.out.println(i);
        if(i<=0){
            return null;
        }
        return userDao.findUserByName(user.getUsername());
    }

    @Override
    public User passwordChange(User user,String newpassword) {

        User u = userDao.findUserByName(user.getUsername());
        if (u == null){
            return null;
        }
        if (!(u.getPassword().equals(user.getPassword()))){
            return null;
        }
        int i = userDao.updateUser(newpassword,user.getUsername());
        if (i<=0){
            return null;
        }
        return userDao.findUserByName(user.getUsername());
    }
}