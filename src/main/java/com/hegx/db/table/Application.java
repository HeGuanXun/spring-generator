package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.enums.ItemEnum;
import com.hegx.annotation.Table;
import com.hegx.db.factroy.Factory;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@Table(value = "jb", tableContext = "加班申请")
public class Application extends Mock implements Factory {

    @Column(value = "加班类型",
            isSearch = true,
            itemType = ItemEnum.Select ,
            options = {"工作日加班","周末加班","节假日加班"}
            )
    private String type;
    @Column(value = "开始时间")
    private Date startTime;
    @Column(value = "结束时间")
    private Date endTime;
    @Column(value = "合计时间")
    private String totalDay;
    @Column(value = "加班内容")
    private String context;

    public Application(String type, Date startTime, Date endTime, String totalDay, String context) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalDay = totalDay;
        this.context = context;
    }

    @Override
    public ArrayList<Application> buildDatas() {
        ArrayList<Application> list = new ArrayList<>();
        list.add(new Application("工作日加班",new Date(),new Date(),"3","赶进度"));
        list.add(new Application("周末加班",new Date(),new Date(),"3","赶进度"));
        list.add(new Application("节假日加班",new Date(),new Date(),"3","赶进度"));
        list.add(new Application("工作日加班",new Date(),new Date(),"3","赶进度"));
        list.add(new Application("工作日加班",new Date(),new Date(),"3","赶进度"));
        return list;
    }



    public Application() {
    }

}