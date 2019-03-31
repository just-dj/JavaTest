package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
    
//        BorderPane border = new BorderPane();
//        border.setPadding(new Insets(20, 0, 20, 20));
//
//        Button btnAdd = new Button("Add");
//        Button btnDelete = new Button("Delete");
//        Button btnMoveUp = new Button("Move Up");
//        Button btnMoveDown = new Button("Move Down");
//
//        double MAX_VALUE = 80;
//        btnAdd.setMaxWidth(MAX_VALUE);
//        btnDelete.setMaxWidth(MAX_VALUE);
//        btnMoveUp.setMaxWidth(MAX_VALUE);
//        btnMoveDown.setMaxWidth(MAX_VALUE);
//
//        VBox vbButtons = new VBox();
//        vbButtons.setSpacing(10);
//        vbButtons.setPadding(new Insets(0, 20, 10, 20));
//        vbButtons.getChildren().addAll(btnAdd, btnDelete, btnMoveUp, btnMoveDown);
    
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(12);
    
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        grid.getColumnConstraints().add(column1);
    
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().add(column2);
    
        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10.0);
        hbButtons.setAlignment(Pos.CENTER);
        
        Button btnSubmit = new Button("Submit");
        Button btnClear = new Button("Clear");
        Button btnExit = new Button("Exit");
        btnSubmit.setStyle("-fx-font-size: 15pt;");
    
        Label lblName = new Label("User name:");
        TextField tfName = new TextField();
        Label lblPwd = new Label("Password:");
        PasswordField pfPwd = new PasswordField();
    
        hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit);
        grid.add(lblName, 0, 0);
        grid.add(tfName, 1, 0);
        grid.add(lblPwd, 0, 1);
        grid.add(pfPwd, 1, 1);
        grid.add(hbButtons, 0, 2, 2, 1);
        grid.setAlignment(Pos.CENTER);
        
        primaryStage.setScene(new Scene(grid,400,400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
