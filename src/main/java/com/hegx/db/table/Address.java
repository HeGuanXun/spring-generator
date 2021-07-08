package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.factroy.Factory;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.util.ArrayList;

@Data
@Table(value = "address", tableContext = "用户地址信息管理系统")
public class Address extends Mock implements Factory {

    @Column(value = "社区名",isSearch = true)
    private String name;

    @Override
    public ArrayList<Address> buildDatas() {
      String[]  sqDataTemp = {"国瑞城", "明珠广场", "富骅大厦", "中央花园", "后海", "秦皇园", "秋英区", "大河",
                "朗月小区", "大英", "府城", "知名", "清平小区", "恒大美丽沙", "西海岸", "海甸岛", "大勇广场", "姜思达", "东风小区"};
        ArrayList<Address> list = new ArrayList<>();
        for (int i = 0; i < sqDataTemp.length; i++) {
            list.add(new Address(sqDataTemp[i]));
        }
        return list;
    }

    public Address(String name) {
        this.name = name;
    }

    public Address() {
    }

}