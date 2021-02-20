package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@Table(value = "Leaves", tableContext = "人事申请-请假")
public class Leaves {
    @Column("请假类型")
    private String type;
    @Column("开始时间")
    private Date startTime;
    @Column("结束时间")
    private Date endTime;
    @Column("请假天数")
    private String leaveCount;
    @Column("请假事由")
    private String reason;

    public Leaves(String type, String leaveCount, String reason) {
        this.type = type;
        this.leaveCount = leaveCount;
        this.reason = reason;
    }

    public Leaves() {
    }

    public ArrayList<Leaves> getDatas(){
        ArrayList<Leaves> list = new ArrayList<>();
        list.add(new Leaves("病假","1","生病"));
        list.add(new Leaves("年假","5","年假"));
        list.add(new Leaves("调休","2","调休"));
        return list;
    }
}