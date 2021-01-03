package org.dimigo.cuccum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static FXMLLoader fxmlLoader; // define FXMLLoader by static way
    @Override
    public void start(Stage primaryStage) throws Exception{ // New Window
    	fxmlLoader=new FXMLLoader(getClass().getResource("game.fxml"));
    	Parent root = (Parent)fxmlLoader.load();
        primaryStage.setTitle("UNO");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
