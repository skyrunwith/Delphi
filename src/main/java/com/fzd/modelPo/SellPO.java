package com.fzd.modelPo;

import com.fzd.model.CustomerEntity;
import com.fzd.model.GoodsEntity;
import com.fzd.model.SellEntity;

import java.io.Serializable;

/**
 * Created by FZD on 2017/3/26.
 */
public class SellPO implements Serializable {
    private Integer goodsId;
    private Integer customerId;
    private SellEntity sell;
    private GoodsEntity goodsEntity;
    private CustomerEntity customerEntity;

    public SellPO(){

    }

    public SellEntity getSell() {
        return sell;
    }

    public void setSell(SellEntity sell) {
        this.sell = sell;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public GoodsEntity getGoodsEntity() {
        return goodsEntity;
    }

    public void setGoodsEntity(GoodsEntity goodsEntity) {
        this.goodsEntity = goodsEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }
}
