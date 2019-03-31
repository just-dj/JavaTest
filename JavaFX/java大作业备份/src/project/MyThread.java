package project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MyThread implements  Runnable{
	
	private ImageView image;
	
	MyThread(ImageView image){
		this.image = image;
	}
	
	@Override
	public void run() {
		image.setVisible(true);
	}
	
	
}
