package org.dimigo.cucum;

import java.net.Socket;
import java.util.Scanner;

public class ClientMainClass {
	public static void main(String[] args) {
		String serverIp="127.0.0.1";
		int port=6500;
		try{
			Socket socket=new Socket(serverIp, port);
			SocketClient client=new SocketClient(socket);
			client.start();
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
