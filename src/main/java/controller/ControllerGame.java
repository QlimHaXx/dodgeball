package controller;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Game;
import view.GameView;

/**
 * 
 * Classe gerant le controlleur
 *
 */
public class ControllerGame implements EventHandler<KeyEvent> {
	Game model;
	GameView gameView;
	List<String> input;

	/**
	   * Constructeur du controlleur
	   * 
	   * @param model_ Metier
	   * @param g_ Vue du jeu
	   */
	public ControllerGame(Game model_, GameView g_){
		this.model = model_;
		this.gameView = g_;
		this.input = new ArrayList<String>();
	}
	
	/**
	   * Fonction qui récupère l'evenemnt de la vue
	   * 
	   * @param e Evenement de type clavier
	   */
	@Override
	public void handle(KeyEvent e) {		
		if(e.getEventType() == KeyEvent.KEY_PRESSED){
			String code = e.getCode().toString();
			if (!input.contains(code)){
				input.add(code);
				
				if(code == "ESCAPE"){
					if(!model.getPause()){
						model.setPause(true);
					}
					else {
						model.setPause(false);
					}
				}
			}
		}
		if(e.getEventType() == KeyEvent.KEY_RELEASED){		    	        
            String code = e.getCode().toString();    	            
            input.remove(code);
		}
	}
	
	/**
	   * Traite l'évenement reçus
	   * 
	   * @param code Chaîne de carctères contenant la touche
	   */
	public void actionPlayer(String code){
		switch (code) {
			case "LEFT":
				model.movePlayersLeft("Bas");
				break;
				
			case "RIGHT":
				model.movePlayersRight("Bas");
				break;
				
			case "UP":
				model.turnPlayersLeft("Bas");
				break;
				
			case "DOWN":
				model.turnPlayersRight("Bas");
				break;
				
			case "SPACE":
				model.playersShoot("Bas");
				break;
				
			case "Q":
				model.movePlayersLeft("Haut");
				break;
				
			case "D":
				model.movePlayersRight("Haut");
				break;
				
			case "Z":
				model.turnPlayersLeft("Haut");
				break;
				
			case "S":
				model.turnPlayersRight("Haut");
				break;
				
			case "C":
				model.playersShoot("Haut");
				break;
				
			case "B":
				model.setBotIA();
				
			default:
				break;
		}
	}
	
	/**
	   * Permet de récupere la liste des touches appuyées
	   * 
	   * @return input
	   */
	public List<String> getInput() {
		return input;
	}
	
	/**
	   * Lance le rafrîchissement du model
	   * 
	   */
	public void updateModel(){
		model.updateGame();
	}
}
