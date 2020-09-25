package application;
	
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


//To-Do
//Detailinfo image
//Dicetab NumBlock
//Beenden Dialog ist auskommentiert
//Problem mit active turn bei remove combatant
//MonsterNaming
//Option welche Detailansicht default ist ToggleGroup
//traverse Policy und shortcuts fehlen noch
//Dice__wenn man mehrmals würfeln tippt, 
//heal, damage und remove deaktivieren, wenn nichts ausgewählt ist
//Turn zurück, eigentlich nicht sinnvoll
//Sortbutton eigentlich überflüssig
//get und set lvl von CR trennen und int draus machen
//exp aus datenbank schmeißen und errechnen lassen


public class EncounterManager extends Application {
	

	private static Stage stage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("EncounterManager.fxml"));
			Scene scene = new Scene(root,1100,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest(new WindowEventHandler());
			initialize(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void initialize(Stage primaryStage)
	{
		stage = primaryStage;
		//primaryStage.setTitle("D&D EncounterManager 5e");
		primaryStage.setTitle("D&D TPK Assistant 5e");
		primaryStage.setResizable(true);
	}
	
	
	
	public static Stage getStage()
	{
		return(stage);
	}
	
	
	private static ButtonType showMessage(AlertType alertType, Image image, String title, String headerText, String message, ButtonType[] buttonTypes)
	{
		
		Alert alert = new Alert(alertType);
		
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(message);
		
		// In der Mitte des eigenen  Fensters anzeigen
		// ansonsten in der Mitte des Desktops.
		alert.initOwner(stage);
		
		// Benutzerdefiniertes Image anzeigen
		if (image != null)
			alert.setGraphic(new ImageView(image));
		
		// Standard Buttons (OK, Abbrechen) entfernen
		alert.getButtonTypes().clear();
				
		// Mit den übergebenen Buttons ersetzen
		alert.getButtonTypes().addAll(buttonTypes);
		
		// Standard Fokusreihenfolge für den ersten Button entfernen
		Button optButton = (Button) alert.getDialogPane().lookupButton( buttonTypes[0] );
		optButton.setDefaultButton(false);
		
		// Den Fokus auf den letzten Button setzen
		optButton = (Button) alert.getDialogPane().lookupButton(buttonTypes[buttonTypes.length - 1]);
		optButton.setDefaultButton(true);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		return result.get();
				
	}
	
	
	
	private static boolean queryExit()
	{
		boolean retValue = false;

		ButtonType optButton = showMessage(AlertType.CONFIRMATION, null, 
				                           "Close Programm", "Don't go! The Drones need you.", "Are you sure?",
				                           new ButtonType[] { ButtonType.YES, ButtonType.NO });

		if (optButton == ButtonType.YES)
			retValue = true;
		
		return retValue;
		//return true;
	}
	
	
	public static void closeManager()
	{
		if (queryExit())
			stage.close();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	private class WindowEventHandler implements EventHandler<WindowEvent>
	{

		@Override
		public void handle(WindowEvent e)
		{
			
			if (e.getEventType() == WindowEvent.WINDOW_CLOSE_REQUEST)
			{

				
				e.consume();

				
				if (queryExit())
					stage.close();

			}

		}

	}
	
	
}
