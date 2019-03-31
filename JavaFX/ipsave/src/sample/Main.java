package sample;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

public class Main extends Application {
    
    
    
    private Parent root;
    private ListView normal ;
    private ListView wlan;
    private ListView virtul;
    private TextField userName;
    private TextField res;
    private Button up;
    
    private ObservableList<String> normalValue;
    private ObservableList<String> wlanValue;
    private ObservableList<String> virtualValue;
    
    private Sql mysql;
    private  Enumeration<NetworkInterface> nifs = null;
    private String result = null;
    private SimpleStringProperty mid;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("ip_show_justdj");
        
        initPara();
        getNetInfo();
        setValue();
        
        mysql.getConnection();
        up.setOnAction(e->{
            if (mid.getValue() != null && !mid.getValue().equals("")){
                mysql.insert(mid.getValue(),result);
                res.setText("http://www.justdj.top/getIp.php?name="+mid.getValue());
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    
    public String getWinIp(){
        //将doc命令查询出来的每一行信息存储在List集合中
        List<String> list = new ArrayList<String>();;
        int position = 0;
        Runtime runTime = Runtime.getRuntime();
        
        try {
            Process p = runTime.exec("ipconfig");
            BufferedReader pw = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            //声明一些中间变量
            String str = "";
            String mid = new String("   默认网关. . . . . . . . . . . . . : ");
            String empty = new String("   默认网关. . . . . . . . . . . . . : 0.0.0.0");
            //mid = new String(mid.getBytes("GBK"),"UTF-8");
            //empty = new String(empty.getBytes("GBK"),"UTF-8");
            
            while ((str = pw.readLine()) != null) {
                if(str.length()>0 && !str.isEmpty()){
                    
                    String gbk = new String(str.getBytes("GBK"),"GBK");
                    list.add(gbk);
                    //System.out.println(gbk + "--------"+list.size());
                    if (gbk.contains(mid) && !gbk.equals(mid) && !gbk.equals(empty) && gbk.length()>= 45){
                        position  = list.size();
                       // System.out.println("666666666" + gbk + "            aa"+gbk.length());
                    }
                    list.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String result = list.get(position -4 );
        result = result.split(". . . . . . . . . . . . : ")[1];
        return result;
    }
    
    private void initPara() {
        normalValue = FXCollections.observableArrayList();
        wlanValue = FXCollections.observableArrayList();
        virtualValue = FXCollections.observableArrayList();
        
        res = (TextField)root.lookup("#as");
        userName = (TextField)root.lookup("#userName") ;
        mid = new SimpleStringProperty();
        mid.bind(userName.textProperty());
        up = (Button)root.lookup("#up");
        normal  = (ListView)root.lookup("#normalListView");
        normal.setCellFactory(TextFieldListCell.forListView());
        wlan =  (ListView)root.lookup("#wlanListView");
        wlan.setCellFactory(TextFieldListCell.forListView());
        virtul = (ListView)root.lookup("#virtulListView");
        virtul.setCellFactory(TextFieldListCell.forListView());
        
        mysql = new Sql("bdm309127484","shannu1997","bdm309127484.my3w.com","3306");
    }
    
    private void getNetInfo(){
        // 获得本机的所有网络接口
        try{
            nifs = NetworkInterface.getNetworkInterfaces();
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
        while (nifs.hasMoreElements()) {
            NetworkInterface nif = nifs.nextElement();
            
            // 获得与该网络接口绑定的 IP 地址，一般只有一个
            Enumeration<InetAddress> addresses = nif.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                
                if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
                    if(!nif.isVirtual()){
                        if(nif.getName().contains("wlan")){
                            wlanValue.add(addr.getHostAddress());
                        }else if (nif.getName().contains("eth")){
                            virtualValue.add(addr.getHostAddress());
                        }else {
                        }
                    }
                }
            }
        }
    }
    
    private void setValue(){
        result = getWinIp();
        normalValue.add(result);
        normal.setItems(normalValue);
        wlan.setItems(wlanValue);
        virtul.setItems(virtualValue);
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
