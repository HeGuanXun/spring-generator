package com.hegx.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.hegx.entity.ControllerContent;
import com.hegx.entity.CustomContent;
import com.hegx.entity.CustomPropertyContent;
import com.hegx.entity.DaoContent;
import com.hegx.entity.DatabaseContent;
import com.hegx.entity.EntityContent;
import com.hegx.entity.FieldAttribute;
import com.hegx.entity.MapperContent;
import com.hegx.entity.ServiceContent;
import com.hegx.entity.ServiceImplContent;
import com.hegx.entity.SqlAssistContent;
import com.hegx.entity.UnitTestContent;
import com.hegx.models.TableAttributeEntity;
import com.hegx.models.TableAttributeKeyValue;
import com.hegx.models.TableAttributeKeyValueTemplate;
import com.hegx.models.TableAttributeKeyValueTemplateVO;
import com.hegx.options.ControllerConfig;
import com.hegx.options.CustomConfig;
import com.hegx.options.CustomPropertyConfig;
import com.hegx.options.DaoConfig;
import com.hegx.options.DatabaseConfig;
import com.hegx.options.EntityConfig;
import com.hegx.options.MapperConfig;
import com.hegx.options.ServiceConfig;
import com.hegx.options.ServiceImplConfig;
import com.hegx.options.SqlAssistConfig;
import com.hegx.options.UnitTestConfig;

/**
 * 转换器
 */
public class ConverterUtil {
	/**
	 * 将实体类属性信息转换为模板类需要用的上下文属性
	 * 
	 * @param ec
	 *          实体类信息
	 * @param content
	 *          装换目标类
	 */
	public static void databaseConfigToContent(DatabaseConfig dc, DatabaseContent content) {
		content.setDisplayName(dc.getConnName());
		content.setHost(dc.getConnURL());
		content.setPort(dc.getListenPort());
		content.setUserName(dc.getUserName());
		content.setUserPwd(dc.getUserPwd());
		content.setDbName(dc.getDbName());
		content.setDbType(dc.getDbType());
		content.setEncoding(dc.getEncoding());
	}
	/**
	 * 将实体类属性信息转换为模板类需要用的上下文属性
	 * 
	 * @param ec
	 *          实体类信息
	 * @param content
	 *          装换目标类
	 */
	public static void entityConfigToContent(EntityConfig ec, EntityContent content) {
		if (!StrUtil.isNullOrEmpty(ec.getTableAlias())) {
			content.setTableAlias(ec.getTableAlias());
		}
		if (!StrUtil.isNullOrEmpty(ec.getPrimaryKey())) {
			content.setPrimaryKey(ec.getPrimaryKey());
		}
		if (ec.getTblPropertyValues() != null) {
			ArrayList<FieldAttribute> list = new ArrayList<>();
			ArrayList<FieldAttribute> cantNullAttrs = null;
			ArrayList<FieldAttribute> otherAttrs = null;

			for (TableAttributeEntity c : ec.getTblPropertyValues()) {
				if (!c.getTdCreate()) {
					continue;
				}
				FieldAttribute attr = new FieldAttribute(c);
				attr.setField(c.getTdField());
				// 列名首字母大写
				String upField = StrUtil.firstToUpCase(c.getTdField());
				attr.setFieldPascal(upField);
				// java数据类型
				String javaType = attr.getJavaType();
				if ("boolean".equalsIgnoreCase(javaType)) {
					attr.setFget("is" + upField);
				} else {
					attr.setFget("get" + upField);
				}
				attr.setFset("set" + upField);
				attr.setFgetType("get" + javaType);
				attr.setFsetType("set" + javaType);
				// 添加属性到所有属性
				list.add(attr);
				// 设置主键的类型与主键的属性列
				if (ec.getPrimaryKey() != null && ec.getPrimaryKey().equals(c.getTdColumnName())) {
					content.setPrimaryKeyJdbcType(c.getTdJdbcType());
					content.setPrimaryKeyJavaType(c.getTdJavaType().getValue());
					content.setPrimaryKeyAttr(attr);
					continue;
				}
				// 添加不能为空的属性属性列与其他属性
				if (!attr.isNullable() && StrUtil.isNullOrEmpty(attr.getColumnDef())) {
					if (cantNullAttrs == null) {
						cantNullAttrs = new ArrayList<>();
					}
					cantNullAttrs.add(attr);
				} else {
					if (otherAttrs == null) {
						otherAttrs = new ArrayList<>();
					}
					otherAttrs.add(attr);
				}
			}
			content.setAttrs(list);
			content.setCantNullAttrs(cantNullAttrs);
			content.setOtherAttrs(otherAttrs);
		}
	}
	/**
	 * 将Service转换为content
	 * 
	 * @param sc
	 *          service配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void serviceConfigToContent(ServiceConfig sc, ServiceContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将ServiceImpl转换为content
	 * 
	 * @param sc
	 *          ServiceImpl配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void serviceImplConfigToContent(ServiceImplConfig sc, ServiceImplContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}

	/**
	 * 将SQL转换为content
	 * 
	 * @param sc
	 *          Sql配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void SqlConfigToContent(DaoConfig sc, DaoContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将Router转换为content
	 * 
	 * @param sc
	 *          Router配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void routerConfigToContent(ControllerConfig sc, ControllerContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将单元测试转换为content
	 * 
	 * @param sc
	 *          单元测试配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void unitTestConfigToContent(UnitTestConfig sc, UnitTestContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将Assist转换为content
	 * 
	 * @param sc
	 *          Assist配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void sqlAssistConfigToContent(SqlAssistConfig sc, SqlAssistContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将MapperConfig转换为content
	 * 
	 * @param sc
	 *          SqlAndParamsConfig配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void mapperConfigToContent(MapperConfig sc, MapperContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}

	/**
	 * 将custom包类转换为content
	 * 
	 * @param sc
	 *          custom包类配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void customConfigToContent(CustomConfig sc, CustomContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValueTemplate c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String cpackage = c.getPackageName().replace("{C}", className).replace("{c}", loCase);
				String name = c.getClassName().replace("{C}", className).replace("{c}", loCase);
				String templateValue = c.getTemplateValue();
				map.put(c.getKey(), new TableAttributeKeyValueTemplateVO(key, cpackage, name, templateValue));
			}
			content.setItem(map);
		}
	}
	/**
	 * 将custom属性转换为content
	 * 
	 * @param sc
	 *          custom属性配置信息
	 * @param content
	 *          上下文
	 * @param className
	 *          实体类名
	 */
	public static void customPropertyConfigToContent(CustomPropertyConfig sc, CustomPropertyContent content, String className) {
		if (sc.getTableItem() != null) {
			String loCase = StrUtil.firstToLoCase(className);
			Map<String, Object> map = new HashMap<>();
			for (TableAttributeKeyValue c : sc.getTableItem()) {
				String key = c.getKey().replace("{C}", className).replace("{c}", loCase);
				String value = c.getValue().replace("{C}", className).replace("{c}", loCase);
				String describe = c.getDescribe().replace("{C}", className).replace("{c}", loCase);
				map.put(c.getKey(), new TableAttributeKeyValue(key, value, describe));
			}
			content.setItem(map);
		}
	}

}
