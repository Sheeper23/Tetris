

/** 
 * Represents an O shaped Tetromino. 
 *
 * @author (Jai Misquith, Bryan Shi)
 * @version (2022-02-16)
 */ 
public class OShape implements Tetromino { 
    private int[][] dimensions; 

    /** 
     * Creates an O shaped Tetromino and stores all of its possible rotated dimensions. 
     */ 
    public OShape() { 
        dimensions = new int[][] {
            {4, 4}, 
            {4, 4}, 
        }; 
    }

    /** 
     * Rotates the O shaped Tetromino 90 degrees clockwise. 
     */ 
    public void rotate() {   
    }

    /** 
     * Rotates the O shaped Tetromino 90 degrees counterclockwise. 
     */ 
    public void unrotate() { 
    } 

    /** 
     * Returns the current dimensions of the O shaped Tetromino. 
     * @return Current dimensions of the O shaped Tetromino. 
     */ 
    public int[][] getDimensions() { 
        return dimensions;
    }
}
