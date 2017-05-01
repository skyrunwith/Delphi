package com.fzd.web.controller;

import com.fzd.dao.UserDao;
import com.fzd.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FZD on 2017/4/9.
 */
@RequestMapping(value = "/login")
@Controller
public class LoginController extends BaseController{

    @Autowired
    private UserDao userDao;
   @RequestMapping(value = "/", method = RequestMethod.POST)
   @ResponseBody
   public Map<String, Object> login(String username, String password,HttpSession session){
       try{
           map = new HashMap<>();
           UserEntity userEntity = userDao.findByUserName("from UserEntity u where u.username = ?", username);
           if(userEntity != null){
               if(password.equals(userEntity.getPassword())){
                   map.put("data", userEntity);
                   map.put("success", true);
                   map.put("msg", "");
                   session.setAttribute("user", userEntity);
               }else{
                   map.put("success", false);
                   map.put("msg", "密码不正确");
               }
           }else {
               map.put("success", false);
               map.put("msg", "账号不存在");
           }
       }catch (Exception e){
           map.put("success", false);
       }
       return map;
   }
}
