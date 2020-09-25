package application;

import java.util.ArrayList;

import dao.Combatant;
import dao.Condition;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public abstract class CombatantPane extends AnchorPane
{

	private CheckBox checkBoxSelect;
	private Label labelName, labelHitPointsMax, labelArmorClass, labelMovement, labelInitiative, labelHP, labelHitPointsMaxText, labelHPText, labelTurn;
	private TextField textFieldHitPoints, textFieldInitiative;

	private long primaryKey; 
	private boolean combatInitialized,activeTurn;
	private int deathSavingThrowSuccess, deathSavingThrowFail;
	protected Combatant combatant;
	private ArrayList <Condition> condition;;


	
	
	public CombatantPane(long primaryKey) 
	{
		this.primaryKey = primaryKey;
		initializeCombatant();
	}

	protected abstract Combatant getCombatant(long primaryKey);

	public abstract String getStyleNormal();

	public String getStyleUnconscious()
	{
		return "-fx-background-color: #808080;" +
				"-fx-background-radius: 5.0;\r\n" + 
				"    fx-background-insets: 0, 1 1 0 0 ;\r\n" +
				"    -fx-padding: 10;\r\n" + 
				"    -fx-hgap: 10;\r\n" + 
				"    -fx-vgap: 10;";
	}

	public String getStyleDead()
	{
		return "-fx-background-color: #FFFFFF;" +
				"-fx-background-radius: 5.0;\r\n" + 
				"    fx-background-insets: 0, 1 1 0 0 ;\r\n" +
				"    -fx-padding: 10;\r\n" + 
				"    -fx-hgap: 10;\r\n" + 
				"    -fx-vgap: 10;";
	}

	protected abstract Combatant getCombatant();


	protected void initializeCombatant()
	{
		condition = new ArrayList<Condition>();
		combatant = getCombatant(primaryKey);

		KeyTypedHandler keyTypedEvent = new KeyTypedHandler();

		checkBoxSelect = new CheckBox();
		checkBoxSelect.setSelected(false);
		checkBoxSelect.setLayoutX(30);
		checkBoxSelect.setLayoutY(18);

		labelTurn = new Label();
		labelTurn.setLayoutX(30);
		labelTurn.setLayoutY(50);
		labelTurn.setText("    ");
		setActiveTurn(false);

		labelName = new Label();
		labelName.setLayoutX(70);
		labelName.setLayoutY(13);
		labelName.setMaxWidth(170);
		labelName.setFont(new Font("", 18));
		labelName.setText(combatant.getName());

		labelInitiative = new Label();
		labelInitiative.setLayoutX(70);
		labelInitiative.setLayoutY(47);
		labelInitiative.setFont(new Font("", 15));
		labelInitiative.setText("Ini:");

		textFieldInitiative = new TextField();
		textFieldInitiative.setText(String.valueOf(rollInitiative()));
		textFieldInitiative.setPrefWidth(40);
		textFieldInitiative.setMaxWidth(40);
		textFieldInitiative.setMinWidth(40);
		textFieldInitiative.setLayoutX(92);
		textFieldInitiative.setLayoutY(47);
		textFieldInitiative.setOnKeyTyped(keyTypedEvent);

		labelHPText = new Label();
		labelHPText.setLayoutX(150);
		labelHPText.setLayoutY(48);
		labelHPText.setFont(new Font("", 15));
		labelHPText.setText("HP: ");

		textFieldHitPoints = new TextField();
		textFieldHitPoints.setPrefWidth(40);
		textFieldHitPoints.setMaxWidth(40);
		textFieldHitPoints.setMinWidth(40);
		textFieldHitPoints.setLayoutX(180);
		textFieldHitPoints.setLayoutY(47);
		textFieldHitPoints.setText(String.valueOf(combatant.getHitPoints()));
		textFieldHitPoints.setOnKeyTyped(keyTypedEvent);

		labelHP = new Label();
		labelHP.setLayoutX(180);
		labelHP.setLayoutY(48);
		labelHP.setFont(new Font("", 15));
		labelHP.setText(String.valueOf(combatant.getHitPoints()));

		labelArmorClass = new Label();
		labelArmorClass.setLayoutX(250);
		labelArmorClass.setLayoutY(17);
		labelArmorClass.setFont(new Font("", 15));
		labelArmorClass.setText("AC: "+ String.valueOf(combatant.getArmorClass()));

		labelHitPointsMaxText = new Label();
		labelHitPointsMaxText.setLayoutX(310);
		labelHitPointsMaxText.setLayoutY(17);
		labelHitPointsMaxText.setFont(new Font("", 15));
		labelHitPointsMaxText.setText("HPMax:");

		labelHitPointsMax = new Label();
		labelHitPointsMax.setLayoutX(365);
		labelHitPointsMax.setLayoutY(17);
		labelHitPointsMax.setFont(new Font("", 15));
		labelHitPointsMax.setText(String.valueOf(combatant.getHitPoints()));

		labelMovement = new Label();
		labelMovement.setLayoutX(250);
		labelMovement.setLayoutY(47);
		labelMovement.setFont(new Font("", 15));
		labelMovement.setText("Speed: " + combatant.getMovement());

		this.getChildren().add(labelName); 
		this.getChildren().add(labelTurn);
		this.getChildren().add(labelHPText);
		this.getChildren().add(labelMovement);
		this.getChildren().add(checkBoxSelect);
		this.getChildren().add(labelInitiative);
		this.getChildren().add(labelArmorClass);
		this.getChildren().add(labelHitPointsMax);
		this.getChildren().add(textFieldHitPoints);
		this.getChildren().add(textFieldInitiative);
		this.getChildren().add(labelHitPointsMaxText);

		this.setStyle(getStyleNormal());
	}


	public boolean isCombatInitialized()
	{ return combatInitialized; }


	public boolean isSelected()
	{
		boolean retValue = false;

		if (checkBoxSelect.isSelected())
			retValue = true;

		return retValue;
	}


	public void setSelected(boolean value)
	{ checkBoxSelect.setSelected(value); }


	public  int getCombatantInitiative() {
		return (Integer.valueOf(textFieldInitiative.getText())); }

	public  void setCombatantInitiative(int value) {
		textFieldInitiative.setText(String.valueOf(value)); }


	public void changeHitpoints(int dmg)
	{
		if (combatInitialized)
		{
			int hp = Integer.valueOf(labelHP.getText());

			hp -= dmg;
			labelHP.setText(String.valueOf(hp));

			if (hp > Integer.valueOf(labelHitPointsMax.getText())) {
				labelHP.setText(String.valueOf(labelHitPointsMax.getText()));
				resetDeathSavingThrows();
				this.setStyle(getStyleNormal());
			}
			else if (hp <= 0)
				this.setStyle(getStyleUnconscious());
			else
				this.setStyle(getStyleNormal());
			resetDeathSavingThrows();
		}
	}


	private int rollInitiative()
	{ return ((Rules.modifier(combatant.getDex())) + (new Dice(Dice.Type.d20, 1).roll())); }


	public void combatReady()
	{
		if (!combatInitialized)// Damit bei erneuter Sortierung der Initiative Reihenfolge bei schon vorhandenen Combatants der aktuelle HP nicht als HPMax gesetzt wird 
		{
			labelHitPointsMax.setText(textFieldHitPoints.getText());
			labelHP.setText(textFieldHitPoints.getText());
			this.getChildren().remove(textFieldHitPoints);
			this.getChildren().add(labelHP);
			combatInitialized = true;
		}
	}

	public int getHitpointsMaxInt()
	{
		if (!combatInitialized) 
			return Integer.valueOf(textFieldHitPoints.getText());
		else
			return Integer.valueOf(labelHitPointsMax.getText());	
	}

	public String getHitpointsMaxString()
	{
		if (!combatInitialized)
			return textFieldHitPoints.getText();
		else
			return labelHitPointsMax.getText();	
	}


	public void setDeathSavingThrow(boolean value)
	{
		if(value)
			deathSavingThrowSuccess++; 		
		else
			deathSavingThrowFail++;	

		if (deathSavingThrowSuccess >= 3)
		{
			labelHP.setText("1");
			resetDeathSavingThrows();
			this.setStyle(getStyleNormal());
		}	
		else if (deathSavingThrowFail >= 3) 
		{
			this.setStyle(getStyleDead());
			resetDeathSavingThrows();
		}

	}

	public void resetDeathSavingThrows()
	{
		deathSavingThrowSuccess = 0;
		deathSavingThrowFail = 0;		
	}


	public boolean isUnconscious()
	{
		boolean retValue = false;

		if(Integer.valueOf(labelHP.getText()) < 1)
			retValue = true;

		return retValue;
	}

		
	
	public int getDeathSavingThrowSuccess()
	{ return deathSavingThrowSuccess; }


	public int getDeathSavingThrowFail()
	{ return deathSavingThrowFail; }


	public void setActiveTurn(boolean value)
	{ 
		activeTurn = value; 

		if(activeTurn)
			labelTurn.setStyle("-fx-background-image: url('/images/green_light.png');" +
					"	-fx-background-size: 17px;" + 
					"   -fx-background-repeat: no-repeat;" + 
					"   -fx-background-position: 50%;"); 
		else
			labelTurn.setStyle("-fx-background-image: url('/images/red_light.png');" +
					"	-fx-background-size: 17px;" + 
					"   -fx-background-repeat: no-repeat;" + 
					"   -fx-background-position: 50%;");	
	}


	public boolean getActiveTurn()
	{ return(activeTurn); }
	
	
	public void setCondition(Condition condition)
	{
		this.condition.add(condition);
		System.out.println(condition.getName());
	}
	
	public ArrayList<Condition> getCondition()
	{
		return condition;
	}



	private class KeyTypedHandler implements EventHandler<KeyEvent>
	{

		@Override
		public void handle(KeyEvent event) 
		{
			if (event.getSource() == textFieldInitiative || event.getSource() == textFieldHitPoints) {
				if (!Character.isDigit(event.getCharacter().charAt(0)))
					event.consume();
			}	

			if (event.getSource() == textFieldHitPoints	&& textFieldHitPoints.getText() == null)
				event.consume();		
			if (event.getSource() == textFieldInitiative && textFieldInitiative.getText() == null)
				event.consume();
		}
	}
}










