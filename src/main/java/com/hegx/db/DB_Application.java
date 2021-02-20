package com.hegx.db;

import com.hegx.annotation.Column;
import com.hegx.annotation.Table;
import com.hegx.db.table.*;
import com.hegx.db.utils.DBUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 创表程序
 */
public class DB_Application {

    public static void main(String[] args) throws Exception {
        //1.创建对象
        UseCar commonObject = new UseCar();
        //2.获取对象数据
        List datas = commonObject.getDatas();
        //3.构建字段
        initColumns(commonObject);
        //4.创建
        createTableContext();
        //5.插入数据
        addData(datas);
    }

    //字段容器
    private static final ArrayList<DbColumn> columns = new ArrayList<>(10);
    //表名
    private static String tableName = "";
    //表备注
    private static String tableContext = "";
    //CREATE DDL
    private static String db_content = "CREATE TABLE `";
    //INSERT DDL
    private static String insertContext = "INSERT INTO`";

    private static <T> void addData(List<T> datas) throws Exception {
        System.out.println();
        for (int i = 0; i < datas.size(); i++) {
            insertContext = "insert into " + tableName + "(";
            columns.forEach(column -> {
                if (!column.getColumnType().equals("java.util.Date")){
                    String columnName = "`" + column.getColumnName() + "`,";
                    insertContext += columnName;
                }
            });
            insertContext = insertContext.substring(0, insertContext.length() - 1) + ")values(";
            T t = datas.get(i);
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getType().getName();
                if (!"java.util.Date".equals(name)){
                    insertContext += "'" + field.get(t) + "',";
                }
            }
            insertContext = insertContext.substring(0, insertContext.length() - 1) + ");";
            //打印创建信息
            System.out.println(insertContext);
            //插入数据
            DBUtils.addDatas(insertContext);
        }
    }

    /**
     * 构建字段 |表名 |表说明
     */
    private static void initColumns(Object t) {
        Field[] fields = t.getClass().getDeclaredFields();
        Table tableNames = t.getClass().getAnnotation(Table.class);
        tableName = tableNames.value();
        tableContext = tableNames.tableContext();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            String name = field.getType().getName();
            columns.add(new DbColumn(field.getName(), name, column.value()));
            field.setAccessible(true);
        }
    }

    private static void createTableContext() throws Exception {
        //开始构建建表sql
        db_content += tableName + "`(\n" + " `id` INT(11) NOT NULL AUTO_INCREMENT,\n";

        columns.forEach(column -> {
            String columnName = column.getColumnName();
            String columnType = column.getColumnType();
            String columnContext = column.getColumnContext();
            switch (columnType) {
                case "java.lang.String":
                    columnType = "varchar(255) DEFAULT NULL";
                    break;
                case "java.lang.Integer":
                    columnType = "int(11) DEFAULT NULL";
                    break;
                case "java.lang.Double":
                    columnType = "decimal(10,0) DEFAULT NULL";
                    break;
                case "java.util.Date":
                    columnType = "datetime DEFAULT CURRENT_TIMESTAMP";
                    break;
            }
            db_content += " `" + columnName + "` " + columnType + " COMMENT '" + columnContext + "',\n";
        });
        //追加表备注
        db_content += " PRIMARY KEY (`id`)\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '" + tableContext + "';";
        //打印建表信息
        System.out.println(db_content);
        //建表
        DBUtils.creatTable(db_content);
    }

    @Data
    static class DbColumn {
        //字段名字
        private String columnName;
        //字段类型
        private String columnType = "VARCHAR";
        //字段说明
        private String columnContext;

        public DbColumn(String columnName, String columnType, String columnContext) {
            this.columnName = columnName;
            if (columnType != null) {
                this.columnType = columnType;
            }
            this.columnContext = columnContext;
        }
    }

}
