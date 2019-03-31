package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.model.Student;

import java.sql.*;

public class Sql {
	private String name ;
	private String passWord;
	private String databaseName;
	
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";//固定的
	private String DB_URL ;//注意修改数据库名
	private Connection conn = null; //与数据库的链接
	private Statement stmt = null; //保持增删改查链接
	
	//构造函数
	Sql(String name, String passWord, String site, String port,String databaseName){
		this.DB_URL = "jdbc:mysql://" + site + ":" + port + "/?autoReconnect=true&useSSL=false";//注意修改数据库名;
		this.name = name;
		this.passWord = passWord;
		this.databaseName = databaseName;
	}
	
	//连接数据库
	public void getConnection( ){
		try{
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);
			// 打开链接
			conn = DriverManager.getConnection(DB_URL,name,passWord);
			// 执行查询
			stmt = conn.createStatement();
			String sql = "use " + databaseName;
			stmt.executeQuery(sql);
		}catch(SQLException se){
			// 处理 JDBC 错误
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("JDBC 错误");
			alert.setContentText("初始化连接失败，请检查登录信息！错误如下：" + se.getCause());
			alert.showAndWait();
			return;
		}catch(ClassNotFoundException e){
			// 处理 Class.forName 错误
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("Class.forName 错误");
			alert.setContentText("初始化连接失败，请检查登录信息！错误如下：" + e.getCause());
			alert.showAndWait();
			return;
		}
	}
	
	
	//进行数据库查询语句
	public ObservableList<Student> doSql(String str){
		ResultSet rs;
		ObservableList<Student> list = FXCollections.observableArrayList();
		try{
			// 执行查询
			rs = stmt.executeQuery(str);
			if(rs != null){
				try {
					while(rs.next()){
						// 通过字段检索
						String id  = rs.getString("id");
						String name  = rs.getString("name");
						String sex  = rs.getString("sex");
						String major  = rs.getString("major");
						String cla  = rs.getString("class");
						String telephone  = rs.getString("telephone");
						String state  = rs.getString("state");
						String instuctor  = rs.getString("instructor");
						// 输出数据
						list.add(new Student(id,name,sex,major,cla,telephone,state,instuctor));
					}
					return list;
				}catch (Exception e){
					e.printStackTrace();
				}
				finally {
					rs.close();
				}
			}
			return null;
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
	
}

