

/** 
 * An interface that represents the Tetromino shapes in Tetris. 
 *
 * @author (Jai Misquith, Bryan Shi)
 * @version (2022-02-16)
 */ 
public interface Tetromino { 
    /** 
     * Rotates a Tetromino 90 degrees clockwise. 
     */ 
    public void rotate(); 

    /**
     * Rotates a Tetromino 90 degrees counterclockwise. 
     */ 
    public void unrotate(); 

    /** 
     * Returns the current dimensions of a Tetromino. 
     * @return The current dimensions of a Tetromino. 
     */ 
    public int[][] getDimensions(); 
} 

