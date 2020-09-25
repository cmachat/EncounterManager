package application;

import dao.Combatant;
import dao.PlayerCharacter;
import dao.PlayerCharacterDAO;

public class NonPlayerCharacterPane extends CombatantPane
{

	public NonPlayerCharacterPane(long primaryKey)
	{
		super(primaryKey);
		
	}

	@Override
	public String getStyleNormal() {
		//#F5F5DC
		return "-fx-background-color: #1A94D1;" +
				"-fx-background-radius: 5.0;\r\n" + 
				"    fx-background-insets: 0, 1 1 0 0 ;\r\n" +
				"    -fx-padding: 10;\r\n" + 
				"    -fx-hgap: 10;\r\n" + 
				"    -fx-vgap: 10;";
	}


	@Override
	protected Combatant getCombatant(long primaryKey)
	{
		
		Combatant combatant = PlayerCharacterDAO.getCharacter(primaryKey);
		return combatant;
	}

	@Override
	protected PlayerCharacter getCombatant()
	{
		
		return (PlayerCharacter) combatant;
	}

	
}
