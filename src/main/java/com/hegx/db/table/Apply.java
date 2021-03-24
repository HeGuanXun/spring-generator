package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.util.ArrayList;

@Data
@Table(value = "apply", tableContext = "送件员申请管理系统")
public class Apply extends Mock{

    @Column("申请时间")
    private String applyTime;
    @Column("姓名")
    private String name;
    @Column("性别")
    private String sex;
    @Column("年龄")
    private String age;
    @Column("身份证")
    private String IDCard;
    @Column("电话")
    private String phone;
    @Column(value = "状态",types = {"待审核","同意","驳回"})
    private String status;


    public Apply(String applyTime, String name, String sex, String age, String IDCard, String phone, String status) {
        this.applyTime = applyTime;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.IDCard = IDCard;
        this.phone = phone;
        this.status = status;
    }

    public Apply() {
    }

}