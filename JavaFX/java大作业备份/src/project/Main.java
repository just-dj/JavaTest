package project;
// select COLUMN_NAME from information_schema.COLUMNS where table_name = "user" and table_schema = "massage";
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main extends Application {
    
    private Stage stage;
    private Sql sql;
    private  ResultSet rs;
    private ListView<String> listView;
    private Label label_right;
    private Label label_del;
    private Label label_add;
    private Label label_addCommit;
    private TableView tableView_center;
    private Map<String, Integer> map=new ConcurrentHashMap<>();
    private StringBuffer priValueNow = new StringBuffer("");
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        Parent sign = FXMLLoader.load(getClass().getResource("layout/signIn.fxml"));
        primaryStage.setTitle("signin");
        signIn(sign);
        primaryStage.setScene(new Scene(sign));
        primaryStage.show();

    }
   
    //获得登陆页面的输入数据
    private void signIn(Parent root){
        TextField site = (TextField) root.lookup("#site");
        TextField port = (TextField) root.lookup("#port");
        TextField userName = (TextField) root.lookup("#userName");
        TextField passWord = (TextField) root.lookup("#passWord");
        javafx.scene.image.ImageView image = (javafx.scene.image.ImageView) root.lookup("#image");
        Label sign = (Label)root.lookup("#signIn");
        
        sign.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String Site = site.getText();
                String Port = port.getText();
                String Name = userName.getText();
                String PassWord = passWord.getText();
                if (Name.equals(""))
                    return;
                //调用
                getDatabase(Site,Port,Name,PassWord);
            }
        });
        site.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")  || newValue == null)
                    ;
            }
        });
        port.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("")  || newValue == null && oldValue != "3")
                    ;
            }
        });
    }
    
    //连接数据库 实例化SQl对象
    private void getDatabase(String site, String port, String name, String PassWord){
        // JDBC 驱动名及数据库 URL
         String DB_URL = "jdbc:mysql://" + site + ":" + port + "/?autoReconnect=true&useSSL=false";//注意修改数据库名
        //获得Sql对象
        sql = new Sql(DB_URL,name,PassWord);
        rs = sql.getConnection();
        ObservableList<String> items = FXCollections.observableArrayList ();
        if(rs != null){
            try {
                while(rs.next()){
                    // 通过字段检索
                    String database  = rs.getString("Database");
                    // 输出数据
                    items.add(database);
                }
                mainPage(items);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
       
    }
    
    //打开主界面
    private  void mainPage (ObservableList<String> items) throws Exception{
        Parent main = FXMLLoader.load(getClass().getResource("layout/page_main.fxml"));
        stage.setTitle("main");
        ChoiceBox choiceBox = (ChoiceBox)main.lookup("#choiceBox") ;
        listView = (ListView) main.lookup("#listView");
        label_right =(Label)main.lookup("#label_right") ;
        label_del =(Label)main.lookup("#label_del");
        label_add = (Label)main.lookup("#label_add");
        label_addCommit = (Label)main.lookup("#label_commit");
        tableView_center = (TableView)main.lookup("#tableView") ;
        tableView_center.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
        
        label_del.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                delItem();
            }
        });
        //choiceBox设置 左上
        choiceBox.setItems(items);
        choiceBox.getSelectionModel().select(0);
        sql.setDatabaseName(choiceBox.getSelectionModel().getSelectedItem().toString());
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                //先同步数据
                sql.setDatabaseName(newValue.toString());
                //刷新左侧表列表
                sql.refrushListView(choiceBox,listView,rs);
                tableView_center.getColumns().clear();
            }
        });
        //ListView设置 左
        listView.setCellFactory(TextFieldListCell.forListView());

        //编辑事件
        listView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> e) {
                Integer n = sql.doSql_update("alter table " +e.getSource().getSelectionModel().getSelectedItem() + "" +
                        " rename " + ""+ e.getNewValue() );
                if(n == 0){
                    System.out.println("更新成功：   " + e.getNewValue());
                    sql.setTableName(e.getNewValue());
                    e.getSource().getItems().set(e.getIndex(),e.getNewValue());
                }
//                System.out.println(e.getSource().getSelectionModel().getSelectedItem()+" 行号："+e.getIndex()  +" 新： " + e
//                        .getNewValue());
            }
        });
        //选择事件
        listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{
                    System.out.println("listView.getSelectionModel()旧 ：" + oldValue +"  新："+ newValue);
                    //清楚旧数据
                    sql.priKey.clear();
                    sql.getDatas().clear();
                    if (newValue != null && !newValue.equals(""))
                        sql.setTableName(newValue);
                    if (newValue != null){
                        sql.getPriKey();
                        table_getColumn();
                    }
                    //刷新右侧区域
                    String mid ;
                    if (sql.priKey.isEmpty())
                        label_right.setText("当前表：" + sql.getTableName() + "\n\n主键：无" +  "\n\n编辑状态:不可编辑" );
                    else{
                        StringBuffer demo = new StringBuffer();
                        for (int i=0; i < sql.priKey.size();++i){
                            demo.append("\n" +"  "+ (i + 1)+ "   "+ sql.priKey.get(i));
                        }
                        label_right.setText("当前表：" + sql.getTableName() + "\n\n主键：" + demo + "\n\n编辑状态:可编辑" );
                    }
                });
        //初始化刷新左侧列表 和center table
        sql.refrushListView(choiceBox,listView,rs);
        
        tableView_center.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                priValueNow.setLength(0);
                if (sql.priKey.isEmpty())
                    return;
                if (oldValue == null) //新选一张表格
                    priValueNow .append(getPriData((data)newValue));
                else   if (newValue == null)//跳到另一张表格
                    priValueNow.setLength(0);
                else
                    priValueNow .append(getPriData((data)newValue));
                System.out.println("当前行主键： "+priValueNow);
            
            }
        });
        
        
        stage.setScene(new Scene(main));
        stage.show();
    }
    
    //点击删除按钮的动作
    private void delItem(){
        //判断当前数据表是否为空 或则是否可编辑
        System.out.println("好像行有改动：" +"delete from " + sql.getTableName() + " where " + priValueNow);
        if (sql.getDatas().isEmpty() || sql.priKey.isEmpty() || priValueNow.toString().isEmpty())
            return;
        int n = sql.doSql_update("delete from " + sql.getTableName() + " where " + priValueNow);
        if (n > 0){ //数据库中执行成功
            sql.getDatas().remove(tableView_center.getSelectionModel().getFocusedIndex());
            tableView_center.setItems(sql.getDatas());
        }
    }
    //刷新table区域
    public void table_getColumn(){
        //首先刷新中间表格区域
        if (tableView_center.getColumns() != null){
            tableView_center.getColumns().clear();
        }
        //获得当前数据库表格 列
        List<TableColumn> allTable_column = setColumn();
        
        if (!map.isEmpty())
            map.clear();
        
        //向map中填入数据 创建映射
        if (!allTable_column.isEmpty())
            for(int i = 0; i < allTable_column.size(); ++ i){
                map.put(allTable_column.get(i).getText(),new Integer(i+1));
            }
        System.out.println("table_getColumn() 输出map:  "+map);
       // System.out.println( allTable_column);
        for (int i = 0;i < allTable_column.size();++i){
            tableView_center.getColumns().add(allTable_column.get(i));
        }
        
        
        ObservableList<data> datas = FXCollections.observableArrayList();
        ObservableList<String> table_info_columnName = sql.getTableInfo();
        List<String> strList = new LinkedList<String>();
        sql.closeResultSet(rs);
        rs = sql.doSql("select * from  " + sql.getTableName());
        if(rs == null)
            return;
        try{
            while(rs.next()){
                strList.clear();
                for (int  j = 0;j < table_info_columnName.size();++j){
                    strList.add(rs.getString(table_info_columnName.get(j))) ;
                }
                //这个构造函数传入的是List<String>
                datas.add(new data(strList));
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!sql.getDatas().isEmpty())
            sql.getDatas().clear();
        //存储到sql类中
        sql.setDatas(datas);
        tableView_center.setItems(datas);
    }
    
    //获得列 列表
    public List<TableColumn> setColumn(){
        ObservableList<String> table_info_columnName = sql.getTableInfo();
        //列 列表
        List<TableColumn> allTable_column = new ArrayList<TableColumn>();
        for (int i = 0; i < table_info_columnName.size();++i){
            TableColumn oneOfTheColumn = new TableColumn(table_info_columnName.get(i));
            oneOfTheColumn.setMinWidth(120);
            oneOfTheColumn.setCellValueFactory(new PropertyValueFactory<>("data" + (i+1)));
            
            //对该clolumn做事件捕获以及处理
            addDataToColumn(oneOfTheColumn,table_info_columnName.get(i),i + 1);
            try{
                allTable_column.add(oneOfTheColumn);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return allTable_column;
    }
    
    //监听列  获得一列数据
    public void addDataToColumn(TableColumn oneOfTheColumn,String columnName,int row){
        //row = 当前列的列数+1，因为那个data函数设置的问题
        sql.closeResultSet(rs);
        if (sql.priKey.isEmpty())
            oneOfTheColumn.setEditable(false);
        else
            oneOfTheColumn.setEditable(true);
        oneOfTheColumn.setCellFactory(TextFieldTableCell.<data>forTableColumn());
        //事件处理
        oneOfTheColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent t) {
                if (sql.priKey.isEmpty()){
                    ((data) t.getTableView().getItems().get(t.getTablePosition().getRow())) .setValue(t.getOldValue() .toString(),row);
                }
                else {
                    priValueNow.setLength(0);
                    priValueNow .append(getPriData((data) t.getTableView().getItems().get(t.getTablePosition().getRow())));
                    //将修改数据插入数据库
                    System.out.println(" update  " + sql.getTableName() + " set " +t.getTableColumn().getText() +
                            " = '" + t.getNewValue().toString() +"'  where " + priValueNow.toString());
                    int n = sql.doSql_update(" update  " + sql.getTableName() + " set " +t.getTableColumn().getText() +
                            " = '" + t.getNewValue().toString() +"'  where " + priValueNow.toString());
                    System.out.println("修改条数： "+n);
                    
                    if (n>=1) //更新成功
                        //做修改数据时候的动作
                        ((data) t.getTableView().getItems().get(t.getTablePosition().getRow())).setValue(t.getNewValue().toString(),row);
                    //获取当前行数据主键，找到主键对应的那几列
                    tableView_center.refresh();
                }
            }
        });
//        rs = sql.doSql("select '" +columnName + "' from " + sql.getTableName());
//        //获得了一列的数据
//        ObservableList<String> items  = sql.getResultSetInfo(rs,columnName);
    }
    
    private String getPriData(data da ){
        if (sql.priKey.isEmpty())
            return null;
        StringBuffer mid = new StringBuffer();
        for(int i = 0; i < sql.priKey.size();++i){
            mid.append(" " + sql.priKey.get(i) +" = '"+  da.getValue(map.get(sql.priKey.get(i))) + "' ") ;
        }
        System.out.println("提取当前行主键数据 ："+mid.toString());
        return mid.toString();
    }
    ///////////////////////////////////
    public static void main(String[] args) {
        launch(args);
    }
}
