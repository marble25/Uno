package org.dimigo.cucum;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClientSender {
	private Socket socket;
	public SocketClientSender(Socket socket) {
		this.socket=socket;
	}
	public void sendMsg (DataOutputStream out){
    	try {
    		out.writeUTF(new Scanner(System.in).nextLine());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
