package org.dimigo.cucum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameUtil {
	List<Integer> order = new ArrayList<>();
	int currentTurn=0;
	Card prevCard;
	Card currentCard;
	List<Card> cardList;
	List<Card> tempCardList=new ArrayList<>();
	public void setUpCardLists(){
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
	public void gameWin(int index) {
		order.remove(index);
	}
	public void addTempCardList(int adder) {
		for(int i=0;i<adder;i++) {
			tempCardList.add(getTopCard());
		}
		System.out.println(tempCardList);
	}
	public List<Card> getTempCardList() {
		List<Card> temp=new ArrayList<Card>();
		temp.addAll(tempCardList);
		tempCardList.clear();
		return temp;
	}
	public void ChangeTurnbyCard(){
		List<Integer> temp=new ArrayList<>();
		temp.addAll(order);
		for(int i=0;i<temp.size();i++) {
			order.set(i, temp.get(order.size()-i-1));
		}
		currentTurn = order.size()-currentTurn-1;
	}
	public int getTurn(){
		return order.get(currentTurn);
	}
	public int ChangeTurn(){
		currentTurn++;
		if(currentTurn==order.size()){
			currentTurn=0;
		}
		return order.get(currentTurn);
	}
	public Card getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(Card currentCard) {
		cardList.add(this.currentCard);
		this.prevCard=this.currentCard;
		this.currentCard = currentCard;
	}
	public boolean ChalCheck(List<Card> cardLists) {
		for(int i=0;i<cardLists.size();i++) {
			if(cardLists.get(i).getColor()==prevCard.getColor()) { // ���� ���ٸ�
				return false;
			}
		}
		return true;
	}
	public List<Card> getCardList() {
		return cardList;
	}
	
	public List<Integer> getOrder() {
		return order;
	}

	public void setOrder() {
		for(int i=0;i<4;i++) {
			order.add(i);
		}
		System.out.println(order);
	}
	
	public List<Card> PlusCard(int adder) {
		List<Card> CardList=new ArrayList<Card>();
		for(int i=0;i<adder;i++) {
			Card card=getTopCard();
			CardList.add(card);
		}
		return CardList;
	}
	public Card getTopCard() {
		Card card = cardList.get(0);
		cardList.remove(0);
		return card;
	}
	public int getNextPersonIndex(int index) {
		if(index<order.size()-1) {
			return order.get(index+1);
		} else {
			return order.get(0);
		}
	}
	public int getNextPersonIndex() {
		if(currentTurn<order.size()-1) {
			return order.get(currentTurn+1);
		} else {
			return order.get(0);
		}
	}
	
	public int getPrevPersonIndex(int index) {
		if(index>0) {
			return order.get(index-1);
		} else {
			return order.get(order.size()-1);
		}
	}
	
	public int getPrevPersonIndex() {
		if(currentTurn>0) {
			return order.get(currentTurn-1);
		} else {
			return order.get(order.size()-1);
		}
	}
}
