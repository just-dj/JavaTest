package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
	    hbox.getStyleClass().add("hbox");
        border.setTop(hbox);
        border.setLeft(addVBox());
        addStackPane(hbox); //添加一个堆栈面板到上方区域的HBox中
//
	    border.setCenter(addAnchorPane(addGridPane()));
        border.setRight(addFlowPane());
	
	    Scene scene = new Scene(border);
	    scene.getStylesheets().add("sample/testPane.css");
	    
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	public AnchorPane addAnchorPane(GridPane grid){
		AnchorPane anchorpane = new AnchorPane();
		
		Button buttonSave = new Button("Save");
		Button buttonCancel = new Button("Cancel");
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(0, 10, 10, 10));
		hb.setSpacing(10);
		hb.getChildren().addAll(buttonSave, buttonCancel);
		
		anchorpane.getChildren().addAll(grid,hb); //添加来自例1-5 的GridPane
		AnchorPane.setBottomAnchor(hb, 8.0);
		AnchorPane.setRightAnchor(hb, 5.0);   //被固定到右下角
		AnchorPane.setTopAnchor(grid, 10.0);
		
		
		anchorpane.getStyleClass().add("pane");
		return anchorpane;
	}
	
	private FlowPane addFlowPane() {
		
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(5, 0, 5, 0));
		flow.setVgap(4);
		flow.setHgap(4);
		flow.setPrefWrapLength(170); // preferred width allows for two columns
		flow.setStyle("-fx-background-color: DAE6F3;");
		
		ImageView pages[] = new ImageView[8];
		for (int i=0; i<8; i++) {
			pages[i] = new ImageView(
					new Image(Main.class.getResourceAsStream(
							"graphics/chart_"+(i+1)+".png")));
			flow.getChildren().add(pages[i]);
		}
		flow.getStyleClass().addAll("pane","flow_tile");
		return flow;
	}
 
	private GridPane addGridPane() {
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		
		// Category in column 2, row 1
		Text category = new Text("Sales:");
		category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(category, 1, 0);
		
		// Title in column 3, row 1
		Text chartTitle = new Text("Current Year");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(chartTitle, 2, 0);
		
		// Subtitle in columns 2-3, row 2
		Text chartSubtitle = new Text("Goods and Services");
		grid.add(chartSubtitle, 1, 1, 2, 1);
		
		// House icon in column 1, rows 1-2
		ImageView imageHouse = new ImageView(
				new Image(Main.class.getResourceAsStream("graphics/house.png")));
		grid.add(imageHouse, 0, 0, 1, 2);
		
		// Left label in column 1 (bottom), row 3
		Text goodsPercent = new Text("Goods\n80%");
		GridPane.setValignment(goodsPercent, VPos.BOTTOM);
		grid.add(goodsPercent, 0, 2);
		
		// Chart in columns 2-3, row 3
		ImageView imageChart = new ImageView(
				//new Image(Main.class.getResourceAsStream("/graphics/piechart.png")));//工程目录
				new Image(Main.class.getResourceAsStream("graphics/piechart.png")));//当前目录
		grid.add(imageChart, 1, 2, 2, 1);
		
		// Right label in column 4 (top), row 3
		Text servicesPercent = new Text("Services\n20%");
		GridPane.setValignment(servicesPercent, VPos.TOP);
		grid.add(servicesPercent, 3, 2);

//        grid.setGridLinesVisible(true);
		grid.getStyleClass().add("grid");
		return grid;
	}
 
	public void addStackPane(HBox hb){
		StackPane stack = new StackPane();
		Rectangle helpIcon = new Rectangle(30.0, 25.0);
		helpIcon.setFill(new LinearGradient(0,0,0,1, true, CycleMethod.NO_CYCLE,
				new Stop[]{
						new Stop(0, Color.web("#4977A3")),
						new Stop(0.5, Color.web("#B0C6DA")),
						new Stop(1,Color.web("#9CB6CF"))}));
		helpIcon.setStroke(Color.web("#D0E6FA"));
		helpIcon.setArcHeight(3.5);
		helpIcon.setArcWidth(3.5);
		
		Text helpText = new Text("?");
		helpText.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		helpText.setFill(Color.WHITE);
		helpText.setStroke(Color.web("#7080A0"));
		
		stack.getChildren().addAll(helpIcon, helpText);
		stack.setAlignment(Pos.CENTER_RIGHT); //右对齐节点
		
		StackPane.setMargin(helpText, new Insets(0, 10, 0, 0)); //设置问号居中显示
		hb.getChildren().add(stack); // 将StackPane添加到HBox中
		HBox.setHgrow(stack, Priority.ALWAYS); // 将HBox水平多余的所有空间都给StackPane，这
																		// 样前面设置的右对齐就能保证问号按钮在最右边
	}
 
	public VBox addVBox(){
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10)); //内边距
		vbox.setSpacing(8); //节点间距
		
		Text title = new Text("Data");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		vbox.getChildren().add(title);
		
		Hyperlink options[] = new Hyperlink[] {
				new Hyperlink("Sales"),
				new Hyperlink("Marketing"),
				new Hyperlink("Distribution"),
				new Hyperlink("Costs")};
		
		for (int i=0; i<4; i++){
			VBox.setMargin(options[i], new Insets(0, 0, 0, 8)); //为每个节点设置外边距
			vbox.getChildren().add(options[i]);
		}
		vbox.getStyleClass().addAll("pane","vbox");
		return vbox;
	}
 
	public HBox addHBox(){
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12)); //节点到边缘的距离
		hbox.setSpacing(10); //节点之间的间距
		
		Button buttonCurrent = new Button("Current");
		buttonCurrent.setPrefSize(100, 20);
		
		Button buttonProjected = new Button("Projected");
		buttonProjected.setPrefSize(100, 20);
		hbox.getChildren().addAll(buttonCurrent, buttonProjected);
		
		
		return hbox;
	}

    public static void main(String[] args) {
        launch(args);
    }
}
