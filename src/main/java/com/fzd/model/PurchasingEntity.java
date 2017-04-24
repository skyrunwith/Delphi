package com.fzd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by FZD on 2017/3/14.
 */
@Entity
@Table(name = "purchasing", schema = "", catalog = "delphi")
public class PurchasingEntity {
    private int id;
    private BigDecimal totalPrice;
    private BigDecimal unitPrice;
    private BigDecimal totalNumber;
    private BigDecimal payout;
    private Timestamp putInTime;
    private String comment;
    private GoodsEntity goodsByGoodsId;
    private ProducerEntity producerByProducerId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "unit_price")
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "totalnumber")
    public BigDecimal getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(BigDecimal totalnumber) {
        this.totalNumber = totalnumber;
    }

    @Basic
    @Column(name = "payout")
    public BigDecimal getPayout() {
        return payout;
    }

    public void setPayout(BigDecimal payout) {
        this.payout = payout;
    }

    @Basic
    @Column(name = "put_in_time")
    public Timestamp getPutInTime() {
        return putInTime;
    }

    public void setPutInTime(Timestamp putInTime) {
        this.putInTime = putInTime;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchasingEntity that = (PurchasingEntity) o;

        if (id != that.id) return false;
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;
        if (totalNumber != null ? !totalNumber.equals(that.totalNumber) : that.totalNumber != null) return false;
        if (payout != null ? !payout.equals(that.payout) : that.payout != null) return false;
        if (putInTime != null ? !putInTime.equals(that.putInTime) : that.putInTime != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        result = 31 * result + (totalNumber != null ? totalNumber.hashCode() : 0);
        result = 31 * result + (payout != null ? payout.hashCode() : 0);
        result = 31 * result + (putInTime != null ? putInTime.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id", nullable = false)
    public GoodsEntity getGoodsByGoodsId() {
        return goodsByGoodsId;
    }

    public void setGoodsByGoodsId(GoodsEntity goodsByGoodsId) {
        this.goodsByGoodsId = goodsByGoodsId;
    }
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    public ProducerEntity getProducerByProducerId() {
        return producerByProducerId;
    }

    public void setProducerByProducerId(ProducerEntity producerByProducerId) {
        this.producerByProducerId = producerByProducerId;
    }
}
