package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Sql {
	
	private String name ;
	private String passWord;
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";//固定的
	private String DB_URL ;//注意修改数据库名
	private Connection conn = null;
	private Statement stmt = null;
	private String databaseName;
	private String tableName;
	public List<String> priKey = new LinkedList<String>();//存储当前表的主键
	private ObservableList<data> datas = FXCollections.observableArrayList(); //存储当前表数据
	//当前表和data类映射关系？
//
//	List<String> str =new LinkedList<String>();
//        str.add("");
//        datas.add(new data(str));
//	tableView_center.getSelectionModel().selectLast();
	
	public ObservableList<data> getDatas() {
		return datas;
	}
	
	public void setDatas(ObservableList<data> datas) {
		this.datas = datas;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}
	
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	//获取当前表的主键
	public void getPriKey(){
		priKey.clear();
		ResultSet rs = doSql( "select column_name from information_schema.COLUMNS where table_name = '"  + tableName +
				"' and table_schema = '" + databaseName + "' and COLUMN_KEY = 'pri' ");
		try {
			while(rs.next()){
				String database  = rs.getString("column_name");
				priKey.add(database);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		closeResultSet(rs);
	}
	//	private ResultSet rs;
	
	Sql(String DB_URL,String name,String passWord){
		this.DB_URL = DB_URL;
		this.name = name;
		this.passWord = passWord;
	}
	
	//分解ObservableList<String>数据
	public ObservableList<String> getResultSetInfo(ResultSet rs,String kind){
		ObservableList<String> table_info_columnName = FXCollections.observableArrayList ();
		try{
			while(rs.next()){
				String info_name  = rs.getString(kind);
				table_info_columnName.add(info_name);
			}
			closeResultSet(rs);
			return table_info_columnName;
		}catch (SQLException e){
			e.printStackTrace();
			closeResultSet(rs);
			return null;
		}
	}
	
	
	//获取表格的列名
	public ObservableList<String> getTableInfo(){
		ObservableList<String> table_info_columnName = FXCollections.observableArrayList ();
		ResultSet rs;
		rs = getTableSchema();
		try{
			while(rs.next()){
				String info_name  = rs.getString("COLUMN_NAME");
				table_info_columnName.add(info_name);
			}
			closeResultSet(rs);
			return table_info_columnName;
		}catch (SQLException e){
			e.printStackTrace();
			closeResultSet(rs);
			return null;
		}
	}
	
	//查询表格信息
	public ResultSet getTableSchema(){
		ResultSet rs = doSql( "select COLUMN_NAME from information_schema.COLUMNS where table_name = '"+
				tableName + "' and table_schema ='" + databaseName + "'");
		return rs;
	}
	
	//连接数据库
	public ResultSet getConnection( ){
		ResultSet rs;
		try{
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);
			// 打开链接
			conn = DriverManager.getConnection( ,name,passWord);
			// 执行查询
			stmt = conn.createStatement();
			String sql = "show databases";
			rs = stmt.executeQuery(sql);
			return rs;
			// 展开结果集数据库
		}catch(SQLException se){
			// 处理 JDBC 错误
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("JDBC 错误");
			alert.setContentText("初始化连接失败，请检查登录信息！错误如下：" + se.getCause());
			alert.showAndWait();
			return null;
		}catch(ClassNotFoundException e){
			// 处理 Class.forName 错误
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Class.forName 错误");
			alert.setContentText("初始化连接失败，请检查登录信息！错误如下：" + e.getCause());
			alert.showAndWait();
			return null;
		}finally{

		}
	}
	
	//进行数据库查询语句
	public ResultSet doSql(String str){
		ResultSet rs;
		try{
			// 执行查询
			rs = stmt.executeQuery(str);
			return rs;
			// 展开结果集数据库
		}catch(SQLException se){
			// 处理 JDBC 错误
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			System.out.println("错误语句："+str);
			alert.setTitle("ERROR");
			alert.setHeaderText("JDBC 错误");
			alert.setContentText("查询失败1，请稍后重试！错误如下：" + se.getCause());
			alert.showAndWait();
			return null;
		}
	}
	
	//进行数据库更新语句
	public int doSql_update(String str){
		int rs = 0;
		try{
			// 执行查询
			rs = stmt.executeUpdate(str);
			return rs;
			// 展开结果集数据库
		}catch(SQLException se){
			// 处理 JDBC 错误
			se.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("JDBC 错误");
			alert.setContentText("数据更新失败，请稍后重试！错误如下：" + se.getCause());
			alert.showAndWait();
			return -1;
		}finally{
		}
	}
	
	//刷新左侧表结构
	public void refrushListView(ChoiceBox<String> choiceBox,ListView<String> listView, ResultSet rs){
		// listView.setCellFactory((ListView<String> l) -> new MyCell());
		//System.out.println( choiceBox.getSelectionModel().getSelectedItem().toString());
		//closeResultSet(rs);
		rs = doSql("use " + choiceBox.getSelectionModel().getSelectedItem().toString());
		//closeResultSet(rs);
		ObservableList<String> item = FXCollections.observableArrayList ();
		rs = doSql("show tables");
		if(rs != null){
			try {
				while(rs.next()){
					// 通过字段检索
					String table  = rs.getString("Tables_in_" +  choiceBox.getSelectionModel().getSelectedItem().toString());
					// 输出数据
					item.add(table);
				}
				listView.setItems(item);
				setTableName(item.get(0));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//
	public void closeResultSet(ResultSet rs){
		try{
			if (rs != null)
				rs.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}

