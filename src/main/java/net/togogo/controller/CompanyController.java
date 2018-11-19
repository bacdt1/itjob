package net.togogo.controller;

import net.togogo.bean.Company;
import net.togogo.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static javax.swing.text.html.CSS.getAttribute;

@Controller
public class CompanyController {
    @Autowired(required = false)
    CompanyMapper companyMapper;

    @RequestMapping("/coinsert")
    @ResponseBody
    public String companyinsert(String username,String password,String email){
        Company company = new Company();
        company.setUsername(username);
        company.setPassword(password);
        company.setEmail(email);
        companyMapper.insert(company);
        return "success";


    }


    @RequestMapping("/check_cname")
    @ResponseBody
    public boolean checkcname(HttpServletRequest request, HttpServletResponse response){
        String uname = request.getParameter("usname");
        System.out.println(uname);
        if((companyMapper.selectbyusername(uname)==null)==false)
            return false;
        else
            return  true;
    }

    @RequestMapping("/check_cemail")
    @ResponseBody
    public boolean checkcemail(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        System.out.println(email);
        if((companyMapper.selectbyemail(email)==null)==false)
            return false;
        else
            return  true;
    }

    @RequestMapping("/companylogininfo")
    @ResponseBody
    public String companylogininfo(HttpSession session) {
        String companyname = (String) session.getAttribute("companyname");
        if (companyname == null)
            return "欢迎来到IT猎户网 51itjob.net！&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/login\">[登录]</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/register\">[免费注册]</a>";

        else
            return "欢迎&nbsp;&nbsp;<a style=\"color:#339900\" href=\"/companyindex\">" + companyname + "</a> 登录！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/userindex\">[会员中心]</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a style=\"color:#0066cc\" href=\"/logout\">[退出]";

    }

    @RequestMapping("/companyindex")
    public String companyindex(HttpSession session, Model model){
        String companyname = (String) session.getAttribute("companyname");
        Company company = companyMapper.selectbyusername(companyname);
        model.addAttribute("company",company);
        return "company_index";

    }

    @RequestMapping("/companyinterview")
    public String companyinterview(){
        return "company/companyinterview";
    }


}
