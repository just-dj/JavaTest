package sample;

import javafx.scene.control.Alert;

import java.sql.*;

public class Sql {
	private String name ;
	private String passWord;
	private String site;
	private String port;
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";//固定的
	private String DB_URL ;//注意修改数据库名
	private Connection conn = null; //与数据库的链接
	private Statement stmt = null; //保持增删改查链接
	
	
	//构造函数
	Sql(String name, String passWord, String site, String port){
		this.DB_URL = "jdbc:mysql://" + site + ":" + port + "/?autoReconnect=true&useSSL=false";//注意修改数据库名;
		this.name = name;
		this.passWord = passWord;
		this.site = site;
		this.port = port;
	}
	;
	//连接数据库  ：：返回当前所有数据库
	public void getConnection( ){
		try{
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);
			// 打开链接
			conn = DriverManager.getConnection(DB_URL,name,passWord);
			// 执行查询
			stmt = conn.createStatement();
			String sql = "use bdm309127484_db";
			stmt.executeQuery(sql);
			// 展开结果集数据库
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
	
	public int insert(String name,String ip){
		boolean isSuccess = false;
		boolean isUp = false;
		ResultSet rs = doSql("select name from ip where name = '" + name + "'");
		if ( rs!=null ){
			try {
				while(rs.next()){
					// 通过字段检索
					String nn  = rs.getString("name");
					if(nn != null){
						isUp = true;
					}else{
						break;
					}
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		if(!isUp){
			System.out.println("insert into ip values('" + name + "','"+ ip + "')");
			doSql_update("insert into ip values('" + name + "','"+ ip + "')");
		}else {
			System.out.println("update ip set ip='" + ip + "' where name = '"+name + "'" );
			doSql_update("update ip set ip='" + ip + "' where name = '"+name + "'" );
		}
		return 0;
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
	
	public void al(){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("success");
		alert.setHeaderText("提交成功");
		alert.setContentText("IP信息成功同步至数据库！" );
		alert.showAndWait();
	}
	//进行数据库更新 删除等语句
	public int doSql_update(String str){
		int rs = 0;
		try{
			// 执行查询
			rs = stmt.executeUpdate(str);
			al();
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
}

