package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

import java.util.Random;

public class GetChar implements Runnable {
	
	//public StringProperty oneChar = new SimpleStringProperty();
	public boolean isRun = true;
	private TextField textField;
	
	public GetChar(TextField textField){
		this.textField =  textField;
	}
	
	private void setChar(){
		int max = 91;
		int min = 65;
		Random random = new Random();
		char mid = (char) (random.nextInt(max)%(max - min) + min);
		textField.setText(mid + "");
	}
	
	@Override
	public void run(){
			try{
				while(isRun){
					Thread.sleep(100);
					setChar();
				}
			}catch (Exception e){
			}
	}
}
