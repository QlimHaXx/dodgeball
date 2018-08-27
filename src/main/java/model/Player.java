package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.Sprite;

/**
 * 
 * Classe gerant un joueur
 *
 */
public abstract class Player {
	double x, y, step, angle;
	String teamId;
	Sprite sprite;
	ImageView PlayerDirectionArrow;
	boolean haveBall, dead;
	Ball ball;
	HitBox hitbox;
	
	/**
	   * Constructeur du Joueur
	   * 
	   * @param x_ position horozontal
	   * @param y_ position vertical
	   * @param step_ vitesse de déplacement
	   * @param s_ idtentifiant de l'équipe
	   */
	public Player(double x_, double y_, double step_, String s_){
		this.x = x_;
		this.y = y_;
		this.angle = 0;
		this.step = step_;
		this.teamId = s_;
		this.haveBall = false;
		this.dead = false;
		this.hitbox = new HitBox(x_, y_, 64, 64);
		
		Image directionArrow;
		
		if(s_ == "Haut"){
        	directionArrow = new Image("assets/PlayerArrowDown.png");
		}
		else{
			directionArrow = new Image("assets/PlayerArrowUp.png");
		}
		
		this.PlayerDirectionArrow = new ImageView();
		this.PlayerDirectionArrow.setImage(directionArrow);
		this.PlayerDirectionArrow.setPreserveRatio(true);
		this.PlayerDirectionArrow.setSmooth(true);
		this.PlayerDirectionArrow.setCache(true);
	}
	
	/**
	   *  Deplacement du joueur vers la gauche, on cantonne le joueur sur le plateau de jeu
	   */
	public void moveLeft(){
		x -= step;
		this.hitbox.updateHitbox(x, y, 64, 64);
	}	
	/**
	 *  Deplacement du joueur vers la droite
	*/
	public void moveRight(){
		x += step;
		this.hitbox.updateHitbox(x, y, 64, 64);
	}	  
	/**
	*  Rotation du joueur vers la gauche
	*/
	public void turnLeft(){		
		angle += 2;
	}	  
	/**
	*  Rotation du joueur vers la droite
	*/
	public void turnRight(){
		angle -= 2;
	}
	
	public void shoot(){
		if(this.getHaveBall()){
			sprite.playShoot();
			this.getBall().setShoot(true);
			this.getBall().setPortee(false, this);
			this.getBall().setAngle(Math.toRadians(getAngle()));
			this.getBall().setAngleOrigine(Math.toRadians(getAngle()));
			this.getBall().moveBall();
			this.setHaveBall(false, null);
		}
	}
	
	/**
	   * Getters et setters
	   * 
	   */
	public Sprite getSprite() {
		return sprite;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public String getTeamId() {
		return teamId;
	}
	
	public double getStep() {
		return step;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public ImageView getPlayerDirectionArrow() {
		return PlayerDirectionArrow;
	}
	
	public boolean getHaveBall(){
		return this.haveBall;
	}
	
	public void setHaveBall(boolean haveBall, Ball b_) {
		this.haveBall = haveBall;
		this.ball = b_;
		
		if(this.haveBall){
			b_.setProprioOrigine(this);
			b_.setPortee(true, this);
			this.angle = 0;
		}
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public boolean getDead(){
		return this.dead;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public HitBox getHitbox() {
		return hitbox;
	}
}
