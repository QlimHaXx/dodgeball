package view;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import controller.ControllerGame;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Ball;
import model.Game;
import model.Player;

/**
 * Classe gérant la vue principale de jeu
 * 
 */
public class GameView extends Group implements Observer {
	Game game;
	ImageView field;
	ControllerGame control;
	MenuPauseView pauseView;
	GameEndView endGameView;
	Text scoreBas, scoreHaut, compteur;
	String gagnant;
	Timer timer;
	Rectangle fond;
	
	/**
	   * Constructeur de l'interface pour initialisé le jeu
	   * 
	   * @param width largeur du terrain
	   * @param height hauteur du terrain
	   * @param nbBotHaut nombre de robot dans l'équipe du haut
	   * @param nbPlayersHaut nombre de joueurs dans l'équipe du haut
	   * @param nbBotBas nombre de robots dans l'équipe du bas
	   * @param nbPlayersBas nombre de joeurs dans l'équipe du bas
	   * @param nbBalls nombre de balls dans le jeu
	   */
	public GameView(int width, int height, int nbBotHaut, int nbPlayersHaut, int nbBotBas, int nbPlayersBas, int nbBalls){
		this.game = new Game(width, height, nbBotHaut, nbPlayersHaut, nbBotBas, nbPlayersBas, nbBalls);
		this.game.addObserver(this);
		this.control = new ControllerGame(this.game, this);
		
		this.endGameView = new GameEndView(game.getField().getWidth(), game.getField().getWidth(), this);
		
		this.pauseView = new MenuPauseView(game.getField().getWidth(), game.getField().getWidth(), this);
		
		this.scoreBas = new Text("Score "+Integer.toString(game.getScoreBottom()));
		this.scoreBas.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.scoreBas.setFill(Color.RED);
		this.scoreBas.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		this.scoreBas.setX(width / 2 - 60);
		this.scoreBas.setY(height / 2 - 30);
		
		this.scoreHaut = new Text("Score "+Integer.toString(game.getScoreTop()));
		this.scoreHaut.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		this.scoreHaut.setFill(Color.web("#08F"));
		this.scoreHaut.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		this.scoreHaut.setX(width / 2 - 60);
		this.scoreHaut.setY(height / 2 + 50);

		this.pauseView = new MenuPauseView(width, height, this);
		
		this.setClip(new Rectangle(width, height)); // bordures de l'ecran
		
		Image img = new Image("assets/Background.png");
		this.field = new ImageView();
		this.field.setImage(img);
		
		this.getChildren().addAll(field, scoreBas, scoreHaut);
		this.afficheJoueurs();
		this.afficheBalls();
		
		this.setFocusTraversable(true);
		
		/** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
		this.setOnKeyPressed(this.control);
		
		/** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
		this.setOnKeyReleased(this.control);
		
		this.timer = new Timer();
		
		this.compteur = new Text("Prêt ?");
		this.compteur.setFont(Font.font("Arial", FontWeight.BOLD, 60));
		this.compteur.setFill(Color.WHITE);
		this.compteur.setStyle("-fx-effect: dropshadow( one-pass-box , rgba(0,0,0,0.8) , 4 , 0.0 , 2 , 0 )");
		this.compteur.setX(width / 2 - 100);
		this.compteur.setY(height / 2);
		
		this.fond = new Rectangle(600, 600);
		this.fond.setFill(Color.web("#000", 0.3));
		
		this.getChildren().addAll(fond, compteur);
		
		decompte();
	}
	
	/**
	   * Fonction applée lors d'un changement dans le model
	   * 
	   */
	@Override
	public void update(Observable o, Object arg) {		
		if(game.getGagant() == null){
			if(!game.getPause()){
				refreshView();
			}
			else {
				this.afficheMenuPause();
			}
		}		
		else {
			this.afficheFinGame();
		}
	}
	
	/**
	   * Fonction qui fait le décompte en début de partie
	   * 
	   */
	public void decompte(){		
		this.timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  compteur.setText("    3");
			  }
		}, 1000);
		
		this.timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  compteur.setText("    2");
			  }
		}, 2000);
		
		this.timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  compteur.setText("    1");
			  }
		}, 3000);
		
		this.timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  animationView();
			  }
		}, 4000);
	}
	
	/**
	   * Fonction qui rafrîchie les éléments de la vue
	   * 
	   */
	public void refreshView(){
		this.getChildren().clear();
		this.getChildren().addAll(field, scoreBas, scoreHaut);
		this.afficheScore();
		this.afficheJoueurs();
		this.afficheBalls();
	}
	
	/**
	   * Affiche les balles
	   * 
	   */
	public void afficheBalls(){
		for(int i = 0; i < game.getNbBalls(); i++){
			Ball b = game.getBalls().get(i);
			
			updateBall(b.getImgBall(), b.getX(), b.getY());
			
			this.getChildren().add(b.getImgBall());
		}
	}
	
	/**
	   *  Affichage des joueurs
	   */
	public void afficheJoueurs(){
		for(int i = 0; i < game.getHaut().getNbPlayers(); i++){
			Player pI = game.getHaut().getPlayers().get(i);
			
			if(!pI.getDead()){
				afficheArrow(pI);
				updateJoueur(pI.getSprite(), pI.getX(), pI.getY());				
				this.getChildren().add(pI.getSprite());
			}
		}
		
		for(int i = 0; i < game.getBas().getNbPlayers(); i++){
			Player pI = game.getBas().getPlayers().get(i);
			
			if(!pI.getDead()){
				afficheArrow(pI);
				updateJoueur(pI.getSprite(), pI.getX(), pI.getY());
				this.getChildren().add(pI.getSprite());
			}
		}
	}
	
	/**
	   * Affiche les flèches
	   * 
	   * @param pI joueur
	   */
	public void afficheArrow(Player pI){
		if(pI.getHaveBall() && pI.getTeamId() == "Bas"){
			pI.getPlayerDirectionArrow().setRotate(pI.getAngle());
			pI.getPlayerDirectionArrow().setX(pI.getX());
			pI.getPlayerDirectionArrow().setY(pI.getY() - 32);
			this.getChildren().add(pI.getPlayerDirectionArrow());
		}
		if(pI.getHaveBall() && pI.getTeamId() == "Haut"){
			pI.getPlayerDirectionArrow().setRotate(pI.getAngle());
			pI.getPlayerDirectionArrow().setX(pI.getX());
			pI.getPlayerDirectionArrow().setY(pI.getY() + 5);
			this.getChildren().add(pI.getPlayerDirectionArrow());
		}
	}
	
	/** 
     * 
     * Boucle principale du jeu
     * 
     * handle() est appelee a chaque rafraichissement de frame
     * soit environ 60 fois par seconde.
     * 
     */
	public void animationView(){
		new AnimationTimer(){
			public void handle(long currentNanoTime){
				if(!game.getPause() && !game.endGame()){
					for(int i = 0; i < control.getInput().size(); i++){
						control.actionPlayer(control.getInput().get(i)); // permet d'appuyer sur plusieurs touches en meme temps
					}
				}
				
				control.updateModel();
	        }
	    }.start(); // On lance la boucle de rafraichissement
	}
	
	/**
	   * Affichage de la vue de fin de partie
	   * 
	   */
	public void afficheFinGame(){		
		if(game.getGagant().equals("Bas")){
			endGameView.getTeam().setText("RED");
			endGameView.getTeam().setFill(Color.RED);
		}
		if(game.getGagant().equals("Haut")){
			endGameView.getTeam().setText("BLUE");
			endGameView.getTeam().setFill(Color.web("#08F"));
		}
		
		this.endGameView.refresh();
		this.refreshView();
		this.getChildren().add(endGameView);
	}
	
	/**
	   * Affichage du menu pause
	   * 
	   */
	public void afficheMenuPause(){
		refreshView();
		this.getChildren().add(pauseView);
	}
	
	/**
	   * Mise a jour visiuelle d'un joueur
	   * 
	   * @param p image d'un joueur
	   * @param x_ position horizontale
	   * @param y_ position verticale
	   */
	public void updateJoueur(ImageView p, double x_, double y_){
		p.setX(x_);
		p.setY(y_);
	}
	
	/**
	   * Mise a jour visiuelle d'une balle
	   * 
	   * @param b image d'un joueur
	   * @param x_ position horizontale
	   * @param y_ position verticale
	   */
	public void updateBall(ImageView b, double x_, double y_){
		b.setX(x_ + 10);
		b.setY(y_ + 30);
	}
	
	/**
	   * Affichage des scores + mise à jour
	   * 
	   */
	public void afficheScore(){
		this.scoreBas.setText("Score "+Integer.toString(game.getScoreTop()));
		this.scoreHaut.setText("Score "+Integer.toString(game.getScoreBottom()));
	}
	
	/**
	   * Fonction utilisée par la vue des contrôles pour revenir a cette vue
	   * 
	   */
	public void retour(){
		this.pauseView.getChildren().clear();
		this.pauseView.getChildren().add(pauseView.fond);
	}
	
	/**
	   * Fermer l'application
	   * 
	   */
	public void endApp(){
		Platform.exit();
	}
}
