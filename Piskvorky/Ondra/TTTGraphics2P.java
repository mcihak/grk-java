//package tictac;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Tic-Tac-Toe: Player vs. AI Graphics version with Simple-OO
 * 
 * Autor: LakomýOndøej
 */
@SuppressWarnings("serial")
public class TTTGraphics2P extends JFrame {
   // Named-constants for the game board
   public static final int ROWS = 10;  // ROWS by COLS cells
   public static final int COLS = 10;
   
   public static final int DOUBLE = 100;	// points for each possible couple
   public static final int TRIPLE = 1000;	// points for each possible triple
   public static final int TRIPLE_BLOCK = 10000; // points for each possible triple block
   public static final int QUADRA = 100000; // points for each possible quadra
   public static final int QUADRA_BLOCK = 10000000; // points for each possible quadra block
   public static final int PENTA = 100000000; // points for each possible penta
   public static final int UNPROT_QUADRA = 1000000; // points for each possible unprotectable quadra
   
   int rowSelected;
   int colSelected;
   int score = 0;
   int maxScore = 0;
   int rowPos = 0;
   int colPos = 0;
   int cellCount = 0;
   boolean hasWon = false;
 
   // Named-constants of the various dimensions used for graphics drawing
   public static final int CELL_SIZE = 60; // cell width and height (square)
   public static final int CANVAS_WIDTH = CELL_SIZE * COLS;  // the drawing canvas
   public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
   public static final int GRID_WIDTH = 6;                   // Grid-line's width
   public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width
   // Symbols (cross/nought) are displayed inside a cell, with padding from border
   public static final int CELL_PADDING = CELL_SIZE / 6;
   public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; // width/height
   public static final int SYMBOL_STROKE_WIDTH = 6; // pen's stroke width
 
   // Use an enumeration (inner class) to represent the various states of the game
   public enum GameState {
      PLAYING, DRAW, CROSS_WON, NOUGHT_WON
   }
   private GameState currentState;  // the current game state
 
   // Use an enumeration (inner class) to represent the seeds and cell contents
   public enum Seed {
      EMPTY, CROSS, NOUGHT
   }
   private Seed currentPlayer;  // the current player
 
   private Seed[][] board   ; // Game board of ROWS-by-COLS cells
   private DrawCanvas canvas; // Drawing canvas (JPanel) for the game board
   private JLabel statusBar;  // Status Bar
   
