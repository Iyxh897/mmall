package com.mmall.service.Impl;

import com.mmall.common.ServerResponse;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImplTest {

    @Autowired
    private IUserService userService;
    @Test
    public void LoginTest(){
        ServerResponse<User> response = userService.login("admin","admin");
        System.out.println(response.getData());
    }
}