package com.fzd.web.controller;

import com.fzd.dao.EmployeeDao;
import com.fzd.dao.PageResults;
import com.fzd.dao.UserDao;
import com.fzd.model.EmployeeEntity;
import com.fzd.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SRKJ on 2017/4/24.
 */
@RequestMapping(value = "/employee")
@Controller
public class EmployeeController extends BaseController{
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private UserDao userDao;
    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addEmployee(@RequestBody EmployeeEntity employeeEntity){
        try {
            UserEntity user = new UserEntity();
            user.setUsername(employeeEntity.getPhone());
            user.setPassword("123456");
            user.setType(3);
            employeeEntity.setUserEntity(user);
            employeeDao.saveOrUpdate(employeeEntity);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/del"}, method = RequestMethod.POST)
    public Map<String, Object> deleteEmployee(@RequestParam(value = "id",required = false, defaultValue = "")Integer[] ids, String[] names){
        try{
            for(Integer item : ids){
                employeeDao.deleteById(item);
            }
            for(String item : names){
                userDao.delByUserName("delete from UserEntity u where u.username = ?", item);
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
            PageResults<EmployeeEntity> list;
            list = (PageResults<EmployeeEntity>) employeeDao.findPageByFetchedHql("from EmployeeEntity cus", null, pageIndex, pageSize);
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
    public Map<String, Object> updateEmployee(EmployeeEntity employeeEntity ){
        try{
            EmployeeEntity oldEmploee = (EmployeeEntity) employeeDao.load(employeeEntity.getId());
            UserEntity userEntity = oldEmploee.getUserEntity();
            userEntity.setUsername(employeeEntity.getPhone());
            employeeEntity.setUserEntity(userEntity);
            employeeDao.merge(employeeEntity);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

}
