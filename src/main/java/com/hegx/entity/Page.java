package com.hegx.entity;

import lombok.Data;

/**
 * SqlAssist的实体类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
@Data
public class Page {
	/** SqlAssist类的包名 */
	private String mapperCurrentPage;
	/** SqlAssist类的类型 */
	private String pageSize ;

	public Page() {
		this.mapperCurrentPage = "#{mapperCurrentPage}";
		this.pageSize = "#{pageSize}";
	}
}
