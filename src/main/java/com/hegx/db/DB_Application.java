package com.hegx.db;

import com.hegx.annotation.Column;
import com.hegx.enums.ItemEnum;
import com.hegx.annotation.Serve;
import com.hegx.annotation.Table;
import com.hegx.db.factroy.Factory;
import com.hegx.db.factroy.HgxFactory;
import com.hegx.db.utils.DBUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 创表程序
 */
@Serve(applicationName = "platform",projectPath = "D:\\App\\A-project\\git-resource\\haiyu-service\\haiyu-platform")
public class DB_Application {
    public static void main(String[] args) throws Exception {
        String table = "com.hegx.db.table.Px";
        //1.获取对象，通过工厂模式
        Factory factory = HgxFactory.getInstance(table);
        //2.构造数据
        ArrayList<Factory> datas = factory.buildDatas();
        //3.构建字段
        initColumns(factory);
        //4.创建表名
        createTableContext();
        //5.插入数据
        addData(datas);
        //6.构建Vue数据结构
        buildVueColumns(factory);
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

    private static <T> void addData(ArrayList<T> datas) throws Exception {
        System.out.println();
        for (T data : datas) {
            insertContext = "insert into " + tableName + "(";
            columns.forEach(column -> {
                if (!column.getColumnType().equals("java.util.Date")) {
                    String columnName = "`" + column.getColumnName() + "`,";
                    insertContext += columnName;
                }
            });
            insertContext = insertContext.substring(0, insertContext.length() - 1) + ")values(";
            Field[] fields = data.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getType().getName();
                if (!"java.util.Date".equals(name)) {
                    insertContext += "'" + field.get(data) + "',";
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
                case "java.math.BigDecimal":
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

    public static String getServeName() {
        Serve serve = DB_Application.class.getAnnotation(Serve.class);
        return serve.applicationName();
    }

    public static String getProjectPath() {
        Serve serve = DB_Application.class.getAnnotation(Serve.class);
        return serve.projectPath();
    }

    /**
     * 侯建vue columnConfig跟searchConfig
     *
     * @param t
     * @param <T>
     */
    private static <T> void buildVueColumns(T t) {
        Field[] fields = t.getClass().getDeclaredFields();
        //1.构建table字段配置
        StringBuilder columnConfig_buff = new StringBuilder();
        columnConfig_buff.append("columnConfig : [\n");
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            buildColumnConfig(fields[i], columnConfig_buff, i, fields.length);
        }
        //页面名字
        Table tableNames = t.getClass().getAnnotation(Table.class);
        System.out.print("'" + tableNames.value() + "':{\n\t");
        System.out.print(columnConfig_buff.toString());
        System.out.print("},");

    }

    /**
     * .构建table字段配置
     *
     * @param field
     * @param config_buff
     * @param index
     * @param length
     */
    private static void buildColumnConfig(Field field, StringBuilder config_buff, int index, int length) {
        String zh_name = field.getAnnotation(Column.class).value();
        boolean isSearch = field.getAnnotation(Column.class).isSearch();
        String[] options = field.getAnnotation(Column.class).options();
        ItemEnum itemType = field.getAnnotation(Column.class).itemType();
        config_buff.append("\t\t{ prop: '").append(field.getName()).append("',label: '").append(zh_name).append("'");
        if (isSearch) {
            config_buff.append(",isSearch: '").append(true).append("'");
        }
        if (options.length > 0) {
            config_buff.append(",type: '").append("Select'");
            config_buff.append(",options: [\n\t");
            for (int i = 0; i < options.length; i++) {
                config_buff.append("\t\t{label: '").append(options[i]).append("',value: '").append(options[i]).append("'},\n\t");
            }
            config_buff.append("\t]");
        }else if (!itemType.equals("Input")){
            config_buff.append(",type: '").append(itemType).append("'");
        }
        config_buff.append("}");
        if (index != length - 1) {
            config_buff.append(",\n");
        } else {
            config_buff.append("\n]");
        }
    }
}
