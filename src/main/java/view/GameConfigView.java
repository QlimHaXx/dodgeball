package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Classe gérant l'interface de la configuration du jeu (non utilisé car non fini)
 * 
 */
public class GameConfigView extends Group {
	GameView gameView;
	Rectangle fond;
	BorderPane layout;
	ImageView ball;
	VBox items;
	HBox team1, team2, hballs;
	Text titre, equipe1, equipe2, vs, nbjoueurs1, nbjoueurs2, nbBalls, ballsTitre;
	int nbJoueursHaut, nbBotHaut, nbJoueursBas, nbBotBas, intBalls;
	
	/**
	   * Constructeur de l'interface
	   * 
	   */
	public GameConfigView() {		
		this.fond = new Rectangle(600, 600);
		this.fond.setFill(Color.web("#111"));
		
		this.titre = new Text("Configuration de la Partie");
		this.titre.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.titre.setFill(Color.WHITE);
		
		Image img = new Image("assets/ball.png");
		this.ball = new ImageView();
		this.ball.setImage(img);
		
		this.ballsTitre = new Text("Nombre de balles");
		this.ballsTitre.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.ballsTitre.setFill(Color.WHITE);
		
		this.nbBalls = new Text("0/4");
		this.nbBalls.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.nbBalls.setFill(Color.WHITE);
		this.nbBalls.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		
		Button tmp = new Button("-");
		tmp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		tmp.setStyle("-fx-background-color: #FFF;-fx-padding: 10px 15px");
		
		this.hballs = new HBox(10);
		this.hballs.setAlignment(Pos.CENTER);
		this.hballs.setStyle("-fx-background-color: #333;-fx-padding: 20px");
		this.hballs.getChildren().addAll(ball, nbBalls);
		
		tmp = new Button("+");
		tmp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		tmp.setStyle("-fx-background-color: #FFF;-fx-padding: 10px");
		tmp.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		
		this.hballs.getChildren().addAll(tmp);
		
		this.nbjoueurs1 = new Text("0/4");
		this.nbjoueurs1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.nbjoueurs1.setFill(Color.WHITE);
		this.nbjoueurs1.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		
		this.nbjoueurs2 = new Text("0/4");
		this.nbjoueurs2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.nbjoueurs2.setFill(Color.WHITE);
		this.nbjoueurs2.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		
		this.equipe1 = new Text("Equipe RED");
		this.equipe1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.equipe1.setFill(Color.WHITE);
		
		this.vs = new Text("VS");
		this.vs.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.vs.setFill(Color.YELLOW);
		
		this.equipe2 = new Text("Equipe BLUE");
		this.equipe2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.equipe2.setFill(Color.WHITE);
		
		tmp = new Button("+");
		tmp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		tmp.setStyle("-fx-background-color: #FFF;-fx-padding: 10px");
		tmp.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		
		this.team1 = new HBox(10);
		this.team1.setAlignment(Pos.CENTER);
		this.team1.setStyle("-fx-background-color: #B20;-fx-padding: 20px");
		this.team1.getChildren().addAll(nbjoueurs1, tmp);
		
		tmp = new Button("+");
		tmp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		tmp.setStyle("-fx-background-color: #FFF;-fx-padding: 10px");
		tmp.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		
		this.team2 = new HBox(10);
		this.team2.setAlignment(Pos.CENTER);
		this.team2.setStyle("-fx-background-color: #05A;-fx-padding: 20px");
		this.team2.getChildren().addAll(nbjoueurs2, tmp);
		
		tmp = new Button("Jouer!");
		tmp.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		tmp.setStyle("-fx-background-color: #FFF;-fx-padding: 10px 20px");
		tmp.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		tmp.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	nbJoueursHaut = 1;
            	nbBotHaut = 1;
            	nbJoueursBas = 1;
            	nbBotBas = 1;
            	intBalls = 1;
            	
            	getChildren().clear();
            	
            	gameView = new GameView(600, 600, 1, 1, 1, 1, 2); // width, height, nbBot, nbPlayers, nbBot2, nbBot2, nbBalss
            	
            	getChildren().add(gameView);
            }
		});
		
		this.items = new VBox(10);
		this.items.setAlignment(Pos.CENTER);
		this.items.getChildren().addAll(ballsTitre, hballs, equipe1, team1, vs, equipe2, team2, tmp);
		
		this.layout = new BorderPane();
		this.layout.setMinWidth(600);
		this.layout.setMinHeight(600);
		this.layout.setStyle("-fx-padding: 20px 0 0 0");
		BorderPane.setAlignment(this.titre, Pos.TOP_CENTER);
		
		this.layout.setTop(this.titre);
		this.layout.setCenter(this.items);
		
		this.getChildren().addAll(fond, layout);
	}
	
	/**
	   * Différents gettes et setters pour initialisé une partie
	   * 
	   */
	public int getNbBotBas() {
		return nbBotBas;
	}
	
	public int getNbJoueursBas() {
		return nbJoueursBas;
	}
	
	public int getNbBotHaut() {
		return nbBotHaut;
	}
	
	public int getNbJoueursHaut() {
		return nbJoueursHaut;
	}
}
