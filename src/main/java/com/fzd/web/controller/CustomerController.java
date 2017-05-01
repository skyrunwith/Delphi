package com.fzd.web.controller;

import com.fzd.dao.CategoryDao;
import com.fzd.dao.CustomerDao;
import com.fzd.dao.PageResults;
import com.fzd.dao.UserDao;
import com.fzd.model.CategoryEntity;
import com.fzd.model.CustomerEntity;
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
    @Autowired
    private CategoryDao categoryDao;
    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addCustomer(CustomerEntity customerEntity){
        try {
            UserEntity user = new UserEntity();
            user.setUsername(customerEntity.getEmail());
            user.setPassword("123456");
            user.setType(2);
            customerEntity.setUserEntity(user);
            customerDao.saveOrUpdate(customerEntity);
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

    /**
     * 通过商品id查询客户
     * @param pageIndex
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/getAll"}, method = RequestMethod.POST)
    public Map<String, Object> getCustomers(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex){
        try{
            PageResults<CustomerEntity> list;
//            if(id > 0){
//                CategoryEntity categoryEntity = (com.fzd.model.CategoryEntity) categoryDao.load(id);
//                list = (PageResults<CustomerEntity>) customerDao51.findPageByFetchedHql("from CustomerEntity cus", null, pageIndex, pageSize, categoryEntity.get);
//            }else{
                list = (PageResults<CustomerEntity>) customerDao.findPageByFetchedHql("from CustomerEntity cus", null, pageIndex, pageSize);
//            }
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
            CustomerEntity oldCustomer = (CustomerEntity) customerDao.load(customerEntity.getId());
            UserEntity userEntity = oldCustomer.getUserEntity();
            userEntity.setUsername(customerEntity.getEmail());
            customerEntity.setUserEntity(userEntity);
            customerDao.merge(customerEntity);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }
}
