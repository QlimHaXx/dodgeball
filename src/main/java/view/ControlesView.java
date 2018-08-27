package view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Classe gérant l'interface présentant les touches
 * 
 */
public class ControlesView extends Group {
	GameMenuView menuview;
	GameView gameView;
	BorderPane layout;
	Text player1, player2, pause, tir1, tir2, titre, retour;
	ImageView z, q, s, d, up, down, left, right, space, escape, c;
	HBox layoutH, touches11, touches12, touches21, touches22;
	VBox layoutV1;
	VBox layoutV2;
	
	/**
	   * Constructeur de l'interface
	   * 
	   * @param gameMenuView_ le menu du jeu depuis lequel est appélé cet écran
	   * @param gameView_ le menu du jeu depuis lequel est appélé cet écran
	   */
	public ControlesView(GameMenuView gameMenuView_, GameView gameView_) {
		menuview = gameMenuView_;
		gameView = gameView_;
		
		layout = new BorderPane();
		layout.setStyle("-fx-background-color: rgba(0,0,0,0.9);-fx-padding: 10px 0 10px 0");
		layout.setMinWidth(600);
		layout.setMinHeight(600);
		
		titre = new Text("Contrôles");
		titre.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		titre.setFill(Color.WHITE);
		titre.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		BorderPane.setAlignment(titre, Pos.TOP_CENTER);
		
		retour = new Text("Retour");
		retour.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		retour.setFill(Color.WHITE);
		retour.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		retour.setCursor(Cursor.HAND);
		BorderPane.setAlignment(retour, Pos.TOP_CENTER);
		retour.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	if(gameView != null){
            		gameView.retour();
            	}
            	if(menuview != null){
            		menuview.retour();
            	}
            }
		});		
		retour.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	retour.setFill(Color.YELLOW);
            }
		});		
		retour.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
            	retour.setFill(Color.WHITE);
            }
		});
		
		player1 = new Text("Joueur 1 :");
		player1.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		player1.setFill(Color.web("#07F"));
		player1.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		player1.setTranslateY(40);
		
		player2 = new Text("Joueur 2 :");
		player2.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		player2.setFill(Color.RED);
		player2.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		player2.setTranslateY(40);
		
		pause = new Text("Pause :");
		pause.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		pause.setFill(Color.WHITE);
		pause.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		
		tir1 = new Text("Tir :");
		tir1.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		tir1.setFill(Color.web("#07F"));
		tir1.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		tir1.setTranslateY(35);
		
		tir2 = new Text("Tir :");
		tir2.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		tir2.setFill(Color.RED);
		tir2.setStyle("-fx-effect: dropshadow( one-pass-box , black , 8 , 0.0 , 2 , 0 )");
		tir2.setTranslateY(35);
		
		Image tmp = new Image("assets/Z.png");
		z = new ImageView();
		z.setImage(tmp);
		
		tmp = new Image("assets/Q.png");
		q = new ImageView();
		q.setImage(tmp);
		
		tmp = new Image("assets/S.png");
		s = new ImageView();
		s.setImage(tmp);
		
		tmp = new Image("assets/D.png");
		d = new ImageView();
		d.setImage(tmp);
		
		tmp = new Image("assets/TOP.png");
		up = new ImageView();
		up.setImage(tmp);
		
		tmp = new Image("assets/DOWN.png");
		down = new ImageView();
		down.setImage(tmp);
		
		tmp = new Image("assets/LEFT.png");
		left = new ImageView();
		left.setImage(tmp);
		
		tmp = new Image("assets/RIGHT.png");
		right = new ImageView();
		right.setImage(tmp);
		
		tmp = new Image("assets/ESC.png");
		escape = new ImageView();
		escape.setImage(tmp);
		
		tmp = new Image("assets/C.png");
		c = new ImageView();
		c.setImage(tmp);
		
		tmp = new Image("assets/ESPACE.png");
		space = new ImageView();
		space.setImage(tmp);
		
		layoutH = new HBox(10);
		layoutH.setAlignment(Pos.CENTER);
		
		touches11 = new HBox(5);
		touches11.setAlignment(Pos.CENTER);
		touches11.getChildren().addAll(up);
		
		touches12 = new HBox(5);
		touches12.setAlignment(Pos.CENTER);
		touches12.getChildren().addAll(left, down, right);
		
		touches21 = new HBox(5);
		touches21.setAlignment(Pos.CENTER);
		touches21.getChildren().addAll(z);
		
		touches22 = new HBox(5);
		touches22.setAlignment(Pos.CENTER);
		touches22.getChildren().addAll(q, s, d);
		
		layoutV1 = new VBox(73);
		layoutV1.setMinWidth(200);
		layoutV1.setAlignment(Pos.CENTER);
		layoutV1.getChildren().addAll(player1, tir1, player2, tir2, pause);
		
		layoutV2 = new VBox(5);
		layoutV2.setAlignment(Pos.CENTER);
		layoutV2.getChildren().addAll(touches11, touches12, space, touches21, touches22, c, escape);
		
		layoutH.getChildren().addAll(layoutV1, layoutV2);
		
		layout.setTop(titre);
		layout.setCenter(layoutH);
		layout.setBottom(retour);
		
		this.getChildren().addAll(layout);
	}
}
