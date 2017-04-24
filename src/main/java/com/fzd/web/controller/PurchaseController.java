package com.fzd.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fzd.dao.GoodsDao;
import com.fzd.dao.PageResults;
import com.fzd.dao.ProducerDao;
import com.fzd.dao.PurchaseDao;
import com.fzd.model.GoodsEntity;
import com.fzd.model.ProducerEntity;
import com.fzd.model.PurchasingEntity;
import com.fzd.modelPo.PurchasePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FZD on 2017/3/20.
 */
@Controller
@RequestMapping(value = {"/purchase"})
public class PurchaseController extends BaseController{

    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ProducerDao producerDao;
    @ResponseBody
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public Map<String, Object> addPurchase(Model model, String data,String option){
        try {
            ObjectMapper mapper = new ObjectMapper();
            PurchasePO purchasePO = mapper.readValue(data, PurchasePO.class);
            PurchasingEntity purchase = purchasePO.getPurchase();
            GoodsEntity goodsEntity = (GoodsEntity) goodsDao.load(purchasePO.getGoodsId());
            BigDecimal oldTotalNumber = BigDecimal.valueOf(0);
            if(!"INSERT".equals(option)){
               oldTotalNumber = ((PurchasingEntity) purchaseDao.get(purchase.getId())).getTotalNumber();
            }
            BigDecimal newTotalNumber = purchase.getTotalNumber();

            // 入库偏差
            BigDecimal storeDeviation = BigDecimal.valueOf(0);

            if("UPDATE".equals(option)){
                storeDeviation = newTotalNumber.subtract(oldTotalNumber);
            }else if("INSERT".equals(option)){
                storeDeviation = newTotalNumber;
            }


            BigDecimal storage = goodsEntity.getStorage();
            BigDecimal totalStorage = goodsEntity.getTotalStorage();
            if(storage == null){
                storage = BigDecimal.valueOf(0);
            }
            if(totalStorage == null){
                totalStorage = BigDecimal.valueOf(0);
            }
            goodsEntity.setTotalStorage(totalStorage.add(storeDeviation));
            goodsEntity.setStorage(storage.add(storeDeviation));

            ProducerEntity producerEntity = (ProducerEntity) producerDao.load(purchasePO.getProducerId());
            purchase.setGoodsByGoodsId(goodsEntity);
            purchase.setProducerByProducerId(producerEntity);
            if("INSERT".equals(option)){
                purchaseDao.saveOrUpdate(purchase);
            }else if("UPDATE".equals(option)){
                purchaseDao.merge(purchase);
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
    public Map<String, Object> deletePurchase(@RequestParam(value = "id",required = false, defaultValue = "")Integer[] ids){
        try{
            for(Integer item : ids){
                PurchasingEntity purchasingEntity= (PurchasingEntity) purchaseDao.getByHQL("from PurchasingEntity p where p.id = ?", item);
                GoodsEntity goodsEntity = (GoodsEntity) goodsDao.get(purchasingEntity.getGoodsByGoodsId().getId());
                //库存偏差
                BigDecimal storeDeviation = BigDecimal.valueOf(0);

                storeDeviation = storeDeviation.subtract(purchasingEntity.getTotalNumber());

                BigDecimal storage = goodsEntity.getStorage();
                BigDecimal totalStorage = goodsEntity.getTotalStorage();
                if(storage == null){
                    storage = BigDecimal.valueOf(0);
                }
                if(totalStorage == null){
                    totalStorage = BigDecimal.valueOf(0);
                }
                goodsEntity.setTotalStorage(totalStorage.add(storeDeviation));
                goodsEntity.setStorage(storage.add(storeDeviation));
                goodsDao.saveOrUpdate(goodsEntity);
                purchaseDao.deleteById(item);
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
    public Map<String, Object> getPurchase(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex){
        try{
            PageResults<PurchasingEntity> list;
            list = (PageResults<PurchasingEntity>) purchaseDao.findPageByFetchedHql("from PurchasingEntity p", null, pageIndex, pageSize);
            map = new HashMap<>();
            map.put("list",list);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/getByParams"}, method = RequestMethod.POST)
    public Map<String, Object> getPurchaseByParams(String goodsName, String producerName,@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex){
        try{
            PageResults<PurchasingEntity> list;
            list = goodsDao.findPageByFetchedHql("select pur from PurchasingEntity pur where pur.goodsByGoodsId.name like ? " +
                    "and pur.producerByProducerId.companyName like ? order by pur.putInTime desc",null,pageIndex,pageSize,new String[]{"%"+goodsName+"%","%"+producerName+"%"});
            List<PurchasingEntity> purchasingEntities = list.getResults();
            List<PurchasePO> purchasePOs = new ArrayList<>();
            for(PurchasingEntity item : purchasingEntities){
                PurchasePO purchasePO = new PurchasePO();
                purchasePO.setPurchase(item);
                purchasePO.setProducerEntity(item.getProducerByProducerId());
                purchasePO.setGoodsEntity(item.getGoodsByGoodsId());
                purchasePOs.add(purchasePO);
            }
            map = new HashMap<>();
            map.put("list",purchasePOs);
            map.put("pageCount", list.getPageCount());
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public Map<String, Object> updatePurchase(PurchasingEntity purchasingEntity ){
        try{
            purchaseDao.saveOrUpdate(purchasingEntity);
            map = new HashMap<>();
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }
}
