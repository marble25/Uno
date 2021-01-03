package org.dimigo.cuccum;

import java.util.LinkedList;
import java.util.List;

public class Person {
	private List<Card> cardLists=new LinkedList<Card>();
	private int index;
	
	public Person(int index) {
		this.index = index;
	}
	
	public void removeCardLists(Card card){
		for(int i=0;i<cardLists.size();i++) {
			if(cardLists.get(i).getColor()==card.getColor() &&cardLists.get(i).getNumber()==card.getNumber()) {
				cardLists.remove(i);
				break;
			}
		}
		
	}
	public void addTempCardLists(List<Card> cards) { // add lists to the Person's lists
		cardLists.addAll(cards);
	}
	public void addCardLists(Card card){ // add cardLists to Person's card
		cardLists.add(card);
	}
	public int getNumber(){ // get Number of cardlists' size
		return cardLists.size();
	}
	public List<Card> getCardList(){ // get all of cardlists
		return cardLists;
	}
	
	public int getIndex() { // get index of Person
		return index;
	}

	
	
}
