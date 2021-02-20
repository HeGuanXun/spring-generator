package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@Table(value = "evection", tableContext = "人事申请-出差")
public class Evection {
    @Column("出差地点")
    private String local;
    @Column("开始时间")
    private Date startTime;
    @Column("结束时间")
    private Date endTime;
    @Column("出差天数")
    private String leaveCount;
    @Column("出差事由")
    private String reason;

    public Evection(String local, String leaveCount, String reason) {
        this.local = local;
        this.leaveCount = leaveCount;
        this.reason = reason;
    }

    public Evection() {
    }

    public ArrayList<Evection> getDatas(){
        ArrayList<Evection> list = new ArrayList<>();
        list.add(new Evection("北京","1","去北京XXX"));
        list.add(new Evection("上海","5","XXX上海"));
        list.add(new Evection("深圳","2","深圳XXXX"));
        return list;
    }
}