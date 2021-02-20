package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@Table(value = "useCar", tableContext = "人事申请-用车")
public class UseCar {
    @Column("用车地点")
    private String local;
    @Column("开始时间")
    private Date startTime;
    @Column("结束时间")
    private Date endTime;
    @Column("用车天数")
    private String leaveCount;
    @Column("用车事由")
    private String reason;

    public UseCar(String local, String leaveCount, String reason) {
        this.local = local;
        this.leaveCount = leaveCount;
        this.reason = reason;
    }

    public UseCar() {
    }

    public ArrayList<UseCar> getDatas(){
        ArrayList<UseCar> list = new ArrayList<>();
        list.add(new UseCar("北京","1","去北京XXX"));
        list.add(new UseCar("上海","5","XXX上海"));
        list.add(new UseCar("深圳","2","深圳XXXX"));
        return list;
    }
}