package com.fzd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by FZD on 2017/3/14.
 */
@Entity
@Table(name = "category", schema = "", catalog = "delphi")
public class CategoryEntity implements Serializable{
    private int id;
    private String name;
    private String comment;
    private Collection<GoodsEntity> goodsesById;

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

        CategoryEntity that = (CategoryEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }


    @OneToMany(mappedBy = "categoryByCategoryId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Collection<GoodsEntity> getGoodsesById() {
        return goodsesById;
    }

    public void setGoodsesById(Collection<GoodsEntity> goodsesById) {
        this.goodsesById = goodsesById;
    }
}
