package model;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field {
	int width;
	int height;
	
	/**
     * Construceteur de la zone de jeu.
     * 
     * @param w_ largeur du canvas
     * @param h_ hauteur du canvas
     */
	public Field(int w_, int h_){
		this.width = w_;
		this.height = h_;
	}
	
	/**
	   * Getters et setters
	   * 
	   */
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
