package sample;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    private Parent root;
    private Slider sliderR;
    private Slider sliderG;
    private Slider sliderB;
    private List<Rectangle> recList ;
    private Rectangle centerRec;
    private Button save;
    private Button clear;
    private int count;
    private DoubleProperty r;
    private DoubleProperty g;
    private DoubleProperty b;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        
        //初始化变量
        initialize();
        
        //两个按钮的点击事件处理
        save.setOnAction((ActionEvent event)->{save();});
        clear.setOnAction((ActionEvent event)->{clear();});
        primaryStage.setTitle("Color the Pane");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    //保存当前颜色
    private void save(){
        recList.get(count).setFill(Color.rgb(r.getValue().intValue(),g.getValue().intValue(),b.getValue().intValue()));
        count ++;
        if (count >= 12)
            count %= 12;
    }
    //清空所有数据
    private void clear(){
        count = 0;
        for (int i = 0;i <12;++i){
            recList.get(i).setFill(Color.WHITE);
        }
        centerRec.setFill(Color.WHITE);
        sliderR.setValue(sliderR.getMax());
        sliderG.setValue(sliderG.getMax());
        sliderB.setValue(sliderB.getMax());
    }
    //初始化变量
    private void  initialize(){
        r = new SimpleDoubleProperty();
        g = new SimpleDoubleProperty();
        b = new SimpleDoubleProperty();
        //三个滑动条
       sliderR = (Slider)root.lookup("#sliderR");
       sliderG = (Slider)root.lookup("#sliderG");
       sliderB = (Slider)root.lookup("#sliderB");
        setSilderStyle(sliderR,r);
        setSilderStyle(sliderG,g);
        setSilderStyle(sliderB,b);
        //载入十二个小矩形
        recList =new LinkedList<Rectangle>();
        for (int i = 0;i  < 12; ++i){
            recList.add((Rectangle)root.lookup("#rectangle" + i) );
        }
        
        centerRec = (Rectangle) root.lookup("#centerRec");
        save = (Button)root.lookup("#save");
        clear = (Button)root.lookup("#clear");
        count = 0;
    }
    
    private void setSilderStyle(Slider s,DoubleProperty value){
        s.setOrientation(Orientation.VERTICAL);
        s.setMax(255);
        s.setPrefSize(50, 310);
        s.setMajorTickUnit(51);
        s.setMinorTickCount(5);
        s.setValue(s.getMax());
        s.setShowTickLabels(true);
        s.setShowTickMarks(true);
        value.bind(s.valueProperty());
        //当数值变动的时候。改变中间区域颜色
        s.valueProperty().addListener((Observable observable) ->{
            centerRec.setFill(Color.rgb(r.getValue().intValue(),g.getValue().intValue(),b.getValue().intValue()));
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
