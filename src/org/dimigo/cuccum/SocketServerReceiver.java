package org.dimigo.cuccum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

class SocketServerReceiver extends Thread {
	private int index; // Index of Person
	private Socket socket; // Socket which is connected to Client
	private SocketServer socketServer; // SocketServer Object to get access to many functions and variables
	private DataInputStream input; // input to read bytes
	private DataOutputStream output; // output to write bytes
	
	public SocketServerReceiver(Socket socket, int index, SocketServer socketServer) { // Constructor using socket, index, socketServer
		this.socketServer=socketServer;
		this.socket=socket;
		this.index=index;
		try {
			input=new DataInputStream(socket.getInputStream());
			output=new DataOutputStream(socket.getOutputStream());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public boolean CardEnableCheck(Card card) { // If Setting Card is Enable
		System.out.println(card);
		System.out.println(socketServer.getGameUtil().getCurrentCard());
		if(card.getNumber()==socketServer.getGameUtil().getCurrentCard().getNumber()
				|| card.getColor()==socketServer.getGameUtil().getCurrentCard().getColor()) {
			return true;
		}
		else if(socketServer.getGameUtil().getCurrentCard().getNumber()==15) {
			return true;
		}
		else if(card.getNumber()==14 || card.getNumber()==13) {
			if(card.getColor()==socketServer.getGameUtil().getCurrentCard().getColor()) {
				return false;
			}
			return true;
		}
		return false;
	}
	public void CardCheck(int number, int color) // Checking Special Card Functions
	{
		if(number==10){ //Skip
			socketServer.getGameUtil().ChangeTurn();
		} else if(number==11){ //Change
			socketServer.getGameUtil().ChangeTurnbyCard();
			socketServer.sendToAll(index+" ChangeTurn");
		} else if(number==12) { //+2
			socketServer.getGameUtil().addTempCardList(2);
			socketServer.getPerson(socketServer.getGameUtil().getNextPersonIndex(index)).addTempCardLists(socketServer.getGameUtil().getTempCardList());
			socketServer.getGameUtil().ChangeTurn();
		} else if(number==13) { //+4
			socketServer.getGameUtil().addTempCardList(4);
			socketServer.getPerson(socketServer.getGameUtil().getNextPersonIndex(index)).addTempCardLists(socketServer.getGameUtil().getTempCardList());
			socketServer.getGameUtil().ChangeTurn();
		}
	}
	public void CheckOperation(String string){ // Check What to do from client message
		System.out.println(string);
		String[] temp=string.split(" ");
		if(string.equals("Get")){ // Get
			Card card=socketServer.getGameUtil().getTopCard();
			socketServer.getPerson(index).addCardLists(card);
			socketServer.sendToAll(index+" Get "+card.getNumber()+" "+card.getColor());
		}
		else if(temp[0].equals("Set")) { // Set Card to the Top
			Card card=new Card(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
			if(CardEnableCheck(card)) {
				socketServer.sendToAll(index+" Success");
			} else {
				socketServer.sendToAll(index+" Fail");
				socketServer.sendToAll(index+" Turn");
				return;
			}
			if(temp[1].equals("13") || temp[1].equals("14")) {
				socketServer.getPerson(index).removeCardLists(new Card(Integer.parseInt(temp[1]), 4));
			}
			else {
				socketServer.getPerson(index).removeCardLists(card);
			}
			
			socketServer.getGameUtil().setCurrentCard(card);
			
			CardCheck(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
		}
		for(int i=0;i<4;i++) { // Winner Check
			if(socketServer.getPerson(i).getCardList().isEmpty()) {
				socketServer.sendToAll(i+" Win");
				return;
			}
		}
		for(int i=0;i<4;i++) { // Send CardList to All
			List<Card> cards=socketServer.getPerson(i).getCardList();
			String strTemp=i+" CardList "+cards.size()+" ";
			for(Card card:cards) {
				strTemp+=card.getNumber()+" "+card.getColor()+" ";
			}
			socketServer.sendToAll(strTemp);
		}
		socketServer.sendToAll("TopCard "+socketServer.getGameUtil().getCurrentCard().getNumber()+" "+socketServer.getGameUtil().getCurrentCard().getColor());
		socketServer.sendToAll(socketServer.getGameUtil().ChangeTurn()+" Turn"); // Send Turn and Top Card
	}
	public void sendMessage(String message) { // write message to client
		try {
			output.writeUTF(message+"\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run() { // get message from client
        try {
            while (input != null) {
            	CheckOperation(input.readUTF());
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

}
