package dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Combatant 
{

	private SimpleLongProperty primaryKey;
	private SimpleIntegerProperty armorClass, hitPointsAverage, Str, Dex, Con, Int, Wis, Cha;
	private SimpleStringProperty name, type, movement, Alignment, Senses, Languages;


	
	public long getPrimaryKey()
	{ return primaryKey.longValue(); }

	
	public void setPrimaryKey(long value)
	{ primaryKey = new SimpleLongProperty(value); }

	
	public String getName()
	{ return name.getValue(); }

	
	public void setName(String value)
	{ name = new SimpleStringProperty(value); }

	
	public String getType()
	{ return type.getValue(); }

	
	public void setType(String value)
	{ type = new SimpleStringProperty(value); }

	
	
	public int getArmorClass()
	{ return armorClass.getValue(); }

	
	public void setArmorClass(int value)
	{ armorClass = new SimpleIntegerProperty(value); }

	
	public int getHitPoints()
	{ return hitPointsAverage.getValue(); }

	
	public void setHitPoints(int value)
	{ hitPointsAverage = new SimpleIntegerProperty(value); }

	
	public String getMovement()
	{ return movement.getValue(); }

	
	public void setMovement(String value)
	{ movement = new SimpleStringProperty(value); }



	
	public int getStr()
	{ return Str.getValue(); }

	
	public void setStr(int value)
	{ Str = new SimpleIntegerProperty(value); }

	
	public int getDex()
	{ return Dex.getValue(); }

	
	public void setDex(int value)
	{ Dex = new SimpleIntegerProperty(value); }

	
	public int getCon()
	{ return Con.getValue(); }

	
	public void setCon(int value)
	{ Con = new SimpleIntegerProperty(value); }

	
	public int getInt()
	{ return Int.getValue(); }

	
	public void setInt(int value)
	{ Int = new SimpleIntegerProperty(value); }

	
	public int getWis()
	{ return Wis.getValue(); }

	
	public void setWis(int value)
	{ Wis = new SimpleIntegerProperty(value); }

	
	public int getCha()
	{ return Cha.getValue(); }

	
	public void setCha(int value)
	{ Cha = new SimpleIntegerProperty(value); }

	
	public String getAlignment()
	{ return Alignment.getValue(); }

	
	public void setAlignment(String value)
	{ Alignment = new SimpleStringProperty(value); }

	
	public String getSenses()
	{ return Senses.getValue(); }

	
	public void setSenses(String value)
	{  Senses = new SimpleStringProperty(value); }

	
	public String getLanguages()
	{ return Languages.getValue(); }

	
	public void setLanguages(String value)
	{ Languages = new SimpleStringProperty(value); }



}
