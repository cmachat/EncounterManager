package application;

import dao.Combatant;
import dao.Monster;
import dao.MonsterDAO;

public class MonsterPane extends CombatantPane {

	public MonsterPane(long primaryKey) {
		super(primaryKey);



	}
	


	@Override
	public String getStyleNormal() {
		// #CD5C5C, #CC6C12

		return "-fx-background-color: #FDA24B;" +
		"-fx-background-radius: 5.0;\r\n" + 
		"    fx-background-insets: 0, 1 1 0 0 ;\r\n" +
		"    -fx-padding: 10;\r\n" + 
		"    -fx-hgap: 10;\r\n" + 
		"    -fx-vgap: 10;";
	}


	@Override
	protected Combatant getCombatant(long primaryKey)
	{
		Combatant combatant = MonsterDAO.getMonster(primaryKey);
		return combatant;
	}


	@Override
	protected Monster getCombatant()
	{
		return (Monster) combatant;
	}







}
