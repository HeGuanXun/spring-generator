package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import lombok.Data;

@Data
@Table(value = "asset", tableContext = "资产管理")
public class Asset {
    @Column("资产编码")
    private String num;
    @Column("资产名称")
    private String name;
    @Column("资产类型")
    private String assetTypeOne;
    @Column("资产分类")
    private String assetTypeTwo;
    @Column("规格型号")
    private String specification;
    @Column("仓库")
    private String store;
    @Column("单位")
    private String unit;
    @Column("数量")
    private Integer count;
    @Column("采购价格")
    private Double cost;
    @Column("市场估值")
    private Double marketValuation ;
    @Column("管理人")
    private String manager;
    @Column("状态")
    private String status;
    @Column("存放位置")
    private String location;
    @Column("备注")
    private String remark;
    @Column("登记时间")
    private String registerTime;

    public String[] getDatas(){
        String a = "13124012,huawei P40,固定资产,数码/电脑/笔记本电脑,XXXX,罗马仕,万科,1000,200000,500000," +
                "花木兰,正常,上海,花木兰的资产管理清单,2021-02-01";
        String b = "13124011,huawei P40,固定资产,数码/电脑/笔记本电脑,XXXX,罗马仕,万科,1000,200000,500000," +
                "花木兰,正常,上海,花木兰的资产管理清单,2021-02-02";
        String c = "13124016,huawei P50,固定资产,数码/电脑/笔记本电脑,XXXX,罗马仕,万科,1000,200000,500000," +
                "李白,报修,上海,李白的资产管理清单,2021-02-03";
        return new String[]{a,b,c};
    }
}