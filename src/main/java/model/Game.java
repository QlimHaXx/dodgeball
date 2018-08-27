package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Classe gérant le jeu
 * 
 */
public class Game extends Observable {
	int nbBotHaut, nbPlayersHaut, nbBotBas, nbPlayersBas, nbBas, nbHaut, nbBalls, scoreTop, scoreBottom;
	boolean pause, botIA;
	String gagant;
	Field field;
	Team haut;
	Team bas;
	List<Ball> balls;
	
	/**
	   * Constructeur du jeu
	   * 
	   * @param width largeur du terrain
	   * @param height hauteur du terrain
	   * @param nbBotHaut nombre de robot dans l'équipe du haut
	   * @param nbPlayersHaut nombre de joueurs dans l'équipe du haut
	   * @param nbBotBas nombre de robots dans l'équipe du bas
	   * @param nbPlayersBas nombre de joeurs dans l'équipe du bas
	   * @param nbBalls_ nombre de balls dans le jeu
	   */
	public Game(int width, int height, int nbBotHaut, int nbPlayersHaut, int nbBotBas, int nbPlayersBas, int nbBalls_){
		this.nbBalls = nbBalls_;
		balls = new ArrayList<Ball>();
		
		this.pause = false;
		
		this.haut = new Team("Haut");
		this.bas = new Team("Bas");
		
		this.nbBas = nbPlayersBas + nbBotBas;
		this.nbHaut = nbPlayersHaut + nbBotHaut;
		this.nbPlayersBas = nbPlayersBas;
		this.nbPlayersHaut = nbPlayersHaut;
		this.nbBotBas = nbBotBas;
		this.nbBotHaut = nbBotHaut;
		this.scoreBottom = 0;
		this.scoreTop = 0;
		
		this.botIA = false;
		
		this.field = new Field(width, height);
		
		int pos = 0;
		
		for(int i = 0; i < this.nbBotHaut; i++){
			this.haut.teamAdd(new Bot((field.getWidth() / this.nbHaut * pos) + 32, 10, 6, "Haut"));
			pos++;
		}
		
		for(int i = 0; i < this.nbPlayersHaut; i++){
			this.haut.teamAdd(new Human((field.getWidth() / this.nbHaut * pos) + 32, 10, 6,"Haut"));
			pos++;
		}
		
		pos = 0;
		
		for(int i = 0; i < this.nbBotBas; i++){
			this.bas.teamAdd(new Bot((field.getWidth() / this.nbBas * pos) + 32, field.getHeight() - 80, 6,"Bas"));
			pos++;
		}
		
		for(int i = 0; i < this.nbPlayersBas; i++){
			this.bas.teamAdd(new Human((field.getWidth() / this.nbBas * pos) + 32, field.getHeight() - 80, 6,"Bas"));
			pos++;
		}
		
		for(int i = 0; i < nbBalls; i++){ // attribution des balls
			if(i % 2 != 0){
				Player randomP = this.getHaut().players.get(i);
				Ball ballI = new Ball(randomP.getX(), randomP.getY(), 10, randomP);
				randomP.setHaveBall(true, ballI);
				balls.add(ballI);
			}
			else{
				Player randomP = this.getBas().players.get(i);
				Ball ballI = new Ball(randomP.getX(), randomP.getY(), 10, randomP);
				randomP.setHaveBall(true, ballI);
				balls.add(ballI);
			}
		}
	}
	
	/**
	   * Mise a jour des données du jeu
	   * 
	   */
	public void updateGame(){
		if(!endGame() && !pause){
			updateBalls();
			updatePlayers();
			actionBots();
		}
		
		setChanged();
		notifyObservers();
	}
	
	/**
	   * Permet de choisir le type de robot (niveau 1 ou 2)
	   * 
	   */
	public void actionBots(){
		if(this.botIA){
			actionBots1();
		}
		else {
			actionBots2();
		}
	}
	
