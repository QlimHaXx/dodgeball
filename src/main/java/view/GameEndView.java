package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Classe de l'interface de fin de jeu
 * 
 */
public class GameEndView extends Group {
	GameView gameView;
	BorderPane fond;
	VBox texts, titreV;
	Text quitter, restart, titre, team, gagne;
	
	/**
	   * Constructeur de l'interface
	   * 
	   * @param width largeur du menu
	   * @param height hauteur du menu
	   * @param gameView_ le menu du jeu depuis lequel est appélé cet écran
	   */
	public GameEndView(int width, int height, GameView gameView_) {
		this.gameView = gameView_;
		
		this.titreV = new VBox(20);
		this.titreV.setAlignment(Pos.CENTER);
		
		this.texts = new VBox(30);
		this.texts.setAlignment(Pos.CENTER);
		
		this.fond = new BorderPane();
		this.fond.setMinWidth(width);
		this.fond.setMinHeight(height);
		this.fond.setStyle("-fx-background-color: rgba(0,0,0,0.9); -fx-padding: 60px 0 0 0");
		
		this.titre = new Text("Partie Terminée");
		this.titre.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		this.titre.setFill(Color.WHITE);
		this.titre.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		
		this.gagne = new Text("Gagne !");
		this.gagne.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.gagne.setFill(Color.WHITE);
		this.gagne.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		
		this.team = new Text();
		this.team.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		this.team.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
        
        this.restart = new Text("Recommencer");
		this.restart.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.restart.setFill(Color.WHITE);
		this.restart.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		this.restart.setCursor(Cursor.HAND);
		this.restart.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	restart();
            }
		});
		this.restart.setOnMouseEntered(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               restart.setFill(Color.YELLOW);
           }
        });        
        this.restart.setOnMouseExited(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               restart.setFill(Color.WHITE);
           }
        });
		
		this.quitter = new Text("Quitter");
		this.quitter.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.quitter.setFill(Color.WHITE);
		this.quitter.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		this.quitter.setCursor(Cursor.HAND);
		this.quitter.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	gameView.endApp();
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
		
        this.titreV.getChildren().addAll(this.titre, this.team, this.gagne);
        
		this.texts.getChildren().addAll(this.restart, this.quitter);
		
		BorderPane.setAlignment(this.titreV, Pos.TOP_CENTER);
		
		this.fond.setTop(this.titreV);
		this.fond.setCenter(this.texts);		
		this.getChildren().add(this.fond);
	}
	
	/**
	   * Fontcion pour rélancé le jeu
	   * 
	   */
	public void restart(){
		this.gameView.game.setPause(false);
		this.getChildren().clear();
		this.gameView = new GameView(600, 600, 2, 1, 2, 1, 2); // width, height, nbBot, nbPlayers, nbBot2, nbBot2, nbBalss
		this.getChildren().add(gameView);
	}
	
	/**
	   * Fontcion pour rafraichir la vue
	   * 
	   */
	public void refresh(){
		this.getChildren().clear();
		this.getChildren().add(this.fond);
	}
	
	public Text getQuitter() {
		return quitter;
	}
	
	public Text getTeam() {
		return team;
	}
}

