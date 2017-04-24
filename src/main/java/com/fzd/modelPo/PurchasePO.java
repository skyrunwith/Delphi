package com.fzd.modelPo;

import com.fzd.model.GoodsEntity;
import com.fzd.model.ProducerEntity;
import com.fzd.model.PurchasingEntity;

import java.io.Serializable;

/**
 * Created by FZD on 2017/3/22.
 */
public class PurchasePO implements Serializable{
    private PurchasingEntity purchase;
    private Integer goodsId;
    private Integer producerId;
    private GoodsEntity goodsEntity;
    private ProducerEntity producerEntity;

    public PurchasePO() {
    }

    public PurchasingEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchasingEntity purchase) {
        this.purchase = purchase;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public ProducerEntity getProducerEntity() {
        return producerEntity;
    }

    public void setProducerEntity(ProducerEntity producerEntity) {
        this.producerEntity = producerEntity;
    }

    public GoodsEntity getGoodsEntity() {
        return goodsEntity;
    }

    public void setGoodsEntity(GoodsEntity goodsEntity) {
        this.goodsEntity = goodsEntity;
    }
}
