package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import dao.Combatant;
import dao.Condition;
import dao.ConditionDAO;
import dao.DBConnection;
import dao.Monster;
import dao.MonsterDAO;
import dao.PlayerCharacter;
import dao.PlayerCharacterDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
//import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class EncounterManagerController implements Initializable
{

	// In einer Zeile geschrieben konnte Scene Builder die Buttons nicht finden....

	@FXML
	Button buttonDiceD100; 	

	@FXML 
	Button buttonDiceD20;

	@FXML
	Button buttonDiceD12;

	@FXML
	Button buttonDiceD10;

	@FXML
	Button buttonDiceD8;

	@FXML
	Button buttonDiceD6;
	// In einer Zeile geschrieben konnte Scene Builder die Buttons nicht finden....
	@FXML
	Button buttonDiceD4;

	@FXML
	Button buttonDiceD2;

	@FXML
	Button buttonDice9;

	@FXML
	Button buttonDice8;

	@FXML
	Button buttonDice7;

	@FXML
	Button buttonDice6;

	@FXML
	Button buttonDice5;

	@FXML
	Button buttonDice4;
	// In einer Zeile geschrieben konnte Scene Builder die Buttons nicht finden....
	@FXML
	Button buttonDice3;

	@FXML
	Button buttonDice2;

	@FXML
	Button buttonDice1;

	@FXML
	Button buttonDice0;

	@FXML
	Button buttonDicePlus;
	// In einer Zeile geschrieben konnte Scene Builder die Buttons nicht finden....
	@FXML
	Button buttonDiceMinus;

	@FXML
	Button buttonDiceRoll;

	@FXML
	Button buttonDiceClear;
	// In einer Zeile geschrieben konnte Scene Builder die Buttons nicht finden....

	@FXML
	Button buttonDiceClearAll;


	@FXML
	Button buttonTurn;

	@FXML
	Button buttonDeathSavingThrowSuccess;

	@FXML
	Button buttonDeathSavingThrowFail;

	@FXML
	ToggleButton toggleGeneralStats;

	@FXML
	ToggleButton toggleCombatStats;

	// In einer Zeile geschrieben konnte Scene Builder die Buttons nicht finden....
	@FXML
	Label labelStatus,labelMonsterTableFilter, labelCharacterTableFilter;

	@FXML
	Label lblMonsterName;

	@FXML
	Label lblMonsterNameCombat;

	@FXML
	Label lblMonsterType;

	@FXML
	Label lblMonsterChallenge;

	@FXML
	Label lblMonsterArmorClass;

	@FXML
	Label lblMonsterHitpoints;

	@FXML
	Label lblMonsterStr;

	@FXML
	Label lblMonsterDex;

	@FXML
	Label lblMonsterCon;
	// In einer Zeile geschrieben konnte Scene Builder die Buttons nicht finden....
	@FXML
	Label lblMonsterInt;

	@FXML
	Label lblMonsterWis;

	@FXML
	Label lblMonsterCha;

	@FXML
	Label lblCharacterName;

	@FXML
	Label lblMonsterMovement;

	@FXML
	Label lblMonsterSkills;
	
	@FXML
	Label lblMonsterResistance;
	
	@FXML
	Label lblMonsterConditionImmunities;

	@FXML
	Label lblMonsterDmgImmunities;

	@FXML
	Label lblMonsterSavingThrows;

	@FXML
	Label lblMonsterSenses;

	@FXML
	Label lblMonsterLanguages;
	
	@FXML
	Label lblCharClass;
	
	@FXML
	Label lblCharRace;
	
	@FXML
	Label lblCharAC;
	
	@FXML
	Label lblCharAllignment;
	
	@FXML
	Label lblCharMovement;
	
	@FXML
	Label lblCharStr;
	
	@FXML
	Label lblCharDex;
	
	@FXML
	Label lblCharCon;
	
	@FXML
	Label lblCharInt;
	
	@FXML
	Label lblCharWis;
	
	@FXML
	Label lblCharCha;
	
	@FXML
	Label lblCharSenses;
	
	@FXML
	Label lblCharLanguages;
	
	@FXML
	Label lblDeathSavingThrows;

	@FXML
	Label lblDeathSavingThrows1;


	// In einer Zeile geschrieben konnte Scene Builder die Buttons nicht finden....


	@FXML
	TextField textFieldDiceInput,textFieldDiceResult,textFieldMonsterTableFilter, textFieldDmg ;

	@FXML
	TextField textFieldCharacterTableFilter;
	
	@FXML
	TextArea textAreaMonsterActions;
	
	@FXML
	TextArea textAreaMonsterDescription;
	
	@FXML
	TextArea textAreaMonsterTraits;
	
	@FXML
	TextArea textAreaMonsterLegendary;
	
	@FXML
	ListView<Condition> listViewMonsterConditions;
	
	
	@FXML
	Tab monsterDetail, characterDetail;
	
	@FXML
	Tab monsterDB;

	@FXML
	VBox vBoxEncounter;

	@FXML
	TabPane mainTabPane;

	@FXML
	TabPane monsterTabPane;

	@FXML
	Tab tabMonsterCombatStats;

	@FXML
	Tab tabMonsterGeneralStats;
	
	@FXML
	TitledPane titledPaneMonsterActions;
	
	@FXML
	Accordion accordionMonsterDetail;
	
	@FXML
	ComboBox<Condition> comboBoxConditions;

	@FXML
	TableView<Monster> tableMonster;

	@FXML
	TableView<PlayerCharacter> tableCharacter;

	@FXML
	private TableColumn<Monster, String> pkMonsterCol,monsterNameCol,monsterTypeCol,monsterChallengeCol;

	@FXML
	private TableColumn<PlayerCharacter, String> characterClassCol;

	@FXML
	private TableColumn<PlayerCharacter, String> pkCharacterCol;

	@FXML
	private TableColumn<PlayerCharacter, String> characterNameCol;

	@FXML
	private TableColumn<PlayerCharacter, Boolean> characterPCCol;

	@FXML
	private TableColumn<PlayerCharacter, Integer> characterLVLCol;

	@FXML
	private TableColumn<PlayerCharacter, String> characterRaceCol;

	private CombatantPane combatantPane;
	private ObservableList<Condition> tableViewConditions, listViewConditions;
	private ObservableList<Monster> tableViewMonsterData;
	private ObservableList<PlayerCharacter> tableViewCharacterData;
	private ArrayList<CombatantPane> combatants;
	private ArrayList<Dice.Type> clickedDice;
	private ArrayList<Dice> allDice;
	private int turnCount; 
	//private ComboBoxConditionListener listener;




	public EncounterManagerController()
	{
		clickedDice = new ArrayList<Dice.Type>();
		combatants = new ArrayList<CombatantPane>();
		
		
		
	}


	@FXML
	private void closeAction()
	{
		EncounterManager.closeManager();
	}
	
	
	@FXML
	private void importXML()
	{
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Compendium XML file");
		chooser.getExtensionFilters().addAll(
				new ExtensionFilter("XML files", "*.xml"),
				new ExtensionFilter("All files", "*.*")
				);
		File file = chooser.showOpenDialog(EncounterManager.getStage());
		if (file != null) {
			try {
				XMLImporter imp = new XMLImporter(file);
				imp.import_to_db();
				initializeMonsterTable();
			} catch (Exception e) {
				e.printStackTrace();
				Alert err = new Alert(AlertType.ERROR);
				err.setTitle("Import error");
				err.setContentText(e.toString());
				err.setHeaderText(null);
				err.setResizable(true);
				err.showAndWait();
			}
		}
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		DBConnection.openMariaDB();
		labelStatus.setText("Connected Database: " + DBConnection.getCatalog());

		initializeMonsterTable();
		initializeCharacterTable();
		
		initializeToggleButtons(); 
		showDeathSavingThrowElements(false);
		setGraphics();
		
		//listener = new ComboBoxConditionListener();
		comboBoxConditions.valueProperty().addListener(new ComboBoxConditionListener());

		ControllerKeyTypedHandler keyTypedEvent = new ControllerKeyTypedHandler();
		textFieldDmg.setOnKeyTyped(keyTypedEvent);

		//Weil die Bearbeitung  von Elementen, die deaktiviert sind, nervt im Scene Builder. 
		monsterDetail.setDisable(true);
		characterDetail.setDisable(true);

		hideUnimplementedStuff();
		
		
	}


	private void initializeMonsterTable()
	{


		pkMonsterCol.setCellValueFactory(new PropertyValueFactory<>("primaryKey"));
		monsterNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		monsterTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		monsterChallengeCol.setCellValueFactory(new PropertyValueFactory<>("challenge"));

		List<Monster> list = MonsterDAO.getMonsters();
		tableViewMonsterData = FXCollections.observableList(list);
		tableMonster.setItems(tableViewMonsterData);

		//		for(Monster p : list){
		//			System.out.println(p.getMonsterName());
		//			System.out.println(p.getMonsterType());
		//			System.out.println(p.getMonsterChallenge());
		//			}

	}

	private void initializeCharacterTable()
	{

		pkCharacterCol.setCellValueFactory(new PropertyValueFactory<>("primaryKey"));
		characterNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		characterClassCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		characterPCCol.setCellValueFactory(new PropertyValueFactory<>("PC"));
		characterLVLCol.setCellValueFactory(new PropertyValueFactory<>("LVL"));
		characterRaceCol.setCellValueFactory(new PropertyValueFactory<>("Race"));


		List<PlayerCharacter> list = PlayerCharacterDAO.getCharacters();
		tableViewCharacterData = FXCollections.observableList(list);
		tableCharacter.setItems(tableViewCharacterData);

		//			for(PlayerCharacter p : list){
		//					System.out.println(p.getPC());
		//					System.out.println(p.getLVL());
		//					System.out.println(p.getRace());
		//				}

	}

	private void initializeConditionsComboBox()
	{
		
		comboBoxConditions.getSelectionModel().clearSelection();
		comboBoxConditions.getItems().clear();
				
		comboBoxConditions.setEditable(false); 

		List<Condition> list = ConditionDAO.getConditions();

		tableViewConditions = FXCollections.observableList(list);

		comboBoxConditions.setItems(tableViewConditions);

		//comboBoxConditions.valueProperty().addListener();
		comboBoxConditions.setCellFactory(new Callback<ListView<Condition>,ListCell<Condition>>(){
					@Override
					public ListCell<Condition> call(ListView<Condition> l){
						//return new ListCell<Condition>(){
						 final ListCell<Condition> cell = new ListCell<Condition>() {
		                        {
		                            //super.setPrefWidth(100);
		                        }    
							
											@Override
												protected void updateItem(Condition item, boolean empty) {
													super.updateItem(item, empty);
													if (item == null || empty) {
														setGraphic(null);
													} else {
														setText(item.getName());
													}
												}
						} ;
						   return cell;
					}
				});	
			
	}

	
	private void setConditionFromComboBox() {

		if (!comboBoxConditions.getSelectionModel().isEmpty())
		{
			Condition condition = comboBoxConditions.getSelectionModel().getSelectedItem();
			//System.out.println(condition.getName());
	
			this.combatantPane.setCondition(condition);
			//comboBoxConditions("Add Condition");
		}

	}
	
	private void initializeListViewMonsterConditions(MonsterPane monsterPane)
	{ 
		
		
		List<Condition> list = monsterPane.getCondition();

		listViewConditions = FXCollections.observableList(list);
		
		
			listViewMonsterConditions.setItems(listViewConditions);
			listViewMonsterConditions.setCellFactory(new Callback<ListView<Condition>,ListCell<Condition>>(){
				@Override
				public ListCell<Condition> call(ListView<Condition> l){
					//return new ListCell<Condition>(){
					 final ListCell<Condition> cell = new ListCell<Condition>() {
	                        {
	                            //super.setPrefWidth(100);
	                        }    
						
										@Override
											protected void updateItem(Condition item, boolean empty) {
												super.updateItem(item, empty);
												if (item == null || empty) {
													setGraphic(null);
												} else {
													setText(item.getName());
												}
											}
					} ;
					   return cell;
				}
			});	
	}
	
	
	private void initializeToggleButtons() 
	{
		toggleGeneralStats.setSelected(true);
		toggleCombatStats.setSelected(false);
	}

	
	@FXML
	private void toggleGeneralStats()
	{ toggleCombatStats.setSelected(false); }

	
	@FXML
	private void toggleCombatStats()
	{ toggleGeneralStats.setSelected(false); }

	
	

	@FXML
	private void tabelsTabMouseEvent(MouseEvent e)
	{


		if (e.getSource() == tableMonster && (e.getClickCount() == 2))
		{
			Combatant combatant;
			combatant = tableMonster.getItems().get(tableMonster.getSelectionModel().getSelectedIndex());
			addMonsterPane(combatant.getPrimaryKey());
		}
		else if (e.getSource() == tableCharacter && (e.getClickCount() == 2))
		{
			PlayerCharacter combatant;
			combatant = tableCharacter.getItems().get(tableCharacter.getSelectionModel().getSelectedIndex());
			if(combatant.getPC())
				addPlayerCharacterPane(combatant.getPrimaryKey());
			else
				addNonPlayerCharacterPane(combatant.getPrimaryKey());
		}

	}


	private void addMonsterPane(long primaryKey) 
	{
		CombatantPane combatant = new MonsterPane(primaryKey); 
		MouseClickedhandler mouseClicked = new MouseClickedhandler();
		combatant.setOnMouseClicked(mouseClicked);
		combatants.add(combatant);
		vBoxEncounter.getChildren().add(combatant);
		combatant.prefWidthProperty().bind(vBoxEncounter.widthProperty()); //test
	}


	private void addPlayerCharacterPane(long primaryKey) 
	{
		CombatantPane combatant = new PlayerCharacterPane(primaryKey);
		MouseClickedhandler mouseClicked = new MouseClickedhandler();
		combatants.add(combatant);
		combatant.setOnMouseClicked(mouseClicked);
		vBoxEncounter.getChildren().add(combatant);
	}


	private void addNonPlayerCharacterPane(long primaryKey) 
	{
		CombatantPane combatant = new NonPlayerCharacterPane(primaryKey);
		MouseClickedhandler mouseClicked = new MouseClickedhandler();
		combatants.add(combatant);
		combatant.setOnMouseClicked(mouseClicked);
		vBoxEncounter.getChildren().add(combatant);
	}

	@FXML
	private void removeCombatants() //Kann nur aktiven Combatant entfernen, wenn er der letzte ist. läuft noch nicht sauber durch, man muss mehrmals klicken
	{
		boolean checkStillSelected = true;

		while (checkStillSelected)
		{

			checkStillSelected = false;
			
			for (int j = 0; j < combatants.size(); j++) {
				if (combatants.get(j).isSelected() && combatants.get(j).getActiveTurn() == false) {				
					combatants.remove(j); 
					checkStillSelected = true;
				}
			}
		}
		for (int j = 0; j < combatants.size(); j++)
		if  (combatants.get(j).isSelected() && combatants.get(j).getActiveTurn() == true && combatants.size() == 1)
		{			
			combatants.remove(j); 
		}
	
		if (!combatants.isEmpty() && turnCount > 0)
			sortCombatants();
		else if (combatants.isEmpty())
			resetEncounterManager();
		else {
			vBoxEncounter.getChildren().clear();
			for (int j = 0; j < combatants.size();j++)
				vBoxEncounter.getChildren().add(combatants.get(j));
		}
	}



	@FXML
	private void sortCombatants()
	{
		// prüft ob CombatantPane Array leer ist.
		// prüft Erster Zug, falls nicht:
		// Sortiert CombatantPanes nach Initiative Order
		// setzt initiative Werte über 40 auf 40
		// schreibt CombatantPanes aus dem Array in die V-Box
		// schaufelt durch die V-Box bis zur aktiven CombatantPane 
		//falls erster Zug, sortiert nach Initiative und setzt die Hitpoints fest
		//wechselt zur Detailansicht des aktiven Combatant



		if (!combatants.isEmpty())
		{
			for (int j = 0; j < combatants.size();j++) {						
				if (combatants.get(j).getCombatantInitiative() > 40)
					combatants.get(j).setCombatantInitiative(40); }

			if (turnCount > 0)
			{
				vBoxEncounter.getChildren().clear();

				for (int i = 40; i > 0; i--)
				{
					for (int j = 0; j < combatants.size();j++)
					{

						if (combatants.get(j).getCombatantInitiative() == i)
						{
							combatants.get(j).combatReady(); 
							vBoxEncounter.getChildren().add(combatants.get(j));
						}

					}
				}

				while (((CombatantPane) vBoxEncounter.getChildren().get(0)).getActiveTurn() == false)
				{
					CombatantPane Combatant = (CombatantPane) vBoxEncounter.getChildren().get(0);
					vBoxEncounter.getChildren().remove(0);
					vBoxEncounter.getChildren().add(Combatant);
				}

			}
			else
			{	
				vBoxEncounter.getChildren().clear();

				for (int i = 40; i > 0; i--)
				{
					for (int j = 0; j < combatants.size();j++) {
						if (combatants.get(j).getCombatantInitiative() == i) {
							combatants.get(j).combatReady(); 
							vBoxEncounter.getChildren().add(combatants.get(j));
						}

					}

				}

			}
		}
		else
		{
			resetEncounterManager();
		}
	}




	@FXML
	private void turn()
	{

		if(combatants.isEmpty())
			return;

		if (turnCount > 0)
		{
			CombatantPane oldCombatant = (CombatantPane) vBoxEncounter.getChildren().get(0); 
			oldCombatant.setActiveTurn(false);

			vBoxEncounter.getChildren().remove(0);
			vBoxEncounter.getChildren().add(oldCombatant);

			((CombatantPane) vBoxEncounter.getChildren().get(0)).setActiveTurn(true);

			CombatantPane newCombatant = (CombatantPane) vBoxEncounter.getChildren().get(0);

			if (!newCombatant.isCombatInitialized())
				newCombatant.combatReady();

			if (newCombatant.isUnconscious())
			{
				String text = "Death Saving Throws! \n succeeded: " + newCombatant.getDeathSavingThrowSuccess() + " failed: " + newCombatant.getDeathSavingThrowFail();
				lblDeathSavingThrows.setText(text);
				showDeathSavingThrowElements(true);
			}
			else
				showDeathSavingThrowElements(false);
		}
		else
		{
			buttonTurn.setStyle("-fx-background-image: url('/images/hourglass.png');" + 
					"-fx-background-size: 30px;" + 
					"    -fx-background-repeat: no-repeat;" + 
					"    -fx-background-position: 50%;");

			sortCombatants();
			((CombatantPane) vBoxEncounter.getChildren().get(0)).setActiveTurn(true);
		}		

		if (vBoxEncounter.getChildren().get(0) instanceof MonsterPane)
			showMonsterDetail((MonsterPane) vBoxEncounter.getChildren().get(0));
		else
			showCharacterDetail((CombatantPane) vBoxEncounter.getChildren().get(0));

		deSelectAll();

		turnCount++;
		labelStatus.setText("  Turn Nr.: " + turnCount);

	}


	@FXML
	private void damageCombatant()
	{
		if(turnCount > 0)
		{
			for (int i = 0; i < combatants.size();i++)
			{
				if(combatants.get(i).isSelected())
					combatants.get(i).changeHitpoints(Integer.valueOf(textFieldDmg.getText()));
			}
		}
	}

	@FXML
	private void healComabatant()
	{
		if(turnCount > 0)
		{
			for (int i = 0; i < combatants.size();i++)
			{
				if(combatants.get(i).isSelected())
					combatants.get(i).changeHitpoints(Integer.valueOf(textFieldDmg.getText())*(-1));
			}
		}
	}



	private void showDeathSavingThrowElements(boolean value)
	{
		lblDeathSavingThrows.setVisible(value);
		buttonDeathSavingThrowSuccess.setVisible(value);
		buttonDeathSavingThrowFail.setVisible(value);
	}

	@FXML
	private void deathSavingThrowSuccess()
	{
		CombatantPane combatant = (CombatantPane) vBoxEncounter.getChildren().get(0); 
		combatant.setDeathSavingThrow(true);
		showDeathSavingThrowElements(false);
	}

	@FXML
	private void deathSavingThrowFail()
	{
		CombatantPane combatant = (CombatantPane) vBoxEncounter.getChildren().get(0); 
		combatant.setDeathSavingThrow(false);
		showDeathSavingThrowElements(false);

	}


	@FXML
	private void deSelectAll()
	{
		for (int i = 0; i < combatants.size(); i++)
			combatants.get(i).setSelected(false);
	}

	@FXML
	private void selectAll()
	{
		for (int i = 0; i < combatants.size(); i++)
			combatants.get(i).setSelected(true);
	}



	@FXML
	private void resetEncounterManager()
	{
		vBoxEncounter.getChildren().clear();
		combatants.clear();
		turnCount = 0;
		labelStatus.setText("Turn Nr.: " + turnCount);
		buttonTurn.setStyle("-fx-background-image: url('/images/crossedswords.png');" +
				"-fx-background-size: 30px;" + 
				"    -fx-background-repeat: no-repeat;" + 
				"    -fx-background-position: 50%;");
		mainTabPane.getSelectionModel().select(monsterDB);
		monsterDetail.setDisable(true);
		characterDetail.setDisable(true);
	}



	@FXML
	private void diceBtnClicked(Event e)
	{
		if(e.getSource() == buttonDiceD100)
			clickedDice.add(Dice.Type.d100);
		else if(e.getSource() == buttonDiceD20)
			clickedDice.add(Dice.Type.d20);
		else if(e.getSource() == buttonDiceD12)
			clickedDice.add(Dice.Type.d12);
		else if(e.getSource() == buttonDiceD10)
			clickedDice.add(Dice.Type.d10);
		else if(e.getSource() == buttonDiceD8)
			clickedDice.add(Dice.Type.d8);
		else if(e.getSource() == buttonDiceD6)
			clickedDice.add(Dice.Type.d6);
		else if(e.getSource() == buttonDiceD4)
			clickedDice.add(Dice.Type.d4);
		else if(e.getSource() == buttonDiceClear && !clickedDice.isEmpty())
			clickedDice.remove(clickedDice.size() - 1);
		else if (e.getSource() == buttonDiceClearAll)
		{
			clickedDice.clear();
			textFieldDiceInput.setText("");
			textFieldDiceResult.setText("");
			return;
		}
		else if(e.getSource() == buttonDiceRoll) {
			if(!clickedDice.isEmpty())
			{
				diceTabWriteResultField();
				return;
			}
		}

		String text = "";
		allDice = new ArrayList<Dice>();

		for (Dice.Type dieType : Dice.Type.values()) {
			Dice die = new Dice(dieType);
			for (int j = 0; j < clickedDice.size(); j++) {
				if (dieType == clickedDice.get(j))
					die.increaseNumber();
			}

			if (die.getNumber() > 0) {
				if (!text.equals(""))
					text += " + ";
				text +=	die;
				allDice.add(die);
			}
		}	

		textFieldDiceInput.setText(text);
	}


	private void diceTabWriteResultField()
	{
		if (allDice.isEmpty())
			return;

		String text = "";
		int roll;
		int result = 0;

		for (Dice die : allDice) {
			roll = die.roll();
			result += roll;
			text += die.getRollString() + " + ";
		}	

		textFieldDiceResult.setText(result + " = " + text.substring(0, text.length() - 3));
	}

	private void setGraphics()
	{
		buttonTurn.setStyle("-fx-background-image: url('/images/crossedswords.png');" +
				"	 -fx-background-size: 30px;" + 
				"    -fx-background-repeat: no-repeat;" + 
				"    -fx-background-position: 50%;");
		buttonDiceRoll.setStyle("-fx-background-image: url('/images/d20.png');" +
				"	-fx-background-size: 20px;" + 
				"   -fx-background-repeat: no-repeat;" + 
				"   -fx-background-position: 50%;");
		buttonDiceClear.setStyle("-fx-background-image: url('/images/deleteTextfield.png');" +
				"	-fx-background-size: 20px;" + 
				"   -fx-background-repeat: no-repeat;" + 
				"   -fx-background-position: 50%;");
		buttonDiceClearAll.setStyle("-fx-background-image: url('/images/clear.png');" +
				"	-fx-background-size: 20px;" + 
				"   -fx-background-repeat: no-repeat;" + 
				"   -fx-background-position: 50%;");
		labelCharacterTableFilter.setStyle("-fx-background-image: url('/images/Filter.png');" +
				"	-fx-background-size: 20px;" + 
				"   -fx-background-repeat: no-repeat;" + 
				"   -fx-background-position: 50%;");
		labelMonsterTableFilter.setStyle("-fx-background-image: url('/images/Filter.png');" +
				"	-fx-background-size: 20px;" + 
				"   -fx-background-repeat: no-repeat;" + 
				"   -fx-background-position: 50%;");
		//vBoxEncounter.setStyle("-fx-background-color:red");
	}


	public void showMonsterDetail(MonsterPane monsterPane)
	{
		this.combatantPane = monsterPane;
		Monster monster = monsterPane.getCombatant();
		initializeConditionsComboBox();

		lblMonsterName.setText(monster.getName());
		lblMonsterNameCombat.setText(monster.getName());
		lblMonsterType.setText(monster.getType() + ", " + monster.getAlignment());
		lblMonsterArmorClass.setText("AC: " + String.valueOf(monster.getArmorClass()));
		lblMonsterHitpoints.setText("HPmax.: "  + monsterPane.getHitpointsMaxString() + " HP-Random: " + monster.getHitpointsRandom());
		lblMonsterMovement.setText("Speed: " + monster.getMovement() +" ft.");

		String text = "STR \n" + monster.getStr() + " (" + Rules.modifier(monster.getStr()) +")";
		lblMonsterStr.setText(text);
		text = "DEX \n" + monster.getDex() + " (" + Rules.modifier(monster.getDex()) +")";
		lblMonsterDex.setText(text);
		text = "CON \n" + monster.getCon() + " (" + Rules.modifier(monster.getCon()) +")";
		lblMonsterCon.setText(text);
		text = "INT \n" + monster.getInt() + " (" + Rules.modifier(monster.getInt()) +")";
		lblMonsterInt.setText(text);
		text = "WIS \n" + monster.getWis() + " (" + Rules.modifier(monster.getWis()) +")";
		lblMonsterWis.setText(text);
		text = "CHA\n" + monster.getCha() + " (" + Rules.modifier(monster.getCha()) +")";
		lblMonsterCha.setText(text);

		lblMonsterSavingThrows.setText("Saving Throws: " + monster.getSavingThrows());
		lblMonsterSkills.setText("Skills: " + monster.getSkills());
		lblMonsterResistance.setText("Resistances: " + monster.getResistance());
		lblMonsterConditionImmunities.setText("Condition Immunities: " + monster.getCondidtionImmunities());
		lblMonsterDmgImmunities.setText("Damage Immunities: " + monster.getDmgImmunities());
		lblMonsterSenses.setText("Senses: " + monster.getSenses());
		lblMonsterLanguages.setText("Languages: " + monster.getLanguages());
		lblMonsterChallenge.setText("CR: " + monster.getChallenge());
		textAreaMonsterActions.setText(monster.getActions());
		textAreaMonsterDescription.setText(monster.getDescription());
		textAreaMonsterTraits.setText(monster.getTraits());
		textAreaMonsterLegendary.setText(monster.getLegendary());

		//Combat Stats
		
		initializeListViewMonsterConditions(monsterPane);
		
		if(monsterPane.isUnconscious()) {	
			text = "Death Saving Throws: \n succeeded: " + monsterPane.getDeathSavingThrowSuccess() + " failed: " + monsterPane.getDeathSavingThrowFail();
			lblDeathSavingThrows1.setText(text);
			lblDeathSavingThrows1.setVisible(true);
		}
		else
		{ lblDeathSavingThrows1.setVisible(false); }

		monsterDetail.setDisable(false);
		characterDetail.setDisable(true);

		mainTabPane.getSelectionModel().select(monsterDetail);
		titledPaneMonsterActions.setExpanded(true);

		if (toggleGeneralStats.isSelected())		
			monsterTabPane.getSelectionModel().select(tabMonsterGeneralStats);
		else
			monsterTabPane.getSelectionModel().select(tabMonsterCombatStats);

	}


	public void showCharacterDetail(CombatantPane CombatantPane)
	{
		PlayerCharacter character = (PlayerCharacter) CombatantPane.getCombatant();
		this.combatantPane = CombatantPane;
		
		initializeConditionsComboBox();
		
		lblCharacterName.setText(character.getName());
		lblCharClass.setText("Lvl " + character.getLVL() + " " + character.getType() + " (" + character.getArchetype() + ")");
		lblCharRace.setText(character.getRace());
		lblCharAllignment.setText("Alignment: " + character.getAlignment());
		lblCharAC.setText("AC: " + character.getArmorClass());
		lblCharMovement.setText("Speed: " + character.getMovement());
		
		String text = "STR \n" + character.getStr() + " (" + Rules.modifier(character.getStr()) +")";
		lblCharStr.setText(text);
		text = "DEX \n" + character.getDex() + " (" + Rules.modifier(character.getDex()) +")";
		lblCharDex.setText(text);
		text = "CON \n" + character.getCon() + " (" + Rules.modifier(character.getCon()) +")";
		lblCharCon.setText(text);
		text = "INT \n" + character.getInt() + " (" + Rules.modifier(character.getInt()) +")";
		lblCharInt.setText(text);
		text = "WIS \n" + character.getWis() + " (" + Rules.modifier(character.getWis()) +")";
		lblCharWis.setText(text);
		text = "CHA \n" + character.getCha() + " (" + Rules.modifier(character.getCha()) +")";
		lblCharCha.setText(text);
		
		lblCharSenses.setText("Senses: " + character.getSenses());
		lblCharLanguages.setText("Languages: " + character.getLanguages());
		

		monsterDetail.setDisable(true);
		characterDetail.setDisable(false);
		mainTabPane.getSelectionModel().select(characterDetail);
	}


	@FXML	
	private void filterMonsterData()
	{
		String filterText = textFieldMonsterTableFilter.getText();
		// Alle Einträge des Datenmodell in eine Filter-Liste übertragen.
		FilteredList<Monster> filteredList = new FilteredList<>(tableViewMonsterData);

		// Anonyme Klasse Predicate, die die Methode test() überschreibt.
		// Nur die Einträge, für die die Methode test() den Wert 'true'
		// zurückliefert, bleiben in der Filter-Liste zurück.

		filteredList.setPredicate(new Predicate<Monster>()
		{
			boolean retValue;

			@Override
			public boolean test(Monster monster)
			{
				retValue = false;

				// Keine Filterbedingung, alle Einträge anzeigen
				if (filterText == null || filterText.isEmpty())
					retValue = true;

				// Vergleich mit PLZ oder Ort ohne Berücksichtigung von Groß-/Kleinschreibung

				else if (monster.getName().contains(filterText) || monster.getType().toLowerCase().contains(filterText.toLowerCase()) || monster.getChallenge().contains(filterText) )
					retValue = true;

				return retValue;
			}
		});

		// Übergebn der gefilterten Einträge an eine SortedList
		SortedList<Monster> sortedList = new SortedList<>(filteredList);

		// Sortierkriterien für die SortedList aus der TableView übernehmen
		sortedList.comparatorProperty().bind(tableMonster.comparatorProperty());

		// Gefilterte und sortierte Liste der TableView zuweisen
		tableMonster.setItems(sortedList);

		// Erste Zeile in TableView selektieren, damit sich die
		// Anzeige in der Statusleiste aktualisiert
		if (sortedList.size() > 0)
			tableMonster.getSelectionModel().select(0);
	}


	@FXML	
	private void filterPlayerData()
	{
		String filterText = textFieldCharacterTableFilter.getText();
		// Alle Einträge des Datenmodell in eine Filter-Liste übertragen.
		FilteredList<PlayerCharacter> filteredList = new FilteredList<>(tableViewCharacterData);

		// Anonyme Klasse Predicate, die die Methode test() überschreibt.
		// Nur die Einträge, für die die Methode test() den Wert 'true'
		// zurückliefert, bleiben in der Filter-Liste zurück.

		filteredList.setPredicate(new Predicate<PlayerCharacter>()
		{
			boolean retValue;

			@Override
			public boolean test(PlayerCharacter character)
			{
				retValue = false;

				// Keine Filterbedingung, alle Einträge anzeigen
				if (filterText == null || filterText.isEmpty())
					retValue = true;

				// Vergleich mit PLZ oder Ort ohne Berücksichtigung von Groß-/Kleinschreibung

				else if (character.getName().contains(filterText) || character.getType().toLowerCase().contains(filterText.toLowerCase())|| character.getRace().contains(filterText)  || character.getLVL().contains(filterText) )
					retValue = true;

				return retValue;
			}
		});

		// Übergebn der gefilterten Einträge an eine SortedList
		SortedList<PlayerCharacter> sortedList = new SortedList<>(filteredList);

		// Sortierkriterien für die SortedList aus der TableView übernehmen
		sortedList.comparatorProperty().bind(tableCharacter.comparatorProperty());

		// Gefilterte und sortierte Liste der TableView zuweisen
		tableCharacter.setItems(sortedList);

		// Erste Zeile in TableView selektieren, damit sich die
		// Anzeige in der Statusleiste aktualisiert
		if (sortedList.size() > 0)
			tableCharacter.getSelectionModel().select(0);
	}



	private void hideUnimplementedStuff()
	{

		buttonDice9.setVisible(false);
		buttonDice8.setVisible(false);;
		buttonDice7.setVisible(false);;
		buttonDice6.setVisible(false);;
		buttonDice5.setVisible(false);;
		buttonDice4.setVisible(false);;
		buttonDice3.setVisible(false);;
		buttonDice2.setVisible(false);;
		buttonDice1.setVisible(false);;
		buttonDice0.setVisible(false);;
		buttonDicePlus.setVisible(false);;
		buttonDiceMinus.setVisible(false);;

	}


	private class MouseClickedhandler implements EventHandler<MouseEvent>
	{

		@Override
		public void handle(MouseEvent event)
		{
			if(event.getSource() instanceof MonsterPane )
			{
				showMonsterDetail((MonsterPane) event.getSource());
			}
			else if(event.getSource() instanceof PlayerCharacterPane )
			{
				showCharacterDetail((CombatantPane) event.getSource());
			}	
			else if(event.getSource() instanceof NonPlayerCharacterPane )
			{
				showCharacterDetail((CombatantPane) event.getSource());
			}
		}

	}


	private class ControllerKeyTypedHandler implements EventHandler<KeyEvent>
	{

		@Override
		public void handle(KeyEvent event) 
		{
			if (event.getSource() == textFieldDmg) {
				if (!Character.isDigit(event.getCharacter().charAt(0)) || textFieldDmg.getText() == null )
					event.consume();
			}	
		}
	}
	private class ComboBoxConditionListener implements ChangeListener<Condition>
	{

		@Override
		public void changed(ObservableValue<? extends Condition> observable, Condition oldValue, Condition newValue)
		{
			setConditionFromComboBox();
			initializeListViewMonsterConditions((MonsterPane) combatantPane);
			
			
						
		}
		
	}

}
