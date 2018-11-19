package net.togogo.controller;

import net.togogo.bean.Company;
import net.togogo.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


}
