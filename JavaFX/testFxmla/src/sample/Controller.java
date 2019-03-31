package sample;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


import java.awt.event.ActionEvent;
import java.sql.*;

public class Controller {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";//固定的
	static final String DB_URL = "jdbc:mysql://bdm309127484.my3w.com:3306/bdm309127484_db?autoReconnect=true&useSSL=false";//注意修改数据库名
	// 数据库的用户名与密码，需要根据自己的设置
	static final String username = "bdm309127484";
	static final String password = "shannu1997";
	
	@FXML private Label test1;
	@FXML private Label test2;
	@FXML private Label test3;
	@FXML private Label label4;
	@FXML private ListView<String> listView;
	
	
	
	int n = 0;
	@FXML
	public void test (ActionEvent event){
	
	}
	
	@FXML
	public void test1 (){
		++n;
		test1.setText("" + n);
		test1.setTranslateX(n * 10);
		test2.toFront();
	}
	
	@FXML
	public void test2 (){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("这是信息提示对话框");
		alert.setHeaderText("这是头部内容");
		alert.setContentText("这是文本内容");
		alert.showAndWait();
		n = 0;
		test1.setText("" + n);
	}
	
	@FXML
	boolean bool = false;
	public void test3 (){
		if(bool){
			bool = false;
			test2.setVisible(true);
		}
		else
		{
			bool = true;
			test2.setVisible(false);
		}
	}
	
	@FXML
	public void test4 (){
		if(bool){
			bool = false;
			test3.setVisible(true);
		}
		else
		{
			bool = true;
			test3.setVisible(false);
			getData();
		}
	}
	
	public void getData(){
		ObservableList<String> items = FXCollections.observableArrayList ();
		Connection conn = null;
		Statement stmt = null;
		try{
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);
			// 打开链接
			conn = DriverManager.getConnection(DB_URL,username,password);
			// 执行查询
			stmt = conn.createStatement();
			String sql = "select * from user";
			ResultSet rs = stmt.executeQuery(sql);
			// 展开结果集数据库
			while(rs.next()){
				// 通过字段检索
				//String id  = rs.getInt("email");
				String id  = rs.getString("email");
				String password = rs.getString("password");
				String phone = rs.getString("phone");
				
				// 输出数据
				String mid = "ID: " + id + " passworld: " + password + "  phone: " + phone;
				items.add(mid);
			}
			// 完成后关闭
			rs.close(); // 关闭结果集
			stmt.close(); //执行查询
			conn.close(); //关闭对数据库的连接
		}catch(SQLException se){
			// 处理 JDBC 错误
			se.printStackTrace();
		}catch(Exception e){
			// 处理 Class.forName 错误
			e.printStackTrace();
		}finally{
			// 关闭资源
			try{
				if(stmt!=null) stmt.close();
			}catch(SQLException se2){
			}// 什么都不做
			try{
				if(conn!=null) conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		listView.setItems(items);
//		listView.setSelectionModel();
//		label4.textProperty().bind(listView.getSelectionModel().selectedItemProperty());
	}
	
	@FXML private void change(){
		
	}
	
}
