package com.fzd.web.controller;

import com.fzd.dao.PageResults;
import com.fzd.dao.ProducerDao;
import com.fzd.dao.UserDao;
import com.fzd.model.CategoryEntity;
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
@RequestMapping(value = {"/producer"})
public class ProducerController extends BaseController{
    @Autowired
    private ProducerDao producerDao;

    @Autowired
    private UserDao userDao;
    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addProducer(ProducerEntity producerEntity){
        try {
            producerDao.saveOrUpdate(producerEntity);
            UserEntity user = new UserEntity();
            user.setUsername(producerEntity.getEmail());
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
    public Map<String, Object> deleteProducer(@RequestParam(value = "id",required = false, defaultValue = "")Integer[] ids){
        try{
            for(Integer item : ids){
                producerDao.deleteById(item);
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
    public Map<String, Object> getProducers(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex){
        try{
            PageResults<ProducerEntity> list;
            list = (PageResults<ProducerEntity>) producerDao.findPageByFetchedHql("from ProducerEntity p", null, pageIndex, pageSize);
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
    public Map<String, Object> updateProducer(ProducerEntity producerEntity ){
        try{
            producerDao.saveOrUpdate(producerEntity);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }
}
