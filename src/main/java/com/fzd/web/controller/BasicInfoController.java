package com.fzd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by FZD on 2017/3/15.
 */
@Controller
@RequestMapping(value = {"/basic"})
public class BasicInfoController extends BaseController{

    @RequestMapping(value = {"/info"}, method = RequestMethod.GET)
    public String index(){
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
}
