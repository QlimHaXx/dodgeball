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
 * Classe gérant la vu du menu pause
 * 
 */
public class MenuPauseView extends Group {
	GameView gameView;
	BorderPane fond;
	VBox texts;
	Text quitter, reprendre, restart, controles, titre;
	ControlesView contorlesView;
	
	/**
	   * Constructeur de l'interface
	   * 
	   * @param width largeur
	   * @param height hauteur
	   * @param gameView_ vue d'où est applé cette vue
	   */
	public MenuPauseView(int width, int height, GameView gameView_) {
		this.gameView = gameView_;
		
		this.texts = new VBox(30);
		this.texts.setAlignment(Pos.CENTER);
		
		this.fond = new BorderPane();
		this.fond.setMinWidth(width);
		this.fond.setMinHeight(height);
		this.fond.setStyle("-fx-background-color: rgba(0,0,0,0.9); -fx-padding: 60px 0 0 0");
		
		this.titre = new Text("Partie En Pause");
		this.titre.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		this.titre.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		this.titre.setFill(Color.WHITE);
		
		this.reprendre = new Text("Reprendre");
		this.reprendre.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.reprendre.setFill(Color.WHITE);
		this.reprendre.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		this.reprendre.setCursor(Cursor.HAND);
		this.reprendre.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	reprendre();
            }
		});
		this.reprendre.setOnMouseEntered(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               reprendre.setFill(Color.YELLOW);
           }
        });        
        this.reprendre.setOnMouseExited(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               reprendre.setFill(Color.WHITE);
           }
        });
        
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
        
        this.controles = new Text("Contrôles");
		this.controles.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.controles.setFill(Color.WHITE);
		this.controles.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		this.controles.setCursor(Cursor.HAND);
		this.controles.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				controles();
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
           public void handle(MouseEvent event){
               quitter.setFill(Color.YELLOW);
           }
        });        
        this.quitter.setOnMouseExited(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event){
               quitter.setFill(Color.WHITE);
           }
        });
		
		this.texts.getChildren().addAll(this.reprendre, this.restart, this.controles, this.quitter);
		
		BorderPane.setAlignment(this.titre, Pos.TOP_CENTER);
		
		this.fond.setTop(this.titre);
		this.fond.setCenter(this.texts);
		
		this.getChildren().add(this.fond);
	}
	
	/**
	   * Fonctions pour les différents boutons
	   * 
	   */
	public void reprendre(){
		gameView.game.setPause(false);
	}
	
	public void endApp(){
		gameView.endApp();
	}
	
	public void restart(){
		this.gameView.game.setPause(false);
		this.getChildren().clear();
		this.gameView = new GameView(600, 600, 2, 1, 2, 1, 2); // width, height, nbBot, nbPlayers, nbBot2, nbBot2, nbBalss
		//this.gameView.control = new ControllerGame(this.gameView.game, this.gameView);
		this.getChildren().add(gameView);
	}
	
	public void controles(){
		contorlesView = new ControlesView(null, gameView);
		this.getChildren().clear();
		this.getChildren().add(contorlesView);
	}
	
	public Text getReprendre() {
		return reprendre;
	}
	
	public Text getQuitter() {
		return quitter;
	}
}