   /** Constructor to setup the game and the GUI components */
   public TTTGraphics2P() {
      canvas = new DrawCanvas();  // Construct a drawing canvas (a JPanel)
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // The canvas (JPanel) fires a MouseEvent upon mouse-click
      canvas.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {  // mouse-clicked handler
            int mouseX = e.getX();
            int mouseY = e.getY();
         // Get the row and column clicked
            rowSelected = mouseY / CELL_SIZE;
            colSelected = mouseX / CELL_SIZE;
           
	            if (currentState == GameState.PLAYING && currentPlayer == Seed.CROSS) {
	                if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0
		                      && colSelected < COLS && board[rowSelected][colSelected] == Seed.EMPTY) {
		                   board[rowSelected][colSelected] = currentPlayer; // Make a move
		                   updateGame(currentPlayer); // update state
		                  currentPlayer = Seed.NOUGHT;
		                }

	            	// Computer turn
	            	if(currentPlayer == Seed.NOUGHT && currentState == GameState.PLAYING) {
	            		for(int x = 0; x < ROWS; x++){
	            			for(int y = 0; y < COLS; y++){
	            				
	            						//Need to consider later if there are cells enough to finish fifth (not to do useless moves)	
	            			
	            		//Computer's attack	
	            			//Counting points how much in the middle the cell is
	            				if(x > 1 && x < 8 && y > 1 && y < 8){
	            					if(x > 2 && x < 7 && y > 2 && y < 7){
	            						int random = (int)(Math.random()*99);
	            						score += random;
	            					}
	            					else{
	            						int random = (int)(Math.random()*70);
	            						score += random;
	            					}
	            				}
	            				
	            			//Making a couple if possible
	            				
	            				// If a free cell is nearby owned cell, counts +COUPLE for each one
	            				
	            				if(x > 0){
	            					if(board[x-1][y] == Seed.NOUGHT){score += DOUBLE;}
	            				}
	            				if(x > 0 && y < 9){
	            					if(board[x-1][y+1] == Seed.NOUGHT){score += DOUBLE;}
	            				}
	            				if(y < 9){
	            					if(board[x][y+1] == Seed.NOUGHT){score += DOUBLE;}
	            				}
	            				if(x < 9 && y < 9){
	            					if(board[x+1][y+1] == Seed.NOUGHT){score += DOUBLE;}
	            				}
	            				if(x < 9){
	            					if(board[x+1][y] == Seed.NOUGHT){score += DOUBLE;}
	            				}
	            				if(x < 9 && y > 0){
	            					if(board[x+1][y-1] == Seed.NOUGHT){score += DOUBLE;}
	            				}
	            				if(y > 0){
	            					if(board[x][y-1] == Seed.NOUGHT){score += DOUBLE;}
	            				}
	            				if(x > 0 && y > 0){
	            					if(board[x-1][y-1] == Seed.NOUGHT){score += DOUBLE;}
	            				}
	            				
	            			//Making a triple if possible
	            				// If a free cell is nearby owned cell, counts +TRIPLE for each one
		            			// Considered the checked cell to be the side one
	            				// top, top-right, right, bot-right, bot, bot-left, left, top-left	
		            			if(x > 1){
		            				if(board[x-2][y] == Seed.NOUGHT && board[x-1][y] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(x > 1 && y < 8){
		            				if(board[x-2][y+2] == Seed.NOUGHT && board[x-1][y+1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(y < 8){
		            				if(board[x][y+2] == Seed.NOUGHT && board[x][y+1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(x < 8 && y < 8){
		            				if(board[x+2][y+2] == Seed.NOUGHT && board[x+1][y+1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(x < 8){
		            				if(board[x+2][y] == Seed.NOUGHT && board[x+1][y] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(x < 8 && y > 1){
		            				if(board[x+2][y-2] == Seed.NOUGHT && board[x+1][y-1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(y > 1){
		            				if(board[x][y-2] == Seed.NOUGHT && board[x][y-1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(x > 1 && y > 1){
		            				if(board[x-2][y-2] == Seed.NOUGHT && board[x-1][y-1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			
		            			// now considered to be the middle cell
		            			if(x > 0 && x < 9){
		            				if(board[x-1][y] == Seed.NOUGHT && board[x+1][y] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(y > 0 && y < 9){
		            				if(board[x][y-1] == Seed.NOUGHT && board[x][y+1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(x > 0 && x < 9 && y > 0 && y < 9){
		            				if(board[x-1][y+1] == Seed.NOUGHT && board[x+1][y-1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			if(x > 0 && x < 9 && y > 0 && y < 9){
		            				if(board[x+1][y+1] == Seed.NOUGHT && board[x-1][y-1] == Seed.NOUGHT){score += TRIPLE;}
		            			}
		            			
		            		//Making a quadra if possible with avoiding useless moves
		            			// considered to be the side cell
		            			// horizontal
		            			if(y > 2 && board[x][y-3] == Seed.NOUGHT && board[x][y-2] == Seed.NOUGHT && board[x][y-1] == Seed.NOUGHT){
		            				switch(y){
		            					case 3: if(board[x][y+1] == Seed.EMPTY){score += QUADRA;}
		            					case 9: if(board[x][y-4] == Seed.EMPTY){score += QUADRA;}
		            				}
		            				if(y > 3 && y < 9){
		            					if(board[x][y+1] == Seed.EMPTY && board[x][y-4] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x][y+1] == Seed.EMPTY && board[x][y-4] == Seed.CROSS ) || (board[x][y+1] == Seed.CROSS && board[x][y-4] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(y < 7 && board[x][y+3] == Seed.NOUGHT && board[x][y+2] == Seed.NOUGHT && board[x][y+1] == Seed.NOUGHT){
		            				switch(y){
		            					case 6: if(board[x][y-1] == Seed.EMPTY){score += QUADRA;}
		            					case 0: if(board[x][y+4] == Seed.EMPTY){score += QUADRA;}
		            				}
		            				if(y < 6 && y > 0){
		            					if(board[x][y-1] == Seed.EMPTY && board[x][y+4] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x][y-1] == Seed.EMPTY && board[x][y+4] == Seed.CROSS ) || (board[x][y-1] == Seed.CROSS && board[x][y+4] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			// vertical
		            			if(x > 2 && board[x-3][y] == Seed.NOUGHT && board[x-2][y] == Seed.NOUGHT && board[x-1][y] == Seed.NOUGHT){
		            				switch(x){
		            					case 3: if(board[x+1][y] == Seed.EMPTY){score += QUADRA;}
		            					case 9: if(board[x-4][y] == Seed.EMPTY){score += QUADRA;}
		            				}
		            				if(x > 3 && x < 9){
		            					if(board[x+1][y] == Seed.EMPTY && board[x-4][y] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x+1][y] == Seed.EMPTY && board[x-4][y] == Seed.CROSS ) || (board[x+1][y] == Seed.CROSS && board[x-4][y] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(x < 7 && board[x+3][y] == Seed.NOUGHT && board[x+2][y] == Seed.NOUGHT && board[x+1][y] == Seed.NOUGHT){
		            				switch(x){
		            					case 6: if(board[x-1][y] == Seed.EMPTY){score += QUADRA;}
		            					case 0: if(board[x+4][y] == Seed.EMPTY){score += QUADRA;}
		            				}
		            				if(x < 6 && x > 0){
		            					if(board[x-1][y] == Seed.EMPTY && board[x+4][y] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x-1][y] == Seed.EMPTY && board[x+4][y] == Seed.CROSS ) || (board[x-1][y] == Seed.CROSS && board[x+4][y] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}// diagonal
		            			if(x > 2 && y > 2 && board[x-3][y-3] == Seed.NOUGHT && board[x-2][y-2] == Seed.NOUGHT && board[x-1][y-1] == Seed.NOUGHT){
		            				if(x == 3 && y == 3 && board[x+1][y+1] == Seed.EMPTY){score += QUADRA;}
		            				if(x == 9 && y == 9 && board[x-4][y-4] == Seed.EMPTY){score += QUADRA;}
		            				
		            				if(x > 3 && x < 9 && y > 3 && y < 9){
		            					if(board[x+1][y+1] == Seed.EMPTY && board[x-4][y-4] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x+1][y+1] == Seed.EMPTY && board[x-4][y-4] == Seed.CROSS ) || (board[x+1][y+1] == Seed.CROSS && board[x-4][y-4] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(x < 7 && y > 2 && board[x+3][y-3] == Seed.NOUGHT && board[x+2][y-2] == Seed.NOUGHT && board[x+1][y-1] == Seed.NOUGHT){
		            				if(x == 6 && y == 3 && board[x-1][y+1] == Seed.EMPTY){score += QUADRA;}
		            				if(x == 0 && y == 9 && board[x+4][y-4] == Seed.EMPTY){score += QUADRA;}
		            				
		            				if(x < 6 && x > 0 && y > 3 && y < 9){
		            					if(board[x-1][y+1] == Seed.EMPTY && board[x+4][y-4] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x-1][y+1] == Seed.EMPTY && board[x+4][y-4] == Seed.CROSS ) || (board[x-1][y+1] == Seed.CROSS && board[x+4][y-4] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(x < 7 && y < 7 && board[x+3][y+3] == Seed.NOUGHT && board[x+2][y+2] == Seed.NOUGHT && board[x+1][y+1] == Seed.NOUGHT){
		            				if(x == 6 && y == 6 && board[x-1][y-1] == Seed.EMPTY){score += QUADRA;}
		            				if(x == 0 && y == 0 && board[x+4][y+4] == Seed.EMPTY){score += QUADRA;}
		            				
		            				if(x < 6 && x > 0 && y < 6 && y > 0){
		            					if(board[x-1][y-1] == Seed.EMPTY && board[x+4][y+4] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x-1][y-1] == Seed.EMPTY && board[x+4][y+4] == Seed.CROSS ) || (board[x-1][y-1] == Seed.CROSS && board[x+4][y+4] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(x > 2 && y < 7 && board[x-3][y+3] == Seed.NOUGHT && board[x-2][y+2] == Seed.NOUGHT && board[x-1][y+1] == Seed.NOUGHT){
		            				if(x == 3 && y == 6 && board[x+1][y-1] == Seed.EMPTY){score += QUADRA;}
		            				if(x == 9 && y == 0 && board[x-4][y+4] == Seed.EMPTY){score += QUADRA;}
		            				
		            				if(x > 3 && x < 9 && y < 6 && y > 0){
		            					if(board[x+1][y-1] == Seed.EMPTY && board[x-4][y+4] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x+1][y-1] == Seed.EMPTY && board[x-4][y+4] == Seed.CROSS ) || (board[x+1][y-1] == Seed.CROSS && board[x-4][y+4] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			
		            			// considered to be a middle cell
		            			// horizontal
		            			if(y > 0 && y < 8 && board[x][y-1] == Seed.NOUGHT && board[x][y+1] == Seed.NOUGHT && board[x][y+2] == Seed.NOUGHT){
		            				switch(y){
		            					case 1: if(board[x][y+3] == Seed.EMPTY){score += QUADRA;}
		            					case 7: if(board[x][y-2] == Seed.EMPTY){score += QUADRA;}
		            				}
		            				if(y > 1 && y < 7){
		            					if(board[x][y+3] == Seed.EMPTY && board[x][y-2] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x][y+3] == Seed.EMPTY && board[x][y-2] == Seed.CROSS ) || (board[x][y+3] == Seed.CROSS && board[x][y-2] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(y < 9 && y > 1 && board[x][y+1] == Seed.NOUGHT && board[x][y-1] == Seed.NOUGHT && board[x][y-2] == Seed.NOUGHT){
		            				switch(y){
		            					case 8: if(board[x][y-3] == Seed.EMPTY){score += QUADRA;}
		            					case 2: if(board[x][y+2] == Seed.EMPTY){score += QUADRA;}
		            				}
		            				if(y < 8 && y > 2){
		            					if(board[x][y-3] == Seed.EMPTY && board[x][y+2] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x][y-3] == Seed.EMPTY && board[x][y+2] == Seed.CROSS ) || (board[x][y-3] == Seed.CROSS && board[x][y+2] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			// vertical
		            			if(x > 0 && x < 8 && board[x-1][y] == Seed.NOUGHT && board[x+1][y] == Seed.NOUGHT && board[x+2][y] == Seed.NOUGHT){
		            				switch(x){
		            					case 1: if(board[x+3][y] == Seed.EMPTY){score += QUADRA;}
		            					case 7: if(board[x-2][y] == Seed.EMPTY){score += QUADRA;}
		            				}
		            				if(x > 1 && x < 7){
		            					if(board[x+3][y] == Seed.EMPTY && board[x-2][y] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x+3][y] == Seed.EMPTY && board[x-2][y] == Seed.CROSS ) || (board[x+3][y] == Seed.CROSS && board[x-2][y] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(x < 9 && x > 1 && board[x+1][y] == Seed.NOUGHT && board[x-1][y] == Seed.NOUGHT && board[x-2][y] == Seed.NOUGHT){
		            				switch(x){
		            					case 8: if(board[x-3][y] == Seed.EMPTY){score += QUADRA;}
		            					case 2: if(board[x+2][y] == Seed.EMPTY){score += QUADRA;}
		            				}
		            				if(y < 8 && y > 2){
		            					if(board[x-3][y] == Seed.EMPTY && board[x+2][y] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x-3][y] == Seed.EMPTY && board[x+2][y] == Seed.CROSS ) || (board[x-3][y] == Seed.CROSS && board[x+2][y] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			// diagonal
		            			if(x > 0 && x < 8 && y > 0 && y < 8 && board[x-1][y-1] == Seed.NOUGHT && board[x+1][y+1] == Seed.NOUGHT && board[x+2][y+2] == Seed.NOUGHT){
		            				if(x == 1 && y == 1 && board[x+3][y+3] == Seed.EMPTY){score += QUADRA;}
		            				if(x == 9 && y == 9 && board[x-2][y-2] == Seed.EMPTY){score += QUADRA;}
		            				
		            				if(x > 1 && x < 7 && y > 1 && y < 7){
		            					if(board[x+3][y+3] == Seed.EMPTY && board[x-2][y-2] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x+3][y+3] == Seed.EMPTY && board[x-2][y-2] == Seed.CROSS ) || (board[x+3][y+3] == Seed.CROSS && board[x-2][y-2] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(x > 1 && x < 9 && y > 0 && y < 8 && board[x+1][y-1] == Seed.NOUGHT && board[x-1][y+1] == Seed.NOUGHT && board[x-2][y+2] == Seed.NOUGHT){
		            				if(x == 8 && y == 1 && board[x+3][y+3] == Seed.EMPTY){score += QUADRA;}
		            				if(x == 2 && y == 9 && board[x-2][y-2] == Seed.EMPTY){score += QUADRA;}
		            				
		            				if(x > 2 && x < 8 && y > 1 && y < 7){
		            					if(board[x-3][y+3] == Seed.EMPTY && board[x+2][y-2] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x-3][y+3] == Seed.EMPTY && board[x+2][y-2] == Seed.CROSS ) || (board[x-3][y+3] == Seed.CROSS && board[x+2][y-2] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(x < 9 && x > 1 && y < 9 && y > 1 && board[x+1][y+1] == Seed.NOUGHT && board[x-1][y-1] == Seed.NOUGHT && board[x-2][y-2] == Seed.NOUGHT){
		            				if(x == 8 && y == 8 && board[x-3][y-3] == Seed.EMPTY){score += QUADRA;}
		            				if(x == 2 && y == 2 && board[x+2][y+2] == Seed.EMPTY){score += QUADRA;}
		            				
		            				if(x < 8 && x > 2 && y < 8 && y > 2){
		            					if(board[x-3][y-3] == Seed.EMPTY && board[x+2][y+2] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x-3][y-3] == Seed.EMPTY && board[x+2][y+2] == Seed.CROSS ) || (board[x-3][y-3] == Seed.CROSS && board[x+2][y+2] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			if(x > 0 && x < 8 && y > 1 && y < 9 && board[x-1][y+1] == Seed.NOUGHT && board[x+1][y-1] == Seed.NOUGHT && board[x+2][y-2] == Seed.NOUGHT){
		            				if(x == 1 && y == 8 && board[x+3][y-3] == Seed.EMPTY){score += QUADRA;}
		            				if(x == 7 && y == 2 && board[x-2][y+2] == Seed.EMPTY){score += QUADRA;}
		            				
		            				if(x > 1 && x < 7 && y < 8 && y > 2){
		            					if(board[x+3][y-3] == Seed.EMPTY && board[x-2][y+2] == Seed.EMPTY){score += UNPROT_QUADRA;}
		            					if((board[x+3][y-3] == Seed.EMPTY && board[x-2][y+2] == Seed.CROSS ) || (board[x+3][y-3] == Seed.CROSS && board[x-2][y+2] == Seed.EMPTY )){score += QUADRA;}
		            				}
		            			}
		            			
		            		//Making a penta if possible
		            			// considering to be the most side cell
		            			if(x > 3){
		            				if(board[x-4][y] == Seed.NOUGHT && board[x-3][y] == Seed.NOUGHT && board[x-2][y] == Seed.NOUGHT && board[x-1][y] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 3 && y < 6){
		            				if(board[x-4][y+4] == Seed.NOUGHT && board[x-3][y+3] == Seed.NOUGHT && board[x-2][y+2] == Seed.NOUGHT && board[x-1][y+1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(y < 6){
		            				if(board[x][y+4] == Seed.NOUGHT && board[x][y+3] == Seed.NOUGHT && board[x][y+2] == Seed.NOUGHT && board[x][y+1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x < 6 && y < 6){
		            				if(board[x+4][y+4] == Seed.NOUGHT && board[x+3][y+3] == Seed.NOUGHT && board[x+2][y+2] == Seed.NOUGHT && board[x+1][y+1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x < 6){
		            				if(board[x+4][y] == Seed.NOUGHT && board[x+3][y] == Seed.NOUGHT && board[x+2][y] == Seed.NOUGHT && board[x+1][y] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x < 6 && y > 3){
		            				if(board[x+4][y-4] == Seed.NOUGHT && board[x+3][y-3] == Seed.NOUGHT && board[x+2][y-2] == Seed.NOUGHT && board[x+1][y-1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(y > 3){
		            				if(board[x][y-4] == Seed.NOUGHT && board[x][y-3] == Seed.NOUGHT && board[x][y-2] == Seed.NOUGHT && board[x][y-1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 3 && y > 3){
		            				if(board[x-4][y-4] == Seed.NOUGHT && board[x-3][y-3] == Seed.NOUGHT && board[x-2][y-2] == Seed.NOUGHT && board[x-1][y-1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			
		            			//considered to be the second/fourth cell
		            			if(x > 2 && x < 9){
		            				if(board[x-3][y] == Seed.NOUGHT && board[x-2][y] == Seed.NOUGHT && board[x-1][y] == Seed.NOUGHT && board[x+1][y] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(y > 2 && y < 9){
		            				if(board[x][y-3] == Seed.NOUGHT && board[x][y-2] == Seed.NOUGHT && board[x][y-1] == Seed.NOUGHT && board[x][y+1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 2 && x < 9 && y > 0 && y < 7){
		            				if(board[x-3][y+3] == Seed.NOUGHT && board[x-2][y+2] == Seed.NOUGHT && board[x-1][y+1] == Seed.NOUGHT && board[x+1][y-1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 0 && x < 7 && y > 0 && y < 7){
		            				if(board[x+3][y+3] == Seed.NOUGHT && board[x+2][y+2] == Seed.NOUGHT && board[x+1][y+1] == Seed.NOUGHT && board[x-1][y-1] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 0 && x < 7){
		            				if(board[x-1][y] == Seed.NOUGHT && board[x+1][y] == Seed.NOUGHT && board[x+2][y] == Seed.NOUGHT && board[x+3][y] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(y > 0 && y < 7){
		            				if(board[x][y-1] == Seed.NOUGHT && board[x][y+1] == Seed.NOUGHT && board[x][y+2] == Seed.NOUGHT && board[x][y+3] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 0 && x < 7 && y > 2 && y < 9){
		            				if(board[x-1][y+1] == Seed.NOUGHT && board[x+1][y-1] == Seed.NOUGHT && board[x+2][y-2] == Seed.NOUGHT && board[x+3][y-3] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 2 && x < 9 && y > 2 && y < 9){
		            				if(board[x+1][y+1] == Seed.NOUGHT && board[x-1][y-1] == Seed.NOUGHT && board[x-2][y-2] == Seed.NOUGHT && board[x-3][y-3] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			
		            			// considered to be the very middle cell
		            			if(x > 1 && x < 8){
		            				if(board[x-2][y] == Seed.NOUGHT && board[x-1][y] == Seed.NOUGHT && board[x+1][y] == Seed.NOUGHT && board[x+2][y] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(y > 1 && y < 8){
		            				if(board[x][y-2] == Seed.NOUGHT && board[x][y-1] == Seed.NOUGHT && board[x][y+1] == Seed.NOUGHT && board[x][y+2] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 1 && x < 8 && y > 1 && y < 8){
		            				if(board[x-2][y+2] == Seed.NOUGHT && board[x-1][y+1] == Seed.NOUGHT && board[x+1][y-1] == Seed.NOUGHT && board[x+2][y-2] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			if(x > 1 && x < 8 && y > 1 && y < 8){
		            				if(board[x+2][y+2] == Seed.NOUGHT && board[x+1][y+1] == Seed.NOUGHT && board[x-1][y-1] == Seed.NOUGHT && board[x-2][y-2] == Seed.NOUGHT){score += PENTA;}
		            			}
		            			
		            	// Computer's defense	
		            			
		            		// defend opponent's tripple without borders
		            			//side block
		            			// horizontal
		            			if(y > 3){
		            				if(board[x][y-1] == Seed.CROSS && board[x][y-2] == Seed.CROSS && board[x][y-3] == Seed.CROSS && board[x][y-4] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(y < 6){
		            				if(board[x][y+1] == Seed.CROSS && board[x][y+2] == Seed.CROSS && board[x][y+3] == Seed.CROSS && board[x][y+4] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			// vertical
		            			if(x > 3){
		            				if(board[x-1][y] == Seed.CROSS && board[x-2][y] == Seed.CROSS && board[x-3][y] == Seed.CROSS && board[x-4][y] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(x < 6){
		            				if(board[x+1][y] == Seed.CROSS && board[x+2][y] == Seed.CROSS && board[x+3][y] == Seed.CROSS && board[x+4][y] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			// diagonal
		            			if(x > 3 && y > 3){
		            				if(board[x-1][y-1] == Seed.CROSS && board[x-2][y-2] == Seed.CROSS && board[x-3][y-3] == Seed.CROSS && board[x-4][y-4] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(x < 6 && y < 6){
		            				if(board[x+1][y+1] == Seed.CROSS && board[x+2][y+2] == Seed.CROSS && board[x+3][y+3] == Seed.CROSS && board[x+4][y+4] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(x > 3 && y < 6){
		            				if(board[x-1][y+1] == Seed.CROSS && board[x-2][y+2] == Seed.CROSS && board[x-3][y+3] == Seed.CROSS && board[x-4][y+4] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(x < 6 && y > 3){
		            				if(board[x+1][y-1] == Seed.CROSS && board[x+2][y-2] == Seed.CROSS && board[x+3][y-3] == Seed.CROSS && board[x+4][y-4] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			
		            			//split triples
		            			// horizontal
		            			if(y > 1 && y < 7){
		            				if(board[x][y-1] == Seed.CROSS && board[x][y+1] == Seed.CROSS && board[x][y+2] == Seed.CROSS && board[x][y-2] != Seed.NOUGHT && board[x][y+3] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(y > 2 && y < 8){
		            				if(board[x][y-2] == Seed.CROSS && board[x][y-1] == Seed.CROSS && board[x][y+1] == Seed.CROSS && board[x][y-3] != Seed.NOUGHT && board[x][y+2] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			// vertical
		            			if(x > 1 && x < 7){
		            				if(board[x-1][y] == Seed.CROSS && board[x+1][y] == Seed.CROSS && board[x+2][y] == Seed.CROSS && board[x-2][y] != Seed.NOUGHT && board[x+3][y] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(x > 2 && x < 8){
		            				if(board[x-2][y] == Seed.CROSS && board[x-1][y] == Seed.CROSS && board[x+1][y] == Seed.CROSS && board[x-3][y] != Seed.NOUGHT && board[x+2][y] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			// diagonal
		            			if(x > 1 && x < 7 && y > 1 && y < 7){
		            				if(board[x-1][y-1] == Seed.CROSS && board[x+1][y+1] == Seed.CROSS && board[x+2][y+2] == Seed.CROSS && board[x-2][y-2] != Seed.NOUGHT && board[x+3][y+3] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(x > 1 && x < 7 && y > 2 && y < 8){
		            				if(board[x-1][y+1] == Seed.CROSS && board[x+1][y-1] == Seed.CROSS && board[x+2][y-2] == Seed.CROSS && board[x-2][y+2] != Seed.NOUGHT && board[x+3][y-3] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(x > 2 && x < 8 && y > 2 && y < 8){
		            				if(board[x+1][y+1] == Seed.CROSS && board[x-1][y-1] == Seed.CROSS && board[x-2][y-2] == Seed.CROSS && board[x+2][y+2] != Seed.NOUGHT && board[x-3][y-3] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			if(x > 2 && x < 8 && y > 1 && y < 7){
		            				if(board[x+1][y-1] == Seed.CROSS && board[x-1][y+1] == Seed.CROSS && board[x-2][y+2] == Seed.CROSS && board[x+2][y-2] != Seed.NOUGHT && board[x-3][y+3] != Seed.NOUGHT){score += TRIPLE_BLOCK;}
		            			}
		            			
		            		// defend opponent's quadra without borders
		            			//side block
		            			// horizontal
		            			if(y > 4){
		            				if(board[x][y-1] == Seed.CROSS && board[x][y-2] == Seed.CROSS && board[x][y-3] == Seed.CROSS && board[x][y-4] == Seed.CROSS/* && board[x][y-5] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(y < 5){
		            				if(board[x][y+1] == Seed.CROSS && board[x][y+2] == Seed.CROSS && board[x][y+3] == Seed.CROSS && board[x][y+4] == Seed.CROSS/* && board[x][y+5] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			// vertical
		            			if(x > 4){
		            				if(board[x-1][y] == Seed.CROSS && board[x-2][y] == Seed.CROSS && board[x-3][y] == Seed.CROSS && board[x-4][y] == Seed.CROSS/* && board[x-5][y] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(x < 5){
		            				if(board[x+1][y] == Seed.CROSS && board[x+2][y] == Seed.CROSS && board[x+3][y] == Seed.CROSS && board[x+4][y] == Seed.CROSS/* && board[x+5][y] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			// diagonal
		            			if(x > 4 && y > 4){
		            				if(board[x-1][y-1] == Seed.CROSS && board[x-2][y-2] == Seed.CROSS && board[x-3][y-3] == Seed.CROSS && board[x-4][y-4] == Seed.CROSS/* && board[x-5][y-5] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(x < 5 && y < 5){
		            				if(board[x+1][y+1] == Seed.CROSS && board[x+2][y+2] == Seed.CROSS && board[x+3][y+3] == Seed.CROSS && board[x+4][y+4] == Seed.CROSS/* && board[x+5][y+5] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(x > 4 && y < 5){
		            				if(board[x-1][y+1] == Seed.CROSS && board[x-2][y+2] == Seed.CROSS && board[x-3][y+3] == Seed.CROSS && board[x-4][y+4] == Seed.CROSS/* && board[x-5][y+5] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(x < 5 && y > 4){
		            				if(board[x+1][y-1] == Seed.CROSS && board[x+2][y-2] == Seed.CROSS && board[x+3][y-3] == Seed.CROSS && board[x+4][y-4] == Seed.CROSS/* && board[x+5][y-5] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			
		            			//second/fourth place block
		            			// horizontal
		            			if(y > 0 && y < 7){
		            				if(board[x][y-1] == Seed.CROSS && board[x][y+1] == Seed.CROSS && board[x][y+2] == Seed.CROSS && board[x][y+3] == Seed.CROSS/* && board[x][y-2] != Seed.NOUGHT && board[x][y+4] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(y > 2 && y < 9){
		            				if(board[x][y-3] == Seed.CROSS && board[x][y-2] == Seed.CROSS && board[x][y-1] == Seed.CROSS && board[x][y+1] == Seed.CROSS/* && board[x][y-4] != Seed.NOUGHT && board[x][y+2] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			// vertical
		            			if(x > 0 && x < 7){
		            				if(board[x-1][y] == Seed.CROSS && board[x+1][y] == Seed.CROSS && board[x+2][y] == Seed.CROSS && board[x+3][y] == Seed.CROSS/* && board[x-2][y] != Seed.NOUGHT && board[x+4][y] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(x > 2 && x < 9){
		            				if(board[x-3][y] == Seed.CROSS && board[x-2][y] == Seed.CROSS && board[x-1][y] == Seed.CROSS && board[x+1][y] == Seed.CROSS/* && board[x-4][y] != Seed.NOUGHT && board[x+2][y] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			// diagonal
		            			if(x > 0 && x < 7 && y > 0 && y < 7){
		            				if(board[x-1][y-1] == Seed.CROSS && board[x+1][y+1] == Seed.CROSS && board[x+2][y+2] == Seed.CROSS && board[x+3][y+3] == Seed.CROSS/* && board[x-2][y-2] != Seed.NOUGHT && board[x+3][y+3] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(x > 0 && x < 7 && y > 2 && y < 9){
		            				if(board[x-1][y+1] == Seed.CROSS && board[x+1][y-1] == Seed.CROSS && board[x+2][y-2] == Seed.CROSS && board[x+3][y-3] == Seed.CROSS/* && board[x-2][y+2] != Seed.NOUGHT && board[x+3][y-3] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(x > 2 && x < 9 && y > 2 && y < 9){
		            				if(board[x+1][y+1] == Seed.CROSS && board[x-1][y-1] == Seed.CROSS && board[x-2][y-2] == Seed.CROSS && board[x-3][y-3] == Seed.CROSS/* && board[x+2][y+2] != Seed.NOUGHT && board[x-3][y-3] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			if(x > 2 && x < 9 && y > 0 && y < 7){
		            				if(board[x+1][y-1] == Seed.CROSS && board[x-1][y+1] == Seed.CROSS && board[x-2][y+2] == Seed.CROSS && board[x-3][y+3] == Seed.CROSS/* && board[x+2][y-2] != Seed.NOUGHT && board[x-3][y+3] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			
		            			//middle block
		            			// horizontal
		            			if(x > 1 && x < 8){
		            				if(board[x-1][y] == Seed.CROSS && board[x-2][y] == Seed.CROSS && board[x+1][y] == Seed.CROSS && board[x+2][y] == Seed.CROSS/*  && board[x-3][y] != Seed.NOUGHT && board[x+3][y] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}
		            			
		            			// vertical
		            			if(y > 1 && y < 8){
		            				if(board[x][y-1] == Seed.CROSS && board[x][y-2] == Seed.CROSS && board[x][y+1] == Seed.CROSS && board[x][y+2] == Seed.CROSS/*  && board[x][y-3] != Seed.NOUGHT && board[x][y+3] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            			}		            			
		            			
		            			// diagonal
		            			if(x > 1 && x < 8 && y > 1 && y < 8){
		            				if(board[x-1][y-1] == Seed.CROSS && board[x-2][y-2] == Seed.CROSS && board[x+1][y+1] == Seed.CROSS && board[x+2][y+2] == Seed.CROSS/*  && board[x-3][y-3] != Seed.NOUGHT && board[x+3][y+3] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
		            				if(board[x-1][y+1] == Seed.CROSS && board[x-2][y+2] == Seed.CROSS && board[x+1][y-1] == Seed.CROSS && board[x+2][y-2] == Seed.CROSS/*  && board[x-3][y+3] != Seed.NOUGHT && board[x+3][y-3] != Seed.NOUGHT*/){score += QUADRA_BLOCK;}
			            		}
		            			
	            				if(board[x][y] == Seed.EMPTY){
		            				if(score > maxScore){
		            					maxScore = score;
		            					rowPos = x;
		            					colPos = y;
		            				}
	            				}
	            				score = 0;
	            				
	            			}
	            		}
	            		board[rowPos][colPos] = Seed.NOUGHT;
        				updateGame(currentPlayer); // update state
        				System.out.println(rowPos + " " + colPos + " " + maxScore);
        				currentPlayer = Seed.CROSS;
        				maxScore = 0;
	            		
	            	}
	             } else {       // game over
	                initGame(); // restart the game
	                hasWon = false;
	             }
	             // Refresh the drawing canvas
	             repaint();  // Call-back paintComponent().
         }        
       });
 
      // Setup the status bar (JLabel) to display status message
      statusBar = new JLabel("  ");
      statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
      statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
      
 
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      pack();  // pack all the components in this JFrame
      setTitle("Tic Tac Toe");
      setVisible(true);  // show this JFrame
 
      board = new Seed[ROWS][COLS]; // allocate array
      initGame(); // initialize the game board contents and game variables
   }
 
   /** Initialize the game-board contents and the status */
   public void initGame() {
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
            board[row][col] = Seed.EMPTY; // all cells empty
         }
      }
      currentState = GameState.PLAYING; // ready to play
      currentPlayer = Seed.CROSS;       // cross plays first
   }
 
   /** Update the currentState after the player with "theSeed" has placed on
       (rowSelected, colSelected). */
   public void updateGame(Seed theSeed) {
	      for(int x = 0; x < ROWS; x++){
	   	    for(int y = 0; y < COLS; y++){
	   	    	
	   		  // Checking horizontal direction 
	   	      if(y > 3 && y < 10){
	   			  if(board[x][y] == currentPlayer && board[x][y-1] == currentPlayer && board[x][y-2] == currentPlayer && board[x][y-3] == currentPlayer && board[x][y-4] == currentPlayer){hasWon = true;}
	   		  }
	   		  if(y >= 0 && y < 6){
	   			  if(board[x][y] == currentPlayer && board[x][y+1] == currentPlayer && board[x][y+2] == currentPlayer && board[x][y+3] == currentPlayer && board[x][y+4] == currentPlayer){hasWon = true;}
	   		  }
	   		  
	   		  //Checking vertical direction
	   		  if(x > 3 && x < 10){
	   			  if(board[x][y] == currentPlayer && board[x-1][y] == currentPlayer && board[x-2][y] == currentPlayer && board[x-3][y] == currentPlayer && board[x-4][y] == currentPlayer){hasWon = true;}
	   		  }
	   		  if(x >= 0 && x < 6){
	   			  if(board[x][y] == currentPlayer && board[x+1][y] == currentPlayer && board[x+2][y] == currentPlayer && board[x+3][y] == currentPlayer && board[x+4][y] == currentPlayer){hasWon = true;}
	   		  }
	   		  
	   		  //Checking both diagonal directions
	   		  if(x >= 0 && x < 6 && y >= 0 && y < 6){
	   			  if(board[x][y] == currentPlayer && board[x+1][y+1] == currentPlayer && board[x+2][y+2] == currentPlayer && board[x+3][y+3] == currentPlayer && board[x+4][y+4] == currentPlayer){hasWon = true;}
	   		  }
	   		  if(x >= 0 && x < 6 && y > 3 && y < 10){
	   			  if(board[x][y] == currentPlayer && board[x+1][y-1] == currentPlayer && board[x+2][y-2] == currentPlayer && board[x+3][y-3] == currentPlayer && board[x+4][y-4] == currentPlayer){hasWon = true;}
	   		  }
	   		  if(x > 3 && x < 10 && y >= 0 && y < 6){
	   			  if(board[x][y] == currentPlayer && board[x-1][y+1] == currentPlayer && board[x-2][y+2] == currentPlayer && board[x-3][y+3] == currentPlayer && board[x-4][y+4] == currentPlayer){hasWon = true;}
	   		  }
	   		  if(x > 3 && x < 10 && y > 3 && y < 10){
	   			  if(board[x][y] == currentPlayer && board[x-1][y-1] == currentPlayer && board[x-2][y-2] == currentPlayer && board[x-3][y-3] == currentPlayer && board[x-4][y-4] == currentPlayer){hasWon = true;}
	   		  }
	   	    }
	      }
	   if (hasWon) {
		   if(currentPlayer == Seed.CROSS){// check for win
			   currentState = GameState.CROSS_WON;
		   }
		   if(currentPlayer == Seed.NOUGHT){// check for win
			   currentState = GameState.NOUGHT_WON;
		   }
	   }
       if (isDraw()) {  // check for draw
         currentState = GameState.DRAW;
      }
      // Otherwise, no change to current state (still GameState.PLAYING).
   }
 
   /** Return true if it is a draw (i.e., no more empty cell) */
   public boolean isDraw() {
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
            if (board[row][col] == Seed.EMPTY) {
               return false; // an empty cell found, not draw, exit
            }
         }
      }
      return true;  // no more empty cell, it's a draw
   }
 
   /** Return true if the player with "theSeed" has won after placing at
       (rowSelected, colSelected) */
     
   /**
    *  Inner class DrawCanvas (extends JPanel) used for custom graphics drawing.
    */
   class DrawCanvas extends JPanel {
      @Override
      public void paintComponent(Graphics g) {  // invoke via repaint()
         super.paintComponent(g);    // fill background
         setBackground(Color.WHITE); // set its background color
 
         // Draw the grid-lines
         g.setColor(Color.LIGHT_GRAY);
         for (int row = 1; row < ROWS; row++) {
            g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
                  CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
         }
         for (int col = 1; col < COLS; col++) {
            g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
                  GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
         }
 
         // Draw the Seeds of all the cells if they are not empty
         // Use Graphics2D which allows us to set the pen's stroke
         Graphics2D g2d = (Graphics2D)g;
         g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND,
               BasicStroke.JOIN_ROUND));  // Graphics2D only
         for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
               int x1 = col * CELL_SIZE + CELL_PADDING;
               int y1 = row * CELL_SIZE + CELL_PADDING;
               if (board[row][col] == Seed.CROSS) {
                  g2d.setColor(Color.RED);
                  int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
                  int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
                  g2d.drawLine(x1, y1, x2, y2);
                  g2d.drawLine(x2, y1, x1, y2);
               } else if (board[row][col] == Seed.NOUGHT) {
                  g2d.setColor(Color.BLUE);
                  g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
               }
            }
         }
 
         // Print status-bar message
         if (currentState == GameState.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            if (currentPlayer == Seed.CROSS) {
               statusBar.setText("X's Turn");
            } else {
               statusBar.setText("O's Turn");
            }
         } else if (currentState == GameState.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again.");
         } else if (currentState == GameState.CROSS_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'X' Won! Click to play again.");
         } else if (currentState == GameState.NOUGHT_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'O' Won! Click to play again.");
         }
      }
   }
 
   /** The entry main() method */
   public static void main(String[] args) {
      // Run GUI codes in the Event-Dispatching thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new TTTGraphics2P(); // Let the constructor do the job
         }
      });
   }
}
