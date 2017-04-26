package com.fzd.web.controller;

import com.fzd.dao.CategoryDao;
import com.fzd.dao.GoodsDao;
import com.fzd.dao.PageResults;
import com.fzd.model.CategoryEntity;
import com.fzd.model.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FZD on 2017/3/17.
 */
@Controller
@RequestMapping(value = {"/goods"})
public class GoodsController extends BaseController{

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private CategoryDao categoryDao;

    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addGoods(GoodsEntity goodsEntity, Integer categoryId){
        try{
            CategoryEntity categoryEntity = (CategoryEntity) categoryDao.get(categoryId);
            goodsEntity.setCategoryByCategoryId(categoryEntity);
            goodsDao.saveOrUpdate(goodsEntity);
            map = new HashMap<>();
            map.put("success",true);
        }catch (Exception e){
            map.put("success",false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/del"}, method = RequestMethod.POST)
    public Map<String, Object> delGoods(@RequestParam(value = "id",required = false, defaultValue = "") Integer[] ids){
        try{
            for(Integer item: ids)
            {
                goodsDao.deleteById(item);
            }
            map = new HashMap<>();
            map.put("success",true);
        }catch (Exception e){
            map.put("success",false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public Map<String, Object> updateGoods(GoodsEntity goodsEntity){
        try{
            GoodsEntity temp = (GoodsEntity) goodsDao.get(goodsEntity.getId());
            goodsEntity.setCategoryByCategoryId(temp.getCategoryByCategoryId());
            goodsDao.merge(goodsEntity);
            map = new HashMap<>();
            map.put("success",true);
        }catch (Exception e){
            map.put("success",false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/getAll"}, method = RequestMethod.POST)
    public Map<String, Object> getGoods(@RequestParam(value = "pageIndex") Integer pageIndex, Integer categoryId){
        try{
            PageResults<GoodsEntity> list;
            list = goodsDao.findPageByFetchedHql("from GoodsEntity g where g.categoryByCategoryId.id = ?", null, pageIndex, pageSize, categoryId);
            map = new HashMap<>();
            map.put("list", list);
            map.put("success",true);
        }catch (Exception e){
            map.put("success",false);
        }
        return map;
    }
}
