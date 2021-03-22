package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.util.ArrayList;

@Data
@Table(value = "upSystem", tableContext = "快递上系统")
public class UpSystem {

    @Column("快递单号")
    private String number;

    @Column("收件人姓名")
    private String name;

    @Column("收件人手机号码")
    private String phone;

    @Column("收件人地址")
    private String address;


    public UpSystem(String number, String name, String phone, String address) {
        this.number = number;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public UpSystem() {
    }

    public ArrayList<UpSystem> getDatas(){
        ArrayList<UpSystem> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
        }
        return list;
    }
}