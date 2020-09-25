package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ConditionDAO
{
	public static List<Condition> getConditions()
	{

		Condition condition = null;

		List<Condition> list = new ArrayList<>();

		String SQL = "SELECT * "; 
		SQL +="FROM conditions ";
		SQL += "ORDER BY name ASC";

		ResultSet rSet = DBConnection.executeQuery(SQL);
		if (rSet == null)
			return list;

		try
		{
			while (rSet.next())
			{
				condition = new Condition();
				condition.setPrimaryKey(rSet.getLong("pk_condition"));
				condition.setName(rSet.getString("name"));
				condition.setDescription(rSet.getString("description"));
							
				list.add(condition);
				
//				for(condition p : list){
//					System.out.println(p.getconditionName());
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
	public static Condition getCondition(long Key)
	{

		Condition condition = null;

		String SQL = "SELECT * "; 
		SQL +="FROM conditions ";
		SQL += "WHERE pk_condition = " + Long.toString(Key);

		ResultSet rSet = DBConnection.executeQuery(SQL);
		if (rSet == null)
			return condition;

		try
		{
			if (rSet.next())
			{
				condition = new Condition();
				condition.setPrimaryKey(rSet.getLong("pk_condition"));
				condition.setName(rSet.getString("name"));
				condition.setDescription(rSet.getString("description"));
							
				
			}
			
			rSet.close();
			
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);

		}

		return condition;
	}

}
