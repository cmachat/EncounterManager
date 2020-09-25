package dao;

public interface ICombatant
{
	public long getPrimaryKey();
	public void setPrimaryKey(long value);
	public String getName();
	public void setName(String value);
	public String getType();
	public void setType(String value);
	public String getAlignment();
	public void setAlignment(String value);	
	public int getArmorClass();
	public void setArmorClass(int value);
	
	public int getHitPoints();
	public void setHitPoints(int value);
	
	public String getMovement();
	public void setMovement(String value);
	
	public int getStr();
	public void setStr(int value);
	
	public int getDex();
	public void setDex(int value);
	
	public int getCon();
	public void setCon(int value);
	
	public int getInt();
	public void setInt(int value);
	
	public int getWis();
	public void setWis(int value);
	
	public int getCha();
	public void setCha(int value);
	
	public String getSenses();
	public void setSenses(String value);
	
	public String getLanguages();
	public void setLanguages(String value);
	
	
//	
//	public String getChallenge();
//	public void setChallenge(String value);
//	
//	public String getAttacks();
//	public void setAttacks(String value);
	
	
	
}
