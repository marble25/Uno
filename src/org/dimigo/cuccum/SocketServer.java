package org.dimigo.cuccum;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {
	private static final int Max=1; // max Player
	private List<SocketServerReceiver> clients;
	private ServerSocket serverSocket;
	private GameUtil gameUtil;
	private Person[] p;
	
	public SocketServer() { // Constructor
		clients = new ArrayList<>();
		gameUtil=new GameUtil();
		p=new Person[]{ // Make Person using Index
			new Person(0),
			new Person(1), 
			new Person(2),
			new Person(3)
		};
	}
	public Person getPerson(int index) { // get Person by Index
		return p[index];
	}
	public GameUtil getGameUtil(){ // get Game Util
		return gameUtil;
	}
	public void setUp(){ // setUp Game
		for(int i=0;i<clients.size();i++){
			String query=i+" CardList 7 ";
			for(int j=0;j<7;j++){
				Card card=gameUtil.getTopCard();
				p[i].addCardLists(card);
				query+=card.getNumber()+" "+card.getColor()+" ";
			}
			sendToAll(query);
		}
		gameUtil.setOrder();
	}
	public void start() {
		try {

			serverSocket = new ServerSocket(6500); // Start Server
			System.out.println("Server Start");

			gameUtil.setUpCardLists();
			while (true) { // Get Socket From Clients in while-loop
				Socket socket = serverSocket.accept();
				SocketServerReceiver receiver = new SocketServerReceiver(socket, clients.size(), this);
				receiver.start();
				clients.add(receiver);
				receiver.sendMessage(clients.size()-1+" Index"); // Send Index Message to Client
				if(clients.size()==Max){ // If Clients are ready to start
					setUp();
					gameUtil.setCurrentCard(gameUtil.getTopCard());
					while(true) {
						if(gameUtil.getCurrentCard().getNumber()>9) { // If currentCard is Special Card
							gameUtil.setCurrentCard(gameUtil.getTopCard());
							continue;
						}
						break;
					}
					System.out.println("Client All Access"); // Game Start
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
	
	public void sendToAll(String message) { // Send Message To All
		System.out.println(message);
		for(int i=0;i<clients.size();i++){
			clients.get(i).sendMessage(message);
		}

	}
	
}
