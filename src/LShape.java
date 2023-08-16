

import java.util.HashMap;

/** 
 * Represents an L shaped Tetromino. 
 *
 * @author (Jai Misquith, Bryan Shi)
 * @version (2022-02-16)
 */ 
public class LShape implements Tetromino { 
    private int[][] dimensions; 
    private int orientation; 
    private HashMap<Integer, int[][]> dimensionMap; 

    /** 
     * Creates an L shaped Tetromino and stores all of its possible rotated dimensions. 
     */ 
    public LShape() { 
        orientation = 0; 
        dimensionMap = new HashMap<>();
        dimensionMap.put(0, new int[][] {
            {0, 0, 3}, 
            {3, 3, 3}, 
        }); 
        dimensionMap.put(1, new int[][] {
            {3, 0}, 
            {3, 0}, 
            {3, 3}, 
        });   
        dimensionMap.put(2, new int[][] {
            {3, 3, 3}, 
            {3, 0, 0}, 
        });   
        dimensionMap.put(3, new int[][] {
            {3, 3}, 
            {0, 3}, 
            {0, 3}, 
        }); 
        dimensions = dimensionMap.get(orientation); 
    }

    /** 
     * Rotates the L shaped Tetromino 90 degrees clockwise. 
     */ 
    public void rotate() { 
        if (orientation < 3) { 
            orientation++; 
        }
        else { 
            orientation = 0; 
        }
        dimensions = dimensionMap.get(orientation); 
    }

    /** 
     * Rotates the L shaped Tetromino 90 degrees counterclockwise. 
     */ 
    public void unrotate() { 
        if (orientation != 0) { 
            orientation--; 
        }
        else { 
            orientation = 3; 
        }
        dimensions = dimensionMap.get(orientation); 
    }

    /** 
     * Returns the current dimensions of the L shaped Tetromino. 
     * @return Current dimensions of the L shaped Tetromino. 
     */ 
    public int[][] getDimensions() { 
        return dimensions;
    }
}
