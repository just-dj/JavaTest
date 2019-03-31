package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private BorderPane borderPane;
    
    private  MenuBar menuBar;
    //fileMenu以及子控件
    private Menu fileMenu;
    private MenuItem exit;
    //shapeMenu以及子控件
    private  Menu shapesMenu;
    private MenuItem rectangle;
    private MenuItem polygon;
    private Menu drawingModel;
    private RadioMenuItem fill;
    private RadioMenuItem unfill;
    //多边形和矩形
    private Polygon polygonC;
    private Rectangle rectangleC;
    @Override
    public void start(Stage primaryStage) throws Exception{
        borderPane = new BorderPane();
        menuBar = new MenuBar();
        //fileMenu布局
        buildFileMenu();
        //shapeMenu布局
        buildShapeMenu();
        menuBar.getMenus().addAll(fileMenu,shapesMenu);
        //绘制polygon
        drawPolygon();
        //绘制rectangle
        drawRectangle();
        borderPane.setTop(menuBar);
        borderPane.setCenter(polygonC);
        borderPane.setCenter(rectangleC);
        
        primaryStage.setTitle("Watch me");
        primaryStage.setScene(new Scene(borderPane, 600, 600));
        primaryStage.show();
    }
    //fileMenu布局
    private void buildFileMenu(){
        fileMenu = new Menu("File");
        exit = new MenuItem("exit");
        //事件监听
        fileMenu.getItems().add(exit);
        
        exit.setOnAction(e->System.exit(0));
        return;
    }
    //shapeMenu布局
    private void buildShapeMenu(){
        //实例化
        shapesMenu = new Menu("shapes");
        rectangle = new MenuItem("retangle");
        polygon = new MenuItem("polygon");
        drawingModel = new Menu("Drawing Model");
        fill = new RadioMenuItem("fill");
        unfill = new RadioMenuItem("unfill");
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(fill, unfill);
        
        drawingModel.getItems().addAll(fill,unfill);
        shapesMenu.getItems().addAll(rectangle,polygon,drawingModel);
        //判断fll状态
        fill.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) ->{
                if (fill.isSelected()){
                    polygonC.setFill(Color.BLACK);
                    rectangleC.setFill(Color.BLACK);
                }else {
                    polygonC.setFill(Color.WHITE);
                    rectangleC.setFill(Color.WHITE);
                }
            });
        //点击事件监听
        rectangle.setOnAction((e)-> {borderPane.setCenter(rectangleC); });
        polygon.setOnAction((e)->{ borderPane.setCenter(polygonC);});
        return;
    }
    //绘制正六边形
    private void drawPolygon(){
        polygonC = new Polygon();
        polygonC.setStroke(Color.BLACK);
        polygonC.setFill(Color.WHITE);
        
        ObservableList<Double> list = polygonC.getPoints();
        final double WIDTH = 200, HEIGHT = 200;
        double centerX = WIDTH / 2, centerY = HEIGHT / 2;
        double radius = Math.min(WIDTH, HEIGHT) * 0.4;
        //计算每个点的坐标
        for (int i = 0; i < 6; i++) {
            list.add(centerX + radius * Math.cos(2 * i * Math.PI / 6));
            list.add(centerY - radius * Math.sin(2 * i * Math.PI / 6));
        }
    }
    //绘制矩形
    private void drawRectangle(){
        rectangleC = new Rectangle(200,50);
        rectangleC.setStroke(Color.BLACK);
        rectangleC.setFill(Color.WHITE);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
