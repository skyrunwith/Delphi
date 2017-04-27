package com.fzd.model;

import javax.persistence.*;

/**
 * Created by FZD on 2017/4/9.
 */
@Entity
@Table(name = "user", schema = "", catalog = "delphi")
public class UserEntity {
    private String username;
    private String password;
    private int id;
    private int type;

//    private ProducerEntity producerEntity;
    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @OneToOne(mappedBy = "userEntity")
//    public ProducerEntity getProducerEntity() {
//        return producerEntity;
//    }
//
//    public void setProducerEntity(ProducerEntity producerEntity) {
//        this.producerEntity = producerEntity;
//    }
}
