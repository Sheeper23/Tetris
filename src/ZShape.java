

import java.util.HashMap; 

/** 
 * Represents an Z shaped Tetromino. 
 *
 * @author (Jai Misquith, Bryan Shi)
 * @version (2022-02-16)
 */ 
public class ZShape implements Tetromino { 
    private int[][] dimensions; 
    private int orientation; 
    private HashMap<Integer, int[][]> dimensionMap; 

    /** 
     * Creates a Z shaped Tetromino and stores all of its possible rotated dimensions. 
     */ 
    public ZShape() { 
        orientation = 0; 
        dimensionMap = new HashMap<>(); 
        dimensionMap.put(0, new int[][] {
            {7, 7, 0}, 
            {0, 7, 7}, 
        }); 
        dimensionMap.put(1, new int[][] {
            {0, 7}, 
            {7, 7}, 
            {7, 0}, 
        }); 
        dimensions = dimensionMap.get(orientation); 
    }

    /** 
     * Rotates the Z shaped Tetromino 90 degrees clockwise. 
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
     * Rotates the Z shaped Tetromino 90 degrees counterclockwise. 
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
     * Returns the current dimensions of the Z shaped Tetromino. 
     * @return Current dimensions of the Z shaped Tetromino. 
     */ 
    public int[][] getDimensions() { 
        return dimensions;
    }
} 
