package com.fzd.web.controller;

import com.fzd.dao.CustomerDao;
import com.fzd.dao.EmployeeDao;
import com.fzd.dao.PageResults;
import com.fzd.dao.UserDao;
import com.fzd.model.CustomerEntity;
import com.fzd.model.EmployeeEntity;
import com.fzd.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SRKJ on 2017/4/24.
 */
@RequestMapping(value = "/employee")
public class EmployeeController extends BaseController{
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private UserDao userDao;
    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addCustomer(EmployeeEntity employeeEntity){
        try {
            employeeDao.saveOrUpdate(employeeEntity);
            UserEntity user = new UserEntity();
            user.setUsername(employeeEntity.getPhone());
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
                employeeDao.deleteById(item);
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
            list = (PageResults<CustomerEntity>) employeeDao.findPageByFetchedHql("from CustomerEntity cus", null, pageIndex, pageSize);
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
