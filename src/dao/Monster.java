package dao;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Monster extends Combatant
{
	private SimpleStringProperty challenge;
	private SimpleStringProperty Description;
	private SimpleStringProperty Legendary;
	private SimpleStringProperty HitpointsRandom;
	private SimpleStringProperty Skills;
	private SimpleStringProperty DmgImmunities;
	private SimpleStringProperty Resistance;
	private SimpleStringProperty CondidtionImmunities;
	private SimpleStringProperty SavingThrows;
	private SimpleStringProperty Vulnerability;
	private SimpleStringProperty Source;
	private SimpleStringProperty ArmorType;
	private SimpleIntegerProperty PassivePerception;
	private SimpleStringProperty Traits;
	private SimpleStringProperty Actions;
	

	public String getChallenge()
	{ return challenge.getValue(); }

	public void setChallenge(String value)
	{ challenge = new SimpleStringProperty(value); }

	public String getSkills()
	{ return Skills.getValue(); }

	public void setSkills(String value)
	{ Skills = new SimpleStringProperty(value); }
	
	public String getResistance()
	{ return Resistance.getValue(); }

	public void setResistance(String value)
	{ Resistance = new SimpleStringProperty(value); }
	
	public String getCondidtionImmunities()
	{ return CondidtionImmunities.getValue(); }

	public void setCondidtionImmunities(String value)
	{ CondidtionImmunities = new SimpleStringProperty(value); }
	
	public String getDmgImmunities()
	{ return DmgImmunities.getValue(); }

	public void setDmgImmunities(String value)
	{ DmgImmunities = new SimpleStringProperty(value); }
	
	public String getSavingThrows()
	{ return SavingThrows.getValue(); }

	public void setSavingThrows(String value)
	{ SavingThrows = new SimpleStringProperty(value); }
	
	public String getDescription()
	{ return Description.getValue(); }

	public void setDescription(String value)
	{ Description = new SimpleStringProperty(value); }
	
	public String getLegendary()
	{ return Legendary.getValue(); }

	public void setLegendary(String value)
	{ Legendary = new SimpleStringProperty(value); }
	
	public String getHitpointsRandom()
	{ return HitpointsRandom.getValue(); }

	public void setHitpointsRandom(String value)
	{ HitpointsRandom = new SimpleStringProperty(value); }
	
	public String getVulnerability()
	{ return Vulnerability.getValue(); }

	public void setVulnerability(String value)
	{ Vulnerability = new SimpleStringProperty(value); }

	public String getSource()
	{ return Source.getValue(); }

	public void setSource(String value)
	{ Source = new SimpleStringProperty(value); }

	public String getArmorType()
	{ return ArmorType.getValue(); }

	public void setArmorType(String value)
	{ ArmorType = new SimpleStringProperty(value); }

	public int getPassivePerception()
	{ return PassivePerception.getValue(); }

	public void setPassivePerception(int value)
	{ PassivePerception = new SimpleIntegerProperty(value); }

	public String getTraits()
	{ return Traits.getValue(); }

	public void setTraits(String value)
	{ Traits = new SimpleStringProperty(value); }

	public String getActions()
	{ return Actions.getValue(); }

	public void setActions(String value)
	{ Actions = new SimpleStringProperty(value); }
	
	
}





