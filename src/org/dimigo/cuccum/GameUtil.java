package org.dimigo.cuccum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameUtil {
	List<Integer> order = new ArrayList<>(); // Set order to this lists
	int currentTurn=0; // which Player to play
	Card prevCard; // the card which the player draw previously
	Card currentCard; // the card which is on the top
	List<Card> cardList; // current Deck
	List<Card> tempCardList=new ArrayList<>(); // list to save card to plus 
	public void setUpCardLists(){ // set cardList at first
		cardList=new ArrayList<Card>();
    	Card card;
    	for(int i=0;i<4;i++) {
    		card=new Card(0, i);
    		cardList.add(card);
    		for(int j=1;j<=12;j++) {
    			card=new Card(j, i);
    			cardList.add(card);
    			cardList.add(card);
    		}
    		card=new Card(13, 4);
    		cardList.add(card);
    		card=new Card(14, 4);
    		cardList.add(card);
    	}
    	Collections.shuffle(cardList);
	}
	public void addTempCardList(int adder) { // add Temp cardlist for adders
		for(int i=0;i<adder;i++) {
			tempCardList.add(getTopCard());
		}
		System.out.println(tempCardList);
	}
	public List<Card> getTempCardList() { // TempCardList getter
		List<Card> temp=new ArrayList<Card>();
		temp.addAll(tempCardList);
		tempCardList.clear();
		return temp;
	}
	public void ChangeTurnbyCard(){ // this method is called when 'change turn' card is set
		List<Integer> temp=new ArrayList<>();
		temp.addAll(order);
		for(int i=0;i<temp.size();i++) {
			order.set(i, temp.get(order.size()-i-1));
		}
		currentTurn = order.size()-currentTurn-1;
	}
	public int getTurn(){ // get Current Turn
		return order.get(currentTurn);
	}
	public int ChangeTurn(){ // Change Turn
		currentTurn++;
		if(currentTurn==4){
			currentTurn=0;
		}
		return order.get(currentTurn);
	}
	public Card getCurrentCard() { // get Current Card
		return currentCard;
	}

	public void setCurrentCard(Card currentCard) { // Set Current Card
		cardList.add(this.currentCard);
		this.prevCard=this.currentCard;
		this.currentCard = currentCard;
	}
	public boolean ChalCheck(List<Card> cardLists) { // method to check if challenge is succeed
		for(int i=0;i<cardLists.size();i++) {
			if(cardLists.get(i).getColor()==prevCard.getColor()) { // challenge not succeed
				return false;
			}
		}
		return true;
	}
	public List<Card> getCardList() { // Cardlist Getter
		return cardList;
	}
	
	public List<Integer> getOrder() { // Order getter
		return order;
	}

	public void setOrder() { // Order setter
		for(int i=0;i<4;i++) {
			order.add(i);
		}
	}
	
	public Card getTopCard() { // Get Top Card of the deck
		Card card = cardList.get(0);
		cardList.remove(0);
		return card;
	}
	
	public int getNextPersonIndex(int index) { // get Next Person of index
		if(index<3) {
			return order.get(index+1);
		} else {
			return order.get(0);
		}
	}
	
	public int getPrevPersonIndex(int index) { // get Previous Person of index
		if(index>0) {
			return order.get(index-1);
		} else {
			return order.get(3);
		}
	}
	public void removeOrder(int index) { // remove order because of winning
		order.remove(order.indexOf(index));
	}
}
