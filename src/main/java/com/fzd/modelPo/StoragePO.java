package com.fzd.modelPo;

import com.fzd.model.GoodsEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by FZD on 2017/3/31.
 */
public class StoragePO implements Serializable{
    private GoodsEntity goodsEntity;
    private BigDecimal totalStorageTime;
    private BigDecimal totalSale;
    private BigDecimal totalSaleTime;
    private BigDecimal totalPayout; //应付款
    private BigDecimal totalNotPayout; //未付款
    private BigDecimal totalReallyReceive; //实际收入
    private BigDecimal totalReceive; //应收款
    private BigDecimal totalNotReceive; //未收款

    public StoragePO() {
    }

    public GoodsEntity getGoodsEntity() {
        return goodsEntity;
    }

    public void setGoodsEntity(GoodsEntity goodsEntity) {
        this.goodsEntity = goodsEntity;
    }

    public BigDecimal getTotalStorageTime() {
        return totalStorageTime;
    }

    public void setTotalStorageTime(BigDecimal totalStorageTime) {
        this.totalStorageTime = totalStorageTime;
    }

    public BigDecimal getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(BigDecimal totalSale) {
        this.totalSale = totalSale;
    }

    public BigDecimal getTotalSaleTime() {
        return totalSaleTime;
    }

    public void setTotalSaleTime(BigDecimal totalSaleTime) {
        this.totalSaleTime = totalSaleTime;
    }

    public BigDecimal getTotalNotPayout() {
        return totalNotPayout;
    }

    public void setTotalNotPayout(BigDecimal totalNotPayout) {
        this.totalNotPayout = totalNotPayout;
    }

    public BigDecimal getTotalReceive() {
        return totalReceive;
    }

    public void setTotalReceive(BigDecimal totalReceive) {
        this.totalReceive = totalReceive;
    }

    public BigDecimal getTotalNotReceive() {
        return totalNotReceive;
    }

    public void setTotalNotReceive(BigDecimal totalNotReceive) {
        this.totalNotReceive = totalNotReceive;
    }

    public BigDecimal getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(BigDecimal totalPayout) {
        this.totalPayout = totalPayout;
    }

    public BigDecimal getTotalReallyReceive() {
        return totalReallyReceive;
    }

    public void setTotalReallyReceive(BigDecimal totalReallyReceive) {
        this.totalReallyReceive = totalReallyReceive;
    }
}
