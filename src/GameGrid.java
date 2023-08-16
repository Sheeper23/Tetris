

import java.util.ArrayList;

/** 
 * Represents a grid to play Tetris on. Consists of 20 rows and 10 columns. 
 * 
 * @author (Jai Misquith, Bryan Shi)
 * @version (2022-02-16)
 */
public class GameGrid {
    private int[][] grid;
    private int[][] shapeCoords; 
    private int[][] predictShapeCoords;
    private Tetromino shape;
    private Tetromino nextShape;
    private Tetromino heldShape; // implement me soon pls
    private int shapeCode;
    private int death;
    private int rowsCleared;
    private int score;
    private int rows;
    private int cols;

    /**
     * Creates the Tetris grid with 20 rows and 10 columns and loads the starting shape onto it.
     */
    public GameGrid(int rowsSet, int colsSet) {
        rows = rowsSet; // keep this ratio 2:1
        cols = colsSet; //  keep this ratio 2:1
    	grid = new int[rowsSet][colsSet];
        shapeCoords = new int[4][2];
        predictShapeCoords = new int[4][2];
        death = 0;
        rowsCleared = 0;
        score = 0;
        createNewShape();
    }
    
    
    
    /** 
     * Gets the number of rows in the grid. 
     * @return The number of rows in the grid. 
     */
    public int getRows() {
		return rows;
	}
    
    /**
	 * Set the number of rows.
	 * 
	 * @param The number of rows.
	 */
    public void setRows(int rows) {
    	this.rows = rows;
    }

    /** 
     * Gets the number of columns in the grid. 
     * @return The number of columns in the grid. 
     */
	public int getCols() {
		return cols;
	}

	/**
	 * Set the number of columns.
	 * 
	 * @param The number of columns.
	 */
	public void setCols(int cols) {
		this.cols = cols;
	}
	
	/**
	 * Gets the next queued shape.
	 * @return The next queued shape.
	 */
	public Tetromino getNextShape() {
		return nextShape;
	}

	/** 
     * Gets the matrix which holds the grid. 
     * @return The matrix which holds the grid. 
     */
    public int[][] getGrid() {
        return grid;
    }

    /** 
     * Returns the state of the player's life. A zero if the player is alive and a one if the player lost.
     * @return The state of the player's life.
     */
    public int getDeath() {
        return death;
    }

    /** 
     * Returns the number of rows cleared this game. 
     * @return The number of rows cleared this game. 
     */
    public int getRowsCleared() {
        return rowsCleared;
    }
    
    /** 
     * Returns the player's score.
     * @return The plauer's score. 
     */
    public int getScore() {
        return score;
    }
    
    

