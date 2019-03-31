package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("test");
        //获得组件
        ListView listView = (ListView) root.lookup("#listView");
        Label test3 = (Label)root.lookup("#test3");
        
        //设置监听
        listView.setCellFactory(TextFieldListCell.forListView());
//        listView.setEditable(true);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on " + listView.getSelectionModel().getSelectedIndex());
                test3.setText( listView.getSelectionModel().getSelectedIndex() + "      "+listView.getSelectionModel()
                        .getSelectedItem().toString());
            }
        });
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
