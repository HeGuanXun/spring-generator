package com.hegx.db.table;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.utils.Mock;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@Table(value = "balanceDetail", tableContext = "送件员余额管理系统-详情")
public class BalanceDetail extends Mock{

    @Column("提现时间")
    private String costTime;
    @Column("余额")
    private BigDecimal balanceDetail;

    public BalanceDetail(String costTime, BigDecimal balanceDetail) {
        this.costTime = costTime;
        this.balanceDetail = balanceDetail;
    }

    public BalanceDetail() {
    }

}