	/**
	   * Mouvement du robot niveau 2
	   * 
	   */	
	public void actionBots2(){
		for(int i = 0; i < getBas().getNbPlayers(); i++){
    		Player p1 = getBas().getPlayers().get(i);
    		
    		Ball b1 = checkCollisionsPlayerBalls(p1);
    		
    		if(b1 != null){
    			updatePlayerBall(b1, p1);
    		}
    		
    		if(p1 instanceof Bot){
    			for(int j = 0; j < getNbBalls(); j++){
    	    		Ball b2 = getBalls().get(j);    				
    				((Bot) p1).moveBot2(b2, "Bas");
    			}
    			
				((Bot) p1).shootBot2(haut);
    		}
		}
		
		for(int i = 0; i < getHaut().getNbPlayers(); i++){
    		Player p1 = getHaut().getPlayers().get(i);
    		
    		Ball b1 = checkCollisionsPlayerBalls(p1);
    		
    		if(b1 != null){
    			updatePlayerBall(b1, p1);
    		}
    		
    		if(p1 instanceof Bot){
    			for(int j = 0; j < getNbBalls(); j++){
    	    		Ball b2 = getBalls().get(j);    				
    				((Bot) p1).moveBot2(b2, "Haut");
    			}
    			
    			((Bot) p1).shootBot2(bas);
    		}
		}
	}
	
	/**
	   * Mouvement du robot niveau 1
	   * 
	   */
	public void actionBots1(){
		for(int i = 0; i < getBas().getNbPlayers(); i++){
    		Player p1 = getBas().getPlayers().get(i);
    		
    		Ball b1 = checkCollisionsPlayerBalls(p1);
    		
    		if(b1 != null){
    			updatePlayerBall(b1, p1);
    		}
    		
    		if(p1 instanceof Bot){
				((Bot) p1).moveBot();
				((Bot) p1).shootBot();
    		}
		}
		
		for(int i = 0; i < getHaut().getNbPlayers(); i++){
    		Player p1 = getHaut().getPlayers().get(i);
    		
    		Ball b1 = checkCollisionsPlayerBalls(p1);
    		
    		if(b1 != null){
    			updatePlayerBall(b1, p1);
    		}
    		
    		if(p1 instanceof Bot){
    			((Bot) p1).moveBot();
    			((Bot) p1).shootBot();
    		}
		}
	}
	
	/**
	   * Déplacement à gauche du joueur
	   * 
	   * @param equipe équipe du joueur
	   */
	public void movePlayersLeft(String equipe){
		if(equipe == "Bas"){
			for(int i = 0; i < getBas().getNbPlayers(); i++){
	    		Player p1 = getBas().getPlayers().get(i);
	    		
	    		Ball b1 = checkCollisionsPlayerBalls(p1);
	    		
	    		if(b1 != null){
	    			updatePlayerBall(b1, p1);
	    		}
	    		
	    		if(p1 instanceof Human){
	    			if(p1.getX() > 5){
	    				p1.moveLeft();
	    			}
	    		}
			}
		}
		else {			
			for(int i = 0; i < getHaut().getNbPlayers(); i++){
	    		Player p1 = getHaut().getPlayers().get(i);
	    		
	    		Ball b1 = checkCollisionsPlayerBalls(p1);
	    		
	    		if(b1 != null){
	    			updatePlayerBall(b1, p1);
	    		}
	    		
	    		if(p1 instanceof Human){
	    			if(p1.getX() > 5){
	    				p1.moveLeft();
	    			}
	    		}
			}
		}
	}
	
	/**
	   * Déplacement à droite du joueur
	   * 
	   * @param equipe équipe du joueur
	   */
	public void movePlayersRight(String equipe){
		if(equipe == "Bas"){
			for(int i = 0; i < getBas().getNbPlayers(); i++){
	    		Player p1 = getBas().getPlayers().get(i);
	    		
	    		Ball b1 = checkCollisionsPlayerBalls(p1);
	    		
	    		if(b1 != null){
	    			updatePlayerBall(b1, p1);
	    		}
	    		
	    		if(p1 instanceof Human){
	    			if(p1.getX() < field.width - 70){
	    				p1.moveRight();
	    			}
	    		}
			}
		}
		else {			
			for(int i = 0; i < getHaut().getNbPlayers(); i++){
	    		Player p1 = getHaut().getPlayers().get(i);
	    		
	    		Ball b1 = checkCollisionsPlayerBalls(p1);
	    		
	    		if(b1 != null){
	    			updatePlayerBall(b1, p1);
	    		}
	    		
	    		if(p1 instanceof Human){
	    			if(p1.getX() < field.width - 70){
	    				p1.moveRight();
	    			}
	    		}
			}
		}
	}
	
