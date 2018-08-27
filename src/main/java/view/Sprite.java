package view;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.util.Duration;

/**
 * Classe gérant les animations
 * 
 */
public class Sprite extends ImageView {
    private final Rectangle2D[] walkClips;
    private final Rectangle2D[] shootClips;
    private final Rectangle2D[] explosionClips;
    private final Rectangle2D[] deadClips;
    private int numCellsWalk;
    private int numCellsShoot;
    private int numCellsExplosion;
    private int numCellsDead;
    private final Timeline walkTimeline;
    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0);
    private final Timeline shootTimeline;
    private final Timeline explosionTimeline;
    private final Timeline deadTimeline;
    private Timeline timeline;
    public boolean isRunning;
    
    /**
	   * Constructeur d'un sprite
	   * 
	   * @param animationImage sprite
	   * @param numCells nombre d'images de l'animation
	   * @param numRows la ligne de sur l'image de l'animation
	   * @param frameTime longueur de l'animation (temps)
	   * @param side identifiant de l'équipe
	   */
    public Sprite(Image animationImage, int numCells, int numRows, Duration frameTime, String side) {

        double cellWidth  = 64;//animationImage.getWidth() / numCells; //64x64
        double cellHeight = 64;//animationImage.getHeight() / numRows;


        numCellsWalk = 9;

        int lineNumber = 8;
        
        if(side == "Haut"){
            lineNumber += 2;
        }
        

        walkClips = new Rectangle2D[numCellsWalk];
        for (int i = 0; i < numCellsWalk; i++) {
            walkClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight*lineNumber,
                    cellWidth, cellHeight
            );
        }

        setImage(animationImage);
        setViewport(walkClips[0]);

        walkTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsWalk);
                    setViewport(walkClips[frameCounter.get()]);
                })
        );

        numCellsShoot = 13;
        lineNumber += 8;

        shootClips = new Rectangle2D[numCellsShoot];
        for (int i = 0; i < numCellsShoot; i++){
            shootClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight*lineNumber,
                    cellWidth, cellHeight
            );
        }

        shootTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsShoot);
                    setViewport(shootClips[frameCounter.get()]);
                }));


        isRunning = false;
        
        numCellsExplosion = 6;

        explosionClips = new Rectangle2D[numCellsExplosion];
        for (int i = 0; i < numCellsExplosion; i++) {
            explosionClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight*1,
                    cellWidth, cellHeight
            );
        }

        setImage(animationImage);
        setViewport(explosionClips[0]);

        explosionTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsExplosion);
                    setViewport(explosionClips[frameCounter.get()]);
                })
        );
        
        numCellsDead = 6;

        deadClips = new Rectangle2D[numCellsDead];
        for (int i = 0; i < numCellsDead; i++) {
            deadClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight * 20,
                    cellWidth, cellHeight
            );
        }

        setImage(animationImage);
        setViewport(deadClips[0]);

        deadTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsDead);
                    setViewport(deadClips[frameCounter.get()]);
                })
        );
        
        timeline = deadTimeline;
    }
    
    /**
	   * Fonctions qui éxecute les différentes animations
	   * 
	   */
    public void playContinuously() {
        isRunning = true;
        frameCounter.set(0);
        timeline = walkTimeline;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.stop();
        timeline.playFromStart();
    }

    public void playShoot(){
        frameCounter.set(0);
        timeline.stop();
        timeline = shootTimeline;
        timeline.setCycleCount(numCellsShoot);
        //timeline.setOnFinished(e -> playContinuously());
        timeline.playFromStart();
    }
    
    public void playDead(){
    	frameCounter.set(0);
        timeline.stop();
        timeline = deadTimeline;
        timeline.setCycleCount(numCellsDead);
        //timeline.setOnFinished(e -> playContinuously());
        timeline.playFromStart();
    }
    
    public void playExplosion(){
    	frameCounter.set(0);
        timeline.stop();
        timeline = explosionTimeline;
        timeline.setCycleCount(numCellsExplosion);
        timeline.setOnFinished(e -> playContinuously());
        timeline.playFromStart();
    }

    public void stop() {
        frameCounter.set(0);
        setViewport(walkClips[frameCounter.get()]);
        walkTimeline.stop();
    }
}
