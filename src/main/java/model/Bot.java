package model;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.util.Duration;
import view.Sprite;

/**
 * Classe gérant les joueurs côntrolés par l'ordinateur
 * 
 */
public class Bot extends Player {
	boolean shoot;
	
	/**
	   * Constructeur du robot
	   * 
	   * @param x_ position horizontale
	   * @param y_ position verticale
	   * @param step_ vitesse de déplacement du robot
	   * @param s_ identifiant de son équipe
	   */
	public Bot(double x_, double y_, double step_, String s_) {
		super(x_, y_, step_, s_);
		
		Image tilesheetImage = new Image("assets/orc.png");
        this.sprite = new Sprite(tilesheetImage, 0,0, Duration.seconds(.2), teamId);
        
        this.shoot = false;
	}
	
	/**
	   * Mouvement du robot niveau 2 (déplacement vers la balle + esquive)
	   * 
	   * @param b_ balle qui initialise l'interaction
	   * @param side identifiznt de l'équipe du robot
	   */
	public void moveBot2(Ball b_, String side){
		if(!b_.getShoot() && !b_.getPortee()){ // va vers les balles au sol
			if(side == "Haut"){
				if(b_.getY() < 300){
					if(b_.getX() <= x && x > 10){
						moveLeft();
					}
					if(b_.getX() >= x && x < 540){
						moveRight();
					}
				}
			}
			else {
				if(b_.getY() > 300){
					if(b_.getX() <= x && x > 10){
						moveLeft();
					}
					if(b_.getX() >= x && x < 540){
						moveRight();
					}
				}
			}
		}
		else {
			if(side == "Haut"){
				if(b_.getY() < 300){
					if(b_.getX() <= x + 10 && x + 10 < 540){
						moveRight();
					}
					if(b_.getX() >= x + 10 && x + 10 > 10){
						moveLeft();
					}
				}
			}
			else {
				if(b_.getY() > 300){
					if(b_.getX() <= x + 10 && x + 10 < 540){
						moveRight();
					}
					if(b_.getX() >= x + 10 && x + 10 > 10){
						moveLeft();
					}
				}
			}
		}
	}
	
	/**
	   * Mouvement du robot niveau 1 (déplacement aléatoire)
	   * 
	   */
	public void moveBot(){ // deplacement aleatoire
		Random random = new Random();
		
		if(random.nextBoolean()){
			if(this.x > 20){
    			moveLeft();
			}
		}
		else {
			if(this.x < 500){
				moveRight();
			}
		}
    		
    }
	
	/**
	   * Tir du robot niveau 2 (tir vers les ennemis)
	   * 
	   * @param a equipe adverse
	   */
	public void shootBot2(Team a){ // tir vers les adversaires
		double moyenne = 1;
		double angle_;
		
		for(int i = 0; i < a.getNbPlayers(); i++){
			moyenne += a.getPlayers().get(i).getX();
		}
		
		moyenne = moyenne / a.getNbPlayers();
		
		angle_ = 540 / (x - moyenne);
		
		angle = Math.tan(angle_) * 10;
		
		if(angle > 50) {
			angle = 50;
		}
		if(angle < -50){
			angle = -50;
		}
		
		shoot();
    }
	
	/**
	   * Tir du robot niveau 1 (aléatoire)
	   * 
	   */
	public void shootBot(){ // tir aléatoire
		Random random = new Random();
		Random bool = new Random();
		
		if(bool.nextBoolean()){
			angle = random.nextInt(45);
		}
		else {
			angle = -random.nextInt(45);
		}
		
		if(bool.nextBoolean()){
			shoot();
		}
    		
    }
	
	/**
	   * Getters et Setters
	   * 
	   */
	public boolean getShoot(){
		return this.shoot;
	}
	
	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

}
