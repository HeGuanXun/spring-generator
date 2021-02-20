package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@Table(value = "bulletin", tableContext = "简报")
public class Bulletin {

    @Column("简报编号")
    private String num;

    @Column("类型")
    private String type;

    @Column("简报周期")
    private String bulletinPeriod;

    @Column("创建人")
    private String createMan;

    @Column("提交时间")
    private Date submitTime;
    @Column("状态")
    private String status;

    public Bulletin(String num, String type, String bulletinPeriod, String createMan, String status) {
        this.num = num;
        this.type = type;
        this.bulletinPeriod = bulletinPeriod;
        this.createMan = createMan;
        this.status = status;
    }

    public Bulletin() {
    }

    public ArrayList<Bulletin> getDatas(){
        ArrayList<Bulletin> list = new ArrayList<>();
        list.add(new Bulletin("3242130","日报","2018年11月09日","花木兰","待审核"));
        list.add(new Bulletin("3246561","周报","2018年第48周","李白","通过"));
        list.add(new Bulletin("3321202","月报","2018年11月09日","典韦","驳回"));
        list.add(new Bulletin("3321202","其他-xxx简报","2018-11-09  09:00","亚瑟","未提交"));
        list.add(new Bulletin("3321202","其他-xxx简报","2018-11-09  09:00","亚瑟","未提交"));
        list.add(new Bulletin("3321202","其他-xxx简报","2018-11-09  09:00","亚瑟","未提交"));
        return list;
    }
}