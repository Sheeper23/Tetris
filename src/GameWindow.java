

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** 
 * Represents the display of the Tetris game.
 * 
 * @author (Jai Misquith, Bryan Shi)
 * @version (2022-02-16)
 */
public class GameWindow extends JFrame {
    private GameGrid grid;
    private JPanel gridPanel;         
    private JPanel extraneousInfo;    
    private JPanel nextShapePanel;    
    private JPanel holdShapePanel;
    private JLabel score;
    
    /**
     * Creates the window which houses the Tetris grid and the shapes on it, as well as the listener for directional key inputs from the user. Constantly moves the current Tetromino down at regular intervals and updates the display when changes are made. If the player has lost, displays the Game Over screen which shows how many rows were cleared.
     */
    public GameWindow(int rows, int cols) {
        super("TETRIS");
        
        setSize((int)(800 * ((double)cols/rows)) + 600, 800);
        setLocation(450, 50);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        
        grid = new GameGrid(rows, cols);
        gridPanel = new JPanel();
        extraneousInfo = new JPanel();
        nextShapePanel = new JPanel();
        holdShapePanel = new JPanel();
        score = new JLabel("Score: "+grid.getScore());
        
        
        
        

    }
    
    public void startGame() {
    	
        
        
        gridPanel.setBackground(Color.BLACK);
        gridPanel.setLayout(new GridLayout(grid.getRows(), grid.getCols(), 1, 1)); // change to 0, 0 for SMOOTH
        gridPanel.setMaximumSize(new Dimension((int)(800 * ((double)grid.getCols()/grid.getRows())), 800));
        
        extraneousInfo.setBackground(Color.DARK_GRAY);
        extraneousInfo.setLayout(new BoxLayout(extraneousInfo, BoxLayout.Y_AXIS));
        extraneousInfo.setMaximumSize(new Dimension(600, 800));
        
        nextShapePanel.setBackground(Color.BLACK);
        nextShapePanel.setLayout(new GridLayout(4, 4, 1, 1));
        nextShapePanel.setMaximumSize(new Dimension(300, 300));
        
        holdShapePanel.setBackground(Color.BLACK);
        holdShapePanel.setLayout(new GridLayout(4, 4, 1, 1));
        holdShapePanel.setMaximumSize(new Dimension(300, 300));
        
        JPanel shapesInfo = new JPanel();
        shapesInfo.setLayout(new BoxLayout(shapesInfo, BoxLayout.X_AXIS));
        shapesInfo.setBackground(Color.BLACK);
        shapesInfo.setMaximumSize(new Dimension(600, 300));
        
        score.setForeground(Color.WHITE);
        
        add(gridPanel);
        extraneousInfo.add(score);
        shapesInfo.add(nextShapePanel);
        shapesInfo.add(holdShapePanel);
        extraneousInfo.add(shapesInfo);
        add(extraneousInfo);
        drawGrid();
        drawNextShape();
        drawHeldShape();
        
        
        
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                // first set represents arrow keys + shift, second set represents WASD + space
                if (key == KeyEvent.VK_UP || key == 87) {  
                    grid.rotateShape();
                    drawGrid();
                }
                if (key == KeyEvent.VK_DOWN || key == 83) {
                    grid.moveDown();
                    drawGrid();
                    drawNextShape();
                }
                if (key == KeyEvent.VK_LEFT || key == 65) {
                    grid.moveLeft();
                    drawGrid();
                }
                if (key == KeyEvent.VK_RIGHT || key == 68) {
                    grid.moveRight();
                    drawGrid();
                }
                if (key == 16 || key == 32) {
                	grid.hardDrop();
                	drawGrid();
                	drawNextShape();
                }
                
            }

