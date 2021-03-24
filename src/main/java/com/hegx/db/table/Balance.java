package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@Table(value = "balance", tableContext = "送件员余额管理系统")
public class Balance extends Mock{

    @Column("身份证")
    private String idCard;
    @Column("姓名")
    private String name;
    @Column("性别")
    private String sex;
    @Column("年龄")
    private String age;
    @Column("电话")
    private String phone;
    @Column("总金额")
    private BigDecimal allMoney;
    @Column("已提现金额")
    private BigDecimal hasCost;
    @Column("余额")
    private BigDecimal balance;

    public Balance(String idCard, String name, String sex, String age, String phone, BigDecimal allMoney, BigDecimal hasCost, BigDecimal balance) {
        this.idCard = idCard;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
        this.allMoney = allMoney;
        this.hasCost = hasCost;
        this.balance = balance;
    }

    public Balance() {
       /* new BigDecimal(100),
                new BigDecimal(300),
                new BigDecimal(700)*/
    }


}