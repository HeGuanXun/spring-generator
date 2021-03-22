package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.util.ArrayList;

@Data
@Table(value = "sjSystem", tableContext = "送件员接单管理系统")
public class sjSystem {

    @Column("快递单号")
    private String number;

    @Column("收件人姓名")
    private String name;

    @Column("收件人手机号码")
    private String phone;

    @Column("收件人地址")
    private String address;


    public sjSystem(String number, String name, String phone, String address) {
        this.number = number;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public sjSystem() {
    }

    public ArrayList<sjSystem> getDatas(){
        ArrayList<sjSystem> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
        }
        return list;
    }
}