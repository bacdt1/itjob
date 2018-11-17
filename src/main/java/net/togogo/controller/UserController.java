package net.togogo.controller;

import net.togogo.bean.User;
import net.togogo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

   @Autowired(required = false)
   UserMapper userMapper;

    @RequestMapping("/hello")
    @ResponseBody
    public Boolean sayHello(){
        User user = userMapper.selectbyusername("123");

        Boolean result  = user.equals(null);
        return result;
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

    @RequestMapping("/register")
    public String register(){
        return "user_reg";
    }

    @RequestMapping("/check_uname")
    @ResponseBody
    public boolean checkuname(HttpServletRequest request, HttpServletResponse response){
        String uname = request.getParameter("uname");
        System.out.println(uname);
      if((userMapper.selectbyusername(uname)==null)==false)
        return false;
      else
          return  true;
    }

    @RequestMapping("/check_email")
    @ResponseBody
    public boolean checkemail(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        System.out.println(email);
        if((userMapper.selectbyemail(email)==null)==false)
            return false;
        else
            return  true;
    }

    @RequestMapping("/userinsert")
    @ResponseBody
    public String userinsert(String username,String password,String email){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userMapper.insert(user);
        return "success";


    }

    @RequestMapping("/coregister")
    public String coregister(){
        return "company_reg";
    }



    @RequestMapping("/logincheck")
    @ResponseBody
    public String logincheck(String username,String password,HttpSession session,HttpServletRequest request, HttpServletResponse response){
        User user = userMapper.selectbyusername(username);
        if (user==null)
            return "err";

        if(user.getUsername().equals(username)&&user.getPassword().equals(password)) {
            session.setAttribute("username",username);
            return "success";
        }
        else
            return "err";
    }

    @RequestMapping("/logininfo")
    @ResponseBody
    public String logininfo(HttpSession session){
        String username =(String) session.getAttribute("username");
        if(username==null)
            return "欢迎来到IT猎户网 51itjob.net！&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/login\">[登录]</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/register\">[免费注册]</a>";

        else
            return "欢迎&nbsp;&nbsp;<a style=\"color:#339900\" href=\"/userindex\">"+username+"</a> 登录！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/userindex\">[会员中心]</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/logout\">[退出]";

    }

    @RequestMapping("/hi")
    public String hi(){
        return "hi";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/userindex")
    public String userindex(){
        return "user_index";
    }

    @RequestMapping("/userprofile")
    public String profile(HttpSession session, Model model){
        String username =(String) session.getAttribute("username");
        User user = userMapper.selectbyusername(username);
        session.setAttribute("person",user);
        model.addAttribute("person",user);
        System.out.println(user.toString());

        return "/user/profile";
    }

    @RequestMapping("/userfavourite")
    public String favourite(){
        return "/user/favourite";
    }

    @RequestMapping("/userapply")
    public String apply(){
        return "/user/userapply";
    }

    @RequestMapping("/userinterview")
    public String interview(){
        return "/user/userinterview";
    }

    @RequestMapping("test")
    public String test(HttpSession session,Model model){

        User user = userMapper.selectbyusername("张三");
        session.setAttribute("user",user);
        model.addAttribute("person",user);
        return "/user/test";
    }



}