            public void keyTyped(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });
        
        
        
    }
    
    public void autoMoveDown() {
        
        grid.moveDown();
        drawGrid();
        drawNextShape();
        drawHeldShape();
        
        
        
    }

    private void drawGrid() {
        gridPanel.removeAll();
        if (grid.getDeath() == 1) {
        	getContentPane().removeAll();
            JPanel panel = new JPanel();
            setLayout(new GridLayout(1, 1, 0, 0));
            panel.setLayout(new GridLayout(3, 1, 0, 0));
            panel.setBackground(Color.WHITE);
            JLabel text1 = new JLabel();
            JLabel text2 = new JLabel();
            JLabel text3 = new JLabel();
            text1.setText("               You lose!");
            text2.setText("            You cleared:");
            if (grid.getRowsCleared() == 1) {
                text3.setText("               1 row. Score of: " + grid.getScore());
            }
            else {
                text3.setText("                   " + grid.getRowsCleared() + " rows. Score of: " + grid.getScore());
            }
            
            panel.add(text1);
            panel.add(text2);
            panel.add(text3);
            add(panel);
            getContentPane().revalidate();
            getContentPane().repaint();
        }
        else {
            for (int i = 0; i < grid.getGrid().length; i++) {
                for (int j = 0; j < grid.getGrid()[i].length; j++) {
                    JPanel panel = new JPanel();
                    if (grid.getGrid()[i][j] == 1 || grid.getGrid()[i][j] == 11) {
                        panel.setBackground(Color.CYAN);
                    }
                    else if (grid.getGrid()[i][j] == 2 || grid.getGrid()[i][j] == 12) {
                        panel.setBackground(Color.BLUE);
                    }
                    else if (grid.getGrid()[i][j] == 3 || grid.getGrid()[i][j] == 13) {
                        panel.setBackground(Color.ORANGE);
                    }
                    else if (grid.getGrid()[i][j] == 4 || grid.getGrid()[i][j] == 14) {
                        panel.setBackground(Color.YELLOW);
                    }
                    else if (grid.getGrid()[i][j] == 5 || grid.getGrid()[i][j] == 15) {
                        panel.setBackground(Color.GREEN);
                    }
                    else if (grid.getGrid()[i][j] == 6 || grid.getGrid()[i][j] == 16) {
                        panel.setBackground(Color.MAGENTA);
                    }
                    else if (grid.getGrid()[i][j] == 7 || grid.getGrid()[i][j] == 17) {
                        panel.setBackground(Color.RED);
                    }
                    else if (grid.getGrid()[i][j] == 30) {
                    	panel.setBackground(Color.GRAY);
                    }
                    else {
                        panel.setBackground(Color.LIGHT_GRAY);
                    }
                    
                    gridPanel.add(panel);
                }
            }
            score.setText("Score: " + grid.getScore());
            gridPanel.revalidate();
            gridPanel.repaint();
        }
        
        
    }
    
    private void drawNextShape() {
    	nextShapePanel.removeAll();
    	int[][] nextShapeGrid = new int[4][4];
    	
    	for (int i = 0; i < grid.getNextShape().getDimensions().length; i++) {
    		for (int j = 0; j < grid.getNextShape().getDimensions()[i].length; j++) {
    			if (grid.getNextShape().getDimensions()[i][j] != 0) {
    				nextShapeGrid[i+1][j] = grid.getNextShape().getDimensions()[i][j];
    			}
    		}
    	}
    	
    	for (int i = 0; i < nextShapeGrid.length; i++) {
            for (int j = 0; j < nextShapeGrid[i].length; j++) {
                JPanel panel = new JPanel();
                if (nextShapeGrid[i][j] == 1) {
                    panel.setBackground(Color.CYAN);
                }
                else if (nextShapeGrid[i][j] == 2) {
                    panel.setBackground(Color.BLUE);
                }
                else if (nextShapeGrid[i][j] == 3) {
                    panel.setBackground(Color.ORANGE);
                }
                else if (nextShapeGrid[i][j] == 4) {
                    panel.setBackground(Color.YELLOW);
                }
                else if (nextShapeGrid[i][j] == 5) {
                    panel.setBackground(Color.GREEN);
                }
                else if (nextShapeGrid[i][j] == 6) {
                    panel.setBackground(Color.MAGENTA);
                }
                else if (nextShapeGrid[i][j] == 7) {
                    panel.setBackground(Color.RED);
                }
                else {
                    panel.setBackground(Color.LIGHT_GRAY);
                }
                
                
                nextShapePanel.add(panel);
            }
        }
    	
    	
    	
    	
    	nextShapePanel.revalidate();
    	nextShapePanel.repaint();
    	
    	
    }
    
    private void drawHeldShape() {
    	holdShapePanel.removeAll();
    	int[][] holdShapeGrid = new int[4][4];
    	for (int i = 0; i < holdShapeGrid.length; i++) {
            for (int j = 0; j < holdShapeGrid[i].length; j++) {
                JPanel panel = new JPanel();
                if (holdShapeGrid[i][j] == 1) {
                    panel.setBackground(Color.CYAN);
                }
                else if (holdShapeGrid[i][j] == 2) {
                    panel.setBackground(Color.BLUE);
                }
                else if (holdShapeGrid[i][j] == 3) {
                    panel.setBackground(Color.ORANGE);
                }
                else if (holdShapeGrid[i][j] == 4) {
                    panel.setBackground(Color.YELLOW);
                }
                else if (holdShapeGrid[i][j] == 5) {
                    panel.setBackground(Color.GREEN);
                }
                else if (holdShapeGrid[i][j] == 6) {
                    panel.setBackground(Color.MAGENTA);
                }
                else if (holdShapeGrid[i][j] == 7) {
                    panel.setBackground(Color.RED);
                }
                else {
                    panel.setBackground(Color.LIGHT_GRAY);
                }
                
                holdShapePanel.add(panel);
            }
        }
    	
    	
    	holdShapePanel.revalidate();
    	holdShapePanel.repaint();
    }
}
