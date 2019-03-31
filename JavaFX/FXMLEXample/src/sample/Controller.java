package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

public class Controller {
	@FXML private PasswordField passwordField;
	@FXML private Text actiontarget; //@FXML注解用于标记由FXML使用的非公开的控制器成员属性和事件处理方法
	@FXML protected void handleSubmitButtonAction(ActionEvent event) {
		actiontarget.setText("哈哈？");
	}
}
