package org.dimigo.cucum;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.dimigo.cucum.test.Controller;
import org.dimigo.cucum.test.Server;

public class SocketServer {
	private static final int Max=4;
	private List<SocketServerReceiver> clients;
	private List<DataOutputStream> outputs;
	private ServerSocket serverSocket;
	private GameUtil gameUtil;
	private Person[] p;
	private SocketServer socketServer;
	boolean unoProtected=false;
	
	public void setSocketServer(SocketServer socketServer){
		this.socketServer=socketServer;
	}
	public SocketServer(String str) {
		clients = new ArrayList<>();
		gameUtil=new GameUtil();
		p=new Person[]{
			new Person(0),
			new Person(1), 
			new Person(2),
			new Person(3)
		};
		outputs=new ArrayList<>();
	}
	public Person getPerson(int index) {
		return p[index];
	}
	public GameUtil getGameUtil(){
		return gameUtil;
	}
	public void setUp(){
		for(int i=0;i<outputs.size();i++){
			String query=i+" CardList 7 ";
			for(int j=0;j<7;j++){
				Card card=gameUtil.getTopCard();
				p[i].addCardLists(card);
				query+=card.getNumber()+" "+card.getNumber()+" ";
			}
			sendToAll(query);
		}
		gameUtil.setOrder();
	}
	public void start() {
		try {
			Socket socket;

			serverSocket = new ServerSocket(6500);

			gameUtil.setUpCardLists();
			while (true) {
				socket = serverSocket.accept();
				SocketServerReceiver receiver = new SocketServerReceiver(socket, clients.size(), socketServer);
				DataOutputStream output=new DataOutputStream(socket.getOutputStream());
				outputs.add(output);
				receiver.start();
				clients.add(receiver);
				sendMessage(output, clients.size()-1+" Index");
				if(clients.size()==Max){
					setUp();
					gameUtil.setCurrentCard(gameUtil.getTopCard());
					while(true) {
						if(gameUtil.getCurrentCard().getNumber()>9) {
							gameUtil.setCurrentCard(gameUtil.getTopCard());
							continue;
						}
						break;
					}
					sendToAll("Game Start");
					sendToAll("TopCard "+gameUtil.getCurrentCard().getNumber()+" "+gameUtil.getCurrentCard().getColor());
					sendToAll(gameUtil.getTurn()+" Turn");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendToAll(String message) {
		System.out.println(message);
		for(int i=0;i<outputs.size();i++){
			sendMessage(outputs.get(i), message);
		}

	}
	
	public void sendMessage(DataOutputStream out, String message) {
		try {
			out.writeUTF(message);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
