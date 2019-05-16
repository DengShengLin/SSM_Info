package com.deng.test;

import com.deng.domain.User;
import com.deng.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author DengShenglin
 *
 **/
public class TestSpring {

    @Test
    public void run1(){
        //加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        UserService as = (UserService) ac.getBean("userService");
        //调用方法
        List<User> list = as.findAll();
        for (User user:list){
            System.out.println(user);
        }


    }

    @Test
    public void run2(){
        //加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //获取对象
        UserService as = (UserService) ac.getBean("userService");
        //调用方法
        User user = new User();
        user.setUsername("a");

        user.setPassword("123");
        System.out.println(user);

        int ret = Integer.parseInt(as.VerifyRegister(user));

        System.out.println(ret);
    }


}
