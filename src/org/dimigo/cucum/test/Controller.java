package org.dimigo.cucum.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.dimigo.cucum.SocketServer;

public class Controller {
	@FXML
	Button Btn_Start;
	@FXML
	Button Btn_End;
	@FXML
	Label IPaddress;
	
	public static int activeUsers;
	public void StartServer() {
		
		try {
			URL url = new URL("http://checkip.amazonaws.com/");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			IPaddress.setText(br.readLine());
			SocketServer server = new SocketServer("");
			server.setSocketServer(server);
			Task task=new Task<Void>() {
				public Void call() {
					server.start();
					return null;
				}
			};
			new Thread(task).start();
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
	
	public void StopServer() {
		Thread[] threadgroup=new Thread[Thread.activeCount()];
		Thread.enumerate(threadgroup);
		System.out.println(threadgroup);
		for(Thread t:threadgroup) {
			t.interrupt();
		}
	}
}
