package sample;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

public class WatchMe extends Application {

    private IntegerProperty centerX = new SimpleIntegerProperty();
    private IntegerProperty centerY = new SimpleIntegerProperty();
    private Ellipse leftC;
    private Ellipse rightC;
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        // Create a pane
        Pane pane = new Pane();
        
        // 创建椭圆对象
        Ellipse leftEye = new Ellipse(0, 0, 20, 40);
        Ellipse rightEye = new Ellipse(0, 0,20, 40);
        leftC = new Ellipse(0, 0, 6, 6);
        rightC = new Ellipse(0, 0, 6, 6);
        
        //椭圆对象样式
        leftEye.setStroke(Color.color(0,0,0,1));
        leftEye.setFill(Color.WHITE);
        rightEye.setStroke(Color.color(0,0,0,1));
        rightEye.setFill(Color.WHITE);
        rightC.setFill(Color.BLACK);
        leftC.setFill(Color.BLACK);
    
        //添加对象到面板
        pane.getChildren().add(leftEye);
        pane.getChildren().add(rightEye);
        pane.getChildren().add(leftC);
        pane.getChildren().add(rightC);
        
        //确保拖动时组件能够居中 ，并且功能正常
        leftEye.centerXProperty().bind(pane.widthProperty().divide(2) .subtract(40));
        leftEye.centerYProperty().bind(pane.heightProperty().divide(2));
        rightEye.centerXProperty().bind(pane.widthProperty().divide(2) .add(40));
        rightEye.centerYProperty().bind(pane.heightProperty().divide(2));
        
                //因为两个小黑点的位置后面还需要自己去调整 所以不能绑定
        pane.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue)-> {
                changeCenter(leftEye.getCenterX(),leftEye.getCenterY(),rightEye.getCenterX(),rightEye.getCenterY());
            });
        pane.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->{
                changeCenter(leftEye.getCenterX(),leftEye.getCenterY(),rightEye.getCenterX(),rightEye.getCenterY());
            });
        
        //获得当前面板中心点坐标
        centerX.bind(pane.widthProperty().divide(2));
        centerY.bind(pane.heightProperty().divide(2));
        
        //鼠标移动事件
        pane.setOnMouseMoved(e -> {
            if (e.getX()>= (centerX.getValue() - 60) && e.getX() <= (centerX.getValue() + 60) ){
                if (e.getY() <= (centerY.getValue() - 40)  ){
                    changeCenter(centerX.getValue() - 40,centerY.getValue() - 34,centerX.getValue() + 40,centerY.getValue() - 34);
                }else if (e.getY() >= (centerY.getValue() + 40) ){
                    changeCenter(centerX.getValue() - 40,centerY.getValue() + 34,centerX.getValue() + 40,centerY.getValue() + 34);
                }
            }
            else if (e.getY()>= (centerY.getValue() - 40)  && e.getY() <= (centerY.getValue() + 40)  ){
                if (e.getX() <= (centerX.getValue() - 60) ){
                    changeCenter(centerX.getValue() - 54,centerY.getValue(),centerX.getValue() + 26,centerY.getValue());
                }else if (e.getX() >= (centerX.getValue() + 60)){
                    changeCenter(centerX.getValue() - 26,centerY.getValue(),centerX.getValue() + 54,centerY.getValue());
                }
            }
        });
    
        //鼠标移出事件
        pane.setOnMouseExited((MouseEvent arg0) -> {
                changeCenter(pane.getWidth()/2 - 40,pane.getHeight()/2,pane.getWidth()/2 + 40,pane.getHeight()/2);
            });
        
        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setTitle("ShowEye");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //调整两个小黑点的位置
    public void changeCenter(double leftCX,double leftCY,double rightCX,double rightCY){
        leftC.setCenterX(leftCX);
        leftC.setCenterY(leftCY);
        rightC.setCenterX(rightCX);
        rightC.setCenterY(rightCY);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
