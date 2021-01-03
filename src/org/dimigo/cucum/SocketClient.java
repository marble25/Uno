package org.dimigo.cucum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient extends Thread{
	private DataOutputStream output;
	private DataInputStream input;
	private Socket socket;
	private int index;
	
	public SocketClient(Socket socket){
		this.socket=socket;
		try {
			input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void sendMsg (String msg){
    	try {
    		output.writeUTF(msg);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
	public void checkOperation(String string){
		System.out.println(string);
		if(string.equals("Game Start")) {
			return;
		}
		String[] temp=string.split(" ");
		if(temp[1].equals("Index")) {
			this.index=Integer.parseInt(temp[0]);
			return;
		}
		else if(temp[1].equals("Turn") && Integer.parseInt(temp[0]) == index) {
			sendMsg(new Scanner(System.in).nextLine());
		}
		else if(temp[1].equals("Plus")) {
			if(Integer.parseInt(temp[0])==index) {
				System.out.println("Plus "+temp[2]);
			}
		}
		else if(temp[1].equals("CardList") || temp[0].equals(String.valueOf(index))) {
			System.out.println();
		}
	}
	public void run(){
		while (input != null) {
			try {
				checkOperation(input.readUTF());
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