	/**
	   * Déplacement à gauche de la flèche du joueur
	   * 
	   * @param equipe équipe du joueur
	   */
	public void turnPlayersLeft(String equipe){
		if(equipe == "Bas"){
			for(int i = 0; i < getBas().getNbPlayers(); i++){
	    		Player p1 = getBas().getPlayers().get(i);
	    		
	    		if(p1 instanceof Human){
	    			if(p1.angle < 80){
	    				p1.turnLeft();
	    			}
	    		}
			}
		}
		else {			
			for(int i = 0; i < getHaut().getNbPlayers(); i++){
	    		Player p1 = getHaut().getPlayers().get(i);
	    		
	    		if(p1 instanceof Human){
	    			if(p1.angle < 80){
	    				p1.turnLeft();
	    			}
	    		}
			}
		}
	}
	
	/**
	   * Déplacement à droite de la flèche du joueur
	   * 
	   * @param equipe équipe du joueur
	   */
	public void turnPlayersRight(String equipe){
		if(equipe == "Bas"){
			for(int i = 0; i < getBas().getNbPlayers(); i++){
	    		Player p1 = getBas().getPlayers().get(i);
	    		
	    		if(p1 instanceof Human){
	    			if(p1.angle > -80){
	    				p1.turnRight();
	    			}
	    		}
			}
		}
		else {			
			for(int i = 0; i < getHaut().getNbPlayers(); i++){
	    		Player p1 = getHaut().getPlayers().get(i);
	    		
	    		if(p1 instanceof Human){
	    			if(p1.angle > -80){
	    				p1.turnRight();
	    			}
	    		}
			}
		}
	}
	
	/**
	   * Tir du joueur
	   * 
	   * @param equipe équipe du joueur
	   */
	public void playersShoot(String equipe){
		if(equipe == "Bas"){
			for(int i = 0; i < getBas().getNbPlayers(); i++){
	    		Player p1 = getBas().getPlayers().get(i);
	    		
	    		if(p1 instanceof Human){
	    			p1.shoot();
	    		}
			}
		}
		else {			
			for(int i = 0; i < getHaut().getNbPlayers(); i++){
	    		Player p1 = getHaut().getPlayers().get(i);
	    		
	    		if(p1 instanceof Human){
	    			p1.shoot();
	    		}
			}
		}
	}
	
