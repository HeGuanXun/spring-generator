package com.hegx.db.utils;

import java.sql.*;

/**
 * Java中使用JDBC连接数据库
 * 1） 加载驱动 2） 创建数据库连接
 * 3） 创建执行sql的语句 4） 执行语句 5） 处理执行结果 6） 释放资源
 */
public class DBUtils {

    public static Connection getConnection() throws Exception {
        // 2.获得数据库链接
        String URL = "jdbc:mysql://localhost:3306/expressage?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai";
        String USER = "root";
        String PASSWORD = "123456";
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void creatTable(String creatSql) throws Exception {
        // 1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取连接
        Connection connection = getConnection();
        // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement statement = connection.createStatement();
        //创建表格
        statement.executeUpdate(creatSql);
        //释放资源
        statement.close();
    }

    public static void addDatas(String datasSql) throws Exception {
        // 1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取连接
        Connection connection = getConnection();
        // 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement statement = connection.createStatement();
        //插入数据
        statement.executeUpdate(datasSql);
        //释放资源
        statement.close();
    }

    public static void main(String[] args) throws Exception {
        long l = getConnection().createStatement().executeLargeUpdate("");
        System.out.println(l);
    }
}
