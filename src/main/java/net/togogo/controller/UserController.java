package net.togogo.controller;

import net.togogo.bean.User;
import net.togogo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

   @Autowired(required = false)
   UserMapper userMapper;

    @RequestMapping("/hello")
    @ResponseBody
    public User sayHello(){
        User user = new User();
        user.setId(1);
        return user;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id")int id){
        return userMapper.selectbyid(id);

    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/hi")
    public String hi(){
        return "hi";
    }


}
