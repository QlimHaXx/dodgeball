package model;

/**
 * Classe gérant les hitboxes (non utilisé)
 * 
 */
public class HitBox {
	double x1, x2, y1, y2;
	
	/**
	   * constructeur d'une hitbox
	   * 
	   * @param x1_ point haut-gauche
	   * @param y1_ point bas-gauche
	   * @param width largeur de la hitbox
	   * @param height hauteur de la hitbox
	   */
	HitBox(double x1_, double y1_, double width, double height){
		x1 = x1_ + 10;
		x2 = x1_ + width - 10;
		y1 = y1_ + 10;
		y2 = y1_ + height - 10;
	}
	
	/**
	   * verification des collisions avec une hitbox données
	   * 
	   * @param b hitbox
	   */
	public boolean checkCollisionHitBox(HitBox b){
		if(b.x1 < x2 && b.x1 >= x1){
			if(b.y1 < y2 && b.y1 >= y1){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	   * Mise a jour des valeurs de la hitbox
	   * 
	   * @param x point haut-gauche
	   * @param y point bas-gauche
	   * @param width largeur de la hitbox
	   * @param height hauteur de la hitbox
	   */
	public void updateHitbox(double x, double y, double width, double height) {
		this.x1 = x + 10;
		this.x2 = x + width - 10;
		this.y1 = y + 10;
		this.y2 = y + height - 10;
	}
}
