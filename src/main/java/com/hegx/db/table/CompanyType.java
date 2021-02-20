package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import lombok.Data;

@Data
@Table(value = "company_type", tableContext = "会议室管理系统")
public class CompanyType {

    @Column("会议室名称")
    private String name;

    @Column("会议室分类")
    private String type;

    @Column("允许使用时长")
    private String useTime;

    @Column("是否最小分类")
    private String ifMinType;

    @Column("备注")
    private String remark;

    public String[] getDatas(){
        String a = "会议室一 母公司 3h 是 这是会议室1";
        String b = "会议室二 子公司 3h 是 这是会议室2";
        String c = "会议室三 母公司 3h 是 这是会议室3";
        return new String[]{a, b, c};
    }
}