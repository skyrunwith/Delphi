package com.fzd.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by FZD on 2017/5/9.
 */
public class FinancialVO implements Comparable{
    private String name; //商品名称
    private String transcationName; //交易对象
    private String employee; //经办人
    private String type; //交易类型
    private BigDecimal transcationMoney; //交易金额
    private BigDecimal paid; //已转账金额
    private BigDecimal paidUn; //欠款金额
    private Timestamp time; //交易时间

    public FinancialVO() {
    }

    public FinancialVO(String name, String transcationName, String employee, String type, BigDecimal transcationMoney, BigDecimal paid, BigDecimal paidUn, Timestamp time) {
        this.name = name;
        this.transcationName = transcationName;
        this.employee = employee;
        this.type = type;
        this.transcationMoney = transcationMoney;
        this.paid = paid;
        this.paidUn = paidUn;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranscationName() {
        return transcationName;
    }

    public void setTranscationName(String transcationName) {
        this.transcationName = transcationName;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTranscationMoney() {
        return transcationMoney;
    }

    public void setTranscationMoney(BigDecimal transcationMoney) {
        this.transcationMoney = transcationMoney;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public BigDecimal getPaidUn() {
        return paidUn;
    }

    public void setPaidUn(BigDecimal paidUn) {
        this.paidUn = paidUn;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof FinancialVO){
            FinancialVO s=(FinancialVO)o;
            if(this.time.getTime()>s.time.getTime()){
                return 1;
            }
            else if (this.time.getTime() == s.time.getTime()){
                return 0;
            }
        }
        return -1;
    }
}
