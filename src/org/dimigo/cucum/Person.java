package org.dimigo.cucum;

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
	public void addTempCardLists(List<Card> cards) {
		cardLists.addAll(cards);
	}
	public void addCardLists(Card card){
		cardLists.add(card);
	}
	public int getNumber(){
		return cardLists.size();
	}
	public List<Card> getCardList(){
		return cardLists;
	}
	
	public int getIndex() {
		return index;
	}

	
	
}
