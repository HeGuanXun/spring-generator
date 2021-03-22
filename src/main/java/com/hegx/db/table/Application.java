package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Data
@Table(value = "application", tableContext = "报销")
public class Application{

    @Column("编号")
    private String num;
    @Column("名称")
    private String name;
    @Column("部门")
    private String department;
    @Column(value = "类型",types = {"客户支出","销售支出"})
    private String type;
    @Column("日期")
    private Date date;
    @Column("相关人员 || 申请人")
    private String relatedMan;
    @Column("金额")
    private BigDecimal money;
    @Column("描述 || 相关内容")
    private String described;
    @Column("备注")
    private String remark;
    @Column("状态")
    private String status;
    @Column("申请时间")
    private Date applyDate;

    public Application(String num, String name, String department, String type, String relatedMan, BigDecimal money, String described, String remark, String status) {
        this.num = num;
        this.name = name;
        this.department = department;
        this.type = type;
        this.relatedMan = relatedMan;
        this.money = money;
        this.described = described;
        this.remark = remark;
        this.status = status;
    }

    public Application() {
    }


}