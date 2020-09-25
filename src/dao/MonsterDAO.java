package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class MonsterDAO
{
	public static long getRecordCount()
	{

		long retValue = 0;

		String SQL = "SELECT COUNT(*) FROM monster";

		Object obj = DBConnection.executeScalar(SQL);

		if (obj != null)
			retValue = ((Number) obj).longValue();

		return retValue;

	}
	
//	public static long getNextKey()
//	{
//		long retValue = 0;
//
//
//		String SQL = "SELECT COALESCE(MAX(pk_monster), 0) + 1 FROM monster";
//
//		Object obj = DBConnection.executeScalar(SQL);
//		return (long) obj;
//
//	}
	

	public static PreparedStatement prepareInsertMonster()
	{

		String SQL = "INSERT INTO monster ";
		SQL += "(name, type) ";
		SQL += "VALUES (?, ?)";

		return DBConnection.prepareStatement(SQL);

	}

	public static PreparedStatement prepareMonsterNameExists()
	{
		String SQL = "SELECT COUNT(*) FROM monster";
		SQL += " WHERE name = ?";
		//SQL += " AND ORT =  ?";

		return DBConnection.prepareStatement(SQL);

	}
	public static boolean insertMonsterPrepared(PreparedStatement prepStatement, Object... parms)
	{
		return DBConnection.executePreparedStatement(prepStatement, parms);
	}

	public static boolean existMonsterNamePrepared(PreparedStatement prepStatement, Object... parms)
	{

		boolean retValue = false;

		Object obj = DBConnection.executePreparedScalarStatement(prepStatement, parms);
		if (obj != null)
			retValue = ((Number) obj).longValue() > 0;

		return retValue;

	}
	
	public static boolean insertMonster(String name, String type)
	{

		String SQL = "INSERT INTO monster ";
		SQL += "(name, type) ";
		SQL += "VALUES (";
		SQL += DBConnection.dbString(name) + ", ";
		SQL += DBConnection.dbString(type) + ")";

		return DBConnection.executeNonQuery(SQL) > 0;

	}
	
	public static List<Monster> getMonsters()
	{

		Monster monster = null;

		List<Monster> list = new ArrayList<>();

		String SQL = "SELECT * "; //"SELECT pk_monster,";
//		SQL +="name,";
//		SQL +="type,";
//		SQL +="alignment,";
//		SQL +="armor_class,";
//		SQL +="hitpoints_average,";
//		SQL +="movement,";
//		SQL +="stat_str, stat_dex, stat_con, stat_int, stat_wis, stat_cha,";
//		SQL +="saving_throws,";
//		SQL +="skills,";
//		SQL +="dmg_immunities,";
//		SQL +="senses,";
//		SQL +="languages,";
//		SQL +="challenge,";
//		SQL +="exp,";
//		SQL +="attacks ";
		SQL +="FROM monster ";
		SQL += "ORDER BY name ASC, type ASC";

		ResultSet rSet = DBConnection.executeQuery(SQL);
		if (rSet == null)
			return list;

		try
		{
			while (rSet.next())
			{
				monster = new Monster();
				monster.setPrimaryKey(rSet.getLong("pk_monster"));
				monster.setName(rSet.getString("name"));
				monster.setType(rSet.getString("type"));
				monster.setAlignment(rSet.getString("alignment"));
				
				monster.setArmorClass(rSet.getInt("armor_class"));
				
				monster.setHitPoints(rSet.getInt("hitpoints_average"));
				monster.setHitpointsRandom(rSet.getString("hitpoints_random"));
				
				monster.setMovement(rSet.getString("movement"));
				
				monster.setStr(rSet.getInt("stat_str"));
				monster.setDex(rSet.getInt("stat_dex"));
				monster.setCon(rSet.getInt("stat_con"));
				monster.setInt(rSet.getInt("stat_int"));
				monster.setWis(rSet.getInt("stat_wis"));
				monster.setCha(rSet.getInt("stat_cha"));
				monster.setSavingThrows(rSet.getString("saving_throws"));
				monster.setSkills(rSet.getString("skills"));
				monster.setResistance(rSet.getString("resistance"));
				monster.setCondidtionImmunities(rSet.getString("condition_immunities"));
				monster.setDmgImmunities(rSet.getString("dmg_immunities"));
				monster.setSenses(rSet.getString("senses"));
				monster.setLanguages(rSet.getString("languages"));
				monster.setChallenge(rSet.getString("challenge"));
				monster.setDescription(rSet.getString("description"));
				monster.setActions(rSet.getString("actions"));
				monster.setLegendary(rSet.getString("legendary"));
				monster.setVulnerability(rSet.getString("vulnerability"));
				monster.setSource(rSet.getString("source"));
				monster.setArmorType(rSet.getString("armor_type"));
				monster.setPassivePerception(rSet.getInt("passive_perception"));
				monster.setTraits(rSet.getString("traits"));
				monster.setActions(rSet.getString("actions"));
				
				
				list.add(monster);
				
//				for(Monster p : list){
//					System.out.println(p.getMonsterName());
//					}

			}

			rSet.close();
		}
		catch (Exception ex)
		{

			JOptionPane.showMessageDialog(null, "Database Access Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}

		return list;

	}
	public static Combatant getMonster(long Key)
	{

		Monster monster = null;

		String SQL = "SELECT * "; //"SELECT pk_monster,";
//		SQL +="name,";
//		SQL +="type,";
//		SQL +="alignment,";
//		SQL +="armor_class,";
//		SQL +="hitpoints_average,";
//		SQL +="movement,";
//		SQL +="stat_str, stat_dex, stat_con, stat_int, stat_wis, stat_cha,";
//		SQL +="saving_throws,";
//		SQL +="skills,";
//		SQL +="dmg_immunities,";
//		SQL +="senses,";
//		SQL +="languages,";
//		SQL +="challenge,";
//		SQL +="exp,";
//		SQL +="attacks ";
		SQL +="FROM monster ";
		SQL += "WHERE pk_monster = " + Long.toString(Key);

		ResultSet rSet = DBConnection.executeQuery(SQL);
		if (rSet == null)
			return monster;

		try
		{
			if (rSet.next())
			{
				monster = new Monster();
				monster.setPrimaryKey(rSet.getLong("pk_monster"));
				monster.setName(rSet.getString("name"));
				monster.setType(rSet.getString("type"));
				monster.setAlignment(rSet.getString("alignment"));
				
				monster.setArmorClass(rSet.getInt("armor_class"));
				
				monster.setHitPoints(rSet.getInt("hitpoints_average"));
				monster.setHitpointsRandom(rSet.getString("hitpoints_random"));
				
				monster.setMovement(rSet.getString("movement"));
				
				monster.setStr(rSet.getInt("stat_str"));
				monster.setDex(rSet.getInt("stat_dex"));
				monster.setCon(rSet.getInt("stat_con"));
				monster.setInt(rSet.getInt("stat_int"));
				monster.setWis(rSet.getInt("stat_wis"));
				monster.setCha(rSet.getInt("stat_cha"));
				monster.setSavingThrows(rSet.getString("saving_throws"));
				monster.setSkills(rSet.getString("skills"));
				monster.setResistance(rSet.getString("resistance"));
				monster.setCondidtionImmunities(rSet.getString("condition_immunities"));
				monster.setDmgImmunities(rSet.getString("dmg_immunities"));
				monster.setSenses(rSet.getString("senses"));
				monster.setLanguages(rSet.getString("languages"));
				monster.setChallenge(rSet.getString("challenge"));
				monster.setDescription(rSet.getString("description"));
				monster.setActions(rSet.getString("actions"));
				monster.setLegendary(rSet.getString("legendary"));
				monster.setVulnerability(rSet.getString("vulnerability"));
				monster.setSource(rSet.getString("source"));
				monster.setArmorType(rSet.getString("armor_type"));
				monster.setPassivePerception(rSet.getInt("passive_perception"));
				monster.setTraits(rSet.getString("traits"));
				monster.setActions(rSet.getString("actions"));
			}
			
			rSet.close();
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);

		}

		return monster;
	}

	
}
