package com.fzd.modelPo;

import com.fzd.model.GoodsEntity;
import com.fzd.model.ProducerEntity;
import com.fzd.model.PurchasingEntity;

/**
 * Created by FZD on 2017/3/24.
 */
public class PurchaseAddPO {
    private PurchasingEntity purchase;
    private Integer goodsId;
    private Integer producerId;

    public PurchaseAddPO() {
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
}
