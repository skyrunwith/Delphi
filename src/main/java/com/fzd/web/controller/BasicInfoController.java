package com.fzd.web.controller;

import com.fzd.model.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by FZD on 2017/3/15.
 */
@Controller
@RequestMapping(value = {"/basic"})
public class BasicInfoController extends BaseController{

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(HttpSession session){
         UserEntity userEntity = (UserEntity) session.getAttribute("user");
        if(userEntity.getType() == 2){
            return "purchase/purchasing";
        }else if(userEntity.getType() == 3){
            return "purchase/sell";
        }
        return "basic/basicinfo";
    }

    @RequestMapping(value = {"/info"}, method = RequestMethod.GET)
    public String info(){
        return "basic/basicinfo";
    }

    @RequestMapping(value = {"/purchase"}, method = RequestMethod.GET)
    public String purchase(){
        return "purchase/purchasing";
    }

    @RequestMapping(value = {"/sell"}, method = RequestMethod.GET)
    public String sell(){
        return "purchase/sell";
    }

    @RequestMapping(value = {"/storage"}, method = RequestMethod.GET)
    public String storage(){
        return "purchase/storage";
    }

    @RequestMapping(value = {"/financial"}, method = RequestMethod.GET)
    public String financial(){
        return "financial/financial";
    }
}
