package com.hegx.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * SqlAssist的实体类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
@Data
public class UtilContent {
	/** SqlAssist类的包名 */
	private String classPackage;
	/** SqlAssist类的类型 */
	private String className;

	public UtilContent(String classPackage, String className) {
		this.classPackage = classPackage;
		this.className = className;
	}
}
