package com.hegx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.hegx.common.ConfigUtil;
import com.hegx.common.DBUtil;
import com.hegx.options.DatabaseConfig;
import com.hegx.spi.DatabaseTypeNames;
import com.hegx.view.AlertUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateConnection extends BaseController {
	private Logger LOG = Logger.getLogger(this.getClass());
	private IndexController indexController;

	/** 连接名称 */
	@FXML
	private Label lblConnName;
	/** 连接名称 */
	@FXML
	private Label lblConnURL;
	/** 监听端口号 */
	@FXML
	private Label lblListenPort;
	/** 数据库类型 */
	@FXML
	private Label lblDBType;
	/** 数据库名字 */
	@FXML
	private Label lblDBName;
	/** 数据库用户名 */
	@FXML
	private Label lblUserName;
	/** 数据库密码 */
	@FXML
	private Label lblUserPwd;
	/** 数据库编码格式 */
	@FXML
	private Label lblDBCoding;
	/** 连接名称 */
	@FXML
	private TextField txtConnName;
	/** 连接URL */
	@FXML
	private TextField txtConnURL;
	/** 端口号 */
	@FXML
	private TextField txtListenPort;
	/** 数据库名称 */
	@FXML
	private TextField txtDBName;
	/** 用户名 */
	@FXML
	private TextField txtUserName;
	/** 密码 */
	@FXML
	private TextField txtUserPwd;
	/** 数据库类型 */
	@FXML
	private ComboBox<String> cboDBType;
	/** 数据库编码 */
	@FXML
	private ComboBox<String> cboDBCoding;
	/** 连接测试 */
	@FXML
	private Button btnTestConn;
	/** 取消 */
	@FXML
	private Button btnCancel;
	/** 保存 */
	@FXML
	private Button btnSave;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnTestConn.widthProperty().addListener(w -> {
			double cw = btnTestConn.getLayoutX() + btnTestConn.getWidth() + 20;
			btnSave.setLayoutX(cw);
		});
		btnSave.widthProperty().addListener(w -> {
			double cw = btnSave.getLayoutX() + btnSave.getWidth() + 20;
			btnCancel.setLayoutX(cw);
		});
		initLanguage();
	}

	public void init() {
		LOG.debug("初始化修改数据库连接窗口...");
		DatabaseConfig config = indexController.getUpdateOfDatabaseConfig();
		// 初始化下拉列表
		cboDBType.setEditable(true);
		cboDBType.getItems().addAll(DatabaseTypeNames.dbTypeNames());
		cboDBType.setValue(config.getDbType());
		cboDBCoding.setEditable(true);
		cboDBCoding.getItems().addAll("utf8", "utf16", "utf32", "utf8mb4", "gb2312", "gbk", "ascii");
		cboDBCoding.setValue(config.getEncoding());
		txtConnName.setText(config.getConnName());
		txtConnURL.setText(config.getConnURL());
		txtDBName.setText(config.getDbName());
		txtListenPort.setText(config.getListenPort());
		txtUserName.setText(config.getUserName());
		txtUserPwd.setText(config.getUserPwd());
		LOG.debug("初始化数据库连接窗口成功!");
	}

	/**
	 * 初始化语言
	 */
	private void initLanguage() {
		ConnectionController.runInitLanguage(lblConnName, lblConnURL, lblListenPort, lblDBType, lblDBName, lblUserName, lblUserPwd, lblDBCoding, txtConnName, txtConnURL, txtListenPort, cboDBType, txtDBName, txtUserName, txtUserPwd, cboDBCoding, btnTestConn, btnSave, btnCancel);
	}

	/**
	 * 确定修改
	 * 
	 * @param event
	 */
	public void btnUpdate(ActionEvent event) {
		LOG.debug("执行修改数据库连接...");
		DatabaseConfig dbConfig = getDatabaseConfig();
		if (dbConfig == null) {
			LOG.debug("连接数据库的数据为null,取消保存操作!!!");
			return;
		}
		try {
			ConfigUtil.updateDatabaseConfig(dbConfig);
			AlertUtil.showAndWaitInfoAlert("修改数据库连接成功!");
			indexController.loadTVDataBase();
			LOG.debug("修改数据库连接成功!");
			getDialogStage().close();
		} catch (Exception e) {
			LOG.error("修改数据库连接失败!!!" , e);
			AlertUtil.showErrorAlert(e.getMessage());
		}
	}

	/**
	 * 取消修改
	 * 
	 * @param event
	 */
	public void onCancel(ActionEvent event) {
		getDialogStage().close();
	}

	/**
	 * 测试连接
	 * 
	 * @param event
	 */
	public void testConnection(ActionEvent event) {
		LOG.debug("执行测试数据库连接...");
		DatabaseConfig config = getDatabaseConfig();
		if (config == null) {
			LOG.debug("连接数据库的数据为null,取消测试操作!!!");
			return;
		}
		try {
			DBUtil.getConnection(config);
			AlertUtil.showInfoAlert("连接成功!");
			LOG.debug("数据库测试连接成功!");
		} catch (Exception e) {
			LOG.error("数据库连接测试失败!!!" , e);
			AlertUtil.showErrorAlert("连接失败!原因:" + e.getMessage());
		}
	}

	/**
	 * 获得连接的所有字段
	 * 
	 * @return
	 */
	public DatabaseConfig getDatabaseConfig() {
		String connName = txtConnName.getText().trim();
		String connURL = txtConnURL.getText().trim();
		String listenPort = txtListenPort.getText().trim();
		String dbName = txtDBName.getText().trim();
		String userName = txtUserName.getText().trim();
		String userPwd = txtUserPwd.getText().trim();
		String dbType = cboDBType.getValue();
		String encoding = cboDBCoding.getValue();
		boolean isEmpty = validata(connName, connURL, listenPort, dbName, userName, dbType, encoding);
		if (isEmpty) {
			DatabaseConfig config = new DatabaseConfig(connName, connURL, listenPort, dbName, userName, userPwd, dbType, encoding);
			return config;
		} else {
			AlertUtil.showWarnAlert("除了密码以外所有属性都为必需填与选择");
			return null;
		}

	}

	/**
	 * 验证所有属性是否已经填写
	 * 
	 * @param str
	 * @return
	 */
	public boolean validata(String... str) {
		for (String string : str) {
			if (string == null || "".equals(string)) {
				return false;
			}
		}
		return true;
	}

	public IndexController getIndexController() {
		return indexController;
	}

	public void setIndexController(IndexController indexController) {
		this.indexController = indexController;
	}

}
