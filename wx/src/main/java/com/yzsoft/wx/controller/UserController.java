package com.yzsoft.wx.controller;


import com.yzsoft.wx.service.UserService;
import com.yzsoft.wx.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserService userService;


    /*
    public Result doLogin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        return Result.ok(userService.findUserByUserName(username));
    }
*/
    @PostMapping("/login")
    public Result doLogin(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
       // String password = loginRequest.getPassword();

        // 你可以在这里添加密码验证逻辑

        return Result.ok(userService.findUserByUserName(username));
    }
    public class LoginRequest {
        private String username;
        private String password;

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}

