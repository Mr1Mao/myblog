package com.example.myblog.controller;

import com.example.myblog.entity.Admin;
import com.example.myblog.mapper.LoginMapper;
import com.example.myblog.response.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginMapper loginMapper;

    @RequestMapping("/getUser/{userId}")
    public Admin login(@PathVariable("userId") Integer userId) {
        return loginMapper.getUser(userId);
    }

    @RequestMapping("/login")
    public ResponseFormat login(HttpSession session, @RequestBody Map<String,Object> user) {
        String userCount = (String) user.get("adminCount");
        String password = (String) user.get("password");
        Admin admin = loginMapper.gerUserByIdAndPassword(userCount,password);
        ResponseFormat responseFormat =  new ResponseFormat();
        if(admin != null){
            System.out.println("登入成功");
            session.setAttribute("admin",admin);
            responseFormat.setCode(0);
            responseFormat.setSuccess(true);
            responseFormat.setData(admin);
            responseFormat.setMsg("登入成功");
        }else{
            System.out.println("登入失败");
            responseFormat.setCode(1);
            responseFormat.setSuccess(false);
            responseFormat.setMsg("登入失败，用户名或密码错误");
        }
        return responseFormat;
    }

    @RequestMapping("/logout")
    public ResponseFormat logout(HttpSession session,@RequestBody Admin admin){
        ResponseFormat responseFormat =  new ResponseFormat();
        session.invalidate();
        responseFormat.setCode(1);
        responseFormat.setSuccess(true);
        responseFormat.setMsg("注销成功");
        System.out.println("注销成功");
        return responseFormat;
    }
}
