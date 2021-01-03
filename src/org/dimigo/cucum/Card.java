package org.dimigo.cucum;

public class Card {
	private int number;//0~9까지, 
					   //10 = Skip, 11 = Change, 12 = +2, 
					   //13 = colorChange, 14 = colorChange +4
					   //15 = 모든 숫자
	private int color; //0 = 빨, 1 = 파, 2 = 노, 3 = 초, 4 = 검
	
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
