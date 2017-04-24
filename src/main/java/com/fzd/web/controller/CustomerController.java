package com.fzd.web.controller;

import com.fzd.dao.CustomerDao;
import com.fzd.dao.PageResults;
import com.fzd.dao.ProducerDao;
import com.fzd.dao.UserDao;
import com.fzd.model.CustomerEntity;
import com.fzd.model.ProducerEntity;
import com.fzd.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FZD on 2017/3/20.
 */
@Controller
@RequestMapping(value = {"/customer"})
public class CustomerController extends BaseController{
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private UserDao userDao;
    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addCustomer(CustomerEntity customerEntity){
        try {
            customerDao.saveOrUpdate(customerEntity);
            UserEntity user = new UserEntity();
            user.setUsername(customerEntity.getEmail());
            user.setPassword("123456");
            user.setType(1);
            userDao.save(user);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/del"}, method = RequestMethod.POST)
    public Map<String, Object> deleteCustomer(@RequestParam(value = "id",required = false, defaultValue = "")Integer[] ids){
        try{
            for(Integer item : ids){
                customerDao.deleteById(item);
            }
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/getAll"}, method = RequestMethod.POST)
    public Map<String, Object> getCustomers(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex){
        try{
            PageResults<CustomerEntity> list;
            list = (PageResults<CustomerEntity>) customerDao.findPageByFetchedHql("from CustomerEntity cus", null, pageIndex, pageSize);
            map = new HashMap<>();
            map.put("list",list);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public Map<String, Object> updateCustomer(CustomerEntity customerEntity ){
        try{
            customerDao.saveOrUpdate(customerEntity);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }
}
