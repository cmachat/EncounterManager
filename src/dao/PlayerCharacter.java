package dao;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlayerCharacter extends Combatant

{
	private SimpleBooleanProperty PC;
	private SimpleIntegerProperty LVL;
	private SimpleStringProperty Race, Archetype;
	
	public Boolean getPC()
	{ return PC.getValue(); }


	public void setPC(Boolean value)
	{ PC = new SimpleBooleanProperty(value); }
	
	
	public String getLVL()
	{ return String.valueOf(LVL.getValue()); }


	public void setLVL(int value)
	{ LVL = new SimpleIntegerProperty(value); }
	
	
	
	public String getArchetype()
	{ return Archetype.getValue(); }


	public void setArchetype(String value)
	{ Archetype = new SimpleStringProperty(value); }

	public String getRace()
	{ return Race.getValue(); }


	public void setRace(String value)
	{ Race = new SimpleStringProperty(value); }

	
}
