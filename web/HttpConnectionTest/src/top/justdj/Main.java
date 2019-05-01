package top.justdj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception{
        URL url = new URL("https://www.baidu.com/");
    
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        
        connection.connect();
        Map<String,List<String>> header = connection.getHeaderFields();
        for (String key:header.keySet()){
            System.out.println(key + " : " + header.get(key));
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
        String string = null;
        while ((string = bufferedReader.readLine()) != null){
            System.out.println(string);
        }
        connection.disconnect();
    }
}
