package org.dimigo.cucum.server;

import org.dimigo.cucum.SocketServer;

public class Server {
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
