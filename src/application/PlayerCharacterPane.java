package application;

import dao.Combatant;
import dao.PlayerCharacter;
import dao.PlayerCharacterDAO;

public class PlayerCharacterPane extends CombatantPane
{

	public PlayerCharacterPane(long primaryKey) {
		super(primaryKey);
		
		
		
	}

	
	@Override
	public String getStyleNormal() {
		
		//#008000
		return "-fx-background-color: #38B955;" +
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
