

import java.util.HashMap; 

/** 
 * Represents an S shaped Tetromino. 
 *
 * @author (Jai Misquith, Bryan Shi)
 * @version (2022-02-16)
 */ 
public class SShape implements Tetromino { 
    private int[][] dimensions; 
    private int orientation; 
    private HashMap<Integer, int[][]> dimensionMap; 

    /** 
     * Creates an S shaped Tetromino and stores all of its possible rotated dimensions. 
     */ 
    public SShape() { 
        orientation = 0; 
        dimensionMap = new HashMap<>(); 
        dimensionMap.put(0, new int[][] {
            {0, 5, 5}, 
            {5, 5, 0}, 
        }); 
        dimensionMap.put(1, new int[][] {
            {5, 0}, 
            {5, 5}, 
            {0, 5}, 
        }); 
        dimensions = dimensionMap.get(orientation); 
    }

    /** 
     * Rotates the S shaped Tetromino 90 degrees clockwise. 
     */ 
    public void rotate() { 
        if (orientation == 0) { 
            orientation++; 
        }
        else { 
            orientation = 0; 
        }
        dimensions = dimensionMap.get(orientation); 
    }

    /** 
     * Rotates the S shaped Tetromino 90 degrees counterclockwise. 
     */ 
    public void unrotate() { 
        if (orientation != 0) { 
            orientation--; 
        }
        else { 
            orientation = 1; 
        }
        dimensions = dimensionMap.get(orientation); 
    }

    /** 
     * Returns the current dimensions of the S shaped Tetromino. 
     * @return Current dimensions of the S shaped Tetromino. 
     */ 
    public int[][] getDimensions() { 
        return dimensions;
    }
}

