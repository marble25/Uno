package org.dimigo.cuccum;

public class Card {
	private int number;//0~9 Numbers
					   //10 = Skip, 11 = Change, 12 = +2, 
					   //13 = colorChange, 14 = colorChange +4
	private int color; //0 = Red, 1 = Blue, 2 = Yellow, 3 = Green, 4 = Black
	
	public Card(int number, int color) { // Constructor
		this.number = number;
		this.color = color;
	}
	
	public int getNumber() { // Number Getter
		return number;
	}
	public void setNumber(int number) { // Number Setter
		this.number = number;
	}
	public int getColor() { // Color Getter
		return color;
	}
	public void setColor(int color) { // Color Setter
		this.color = color;
	}

	@Override
	public String toString() { // toString method
		return "Card [number=" + number + ", color=" + color + "]";
	}
	
	
}