	/**
	   * Mise a jour d'une balle et d'un joueurs(qui sont entrés en collision)
	   * 
	   * @param b balle
	   * @param p le joueur
	   */
	public void updatePlayerBall(Ball b, Player p){
		if(b != null && p != null){
			if(!b.getShoot()){
				if(p.getBall() == null && b.getPortee() && p instanceof Human && b.getProprioOrigine() instanceof Bot){ // ball portée
					b.setProprioOrigine(p);
					b.getProprio().setHaveBall(false, null);
					b.setPortee(true, p);
					p.setHaveBall(true, b);
					p.setAngle(0);
				}
				if(p.getBall() == null && !b.getPortee()){ // ball au sol
					b.setProprioOrigine(p);
					b.setPortee(true, p);
					b.setAngleOrigine(p.getAngle());
					b.setDirection(p.getTeamId());
					p.setHaveBall(true, b);
					p.setAngle(0);
				}
			}
			if(b.getShoot()) {
				if(b.getProprio().getTeamId() != p.getTeamId()){ // tue un adversaire
					p.setDead(true);
					p.setHaveBall(false, null);
					
					if(p.getTeamId() == "Haut"){
						getHaut().getPlayers().remove(p);
						getHaut().setNbPlayers(getHaut().getNbPlayers() - 1);
					}
					else {
						getBas().getPlayers().remove(p);
						getBas().setNbPlayers(getBas().getNbPlayers() - 1);
					}
					
					if(p.getTeamId() == "Haut"){
						scoreBottom++;
					}
					else {
						scoreTop++;
					}
				}
				else { // passe a un copéquipier
					if(p.getTeamId() == "Haut"){
						/*if(getHaut().getNbPlayers() < 2 && p instanceof Human){
							if(!p.getHaveBall()){ // passe a lui meme
								b.setShoot(false);
								b.setRebond(false);
								b.setPortee(true, p);
								p.setHaveBall(true, b);
								p.setAngle(0);
							}
						}
						else {*/
							if(!p.getHaveBall() && p != b.getProprioOrigine() && b.getProprioOrigine() instanceof Human){
								b.setShoot(false);
								b.setRebond(false);
								b.setPortee(true, p);
								p.setHaveBall(true, b);
								p.setAngle(0);
							}
						//}
					}	
					if(p.getTeamId() == "Bas"){
						/*if(getBas().getNbPlayers() < 2 && p instanceof Human){ // si le nombre de joueur < 2
							if(!p.getHaveBall()){ // passe a lui meme
								b.setShoot(false);
								b.setRebond(false);
								b.setPortee(true, p);
								p.setHaveBall(true, b);
								p.setAngle(0);
							}
						}
						else { // pas de passe a lui meme*/
							if(!p.getHaveBall() && p != b.getProprioOrigine() && b.getProprioOrigine() instanceof Human){
								b.setShoot(false);
								b.setRebond(false);
								b.setPortee(true, p);
								p.setHaveBall(true, b);
								p.setAngle(0);
							}
						//}
					}
				}
			}
		}
	}
	
	/**
	   * Mise a jour des joueurs
	   * 
	   */
	public void updatePlayers(){
		for(int i = 0; i < getBas().getNbPlayers(); i++){
    		Player p1 = getBas().getPlayers().get(i);
        			
			Ball b1 = checkCollisionsPlayerBalls(p1);
    			
			if(b1 != null){
				updatePlayerBall(b1, p1); // mise a jour des objet conserné
			}
			
			if(p1.getHaveBall()){
				p1.getBall().setBall(p1.getX(), p1.getY());
			}
    	}
    	
    	for(int i = 0; i < getHaut().getNbPlayers(); i++){
    		Player p1 = getHaut().getPlayers().get(i);
    		
			Ball b1 = checkCollisionsPlayerBalls(p1);
        			
			if(b1 != null){
				updatePlayerBall(b1, p1);
			}
			
			if(p1.getHaveBall()){
				p1.getBall().setBall(p1.getX(), p1.getY());
			}
		}
	}
	
	/**
	   * Mise à jour des balles
	   * 
	   */
	public void updateBalls(){
		for(int i = 0; i < getNbBalls(); i++){
    		Ball b1 = getBalls().get(i);
    		
    		Player p1 = checkCollisionsBallPlayers(b1);
				
    		if(p1 != null){
				updatePlayerBall(b1, p1); // mise a jour des objet conserné
			}
    		else{
	    		if(b1.getShoot() && !checkCollisionsBallWalls(b1, field.getWidth(), field.getWidth())){
	    			b1.moveBall();
	    		}
    		}
    	}
	}
	
	/**
	   * verification des collisions entre un joueur donné et les balles du jeu
	   * 
	   * @param b joueur
	   */
	public Ball checkCollisionsPlayerBalls(Player b){ // collision joueur - ball
		for(int i = 0; i < this.getNbBalls(); i++){
			Ball a = this.getBalls().get(i);
			
			if(a != b.getBall()){
				if(a.getImgBall().getBoundsInLocal().intersects(b.getSprite().getBoundsInLocal()) && !b.getDead()){
					return a;
				}
			}
		}
		
		return null;
	}
	
