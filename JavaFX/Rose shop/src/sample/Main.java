package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.awt.*;

public class Main extends Application {
	private GridPane contain;
	private CheckBox[] check = new CheckBox[3];
	private int[] value = new int[3];//记录单价
	private TextField[] numberEdit = new TextField[3];//数量输入框
	private TextField[] resultEdit = new TextField[3];//结果输出框
	private int[] result = new int[3];//每个项的计算结果
	private Button count; //合计按钮
	private Button clear; //清空按钮
	private TextField all; //总金额编辑框
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        
        contain = (GridPane)root.lookup("#contain");
	    contain.setHgap(15);
	    contain.setVgap(15);
        for (int i = 0; i < 3;++i){//获取控件
	        numberEdit[i] = (TextField) root.lookup("#numberEdit" + i);//数量编辑框
	        resultEdit[i] = (TextField)root.lookup("#result" + i);//结果编辑框
	        value[i] = Integer.parseInt(((Label)root.lookup("#value" + i)).getText());//单价读取
	        check[i] = (CheckBox)root.lookup("#check" + i);//选择框
        }
	    count = (Button)root.lookup("#count");//计算按钮
	    clear = (Button)root.lookup("#clear");//清除按钮
	    all = (TextField)root.lookup("#all");//总价编辑框
	    for (int i = 0;i < 3;++i)//为数量输入框绑定事件
	    	actionBind(i);
	    count.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
				int allMoney = calculateAll();
				if (allMoney != 0)
					all.setText(allMoney + "");
		    }
	    });
	    clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
			for (int i = 0;i < 3;++i){
				numberEdit[i].setText("");
				resultEdit[i].setText("");
				check[i].setSelected(false);
				result[i] = 0;
			}
			all.setText("");
		    }
	    });
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
	
    public static void main(String[] args) {
        launch(args);
    }
	
	private void actionBind(int n){
		numberEdit[n].focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (oldValue ==true && newValue == false){//失去焦点
					Integer num = null;
					try{
						num = Integer.parseInt(numberEdit[n].getText());
						result[n] = value[n] * num;
						resultEdit[n].setText(result[n] + "");
					}catch (Exception e){
						result[n] = 0;
						resultEdit[n].setText("");
					}
				}
			}
		});
	}
	
	private int calculateAll(){
		int allMoney = 0;
		for (int i = 0;i < 3;++i){
			if(check[i].isSelected())
				allMoney+= result[i];
		}
		return allMoney;
	}
}
