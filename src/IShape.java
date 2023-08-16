

import java.util.HashMap; 

/** 
 * Represents an I shaped Tetromino. 
 *
 * @author (Jai Misquith, Bryan Shi)
 * @version (2022-02-16)
 */ 
public class IShape implements Tetromino { 
    private int[][] dimensions; 
    private int orientation; 
    private HashMap<Integer, int[][]> dimensionMap; 

    /** 
     * Creates an I shaped Tetromino and stores all of its possible rotated dimensions. 
     */ 
    public IShape() { 
        orientation = 0; 
        dimensionMap = new HashMap<>(); 
        dimensionMap.put(0, new int[][] {
            {1, 1, 1, 1}, 
        }); 
        dimensionMap.put(1, new int[][] {
            {1}, 
            {1}, 
            {1}, 
            {1}
        }); 
        dimensions = dimensionMap.get(orientation); 
    } 

    /** 
     * Rotates the I shaped Tetromino 90 degrees clockwise. 
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
     * Rotates the I shaped Tetromino 90 degrees counterclockwise. 
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
     * Returns the current dimensions of the I shaped Tetromino. 
     * @return Current dimensions of the I shaped Tetromino. 
     */ 
    public int[][] getDimensions() { 
        return dimensions;
    }
}
