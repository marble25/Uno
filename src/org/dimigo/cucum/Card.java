package org.dimigo.cucum;

public class Card {
	private int number;//0~9����, 
					   //10 = Skip, 11 = Change, 12 = +2, 
					   //13 = colorChange, 14 = colorChange +4
					   //15 = ��� ����
	private int color; //0 = ��, 1 = ��, 2 = ��, 3 = ��, 4 = ��
	
	public Card(int number, int color) {
		this.number = number;
		this.color = color;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Card [number=" + number + ", color=" + color + "]";
	}
	
	
}
