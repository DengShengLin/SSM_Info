package com.deng.dao;

import com.deng.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DengShenglin
 * 用户Dao接口
 **/
@Repository
public interface UserDao {

    //查询所有用户
    @Select("select * from user")
    public List<User> findAll();

    //用户姓名查找
    @Select("select * from user where username = #{username}")
    public User findUserByName(String username);

    //增加用户信息。。。。
    @Insert("insert into user(username,password) values(#{username},#{password})")
    public int saveUser(User user);

    //注销用户
    @Delete("delete from user where username= #{username}")
    public int deleteUser(User user);

    //修改密码
    @Update("update user set password=#{password} where username=#{username}")
    public int updateUser(@Param("password") String password,@Param("username") String username);


}
