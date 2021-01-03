package org.dimigo.cucum;

import java.net.Socket;

public class ServerMainClass {
	
	public static void main(String[] args) {
		try {
			SocketServer server = new SocketServer("");
			server.setSocketServer(server);
			server.start();
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
	}

}
