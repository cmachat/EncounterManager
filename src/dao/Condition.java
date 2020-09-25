package dao;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Condition
{
	private SimpleLongProperty PrimaryKey;
	private SimpleStringProperty Name;
	private SimpleStringProperty Description;
	
	public long getPrimaryKey()
	{ return PrimaryKey.longValue(); }

	
	public void setPrimaryKey(long value)
	{ PrimaryKey = new SimpleLongProperty(value); }

	
	
	public String getName()
	{ return Name.getValue(); }


	public void setName(String value)
	{ Name = new SimpleStringProperty(value); }
	
	public String getDescription()
	{ return Description.getValue(); }


	public void setDescription(String value)
	{ Description = new SimpleStringProperty(value); }
	
}
