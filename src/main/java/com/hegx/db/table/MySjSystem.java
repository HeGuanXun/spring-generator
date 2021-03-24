package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.DB_Application;
import com.hegx.db.factroy.Factory;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@Table(value = "test5", tableContext = "送件员接单管理系统")
public class MySjSystem extends Mock implements Factory {

    @Column("接单日期")
    private String time;
    @Column("订单号")
    private String order;
    @Column(value = "发件人",isSearch = true)
    private String sendMan;
    @Column(value = "发件电话",isSearch = true)
    private String sendManPhone;
    @Column("发地址")
    private String sendManAddress;
    @Column(value = "类型", types = {"电子产品", "家具", "衣物", "食品"})
    private String type;
    @Column("接收人")
    private String receiver;
    @Column("接收人电话")
    private String receiverPhone;
    @Column("接收人地址")
    private String receiverAddress;
    @Column("费用")
    private BigDecimal cost;
    @Column("备注")
    private String remark;
    @Column(value = "状态", types = {"已接单", "已出库", "待发货", "已发货", "已签收"})
    private String status;

    public MySjSystem() {
    }

    public MySjSystem(String time, String order, String sendMan, String sendManPhone, String sendManAddress, String type, String receiver, String receiverPhone, String receiverAddress, BigDecimal cost, String remark, String status) {
        this.time = time;
        this.order = order;
        this.sendMan = sendMan;
        this.sendManPhone = sendManPhone;
        this.sendManAddress = sendManAddress;
        this.type = type;
        this.receiver = receiver;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.cost = cost;
        this.remark = remark;
        this.status = status;
    }


    @Override
    public  ArrayList<MySjSystem> buildDatas() {
        ArrayList<MySjSystem> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new MySjSystem(
                    mockCurrentTime(),
                    mockNumber(),
                    mockUserName(),
                    mockPhone(),
                    mockAddress(),
                    mockType(MySjSystem.class, "'type'"),
                    mockUserName(),
                    mockPhone(),
                    mockAddress(),
                    mockMoney(), "XXX",
                    mockType(MySjSystem.class, "status")
            ));
        }
        return list;
    }
}