    /** 
     * Moves the active Tetromino right by 1 space if possible.
     */
    public void moveRight() {
        int[][] gridChng = new int[rows][cols];
        for (int i = 0; i < gridChng.length; i++) {
            for (int j = 0; j < gridChng[i].length; j++) {
                gridChng[i][j] = grid[i][j];
            }
        }

        int rightest = 0;
        for (int i = 0; i < shapeCoords.length; i++) {
            if (shapeCoords[i][1] > rightest) {
                rightest = shapeCoords[i][1];
            }
        }

        if (rightest < grid[0].length-1) {
            for (int i = shapeCoords.length-1; i >= 0; i--) {
                gridChng[shapeCoords[i][0]][shapeCoords[i][1]] = 0;
                shapeCoords[i][1] = shapeCoords[i][1] + 1;
                gridChng[shapeCoords[i][0]][shapeCoords[i][1]] = shapeCode;
            } 
            
            
            if (validAction(gridChng, grid, shapeCoords)) {
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i][j] = gridChng[i][j];
                    }
                }
            }
            else {
                for (int i = 0; i < shapeCoords.length; i++) {
                    shapeCoords[i][1] = shapeCoords[i][1] - 1;
                } 
            }
            
        }
        predictor();

    }

    /** 
     * Moves the active Tetromino left by 1 space if possible.
     */
    public void moveLeft() { 
        int[][] gridChng = new int[rows][cols];
        for (int i = 0; i < gridChng.length; i++) {
            for (int j = 0; j < gridChng[i].length; j++) {
                gridChng[i][j] = grid[i][j];
            }
        }

        int leftest = 9;
        for (int i = 0; i < shapeCoords.length; i++) {
            if (shapeCoords[i][1] < leftest) {
                leftest = shapeCoords[i][1];
            }
        }
        if (leftest > 0) {
            for (int i = 0; i < shapeCoords.length; i++) {
                gridChng[shapeCoords[i][0]][shapeCoords[i][1]] = 0;
                shapeCoords[i][1] = shapeCoords[i][1] - 1;
                gridChng[shapeCoords[i][0]][shapeCoords[i][1]] = shapeCode;
            } 
            
            
            if (validAction(gridChng, grid, shapeCoords)) {
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i][j] = gridChng[i][j];
                    }
                }
            }
            else {
                for (int i = 0; i < shapeCoords.length; i++) {
                    shapeCoords[i][1] = shapeCoords[i][1] + 1;
                } 
            }
            
        }
        predictor();

    }
    
    /** 
     * Rotates the active Tetromino 90 degrees clockwise if possible.
     */
    public void rotateShape() {
        
        shape.rotate();
        int[][] tempDimens = shape.getDimensions();
        
        

        grid[shapeCoords[0][0]][shapeCoords[0][1]] = 0;
        grid[shapeCoords[1][0]][shapeCoords[1][1]] = 0;
        grid[shapeCoords[2][0]][shapeCoords[2][1]] = 0;
        grid[shapeCoords[3][0]][shapeCoords[3][1]] = 0;

        int yDisp = shapeCoords[0][0];
        int xDisp = shapeCoords[0][1];

        int counter = 0;
        for (int i = 0; i < tempDimens.length; i++) {
            for (int j = 0; j < tempDimens[i].length; j++) {
                if (tempDimens[i][j] != 0) { 
                    if (!(i + yDisp <= rows-1 && j + xDisp <= cols-1 && (grid[i + yDisp][j + xDisp] == 0 || grid[i + yDisp][j + xDisp] == 30))) {
                        grid[shapeCoords[0][0]][shapeCoords[0][1]] = shapeCode;
                        grid[shapeCoords[1][0]][shapeCoords[1][1]] = shapeCode;
                        grid[shapeCoords[2][0]][shapeCoords[2][1]] = shapeCode;
                        grid[shapeCoords[3][0]][shapeCoords[3][1]] = shapeCode;
                        shape.unrotate();
                        return;
                    }
                    
                }
            }
        }
        
        for (int i = 0; i < tempDimens.length; i++) {
            for (int j = 0; j < tempDimens[i].length; j++) {
                if (tempDimens[i][j] != 0) {
                    shapeCoords[counter][0] = i + yDisp; 
                    shapeCoords[counter][1] = j + xDisp;
                    grid[shapeCoords[counter][0]][shapeCoords[counter][1]] = shapeCode;
                    counter++;
                }
            }
        }
        predictor();

    }
    
    /** 
     * Moves the active Tetromino down 1 space if possible. If the Tetromino cannot move down, it is set in place, all full rows are cleared, and a new shape is spawned.
     */
    public boolean moveDown() { 
        int[][] gridChng = new int[rows][cols];
        for (int i = 0; i < gridChng.length; i++) {
            for (int j = 0; j < gridChng[i].length; j++) {
                gridChng[i][j] = grid[i][j];
            }
        }
        if (shapeCoords[3][0] < gridChng.length-1) {
            for (int i = shapeCoords.length-1; i >= 0; i--) {
                gridChng[shapeCoords[i][0]][shapeCoords[i][1]] = 0;
                shapeCoords[i][0] = shapeCoords[i][0] + 1;
                gridChng[shapeCoords[i][0]][shapeCoords[i][1]] = shapeCode;
            } 
            
            
            if (validAction(gridChng, grid, shapeCoords)) {
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i][j] = gridChng[i][j];
                    }
                }
            }
            else {
                for (int i = 0; i < shapeCoords.length; i++) {
                    shapeCoords[i][0] = shapeCoords[i][0] - 1;
                }
                for (int i = 0; i < shapeCoords.length; i++) {
                    grid[shapeCoords[i][0]][shapeCoords[i][1]] = shapeCode + 10;
                }
                clearFullRows();
                createNewShape();
                return false;
            }
            
        }
        else {
            for (int i = 0; i < shapeCoords.length; i++) {
                grid[shapeCoords[i][0]][shapeCoords[i][1]] = shapeCode + 10;
            }
            clearFullRows();
            createNewShape();
            return false;
        }
        predictor();
        return true;
    }
    
    /**
     * Stores the current Tetromion as a held Tetromino to be spawned when the user pleases.
     */
    public void holdShape() {
    	// nvm this is gonna be more annoying than I thought, ill come back to this later
    	// and finish it hopefully??? honestly its not that big of a deal anyway
    	
    	if (shape instanceof IShape) {
			heldShape = new IShape();
		}
		else if (shape instanceof JShape) {
			heldShape = new JShape();
		}
		else if (shape instanceof LShape) {
			heldShape = new LShape();
		}
		else if (shape instanceof OShape) {
			heldShape = new OShape();
		}
		else if (shape instanceof SShape) {
			heldShape = new SShape();
		}
		else if (shape instanceof TShape) {
			heldShape = new TShape();
		}
		else if (shape instanceof ZShape) {
			heldShape = new ZShape();
		}
    }
    
    /**
     * Instantly drops the active Tetromino as far down as it can go.
     */
    public void hardDrop() {
    	for (int i = 0; i < predictShapeCoords.length; i++) {
            grid[shapeCoords[i][0]][shapeCoords[i][1]] = 0;
    		grid[predictShapeCoords[i][0]][predictShapeCoords[i][1]] = shapeCode+10;
            
        }
    	for (int i = 0; i < predictShapeCoords.length; i++) {
            shapeCoords[i][0] = predictShapeCoords[i][0];
            shapeCoords[i][1] = predictShapeCoords[i][1];
            
        }
    	moveDown();
    }
    
    
    private void predictor() {
    	ArrayList<Integer> noClears = new ArrayList<Integer>();
    	for (int i = 0; i < shapeCoords.length; i++) {
	    	for (int j = 0; j < shapeCoords.length; j++) {
				if (!(shapeCoords[i][0] == predictShapeCoords[j][0] && shapeCoords[i][1] == predictShapeCoords[j][1]) && predictShapeCoords[0][0] < rows && predictShapeCoords[0][1] < cols) {
					// lotta checks gotta happen and im too lazy to rewrite this, it works as it is so who cares
	    		}
				else {
					noClears.add(j);
				}
	    	}
    	}
    	for (int i = 0; i < predictShapeCoords.length; i++) {
    		if (noClears.contains(i)) continue;
    		grid[predictShapeCoords[i][0]][predictShapeCoords[i][1]] = 0;
    	}
    	
	    	
    	int[][] gridChng = new int[rows][cols];
        for (int i = 0; i < gridChng.length; i++) {
            for (int j = 0; j < gridChng[i].length; j++) {
                gridChng[i][j] = grid[i][j];
            }
        }
        boolean dummy = true;
        
        for (int i = 0; i < predictShapeCoords.length; i++) {
            predictShapeCoords[i][0] = shapeCoords[i][0];
            predictShapeCoords[i][1] = shapeCoords[i][1];
        }
    	while (dummy) {  		
    		dummy = moveDownTester(gridChng);
    	}
    	
    	if (shapeCoords[3][0] + 1 < predictShapeCoords[0][0]) {
    		for (int i = 0; i < predictShapeCoords.length; i++) {
                grid[predictShapeCoords[i][0]][predictShapeCoords[i][1]] = 30;
                
            }
    	}
    	
    }
    private boolean moveDownTester(int[][] grid) {
    	int[][] gridChng = new int[rows][cols];
        for (int i = 0; i < gridChng.length; i++) {
            for (int j = 0; j < gridChng[i].length; j++) {
                gridChng[i][j] = grid[i][j];
            }
        }
        
        if (predictShapeCoords[3][0] < gridChng.length-1) {
            for (int i = predictShapeCoords.length-1; i >= 0; i--) {
                gridChng[predictShapeCoords[i][0]][predictShapeCoords[i][1]] = 0;
                predictShapeCoords[i][0] = predictShapeCoords[i][0] + 1;
                gridChng[predictShapeCoords[i][0]][predictShapeCoords[i][1]] = shapeCode;
            } 
            
            
    		
            
            if (validAction(gridChng, grid, predictShapeCoords)) {
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i][j] = gridChng[i][j];
                    }
                }
            }
            else {
                for (int i = 0; i < predictShapeCoords.length; i++) {
                	predictShapeCoords[i][0] = predictShapeCoords[i][0] - 1;
                }
                return false;
            }
            
        }
        else {
            return false;
        }
        
        return true;
        
        
    }

    private void createNewShape() {
    	if (shape == null) {
	    	double randog = Math.random() * 7.0;
	        if (randog > 6) {
	            shape = new IShape();
	        }
	        else if (randog > 5) {
	        	shape = new JShape();
	        }
	        else if (randog > 4) {
	        	shape = new LShape();
	        }
	        else if (randog > 3) {
	        	shape = new OShape();
	        }
	        else if (randog > 2) {
	        	shape = new SShape();
	        }
	        else if (randog > 1) {
	        	shape = new TShape();
	        }
	        else if (randog > 0) {
	        	shape = new ZShape();
	        }
	        for (int i = 0; i < shape.getDimensions().length; i++) {
	            for (int j = 0; j < shape.getDimensions()[i].length; j++) {
	                if (shape.getDimensions()[i][j] != 0) {
	                    shapeCode = shape.getDimensions()[i][j];
	                    break;
	                }
	            }
	        }
	        
	        
	        int oldShape = shapeCode;
	    	while (oldShape == shapeCode) {
		    	double rand = Math.random() * 7.0;
		        if (rand > 6) {
		            nextShape = new IShape();
		        }
		        else if (rand > 5) {
		        	nextShape = new JShape();
		        }
		        else if (rand > 4) {
		        	nextShape = new LShape();
		        }
		        else if (rand > 3) {
		        	nextShape = new OShape();
		        }
		        else if (rand > 2) {
		        	nextShape = new SShape();
		        }
		        else if (rand > 1) {
		        	nextShape = new TShape();
		        }
		        else if (rand > 0) {
		        	nextShape = new ZShape();
		        }
		        for (int i = 0; i < nextShape.getDimensions().length; i++) {
		            for (int j = 0; j < nextShape.getDimensions()[i].length; j++) {
		                if (nextShape.getDimensions()[i][j] != 0) {
		                    shapeCode = nextShape.getDimensions()[i][j];
		                    break;
		                }
		            }
		        }
	        }
    	}
    	else {
    		if (nextShape instanceof IShape) {
    			shape = new IShape();
    		}
    		else if (nextShape instanceof JShape) {
    			shape = new JShape();
    		}
    		else if (nextShape instanceof LShape) {
    			shape = new LShape();
    		}
    		else if (nextShape instanceof OShape) {
    			shape = new OShape();
    		}
    		else if (nextShape instanceof SShape) {
    			shape = new SShape();
    		}
    		else if (nextShape instanceof TShape) {
    			shape = new TShape();
    		}
    		else if (nextShape instanceof ZShape) {
    			shape = new ZShape();
    		}
    		for (int i = 0; i < shape.getDimensions().length; i++) {
	            for (int j = 0; j < shape.getDimensions()[i].length; j++) {
	                if (shape.getDimensions()[i][j] != 0) {
	                    shapeCode = shape.getDimensions()[i][j];
	                    break;
	                }
	            }
	        }
    		int oldShape = shapeCode;
    		
        	while (oldShape == shapeCode) {
    	    	double rand = Math.random() * 7.0;
    	        if (rand > 6) {
    	            nextShape = new IShape();
    	        }
    	        else if (rand > 5) {
    	        	nextShape = new JShape();
    	        }
    	        else if (rand > 4) {
    	        	nextShape = new LShape();
    	        }
    	        else if (rand > 3) {
    	        	nextShape = new OShape();
    	        }
    	        else if (rand > 2) {
    	        	nextShape = new SShape();
    	        }
    	        else if (rand > 1) {
    	        	nextShape = new TShape();
    	        }
    	        else if (rand > 0) {
    	        	nextShape = new ZShape();
    	        }
    	        for (int i = 0; i < nextShape.getDimensions().length; i++) {
    	            for (int j = 0; j < nextShape.getDimensions()[i].length; j++) {
    	                if (nextShape.getDimensions()[i][j] != 0) {
    	                    shapeCode = nextShape.getDimensions()[i][j];
    	                    break;
    	                }
    	            }
    	        }
            }
    	}
    	
        
        
        
        int counter = 0;
        for (int i = 0; i < shape.getDimensions().length; i++) {
            for (int j = 0; j < shape.getDimensions()[i].length; j++) {
                if (shape.getDimensions()[i][j] != 0) {
                    shapeCode = shape.getDimensions()[i][j];
                    shapeCoords[counter][0] = i;
                    shapeCoords[counter][1] = j + (grid[i].length-4)/2;
                    counter++;
                    if (grid[i][j+ (grid[i].length-4)/2] == 0) {
                        grid[i][j+ (grid[i].length-4)/2] = shapeCode;
                    }
                    else {
                        death = 1;
                    }
                }
            }
        }
        for (int i = 0; i < predictShapeCoords.length; i++) {
            predictShapeCoords[i][0] = rows;
            predictShapeCoords[i][1] = cols;
        }
        predictor();
    }


    private void clearFullRows() { 
        ArrayList<Integer> clearedRows = new ArrayList<Integer>();
        for (int i = grid.length - 1; i >= 0; i--) {
            boolean doMe = true;
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    doMe = false;
                    break;
                }
            }
            if (doMe) {
                for (int z = 0; z < grid[i].length; z++) {
                    grid[i][z] = 0;
                }
                clearedRows.add(i);
                rowsCleared++;
            }
        }
        
        score += clearedRows.size() * clearedRows.size() * 1000;
        for (int r = 0; r < clearedRows.size(); r++) {
            for (int i = clearedRows.get(r); i >= 1; i--) {
                for (int j = 0; j < grid[i].length; j++) {
                    grid[i][j] = grid[i-1][j];
                }
            }
            if (r == clearedRows.size()-1) {
                return;
            }
            for (int nice = r+1; nice < clearedRows.size(); nice++) {
                clearedRows.set(nice, clearedRows.get(nice)+1);
            }
        }
        
    }

    private boolean validAction(int[][] gridChng, int[][] grid, int[][] shapeCoords) { 
        for (int i = 0; i < shapeCoords.length; i++) {
            if ((gridChng[shapeCoords[i][0]][shapeCoords[i][1]] - grid[shapeCoords[i][0]][shapeCoords[i][1]]) != gridChng[shapeCoords[i][0]][shapeCoords[i][1]]) {
                if ((gridChng[shapeCoords[i][0]][shapeCoords[i][1]] - grid[shapeCoords[i][0]][shapeCoords[i][1]]) == 0) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

}
