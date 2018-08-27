package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe gérant les équipes
 * 
 */
public class Team {
	String side;
	int nbPlayers;
	List<Player> players;
	
	/**
	   * Constructeur d'une équipe
	   * 
	   * @param s_ identifiant de l'équipe
	   */
	public Team(String s_){
		this.side = new String(s_);
		this.players = new ArrayList<Player>();
		this.nbPlayers = 0;
	}
	
	/**
	   * Ajout d'un joueur dans une équipe
	   * 
	   * @param p_ joueur
	   */
	public void teamAdd(Player p_){
		players.add(p_);
		this.nbPlayers++;
	}
	
	/**
	   * Getters setters
	   * 
	   */
	public String getSide() {
		return side;
	}
	
	public int getNbPlayers() {
		return nbPlayers;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void setNbPlayers(int nbPlayers) {
		this.nbPlayers = nbPlayers;
	}
}
