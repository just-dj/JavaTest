package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    private TextField textField0;
    private TextField textField1;
    private TextField textField2;
    private Button button;
    private Parent root;
    private ExecutorService executor;
    private GetChar getChar0;
    private GetChar getChar1;
    private GetChar getChar2;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("线程控制");
        //初始化变量
        initVar();
        
        button.setOnAction((ActionEvent event) ->{
            if (button.getText().equals("开始")){
                executor = Executors.newFixedThreadPool(10);
                getChar0 = new GetChar(textField0);
                getChar1 = new GetChar(textField1);
                getChar2 = new GetChar(textField2);

                executor.execute(getChar0);
                executor.execute(getChar1);
                executor.execute(getChar2);
                button.setText("停止");
            }else {
                getChar0.isRun = false;
                getChar1.isRun = false;
                getChar2.isRun = false;
                button.setText("开始");
            }
        });
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initVar(){
        textField0 = (TextField)root.lookup("#textFiled0");
        textField1 = (TextField)root.lookup("#textFiled1");
        textField2 = (TextField)root.lookup("#textFiled2");
        button = (Button)root.lookup("#button");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
