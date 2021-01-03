package org.dimigo.cuccum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.application.Platform;

public class SocketClient extends Thread{
	private DataOutputStream output; // stream to write socket
	private DataInputStream input; // stream to read socket
	private Socket socket; // socket to communicate with server
	private Controller controller; //JavaFX controller
	private int index; // index of Person
	private int[] orders; // define Temp Order
	private int[] tempArray=new int[4];// temp Array
	
	public SocketClient() { // Constructor
		String serverIp="127.0.0.1"; // Ip Setting
		int port=6500; // Port Setting
		this.controller=Main.fxmlLoader.getController(); // Controller Setting
		try{
			orders=new int[4];
			socket=new Socket(serverIp, port);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			controller.setSocket(output); // set Controller Stream
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void run() { // get stream from Server
		try {
			while(input!=null) {
				checkOperation(input.readUTF()); // check What to do
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkOperation(String string){

		System.out.println(string);
		if(string.equals("Game Start")) { //Game Start
			controller.finishLoading();
			return;
		}
		final String[] temp=string.split(" ");
		if(temp[1].equals("Index")) { // Set Index to this Person
			index=Integer.parseInt(temp[0]);
			int cnt=index;
			for(int i=0;i<4;i++) { // Set Temp Order to this Person
				orders[i]=cnt++;
				if(cnt>3) {
					cnt=0;
				}
			}
			for(int i=0;i<4;i++) {
				tempArray[orders[i]]=i;
			}
		}
		else if(temp[1].equals("Turn")) { // Turn Check
			if(Integer.parseInt(temp[0]) == index) { // If this Player's turn
				controller.setIsTurn(true);
			}
			else {
				controller.setIsTurn(false);
			}
			for(int i=0;i<4;i++) { // set Turn
				if(orders[i]==Integer.parseInt(temp[0])) {
					controller.setTurn(i+1);
					break;
				}
			}
		}
		else if(temp[1].equals("CardList")) { // get CardList from the Server
			if(temp[0].equals(String.valueOf(index))) { // If this Player's cardlist
				for(int i=0;i<Integer.parseInt(temp[2]);i++) {
					controller.setDeck(Integer.parseInt(temp[3+i*2]), Integer.parseInt(temp[4+i*2]), i+1);
				}
			} else {
				for(int i=0;i<4;i++) { // set CardNumber
					if(orders[i]==Integer.parseInt(temp[0])) {
						Platform.runLater(new Runnable() {
							public void run() {
								controller.setCardNum(tempArray[Integer.parseInt(temp[0])], Integer.parseInt(temp[2]));
							}
						});
						break;
					}
				}
			}
		}
		else if(temp[0].equals("TopCard")) { // set Top Card 
			controller.setTopCard(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
		}
		else if(temp[1].equals("Success") && temp[0].equals(String.valueOf(index))) { // If this Player success to throw Card
			controller.throwCard2();
		}
		else if(temp[1].equals("Fail") && temp[0].equals(String.valueOf(index))) { // If this Player fail to throw Card
			System.out.println("fail");
		}
		else if(temp[1].equals("Get") && temp[0].equals(String.valueOf(index))) { // If this Player Get Card from Server
			controller.drawUno3(Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
		}
		else if(temp[1].equals("ChangeTurn")) { // If Change Turn by Card
			controller.changeOrder();
		}
		else if(temp[1].equals("Win")) { // If Any player wins
			if(temp[0].equals(String.valueOf(index))) {
				controller.victory();
			}
			else {
				controller.defeat();
			}
		}
	}
	
}
