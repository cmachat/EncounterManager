package application;

import java.util.ArrayList;
import java.util.Random;

public class Dice
{
	
	enum Type {
		d2, d4, d6, d8, d10, d12, d20, d100
	}
	
	
	int size;
	int number;
	ArrayList<Integer> rolls;
	
	
	public Dice(Type dice)
	{ this(dice, 0); }
	

	public Dice(Type dice, int number)
	{
		this.number = number;
		rolls = new ArrayList<Integer>();
		
		switch (dice) {
		case d100: 
			size = 100;
			break;
		case d20:
			size = 20;
			break;
		case d12:
			size = 12;
			break;
		case d10:
			size = 10;
			break;
		case d8:
			size = 8;
			break;
		case d6:
			size = 6;
			break;
		case d4:
			size = 4;
			break;
		case d2:
			size = 2;
			break;
		}
	}
	
	
	public int roll()
	{
		Random rnd = new Random();
		int value = 0;
		for (int i = 0; i < number; i++) {
			int v = rnd.nextInt(size) + 1;
			rolls.add(v);
			value += v;
		}
		return value;
	}
	
	
	public void increaseNumber()
	{ number++; }
	
	
	public int getNumber()
	{ return number; }
	
	
	
	public String getRollString()
	{
		String rv = "";
		for (Integer i : rolls) {
			rv += i + ", ";
		}
		return rv.substring(0, rv.length() - 2);
	}
	
	
	@Override
	public String toString()
	{
		if (number > 0)
			return Integer.toString(number) + "d" + Integer.toString(size);
		else
			return "";
	}
	
	
}
