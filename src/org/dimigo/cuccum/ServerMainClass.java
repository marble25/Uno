package org.dimigo.cuccum;

public class ServerMainClass {
	
	public static void main(String[] args) { //Starting Server
		try {
			SocketServer server = new SocketServer();
			server.start();
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
}
