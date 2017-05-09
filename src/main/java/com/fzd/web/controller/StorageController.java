package com.fzd.web.controller;

import com.fzd.dao.GoodsDao;
import com.fzd.dao.PageResults;
import com.fzd.dao.PurchaseDao;
import com.fzd.dao.SellDao;
import com.fzd.model.GoodsEntity;
import com.fzd.modelPo.StoragePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FZD on 2017/3/30.
*/
@Controller
@RequestMapping(value = {"/storage"})
public class StorageController extends BaseController{
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SellDao sellDao;
    @Autowired
    private PurchaseDao purchaseDao;
    @RequestMapping(value = {"/getAll"})
    @ResponseBody
    public Map<String,Object> getAll(String beginTime, String endTime ,int pageIndex ,String productName){
        try {
            PageResults<GoodsEntity> pageResults = goodsDao.findPageByFetchedHql("from GoodsEntity g where g.name like ?", null, pageIndex, pageSize, "%"+productName +"%");
            List<GoodsEntity> list = pageResults.getResults();
            PageResults<StoragePO> storagePOPageResults = new PageResults<>();
            List<StoragePO> storageList = new ArrayList<>();
            for(GoodsEntity item : list){
                StoragePO storagePO = new StoragePO();
                storagePO.setGoodsEntity(item);
                storagePO.setTotalSale((BigDecimal) sellDao.getByHQL("select sum(s.totalNumber) from SellEntity s where s.goodsByGoodsId.id = ?", item.getId()));
                storagePO.setTotalSaleTime(sellDao.findBySellTime("select sum(s.totalNumber) from SellEntity s where s.goodsByGoodsId.id = ? and s.sellTime >= ? and s.sellTime <= ?", item.getId(), new Timestamp(Long.valueOf(beginTime)), new Timestamp(Long.valueOf(endTime))));
                storagePO.setTotalStorageTime(sellDao.findBySellTime("select sum(p.totalNumber) from PurchasingEntity p where p.goodsByGoodsId.id = ? and p.putInTime >= ? and p.putInTime <= ?", item.getId(), new Timestamp(Long.valueOf(beginTime)), new Timestamp(Long.valueOf(endTime))));
                //应付款
                storagePO.setTotalPayout(purchaseDao.sumPayoutByGoodsId("select sum(p.totalPrice) from PurchasingEntity p where p.goodsByGoodsId.id = ?", item.getId()));
                //未付款
                storagePO.setTotalNotPayout(purchaseDao.sumPayoutByGoodsId("select sum(p.totalPrice - p.payout) from PurchasingEntity p where p.goodsByGoodsId.id = ?", item.getId()));
                //应收款
                storagePO.setTotalReceive(sellDao.sumPayoutByGoodsId("select sum(s.totalPrice) from SellEntity s where s.goodsByGoodsId.id = ?", item.getId()));
                //未收款
                storagePO.setTotalNotReceive(sellDao.sumPayoutByGoodsId("select sum(s.totalPrice - s.paid) from SellEntity s where s.goodsByGoodsId.id = ?", item.getId()));
//                storagePO.setTotalReallyReceive(storagePO.getTotalReceive().subtract(storagePO.getTotalNotReceive()));
                storageList.add(storagePO);
            }
            storagePOPageResults.setResults(storageList);
            storagePOPageResults.setCurrentPage(pageIndex);
            storagePOPageResults.setTotalCount(pageResults.getTotalCount());
            storagePOPageResults.setPageSize(pageResults.getPageSize());
            storagePOPageResults.resetPageNo();
            map = new HashMap<>();
            map.put("list", storagePOPageResults);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String,Object> updateStorage(Integer id, Integer lose){
        try{
            GoodsEntity goodsEntity = (GoodsEntity) goodsDao.load(id);
            goodsEntity.setLose(BigDecimal.valueOf(lose));
            goodsEntity.setStorage(goodsEntity.getStorage().subtract(BigDecimal.valueOf(lose)).add(goodsEntity.getLose()));
            goodsDao.save(goodsEntity);
            map = new HashMap<>();
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
            List<Object[]> list = goodsDao.findListByhql("select g.id ,g.name,g.storage from goods g GROUP BY g.category_id,g.id", new Timestamp(Long.valueOf(beginTime)), new Timestamp(Long.valueOf(endTime)), goodsEntity.getId());
            List<Map<String,Object>> list1 = new ArrayList<>();
            for(Object[] item : list){
                Map<String,Object> mapChart = new HashMap<>();
                mapChart.put("year", item[0]);
                mapChart.put("month", item[1]);
                mapChart.put("sell", item[2]);
//                mapChart.put("goodName", item[]);
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

}
