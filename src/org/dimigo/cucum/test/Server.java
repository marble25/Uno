package org.dimigo.cucum.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.dimigo.cucum.SocketServer;

public class Server extends Application{
	public static void main(String[] args) {
		launch();
		
	}
	
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("Server.fxml"));
		Scene scene = new Scene(root, 600, 400);
		
		stage.setTitle("Uno ¼­¹ö");
		stage.setScene(scene);
		stage.show();
	}

}
