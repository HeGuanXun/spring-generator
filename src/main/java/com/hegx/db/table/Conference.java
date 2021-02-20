package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import lombok.Data;

@Data
@Table(value = "conference", tableContext = "会议室管理系统")
public class Conference {
    @Column("会议编号")
    private String num;
    @Column("组织")
    private String organization;
    @Column("主题")
    private String theme;
    @Column("会议名称")
    private String name;
    @Column("会议路径")
    private String location;
    @Column("会议状态")
    private String status;
    @Column("参考容量")
    private String ck_container;
    @Column("会议室信息")
    private String info;

    public String[] getDatas(){
        String a = "000001,母公司,清云, 四楼大会议室,四楼前台后面,已满,30,.09:00-11:30 XXX会议室 .13:00-14:30 XXX会议室.14:30.00-15:30 XXX会议室 .15:30-17:30 XXX会议室";
        String b = "000001,母公司,清云,五楼大会议室,四楼前台后面,可预约,30, .09:00-11:30 XXX会议室 .13:00-14:30 XXX会议室";
        String c = "000001,子公司,清云,六楼大会议室,四楼前台后面,可预约,30,可预约";
        return new String[]{a, b, c};
    }
}