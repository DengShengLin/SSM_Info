package com.deng.controller;

import com.deng.domain.User;
import com.deng.re.ServerResponse;
import com.deng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author DengShenglin
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Author: Deng
     * @URL： http://localhost:8080/user/passwordChange
     * @Description: 用户密码更新请求
     * @Create time: 2019/5/15 15:12
      * @Param: username,password,newpassword
     * @Return： status{0,1}(1的话返回用户的用户名和密码（新密码）)
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    @RequestMapping("/passwordChange")
    public ServerResponse passwordChange(User user,String newpassword){
        User u = userService.passwordChange(user,newpassword);

        if(u==null){
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(u);
    }

    /**
     * @Author: Deng
     * @URL： http://localhost:8080/user/register
     * @Description: 用户注册
     * @Create time: 2019/5/15 13:54 
      * @Param: username,password
     * @Return： status{0,1}(若为1则发送用户全部信息)
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    @RequestMapping("/register")
    public ServerResponse register(User user){
        User u=userService.register(user);
        if (u == null){
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(u);
    }

    /**
     * @Author: Deng
     * @URL： http://localhost:8080/user/userNameExist
     * @Description: 查询用户名是否存在
     * @Create time: 2019/5/15 13:39
     * @Param: username
     * @Return： status{0,1}
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    @RequestMapping("/userNameExist")

    public ServerResponse UserNameExist(String username) {

        User user = userService.UserNameExist(username);
        if (user == null) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    /**
     * @Author: Deng
     * @URL： http://localhost:8080/user/findAll
     * @Description: 显示所有用户信息
     * @Create time: 2019/5/15 13:39
     * @Param: null
     * @Return： list(一个status和全部data)
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    @RequestMapping("/findAll")
    public ServerResponse findAll() {
        System.out.println("表现层。返回所有用户信息。。。");
        List<User> list = userService.findAll();
        for (User user : list) {
            System.out.println(user);
        }
        //List<User>类型数据不能直接返回到前端
        return ServerResponse.createBySuccess(list);
    }

    /**
     * @Author: Deng
     * @URL： http://localhost:8080/user/login
     * @Description: 登录校验
     * @Create time: 2019/5/15 13:38
     * @Param: username,password
     * @Return： status{0,1}
     */

    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ServerResponse login(User user, HttpServletRequest req, HttpServletResponse resp) {
        String value = userService.VerifyRegister(user);
        User user1 = userService.findUserByName(user.getUsername());
        if (Integer.parseInt(value) == 1) {
            return ServerResponse.createBySuccess(value, user1);
        }
        return ServerResponse.createByError();

    }


//    @RequestMapping("/Ajax")
//    public @ResponseBody User Ajax(@RequestBody User user) {
//        System.out.println("Ajax方法执行。。。。");
//        // 客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user对象中
//        System.out.println(user);
//        //模拟查询数据
//        user.setUsername("maxiongmiong");
//        user.setPassword("9999999");
//        //做响应
//        return user;
//
//    }


    /**
     * 模拟异步请求响应
     */
    @RequestMapping("/Ajax")
    public @ResponseBody
    User Ajax(@RequestBody User user) {
        System.out.println("Ajax方法执行了...");
        // 客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);
        // 做响应，模拟查询数据库
        user.setUsername("haha");
        user.setPassword("9999999");
        // 做响应
        return user;

    }
}
