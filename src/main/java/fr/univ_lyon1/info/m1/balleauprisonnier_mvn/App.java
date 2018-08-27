package fr.univ_lyon1.info.m1.balleauprisonnier_mvn;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.GameMenuView;
import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * Classe principale de l'application 
 * s'appuie sur javafx pour le rendu
 */
public class App extends Application {
	/**
	 * En javafx start() lance l'application
	 *
	 * On cree le SceneGraph de l'application ici
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			// Nom de la fenetre
	        primaryStage.setTitle("BalleAuPrisonnier");
	        
			Group root = new Group();
			Scene scene = new Scene(root);
			GameMenuView menu = new GameMenuView();
			
			root.getChildren().add(menu);
			
			// On ajoute la scene a la fenetre et on affiche
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
