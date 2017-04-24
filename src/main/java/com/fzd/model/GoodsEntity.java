package com.fzd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by FZD on 2017/3/14.
 */
@Entity
@Table(name = "goods", schema = "", catalog = "delphi")
public class GoodsEntity {
    private int id;
    private String name;
    private String birthplace;
    private String specification;
    private String packaging;
    private BigDecimal sales;
    private BigDecimal storage;
    private BigDecimal totalStorage;
    private String comment;
    private BigDecimal lose;
    private BigDecimal purchasePrice;
    private BigDecimal sellPrice;

    private CategoryEntity categoryByCategoryId;
    private Collection<PurchasingEntity> purchasingsById;
    private Collection<SellEntity> sellsById;

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "birthplace")
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Basic
    @Column(name = "specification")
    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @Basic
    @Column(name = "packaging")
    public String getPackaging() {
        return packaging;
    }

    @Basic
    @Column(name = "lose")
    public BigDecimal getLose() {
        return lose;
    }

    public void setLose(BigDecimal lose) {
        this.lose = lose;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    @Basic
    @Column(name = "sales")
    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    @Basic
    @Column(name = "storage")
    public BigDecimal getStorage() {
        return storage;
    }

    public void setStorage(BigDecimal storage) {
        this.storage = storage;
    }

    @Basic
    @Column(name = "total_storage")
    public BigDecimal getTotalStorage() {
        return totalStorage;
    }

    public void setTotalStorage(BigDecimal totalStorage) {
        this.totalStorage = totalStorage;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Basic
    @Column(name = "purchase_price")
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    @Basic
    @Column(name = "sell_price")
    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsEntity that = (GoodsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (specification != null ? !specification.equals(that.specification) : that.specification != null)
            return false;
        if (packaging != null ? !packaging.equals(that.packaging) : that.packaging != null) return false;
        if (sales != null ? !sales.equals(that.sales) : that.sales != null) return false;
        if (storage != null ? !storage.equals(that.storage) : that.storage != null) return false;
        if (totalStorage != null ? !totalStorage.equals(that.totalStorage) : that.totalStorage != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (specification != null ? specification.hashCode() : 0);
        result = 31 * result + (packaging != null ? packaging.hashCode() : 0);
        result = 31 * result + (sales != null ? sales.hashCode() : 0);
        result = 31 * result + (storage != null ? storage.hashCode() : 0);
        result = 31 * result + (totalStorage != null ? totalStorage.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    
    @OneToMany(mappedBy = "goodsByGoodsId")
    public Collection<PurchasingEntity> getPurchasingsById() {
        return purchasingsById;
    }

    public void setPurchasingsById(Collection<PurchasingEntity> purchasingsById) {
        this.purchasingsById = purchasingsById;
    }

    @OneToMany(mappedBy = "goodsByGoodsId")
    public Collection<SellEntity> getSellsById() {
        return sellsById;
    }

    public void setSellsById(Collection<SellEntity> sellsById) {
        this.sellsById = sellsById;
    }
}
