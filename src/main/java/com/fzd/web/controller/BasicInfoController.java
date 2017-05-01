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
        if(userEntity.getType() == 1){
            return "purchase/purchasing_producer";
        }
        if(userEntity.getType() == 2){
            return "purchase/sell_customer";
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
    //厂商销售管理
    @RequestMapping(value = {"purchase/purchasing_producer"}, method = RequestMethod.GET)
    public String purchaseProducer(){
        return "purchase/purchasing_producer";
    }
    //客户采购管理
    @RequestMapping(value = {"sell/sell_customer"}, method = RequestMethod.GET)
    public String sellCustomer(){
        return "purchase/sell_customer";
    }
    //库存管理
    @RequestMapping(value = {"/storage"}, method = RequestMethod.GET)
    public String storage(){
        return "purchase/storage";
    }

    @RequestMapping(value = {"/financial"}, method = RequestMethod.GET)
    public String financial(){
        return "purchase/financial";
    }

    //统计管理
    @RequestMapping(value = {"/statistical"}, method = RequestMethod.GET)
    public String statistical(){
        return "purchase/goodsStatistical";
    }
}
