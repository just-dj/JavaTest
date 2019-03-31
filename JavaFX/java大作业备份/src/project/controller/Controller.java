package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
	
	@FXML private TextField site;
	@FXML private TextField port;
	@FXML private TextField userName;
	@FXML private TextField passWord;
	@FXML private Label signIn;
	
	
	@FXML//没用到
	private  void signIn(){
		String Site = site.getText();
		String Port = port.getText();
		String Name = userName.getText();
		String PassWord = passWord.getText();
		if (Name.equals(""))
			return;
		System.out.println(Site +":" + Port + ":" +Name + ":" +PassWord);
		
		
	}
}
