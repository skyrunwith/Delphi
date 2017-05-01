package com.fzd.web.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzd.dao.*;
import com.fzd.model.CustomerEntity;
import com.fzd.model.GoodsEntity;
import com.fzd.model.PurchasingEntity;
import com.fzd.model.SellEntity;
import com.fzd.modelPo.SellPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FZD on 2017/3/26.
 */
@Controller
@RequestMapping(value = {"/sell"})
public class SellController extends BaseController{

    @Autowired
    private SellDao sellDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private CustomerDao customerDao;
    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addPurchase(Model model, String data,String option){
        try {
            ObjectMapper mapper = new ObjectMapper();
            SellPO sellPO = mapper.readValue(data, SellPO.class);
            SellEntity sellEntity = sellPO.getSell();
            GoodsEntity goodsEntity = (GoodsEntity) goodsDao.load(sellPO.getGoodsId());
//            //??????????????????????????????????????
//            BigDecimal oldTotalNumber = BigDecimal.valueOf(0);
//            if(!"INSERT".equals(option)){
//                oldTotalNumber = ((SellEntity) sellDao.get(sellEntity.getId())).getTotalNumber();
//            }
//            BigDecimal newTotalNumber = sellEntity.getTotalNumber();
//
//            //??????
//            BigDecimal storeDeviation = BigDecimal.valueOf(0);
//            //???????
//            BigDecimal salesDeviation = BigDecimal.valueOf(0);
//            if("UPDATE".equals(option)){
//                salesDeviation = newTotalNumber.subtract(oldTotalNumber);
//                storeDeviation = storeDeviation.subtract(salesDeviation);
//            }else if("INSERT".equals(option)){
//                storeDeviation = storeDeviation.subtract(newTotalNumber);
//                salesDeviation = newTotalNumber;
//            }
//
//
//            BigDecimal storage = goodsEntity.getStorage();
//            BigDecimal sales = goodsEntity.getSales();
//            if(storage == null){
//                storage = BigDecimal.valueOf(0);
//            }
//            if(sales == null){
//                sales = BigDecimal.valueOf(0);
//            }
//            goodsEntity.setSales(sales.add(salesDeviation));
//            goodsEntity.setStorage(storage.add(storeDeviation));

            CustomerEntity customerEntity = (CustomerEntity) customerDao.load(sellPO.getCustomerId());
            sellEntity.setGoodsByGoodsId(goodsEntity);
            sellEntity.setCustomerByCustomerId(customerEntity);
            sellEntity.setState("0");
            if("INSERT".equals(option)){
                sellDao.save(sellEntity);
            }else if("UPDATE".equals(option)){
                sellDao.merge(sellEntity);
            }
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/del"}, method = RequestMethod.POST)
    public Map<String, Object> deleteSell(@RequestParam(value = "id",required = false, defaultValue = "")Integer[] ids){
        try{
            for(Integer item : ids){
                SellEntity sellEntity= (SellEntity) sellDao.getByHQL("from SellEntity p where p.id = ?", item);
                GoodsEntity goodsEntity = (GoodsEntity) goodsDao.get(sellEntity.getGoodsByGoodsId().getId());
                //??????
                BigDecimal storeDeviation = BigDecimal.valueOf(0);
                //???????
                BigDecimal salesDeviation = BigDecimal.valueOf(0);

                storeDeviation = sellEntity.getTotalNumber();
                salesDeviation = sellEntity.getTotalNumber();

                BigDecimal storage = goodsEntity.getStorage();
                BigDecimal sales = goodsEntity.getSales();
                if(storage == null){
                    storage = BigDecimal.valueOf(0);
                }
                if(sales == null){
                    sales = BigDecimal.valueOf(0);
                }

                goodsEntity.setSales(sales.subtract(salesDeviation));
                goodsEntity.setStorage(storage.add(storeDeviation));
                goodsDao.saveOrUpdate(goodsEntity);
                sellDao.deleteById(item);
            }
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/getByParams"}, method = RequestMethod.POST)
    public Map<String, Object> getSellByParams(String goodsName, String customerName, @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex , String state){
        try{
            PageResults<SellEntity> list;
            list =  goodsDao.findPageByFetchedHql("select pur from SellEntity pur where pur.goodsByGoodsId.name like ? " +
                    "and pur.customerByCustomerId.companyName like ? and pur.state = ? order by pur.sellTime desc",null,pageIndex,pageSize,new String[]{"%"+goodsName+"%","%"+ customerName +"%",state});
            List<SellEntity> sellEntities = list.getResults();

            List<SellPO> sellPOs = new ArrayList<>();
            for(SellEntity item : sellEntities){
                SellPO sellPO = new SellPO();
                sellPO.setSell(item);
                sellPO.setCustomerEntity(item.getCustomerByCustomerId());
                sellPO.setGoodsEntity(item.getGoodsByGoodsId());
                sellPOs.add(sellPO);
            }
            map = new HashMap<>();
            map.put("list",sellPOs);
            map.put("pageCount", list.getPageCount());
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    /**
     * 销售量统计图
     * @param beginTime
     * @param endTime
     * @param goodsName
     * @return
     */
    @RequestMapping(value = {"/getSellChartData"})
    @ResponseBody
    public Map<String,Object> getSellChartData(String beginTime, String endTime,String goodsName){
        try {
            //查找商品
            GoodsEntity goodsEntity = (GoodsEntity) goodsDao.getByHQL("from GoodsEntity s where s.name like ?", "%" + goodsName + "%");
            //查找商品在某时间段的销售情况
            List<Object[]> list = goodsDao.findListByhql("select year(s.sellTime),month(s.sellTime),sum(s.totalNumber) from SellEntity s where s.sellTime >= ? and s.sellTime <= ? and s.goodsByGoodsId.id = ? group by year(s.sellTime)" +
                    ",month(s.sellTime)", new Timestamp(Long.valueOf(beginTime)), new Timestamp(Long.valueOf(endTime)), goodsEntity.getId());
            List<Map<String,Object>> list1 = new ArrayList<>();
            for(Object[] item : list){
                Map<String,Object> mapChart = new HashMap<>();
                mapChart.put("year", item[0]);
                mapChart.put("month", item[1]);
                mapChart.put("sell", item[2]);
                list1.add(mapChart);
            }
            map = new HashMap<>();
            map.put("list", list1);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }



    /**
     * 库存量统计图
     * @param beginTime
     * @param endTime
     * @param goodsName
     * @return
     */
    @RequestMapping(value = {"/getStorageChartData"})
    @ResponseBody
    public Map<String,Object> getStorageChartData(String beginTime, String endTime,String goodsName){
        try {
            //查找商品
            GoodsEntity goodsEntity = (GoodsEntity) goodsDao.getByHQL("from GoodsEntity s where s.name like ?", "%" + goodsName + "%");
            //查找商品在某时间段的销售情况
            List<Object[]> list = goodsDao.findListByhql("select year(s.sellTime),month(s.sellTime),sum(s.totalNumber) from SellEntity s where s.sellTime >= ? and s.sellTime <= ? and s.goodsByGoodsId.id = ? group by year(s.sellTime)" +
                    ",month(s.sellTime)", new Timestamp(Long.valueOf(beginTime)), new Timestamp(Long.valueOf(endTime)), goodsEntity.getId());
            List<Map<String,Object>> list1 = new ArrayList<>();
            for(Object[] item : list){
                Map<String,Object> mapChart = new HashMap<>();
                mapChart.put("year", item[0]);
                mapChart.put("month", item[1]);
                mapChart.put("sell", item[2]);
                list1.add(mapChart);
            }
            map = new HashMap<>();
            map.put("list", list1);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/confirmComplete"}, method = RequestMethod.POST)
    public Map<String, Object> updateState(@RequestParam(value = "id", required = false, defaultValue = "") Integer[] ids) {
        try {
            for (Integer item : ids) {
                SellEntity sellEntity = (SellEntity) sellDao.load(item);
                //????????
                BigDecimal newTotalNumber = sellEntity.getTotalNumber();

                //??????
                BigDecimal storeDeviation = BigDecimal.valueOf(0);
                //???????
                BigDecimal salesDeviation = BigDecimal.valueOf(0);
                //?????? = ?????? - ????????
                storeDeviation = storeDeviation.subtract(newTotalNumber);
                salesDeviation = newTotalNumber;

                GoodsEntity goodsEntity = (GoodsEntity) goodsDao.load(sellEntity.getGoodsByGoodsId().getId());
                BigDecimal storage = goodsEntity.getStorage();
                BigDecimal sales = goodsEntity.getSales();
                if (storage == null) {
                    storage = BigDecimal.valueOf(0);
                }
                if (sales == null) {
                    sales = BigDecimal.valueOf(0);
                }
                goodsEntity.setSales(sales.add(salesDeviation));
                goodsEntity.setStorage(storage.add(storeDeviation));
                sellEntity.setState("1");
                sellDao.saveOrUpdate(sellEntity);
            }
            map = new HashMap<>();
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
        }
        return map;
    }
}
