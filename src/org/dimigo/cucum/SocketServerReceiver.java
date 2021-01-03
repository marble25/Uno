package org.dimigo.cucum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

class SocketServerReceiver extends Thread {
	private int index;
	private Socket socket;
	private SocketServer socketServer;
	private DataInputStream input;
	
	public Socket getSocket() {
		return socket;
	}
	public SocketServerReceiver(Socket socket, int index, SocketServer socketServer) {
		this.socketServer=socketServer;
		this.socket=socket;
		this.index=index;
		try {
			input=new DataInputStream(socket.getInputStream());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public boolean CardEnableCheck(Card card) {
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
	public void CardCheck(int number, int color)
	{
		if(number==10){ //Skip
			socketServer.getGameUtil().ChangeTurn();
		} else if(number==11){ //Change
			socketServer.getGameUtil().ChangeTurnbyCard();
		} else if(number==12) { //+2
			socketServer.getGameUtil().addTempCardList(2);
			socketServer.getPerson(socketServer.getGameUtil().getNextPersonIndex(index)).addTempCardLists(socketServer.getGameUtil().getTempCardList());
			socketServer.getGameUtil().ChangeTurn();
		} else if(number==14) { //+4
			socketServer.getGameUtil().addTempCardList(4);
			socketServer.sendToAll(index+" Plus "+socketServer.getGameUtil().getNextPersonIndex(index)+" 4");
		}
	}
	public void CheckOperation(String string){
		System.out.println(string);
		String[] temp=string.split(" ");
		if(string.equals("Get")){
			Card card=socketServer.getGameUtil().getTopCard();
			socketServer.getPerson(index).addCardLists(card);
		}
		else if(string.equals("NoChal")) {
			socketServer.getPerson(index).addTempCardLists(socketServer.getGameUtil().getTempCardList());
		} 
		else if(string.equals("Chal")) {
			List<Card> cardLists=socketServer.getPerson(socketServer.getGameUtil().getPrevPersonIndex(index)).getCardList();
			if(!socketServer.getGameUtil().ChalCheck(cardLists)) {
				socketServer.getGameUtil().addTempCardList(2);
				socketServer.getPerson(socketServer.getGameUtil().getPrevPersonIndex(index))
				.addTempCardLists(socketServer.getGameUtil().getTempCardList());
			} else {
				socketServer.getPerson(index).addTempCardLists(socketServer.getGameUtil().getTempCardList());
			}
		}
		else if(string.equals("UNO")) {
			if(socketServer.getPerson(socketServer.getGameUtil().getPrevPersonIndex()).getCardList().size()==1) {
				if(socketServer.getGameUtil().getTurn()==index) {
					socketServer.unoProtected=true;
				} else {
					if(!socketServer.unoProtected) {
						socketServer.getGameUtil().addTempCardList(2);
						socketServer.getPerson(socketServer.getGameUtil().getTurn())
						.addTempCardLists(socketServer.getGameUtil().getTempCardList());
						socketServer.unoProtected=true;
					}
				}
			} else {
				socketServer.getGameUtil().addTempCardList(2);
				socketServer.getPerson(index)
				.addTempCardLists(socketServer.getGameUtil().getTempCardList());
			}
		}
		else if(temp[0].equals("Set")) {
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
		for(int i=0;i<4;i++) {
			List<Card> cards=socketServer.getPerson(i).getCardList();
			String strTemp=i+" CardList "+cards.size()+" ";
			for(Card card:cards) {
				strTemp+=card.getNumber()+" "+card.getColor()+" ";
			}
			socketServer.sendToAll(strTemp);
		}
		socketServer.sendToAll("TopCard "+socketServer.getGameUtil().getCurrentCard().getNumber()+" "+socketServer.getGameUtil().getCurrentCard().getColor());
		socketServer.sendToAll(socketServer.getGameUtil().ChangeTurn()+" Turn");
	}
	public void run() {
        try {
            while (input != null) {
            	CheckOperation(input.readUTF());
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

}
