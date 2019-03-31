package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StackPane pane = new StackPane();
        Button btOk = new Button("OK");
        btOk.setStyle("-fx-border-color: aqua");
        pane.getChildren().add(btOk);
        
        pane.setRotate(45);
        pane.setStyle("-fx-border-color: red;-fx-background-color: lightgray");
        
        Scene scene = new Scene(pane,200,250);
        primaryStage.setTitle("haha");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
