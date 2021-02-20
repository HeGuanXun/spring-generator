package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import lombok.Data;

import java.util.ArrayList;

@Data
@Table(value = "zc_type", tableContext = "资产-资产分类")
public class Zctype {

    @Column("分类名称")
    private String name;

    @Column("上级分类")
    private String upType;

    @Column("摊销时长")
    private String useTime;

    @Column("是否最小分类")
    private String ifMinType;

    @Column("备注")
    private String remark;

    public Zctype(String name, String upType, String useTime, String ifMinType, String remark) {
        this.name = name;
        this.upType = upType;
        this.useTime = useTime;
        this.ifMinType = ifMinType;
        this.remark = remark;
    }

    public Zctype() {
    }

    public ArrayList<Zctype> getDatas(){
        ArrayList<Zctype> list = new ArrayList<>();
        list.add(new Zctype("固定资产","","100天","是","固定资产"));
        list.add(new Zctype("数码","固定资产","100天","是","数码"));
        list.add(new Zctype("办公家具","固定资产","100天","是","办公家具"));
        list.add(new Zctype("XXX","固定资产","100天","是","XXX"));
        list.add(new Zctype("易耗品","","100天","是","易耗品"));
        list.add(new Zctype("办公用品","易耗品","100天","是","办公用品"));
        list.add(new Zctype("XXX","易耗品","100天","是","XXX"));
        return list;
    }
}