package com.fzd.web.controller;

import com.fzd.dao.GoodsDao;
import com.fzd.dao.PageResults;
import com.fzd.dao.ProducerDao;
import com.fzd.dao.PurchaseDao;
import com.fzd.model.FinancialVO;
import com.fzd.model.PurchasingEntity;
import com.fzd.model.SellEntity;
import com.fzd.modelPo.PurchasePO;
import org.apache.commons.beanutils.BeanComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by FZD on 2017/5/9.
 */
@Controller
@RequestMapping(value = "/financial")
public class FinancialController extends BaseController{
    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ProducerDao producerDao;
    @ResponseBody
    @RequestMapping(value = {"/getByParams"}, method = RequestMethod.POST)
    public Map<String, Object> getPurchaseByParams(String beginTime, String endTime,@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex){
        try{
            List<PurchasingEntity> purchasingEntities;
            List<SellEntity> sellEntities;
            purchasingEntities = goodsDao.findListByhql("from PurchasingEntity p where p.putInTime >= ? and p.putInTime <= ? order by p.putInTime desc",new Timestamp(Long.valueOf(beginTime)), new Timestamp(Long.valueOf(endTime)));
            sellEntities = goodsDao.findListByhql("from SellEntity s where s.sellTime >= ? and s.sellTime <= ? order by s.sellTime desc",new Timestamp(Long.valueOf(beginTime)), new Timestamp(Long.valueOf(endTime)));
            List<FinancialVO> financialVOs = getFinancialVo(purchasingEntities,sellEntities);
            map = new HashMap<>();
            map.put("list",financialVOs);
            map.put("pageCount", financialVOs.size());
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
        }
        return map;
    }

    private List<FinancialVO> getFinancialVo(List<PurchasingEntity> purchasingEntities, List<SellEntity> sellEntities) {
        List<FinancialVO> financialVOs = new ArrayList<>();
        for(PurchasingEntity item: purchasingEntities){
            FinancialVO financialVO = new FinancialVO();
            financialVO.setName(item.getGoodsByGoodsId().getName());
            financialVO.setTranscationName(item.getProducerByProducerId().getCompanyName());
            financialVO.setEmployee("小红");
            financialVO.setType("支出");
            financialVO.setTranscationMoney(item.getTotalPrice());
            financialVO.setPaid(item.getPayout());
            financialVO.setPaidUn(item.getTotalPrice().subtract(item.getPayout()));
            financialVO.setTime(item.getPutInTime());
            financialVOs.add(financialVO);
        }
        for(SellEntity item: sellEntities){
            FinancialVO financialVO = new FinancialVO();
            financialVO.setName(item.getGoodsByGoodsId().getName());
            financialVO.setTranscationName(item.getCustomerByCustomerId().getCompanyName());
            financialVO.setEmployee("小明");
            financialVO.setType("收入");
            financialVO.setTranscationMoney(item.getTotalPrice());
            financialVO.setPaid(item.getPaid());
            financialVO.setPaidUn(item.getTotalPrice().subtract(item.getPaid()));
            financialVO.setTime(item.getSellTime());
            financialVOs.add(financialVO);
        }
        Collections.sort(financialVOs);
        return financialVOs;
    }


    /**
     * List 元素的多个属性进行排序。例如 ListSorter.sort(list, "time")，
     *
     * @param list  包含要排序元素的 List
     * @param properties 要排序的属性。前面的值优先级高。
     */
    public static <V> void sort(List<V> list, final String... properties) {
        Collections.sort(list, new Comparator<V>() {
            public int compare(V o1, V o2) {
                if (o1 == null && o2 == null) return 0;
                if (o1 == null) return -1;
                if (o2 == null) return 1;

                for (String property : properties) {
                    Comparator c = new BeanComparator(property);
                    int result = c.compare(o1, o2);
                    if (result != 0) {
                        return result;
                    }
                }
                return 0;
            }
        });
    }
}
