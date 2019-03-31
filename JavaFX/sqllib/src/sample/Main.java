package sample;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.model.Student;


public class Main extends Application {
    
    private String name = "root";
    private String passWord = "521947";
    private String site = "localhost";
    private String port = "3306";
    private String databaseName = "demo";
    
    private Parent root;
    private ComboBox<String> comboBox;
    private Button button;
    
    private TableView<Student> tableView;
    private TableColumn id;
    private TableColumn sName;
    private TableColumn sex;
    private TableColumn major;
    private TableColumn cla;
    private TableColumn telephone;
    private TableColumn state;
    private TableColumn instructor;
    
    
    private ObservableList<String> list;
    private StringProperty selectItem;
    private ObservableList<Student> data;
    //处理sql连接的类
    private Sql mysql;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("查询");
        
        initPara();
    
        button.setOnAction(e->{
            if(selectItem != null)
                if (selectItem.getValue().equals("待定") || selectItem.getValue().equals("选定")){
                    data = mysql.doSql("select * from studentdata where state like '%" + selectItem.getValue() + "%'" );
                    tableView.setItems(data);
                }
        });
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    //初始化变量
    private void initPara() {
        tableView = (TableView<Student>)root.lookup("#table");
        button = (Button)root.lookup("#button");
        comboBox = (ComboBox<String>)root.lookup("#com");
        list = FXCollections.observableArrayList(new String[]{"待定","选定"});
        comboBox.setItems(list);
        
        id = new TableColumn("学号");
        sName = new TableColumn("姓名");
        sex = new TableColumn("性别");
        major = new TableColumn("专业");
        cla = new TableColumn("班级");
        cla.setMinWidth(100);
        telephone = new TableColumn("电话");
        telephone.setMinWidth(150);
        state = new TableColumn("状态");
        instructor = new TableColumn("导师");
        id.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
        sName.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        sex.setCellValueFactory(new PropertyValueFactory<Student,String>("sex"));
        major.setCellValueFactory(new PropertyValueFactory<Student,String>("major"));
        cla.setCellValueFactory(new PropertyValueFactory<Student,String>("cla"));
        telephone.setCellValueFactory(new PropertyValueFactory<Student,String>("telephone"));
        state.setCellValueFactory(new PropertyValueFactory<Student,String>("state"));
        instructor.setCellValueFactory(new PropertyValueFactory<Student,String>("instructor"));
        tableView.getColumns().addAll(id,sName,sex,major,cla,telephone,state,instructor);
        
        selectItem = new SimpleStringProperty();
        selectItem.bind(comboBox.getSelectionModel().selectedItemProperty());
        
        mysql = new Sql(name,passWord,site,port,databaseName);
        mysql.getConnection();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
