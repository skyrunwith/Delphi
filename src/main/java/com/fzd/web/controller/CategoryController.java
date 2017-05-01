package com.fzd.web.controller;

import com.fzd.dao.CategoryDao;
import com.fzd.dao.PageResults;
import com.fzd.dao.ProducerDao;
import com.fzd.model.CategoryEntity;
import com.fzd.model.ProducerEntity;
import com.fzd.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FZD on 2017/3/16.
 */
@Controller
@RequestMapping(value = {"/category"})
public class CategoryController extends BaseController{

    @Autowired
    public CategoryDao categoryDao;
    @Autowired
    public ProducerDao producerDao;

    /**
     * 根据厂商添加各自的商品分类
     * @param categoryEntity
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addCategory(CategoryEntity categoryEntity,HttpSession session){
        try {
            UserEntity userEntity = (UserEntity) session.getAttribute("user");
            ProducerEntity producerEntity = (ProducerEntity) producerDao.getByHQL("from ProducerEntity p where p.userEntity.id = ?", userEntity.getId());
            categoryEntity.setProducer(producerEntity);
            categoryDao.saveOrUpdate(categoryEntity);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/del"}, method = RequestMethod.POST)
    public Map<String, Object> deleteCategory(@RequestParam(value = "id",required = false, defaultValue = "")Integer[] ids){
        try{
            for(Integer item : ids){
                categoryDao.deleteById(item);
            }
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    /**
     * 通过厂商查找各自拥有的分类
     * @param pageIndex
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/getAll"}, method = RequestMethod.POST)
    public Map<String, Object> getCategories(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,HttpSession session){
        try{
            PageResults<CategoryEntity> list;
            int id =  ((UserEntity) session.getAttribute("user")).getId();
            int type =((UserEntity) session.getAttribute("user")).getType();
            if(type == 0) {
                list = (PageResults<CategoryEntity>) categoryDao.findPageByFetchedHql("from CategoryEntity c", null, pageIndex, pageSize);
            }else{
                ProducerEntity producerEntity = (ProducerEntity) producerDao.getByHQL("from ProducerEntity p where p.userEntity.id = ?", id);
                list = (PageResults<CategoryEntity>) categoryDao.findPageByFetchedHql("from CategoryEntity c where c.producer.id = ?", null, pageIndex, pageSize, producerEntity.getId());
            }
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
    public Map<String, Object> updateCategory(CategoryEntity categoryEntity ){
        try{
            categoryDao.saveOrUpdate(categoryEntity);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

}
