package com.hegx.db.utils;

import com.hegx.annotation.Column;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Mock {
    //用户名
    private static final String[] userNames = new String[]{"花木兰","李白","妲己","孙悟空","马可波罗","鲁班","百里守约","甄姬","明世隐","亚瑟"};
    //手机号码
    private static final String[] phones = new String[]{"17666112400","18866611240","1868876578","18977665466","15999663200"};
    //地址
    private static final String[] address = new String[]{"上海市普陀区金沙江路 1518 弄","海南省海口市美兰区海南大厦XXX","广东省深圳市福田区xxx","海南省儋州市那大镇xxx"};
    //部门
    private static final String[] departments = new String[]{"技术部","销售部","风控部","法务部","职能部"};
    //审核状态
    private static final String[] checkStatus = new String[]{"待审核","已审核","审核通过","已拒绝","已驳回"};
    //身份证号码
    private static final String[] idCards = new String[]{"33078119850907351X","11010119900307707X","110101199003072930","110101199503076655","110101199003071102"};
    //性别
    private static final String[] sex = new String[]{"男","女"};
    //年龄
    private static final String[] ages = new String[]{"21","25","24","28","31"};
    //金额
    private static final Double[] moneys = new Double[]{10000.00,20000.00,30000.00,40000.00,50000.00};

    //获取年龄
    public static String getMockAge() {
        return ages[(int)(Math.random()*(ages.length-1))];
    }
    //获取当前时间
    public static String getMockCurrentTime() {
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return tempDate.format(new java.util.Date());
    }
    //获取性别
    public static String getMockSex() {
        return sex[(int)(Math.random()*(sex.length-1))];
    }
    //获取一个随机8位的随机数
    public static String getMockNumber() {
        Random random = new Random();
        return String.valueOf(random.nextLong()).substring(1, 8 + 1);
    }
    //获取人名
    public static String getMockUserName() {
        return userNames[(int)(Math.random()*(userNames.length-1))];
    }
    //获取身份证号码
    public static String getMockIdCard() {
        return idCards[(int)(Math.random()*(idCards.length-1))];
    }
    //获取手机号码
    public static String getMockPhone() {
        return phones[(int)(Math.random()*(phones.length-1))];
    }
    //获取地址
    public static String getMockAddress() {
        return address[(int)(Math.random()*(address.length-1))];
    }
    //获取部门
    public static String getDepartment() {
        return departments[(int)(Math.random()*(departments.length-1))];
    }
    //获取金钱
    public static BigDecimal getMockMoney() {
        Double money = moneys[(int) (Math.random() * (moneys.length - 1))];
        return new BigDecimal(money);
    }
    //获取审核状态
    public static String getMockCheckStatus() {
        return checkStatus[(int)(Math.random()*(checkStatus.length-1))];
    }
    //获取审核状态
    public static String getMockType(Class<?> t,String column)  {
        String status= "";
        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(column)) {
                Column annotation = field.getAnnotation(Column.class);
                String[] types = annotation.types();
                status = types[(int) (Math.random() * (types.length - 1))];
            }
        }
        return status;
    }
}
