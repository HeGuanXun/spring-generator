package com.hegx.entity;

import lombok.Data;

/**
 * 生成文件的上下文
 */
@Data
public class GeneratorContent {
	/** 数据库配置文件 */
	private DatabaseContent database;
	/** 实体类配置信息 */
	private EntityContent entity;
	/** 数据库表的属性 */
	private TableContent table;
	/** 实体类配置信息 */
	private ServiceContent service;
	/** 实体类配置信息 */
	private ServiceImplContent serviceImpl;
	/** 实体类配置信息 */
	private DaoContent dao;
	/** 实体类配置信息 */
	private MapperContent mapper;
	/** 实体类配置信息 */
	private ControllerContent controller;
	/** 实体类配置信息 */
	private UnitTestContent unitTest;
	/** 实体类配置信息 */
	private SqlAssistContent assist;
	/** 实体类配置信息 */
	private CustomContent custom;
	/** 实体类配置信息 */
	private CustomPropertyContent customProperty;
	/** 追加工具类信息*/
	private String basePackage;
	/** 分页信息*/
	private Page page;
}
