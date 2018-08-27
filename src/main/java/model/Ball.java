package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe g"rant les projectiles
 * 
 */
public class Ball {
	double x, y, speed, angle, angleOrigine;
	boolean shoot, portee, rebond;
	String direction;
	ImageView imgBall;
	Player proprio, proprioOrigine;
	HitBox hitbox;
	
	/**
	   * Constructeur du projectile
	   * 
	   * @param x_ position horizontale
	   * @param y_ position verticale
	   * @param speed_ vitesse de déplacement de la balle
	   */
	public Ball(double x_, double y_, double speed_, Player p_){
		this.x = x_; 
		this.y = y_;
		this.speed = speed_;
		this.proprio = p_;
		this.rebond = false;
		this.hitbox = new HitBox(x_, y_, 64, 64);
		
		this.shoot = false;
		this.portee = true;
		
		this.angle = Math.toRadians(p_.getAngle());
		this.angleOrigine = Math.toRadians(p_.getAngle());
		this.proprioOrigine = p_;
		this.direction = p_.getTeamId();
		
		Image img = new Image("assets/ball.png");
		this.imgBall = new ImageView();
		this.imgBall.setImage(img);
		this.imgBall.setX(this.x + 10);
		this.imgBall.setY(this.y + 30);
	}
	
	/**
	   * Déplacement de la balle
	   * 
	   */
	public void moveBall(){
		double vectY = Math.abs(Math.cos(this.angle)) * Math.cos(this.angle);
		double vectX = Math.abs(Math.sin(this.angle)) * Math.sin(this.angle);
		
		if(direction == "Haut"){
			y += vectY * speed;
			
			if(this.angle < 0){
				x += vectX * speed / this.angle;
			}
			if(this.angle > 0){
				x -= vectX * speed / this.angle;
			}
		}
		if(direction == "Bas"){
			y -= vectY * speed;
			
			if(this.angle < 0){
				x -= vectX * speed / this.angle;
			}
			else if(angle > 0){
				x += vectX * speed / this.angle;
			}
		}
		
		this.hitbox.updateHitbox(x, y, 64, 64);
		
	}
	
	/**
	   * Setters et Getters
	   * 
	   */
	public ImageView getImgBall() {
		return imgBall;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setBall(double x_, double y_){
		this.x = x_;
		this.y = y_;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}
	
	public boolean getShoot(){
		return shoot;
	}
	
	public boolean getPortee(){
		return this.portee;
	}
	
	public void setPortee(boolean b_, Player p_){
		this.portee = b_;
		this.proprio = p_;
	}
	
	public Player getProprio(){
		return this.proprio;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setRebond(boolean rebond) {
		this.rebond = rebond;
	}
	
	public boolean getRebond(){
		return this.rebond;
	}
	
	public void setAngleOrigine(double angleOrigine) {
		this.angleOrigine = angleOrigine;
	}
	
	public double getAngleOrigine() {
		return angleOrigine;
	}
	
	public void setProprioOrigine(Player proprioOrigine) {
		this.proprioOrigine = proprioOrigine;
	}
	
	public Player getProprioOrigine() {
		return proprioOrigine;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public HitBox getHitbox() {
		return hitbox;
	}
}
