package application;

public class Rules
{

	
	public static int modifier(int value)
	{
		double temp = value;
		return (int) (Math.floor((temp - 10)/2));
	}
	
	public static int proficiencyBonus(int value)
	{
		return value; //f�r sp�ter
		
	}
	
	public static int spellDC(int value)
	{
		return value; //f�r sp�ter
	}
	
	public static int spellAttack(int value)
	{
		return value; //f�r sp�ter
	}
	
	public static int passivePerception(int value)
	{
		return value; //f�r sp�ter
	}
	
	
	
	
}
