package view;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Classe gérant l'interface du menu principal
 * 
 */
public class GameMenuView extends Group {
	Text titre, quitter, nouvelle, controles;
	VBox texts;
	ImageView fond;
	BorderPane layout;
	GameView gameView;
	ControlesView ctrlMenu;
	
	/**
	   * Constructeur de l'interface
	   * 
	   */
	public GameMenuView() {
		Image img = new Image("assets/FondMenu.png");
		
		this.fond = new ImageView();
		this.fond.setImage(img);
		
		this.titre = new Text("DodgeBall Madness");
		this.titre.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		this.titre.setFill(Color.WHITE);
		this.titre.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		
		this.controles = new Text("Contrôles");
		this.controles.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.controles.setFill(Color.WHITE);
		this.controles.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		this.controles.setCursor(Cursor.HAND);
		this.controles.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	controlesMenuAffiche();
            }
		});		
		this.controles.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	controles.setFill(Color.YELLOW);
            }
		});		
		this.controles.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	controles.setFill(Color.WHITE);
            }
		});
		
		this.nouvelle = new Text("Nouvelle Partie");
		this.nouvelle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.nouvelle.setFill(Color.WHITE);
		this.nouvelle.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		this.nouvelle.setCursor(Cursor.HAND);
		this.nouvelle.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	configNewGame();
            }
		});		
		this.nouvelle.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	nouvelle.setFill(Color.YELLOW);
            }
		});		
		this.nouvelle.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	nouvelle.setFill(Color.WHITE);
            }
		});
		
		this.quitter = new Text("Quitter");
		this.quitter.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.quitter.setFill(Color.WHITE);
		this.quitter.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		this.quitter.setCursor(Cursor.HAND);
		this.quitter.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	endApp();
            }
		});		
		this.quitter.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	quitter.setFill(Color.YELLOW);
            }
		});		
		this.quitter.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	quitter.setFill(Color.WHITE);
            }
		});
		
		this.texts = new VBox(30);
		this.texts.setAlignment(Pos.CENTER);
		this.texts.getChildren().addAll(this.nouvelle, this.controles, this.quitter);
		
		this.layout = new BorderPane();
		BorderPane.setAlignment(this.titre, Pos.TOP_CENTER);
		this.layout.setStyle("-fx-background-color: rgba(0,0,0,0.7);-fx-padding: 60px 0 0 0");
		this.layout.setMinWidth(600);
		this.layout.setMinHeight(600);
		this.layout.setCenter(this.texts);
		this.layout.setTop(this.titre);
		
		this.getChildren().addAll(this.fond, this.layout);
	}
	
	/**
	   * Fontcion pour les différents boutons
	   * 
	   */
	public void configNewGame(){
		this.getChildren().clear();
		this.gameView = new GameView(600, 600, 2, 1, 2, 1, 2); // width, height, nbBot, nbPlayers, nbBot2, nbBot2, nbBalss
		this.getChildren().add(gameView);
	}
	
	public void controlesMenuAffiche(){
		this.getChildren().clear();
		this.ctrlMenu = new ControlesView(this, null);
		this.getChildren().add(ctrlMenu);
	}
	
	public void retour(){
		this.getChildren().clear();
		this.getChildren().addAll(this.fond, this.layout);
	}
	
	public void endApp(){
		Platform.exit();
	}
}
