package model;

import javafx.scene.image.Image;
import javafx.util.Duration;
import view.Sprite;

/**
 * Classe gérant les joueurs humain heritant de player
 * 
 */
public class Human extends Player {
	/**
	   * constructeur d'un Humain
	   * 
	   * @param x_ position horozontal
	   * @param y_ position vertical
	   * @param step_ vitesse de déplacement
	   * @param s_ idtentifiant de l'équipe
	   */
	public Human(double x_, double y_, double step_, String s_) {
		super(x_, y_, step_, s_);
		
		Image tilesheetImage;
		
		if(s_ == "Haut"){
			tilesheetImage = new Image("assets/PlayerRed.png");
		}
		else {
			tilesheetImage = new Image("assets/PlayerBlue.png");
		}
		
        this.sprite = new Sprite(tilesheetImage, 0, 0, Duration.seconds(.2), teamId);
        this.sprite.setX(this.x);
        this.sprite.setY(this.y);
	}	
}