	/**
	   * verification des collisions entre une baller donné et les joueurs du jeu
	   * 
	   * @param a balle
	   */
	public Player checkCollisionsBallPlayers(Ball a){ // collision ball - joueurs
		if(a.getDirection() == "Bas"){
			for(int i = 0; i < this.getHaut().getNbPlayers(); i++){
				Player b = this.getHaut().getPlayers().get(i);
				
				if(a.getImgBall().getBoundsInLocal().intersects(b.getSprite().getBoundsInLocal()) && !b.getDead()){
					return b;
				}
			}
		}
		if(a.getDirection() == "Haut"){
			for(int i = 0; i < this.getBas().getNbPlayers(); i++){
				Player b = this.getBas().getPlayers().get(i);
				
				if(a.getImgBall().getBoundsInLocal().intersects(b.getSprite().getBoundsInLocal()) && !b.getDead()){
					return b;
				}
			}
		}
		
		return null;
	}
	
	/**
	   * verification des collisions entre une balle donné et les bords du terrain
	   * 
	   * @param b balle
	   * @param width largeur du terrain
	   * @param height heuteur du terrain
	   */
	public boolean checkCollisionsBallWalls(Ball b, int width, int height){
		if(b.getX() < -5){
			if(!b.getRebond()){
				b.setAngle(- b.getAngleOrigine());
				b.setRebond(true);
			}
			else {
				b.setAngle(b.getAngleOrigine());
				b.setRebond(false);
			}
			
			return false;			
		}
		if(b.getX() > width - 30){
			if(!b.getRebond()){
				b.setAngle(- b.getAngleOrigine());
				b.setRebond(true);
			}
			else {
				b.setAngle(b.getAngleOrigine());
				b.setRebond(false);
			}
			return false;
		}
		if(b.getY() > height - 80){
			b.setShoot(false);
			b.setPortee(false, null);
			b.setRebond(false);
			
			return true;
		}
		if(b.getY() < 5){
			b.setShoot(false);
			b.setPortee(false, null);
			b.setRebond(false);
			
			return true;
		}
		
		return false;
	}
	
	/**
	   * verification des collisions entre les balles (non utilisé)
	   * 
	   * @param b balle
	   */
	public boolean checkCollisionsBallBalls(Ball b){
		for(int i = 0; i < this.getNbBalls(); i++){
    		Ball b2 = this.getBalls().get(i);
    		
			if(b.getImgBall().getBoundsInLocal().intersects(b2.getImgBall().getBoundsInLocal()) && b != b2 && !b2.getPortee()){
				b.setAngle(- b.getAngleOrigine());
				
				if(b.getRebond()){
					b.setRebond(false);
				}
				else {
					b.setRebond(true);
				}
				
				b2.setAngle(- b.getAngleOrigine());
				
				if(b2.getRebond()){
					b2.setRebond(false);
				}
				else {
					b2.setRebond(true);
				}
				
				if(b.getDirection() == "Haut"){
					b.setDirection("Bas");
				}
				else {
					b.setDirection("Haut");
				}
				
				if(b2.getDirection() == "Bas"){
					b2.setDirection("Haut");
				}
				else {
					b2.setDirection("Bas");
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	   * verification de la fin de la partie
	   * 
	   */
	public boolean endGame(){
		if(this.scoreTop == this.nbBas){
			this.gagant = new String("Bas");
			return true;
		}		
		if(this.scoreBottom == this.nbHaut){
			this.gagant = new String("Haut");
			return true;
		}
		
		return false;
	}
	
	/**
	   * Getters et setters
	   * 
	   */
	public String getGagant() {
		return gagant;
	}
	
	public Field getField() {
		return field;
	}
	
	public Team getBas() {
		return bas;
	}
	
	public Team getHaut() {
		return haut;
	}
	
	public int getNbBotBas() {
		return nbBotBas;
	}
	
	public int getNbBotHaut() {
		return nbBotHaut;
	}
	
	public int getNbPlayersBas() {
		return nbPlayersBas;
	}
	
	public int getNbPlayersHaut() {
		return nbPlayersHaut;
	}
	
	public List<Ball> getBalls() {
		return balls;
	}
	
	public int getNbBalls() {
		return nbBalls;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public boolean getPause(){
		return this.pause;
	}
	
	public int getScoreBottom() {
		return scoreBottom;
	}
	
	public int getScoreTop() {
		return scoreTop;
	}
	
	public void setBotIA(){
		if(botIA){
			botIA = false;
		}
		else {
			botIA = true;
		}
	}
}
