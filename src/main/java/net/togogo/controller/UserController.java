package net.togogo.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.togogo.bean.*;
import net.togogo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    CompanyMapper companyMapper;

    @Autowired(required = false)
    CollectMapper collectMapper;

    @Autowired(required = false)
    DetailMapper detailMapper;

    @Autowired(required = false)
    ApplicationMapper applicationMapper;


    @RequestMapping("/hello")
    @ResponseBody
    public Boolean sayHello() {
        User user = userMapper.selectbyusername("123");

        Boolean result = user.equals(null);
        return result;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") int id) {
        return userMapper.selectbyid(id);

    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "user_reg";
    }

    @RequestMapping("/check_uname")
    @ResponseBody
    public boolean checkuname(HttpServletRequest request, HttpServletResponse response) {
        String uname = request.getParameter("uname");
        System.out.println(uname);
        if ((userMapper.selectbyusername(uname) == null) == false)
            return false;
        else
            return true;
    }

    @RequestMapping("/check_email")
    @ResponseBody
    public boolean checkemail(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        System.out.println(email);
        if ((userMapper.selectbyemail(email) == null) == false)
            return false;
        else
            return true;
    }

    @RequestMapping("/userinsert")
    @ResponseBody
    public String userinsert(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userMapper.insert(user);
        return "success";


    }

    @RequestMapping("/coregister")
    public String coregister() {
        return "company_reg";
    }


    @RequestMapping("/logincheck")
    @ResponseBody
    public String logincheck(String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = userMapper.selectbyusername(username);
        Company company = companyMapper.selectbyusername(username);
        if (user == null && company == null)
            return "err";

        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            session.setAttribute("username", username);
            return "success";
        } else if (company!=null&&company.getUsername().equals(username) && company.getPassword().equals(password)) {
            session.setAttribute("companyname", username);

            return "succ";
        } else
            return "err";
    }

    @RequestMapping("/logininfo")
    @ResponseBody
    public String logininfo(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null)
            return "欢迎来到IT猎户网 51itjob.net！&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/login\">[登录]</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/register\">[免费注册]</a>";

        else
            return "欢迎&nbsp;&nbsp;<a style=\"color:#339900\" href=\"/userindex\">" + username + "</a> 登录！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/userindex\">[会员中心]</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/logout\">[退出]";

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("username", "");
        return "/login";
    }

    @RequestMapping("/hi")
    public String hi() {
        return "hi";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/userindex")
    public String userindex(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");

        User user = userMapper.selectbyusername(username);
        List<Application> apply = applicationMapper.selectbyuserid(user.getId());
        List<Application> interview = applicationMapper.selectbystatus(user.getId());

        int applynum = apply.size();
        int interviewnum = interview.size();
        model.addAttribute("person", user);
        model.addAttribute("applynum",applynum);
        model.addAttribute("interviewnum",interviewnum);

        return "user_index";
    }

    @RequestMapping("/userprofile")
    public String profile(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = userMapper.selectbyusername(username);
        session.setAttribute("person", user);
        model.addAttribute("person", user);
        System.out.println(user.toString());

        return "user/profile";
    }

    @RequestMapping("/save_userprofile")
    public String saveprofile(String sex_cn, String education_cn, String mobile, HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = userMapper.selectbyusername(username);

        user.setSex(sex_cn);
        user.setDegree(education_cn);
        user.setPhone(mobile);
        userMapper.updateuser(user);

        return "redirect:/userprofile";
    }

    @RequestMapping("/userpassword")
    public String userpassword() {
        return "user/userpassword";
    }

    @RequestMapping("/userfavourite")
    public String favourite(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = userMapper.selectbyusername(username);
        int id = user.getId();
        System.out.println(id);

        List<Collect> collect = collectMapper.selectbyuserid(id);

        List<CollectVO> collectVOList = new ArrayList<CollectVO>();

        for (int i = 0; i < collect.size(); i++) {
            CollectVO collectVO = new CollectVO();
            collectVO.setJobname(detailMapper.selectbyid(collect.get(i).getDetailId()).getJobname());
            collectVO.setCompanyname(companyMapper.selectByPrimaryKey(detailMapper.selectbyid(collect.get(i).getDetailId()).getCompanyId()).getFullname());
            collectVO.setSalary(detailMapper.selectbyid(collect.get(i).getDetailId()).getSalary());
            collectVOList.add(collectVO);

        }

        model.addAttribute("collect", collectVOList);
        return "user/favourite";
    }

    @RequestMapping("/userapply")
    public String apply(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        User user = userMapper.selectbyusername(username);
        int id = user.getId();
        List<Application> application = applicationMapper.selectbyuserid(id);
        List<ApplicationVO> applicationVOList = new ArrayList<>();
        for(int i=0;i<application.size();i++){
            ApplicationVO applicationVO = new ApplicationVO();
            applicationVO.setCompanyname(companyMapper.selectByPrimaryKey(detailMapper.selectbyid(application.get(i).getDetailId()).getCompanyId()).getFullname());
            applicationVO.setJobname(detailMapper.selectbyid(application.get(i).getDetailId()).getJobname());
            applicationVO.setStatus(application.get(i).getState().toString());
            applicationVOList.add(applicationVO);
        }

        model.addAttribute("apply",applicationVOList);

        return "user/userapply";
    }

    @RequestMapping("/userinterview")
    public String interview(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        User user = userMapper.selectbyusername(username);
        int id = user.getId();
        List<Application> application = applicationMapper.selectbystatus(id);
        List<ApplicationVO> applicationVOList = new ArrayList<>();
        for(int i=0;i<application.size();i++){
            ApplicationVO applicationVO = new ApplicationVO();
            applicationVO.setApplication(application.get(i));
            applicationVO.setCompanyname(companyMapper.selectByPrimaryKey(detailMapper.selectbyid(application.get(i).getDetailId()).getCompanyId()).getFullname());
            applicationVO.setJobname(detailMapper.selectbyid(application.get(i).getDetailId()).getJobname());
            applicationVO.setStatus(application.get(i).getState().toString());
            applicationVOList.add(applicationVO);
        }

        model.addAttribute("interview",applicationVOList);
        return "user/userinterview";
    }

    @RequestMapping("del_interview")
    public void delinterview(String id){
        System.out.println("该方法传过来的值为"+id);

    }

    @RequestMapping("test")
    public String test(HttpSession session, Model model) {

        List<Collect> collect = collectMapper.selectbyuserid(1);
        System.out.println(companyMapper.selectByPrimaryKey(1).getFullname());


        System.out.println(collect.get(0).getDetailId());
        System.out.println(detailMapper.selectbyid(1).getCompanyId());


        model.addAttribute("user", collect);
        return "user/test";
    }


}
