package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

public class Main extends Application {

    private Parent root;
    private ComboBox chooseMode;
    private ListView listView;
    private Label result;
    private ObservableList<String> options;
    private final int MULTIPLE = 0;
    private final int SINGLE = 1;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //初始化变量
        initVariables();
        //模式调整
        chooseMode.getSelectionModel().selectedItemProperty().addListener(new ModeChange());
        
        //item选择
        listView.getSelectionModel().selectedItemProperty().addListener(new ItemSelect());
        
        primaryStage.setTitle("Exercise");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    private class ModeChange implements InvalidationListener {
        @Override
        public void invalidated(Observable arg0) {
            int n =  chooseMode.getSelectionModel().getSelectedIndex();
            switch (n){
                case MULTIPLE:
                    listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                    break;
                case SINGLE:
                    listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    break;
                default:
                    break;
            }
            return;
        }
    }
  
    private class ItemSelect implements InvalidationListener{
        @Override
        public void invalidated(Observable arg0) {
            ObservableList<String> re = listView.getSelectionModel().getSelectedItems();
            StringBuilder mid;
            //提示语做相应改变
            if (chooseMode.getSelectionModel().getSelectedIndex() == 0)
                mid = new StringBuilder("Selected Items are : ");
            else
                mid = new StringBuilder("Selected Item are : ");
            
            for (String str: re)
                mid.append(str + "  ");
            
            result.setText(mid.toString());
        }
    }
    private void initVariables(){
        chooseMode = (ComboBox)root.lookup("#mode");
        chooseMode.setItems(FXCollections.observableArrayList("MULTIPLE","SINGLE"));
        chooseMode.getSelectionModel().select(1);
        
        listView = (ListView)root.lookup("#choice");
        options = FXCollections.observableArrayList("Canada","China","Denmark","France","Germany","India","Norway","Barbados",
                "Belgium","Jordan");
        listView.setItems(options);
        
        result = (Label)root.lookup("#result");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
