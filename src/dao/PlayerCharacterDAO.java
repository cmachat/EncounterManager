package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class PlayerCharacterDAO
{
	public static long getRecordCount()
	{

		long retValue = 0;

		String SQL = "SELECT COUNT(*) FROM pc_npc";

		Object obj = DBConnection.executeScalar(SQL);

		if (obj != null)
			retValue = ((Number) obj).longValue();

		return retValue;

	}

	public static List<PlayerCharacter> getCharacters()
	{

		PlayerCharacter character = null;

		List<PlayerCharacter> list = new ArrayList<>();

		String SQL = "SELECT pk_character, ";
		SQL += " name,"; 
		SQL += " pc,"; 
		SQL += " level,"; 
		SQL += " class,"; 
		SQL += " archetype,"; 
		SQL += " race,"; 
		SQL += " armor_class,"; 
		SQL += " alignment,"; 
		SQL += " max_hitpoints,"; 
		SQL += " movement,"; 
		SQL += " stat_str, stat_dex, stat_con, stat_int, stat_wis, stat_cha,"; 
		SQL += " languages,"; 
		SQL += " senses "; 
		SQL += "FROM pc_npc ";
		SQL += "ORDER BY name ASC, class ASC";

		ResultSet rSet = DBConnection.executeQuery(SQL);
		if (rSet == null)
			return list;

		try
		{
			while (rSet.next())
			{
				character = new PlayerCharacter();
				character.setPrimaryKey(rSet.getLong("pk_character"));
				character.setName(rSet.getString("name"));
				character.setPC(rSet.getBoolean("pc"));
				character.setLVL(rSet.getInt("level"));
				character.setType(rSet.getString("class"));
				character.setArchetype(rSet.getString("archetype"));
				character.setRace(rSet.getString("race"));
				character.setAlignment(rSet.getString("alignment"));
				character.setArmorClass(rSet.getInt("armor_class"));
				character.setHitPoints(rSet.getInt("max_hitpoints"));
				character.setMovement(rSet.getString("movement"));
				
				character.setStr(rSet.getInt("stat_str"));
				character.setDex(rSet.getInt("stat_dex"));
				character.setCon(rSet.getInt("stat_con"));
				character.setInt(rSet.getInt("stat_int"));
				character.setWis(rSet.getInt("stat_wis"));
				character.setCha(rSet.getInt("stat_cha"));
				character.setLanguages(rSet.getString("languages"));
				character.setSenses(rSet.getString("senses"));

				list.add(character);

				//			for(Monster p : list){
				//				System.out.println(p.getMonsterName());
				//				}

			}

			rSet.close();
		}
		catch (Exception ex)
		{

			JOptionPane.showMessageDialog(null, "Database Access Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}

		return list;

	}
	
	public static Combatant getCharacter(long Key)
	{

		PlayerCharacter character = null;

		String SQL = "SELECT pk_character, ";
		SQL += " name,"; 
		SQL += " pc,"; 
		SQL += " level,"; 
		SQL += " class,"; 
		SQL += " archetype,";
		SQL += " race,"; 
		SQL += " alignment,"; 
		SQL += " armor_class,"; 
		SQL += " max_hitpoints,"; 
		SQL += " movement,"; 
		SQL += " stat_str, stat_dex, stat_con, stat_int, stat_wis, stat_cha,"; 
		SQL += " languages,"; 
		SQL += " senses "; 
		SQL += "FROM pc_npc ";
		SQL += "WHERE pk_character = " + Long.toString(Key);

		ResultSet rSet = DBConnection.executeQuery(SQL);
		if (rSet == null)
			return character;

		try
		{
			if (rSet.next())
			{
				character = new PlayerCharacter();
				character.setPrimaryKey(rSet.getLong("pk_character"));
				character.setName(rSet.getString("name"));
				character.setPC(rSet.getBoolean("pc"));
				character.setLVL(rSet.getInt("level"));
				character.setType(rSet.getString("class"));
				character.setArchetype(rSet.getString("archetype"));
				character.setRace(rSet.getString("race"));
				character.setAlignment(rSet.getString("alignment"));
				character.setArmorClass(rSet.getInt("armor_class"));
				character.setHitPoints(rSet.getInt("max_hitpoints"));
				character.setMovement(rSet.getString("movement"));
				character.setStr(rSet.getInt("stat_str"));
				character.setDex(rSet.getInt("stat_dex"));
				character.setCon(rSet.getInt("stat_con"));
				character.setInt(rSet.getInt("stat_int"));
				character.setWis(rSet.getInt("stat_wis"));
				character.setCha(rSet.getInt("stat_cha"));	
				character.setLanguages(rSet.getString("languages"));
				character.setSenses(rSet.getString("senses"));

			}
			
			rSet.close();
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);

		}

		return character;
	}

}