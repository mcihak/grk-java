import java.awt.Graphics;

/**
 * The Board class models the game-board.
 */
public class Board {  // save as Board.java
   // Named-constants for the dimensions
   public static final int ROWS = 10;
   public static final int COLS = 10;
   public static final int WIN = 5;
   
   public static final int CELL_WIDTH = 40;
   public static final int CELL_HEIGHT = 40;
 
   // package access
   Cell[][] cells;  // a board composes of ROWS-by-COLS Cell instances
   int currentRow, currentCol;  // the current seed's row and column
 
   /** Constructor to initialize the game board */
   public Board() {
      cells = new Cell[ROWS][COLS];  // allocate the array
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
            cells[row][col] = new Cell(row, col); // allocate element of the array
         }
      }
   }
 
   /** Initialize (or re-initialize) the contents of the game board */
   public void init() {
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
            cells[row][col].clear();  // clear the cell content
         }
      }
   }
 
   /** Return true if it is a draw (i.e., no more EMPTY cell) */
   public boolean isDraw() {
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
            if (cells[row][col].content == Seed.EMPTY) {
               return false; // an empty seed found, not a draw, exit
            }
         }
      }
      return true; // no empty cell, it's a draw
   }
 
   /** Return true if the player with "theSeed" has won after placing at
       (currentRow, currentCol) */
   public boolean hasWon(Seed theSeed) {
	   // Check for 4-in-a-line on the rowSelected
	   int count = 0;
	   for (int col = 0; col < COLS; col++) {
	      if (cells[currentRow][col].content == theSeed) {
	         count++;
	         if (count == WIN) return true;  // found
	      } else {
	         count = 0; // reset and count again if not consecutive
	      }
	   }
	   
	   count = 0;
	   for (int row = 0; row < ROWS; row++) {
	      if (cells[row][currentCol].content == theSeed) {
	         count++;
	         if (count == WIN) return true;  // found
	      } else {
	         count = 0; // reset and count again if not consecutive
	      }
	   }
	   
	   // DIAGONAL
	   count = 0;
	   for (int row = currentRow, col = currentCol; row < ROWS && col < COLS; row++, col++) {
	      if (cells[row][col].content == theSeed) {
	         count++;
	         if (count == WIN) return true;  // found
	      } else {
	         count = 0; // reset and count again if not consecutive
	      }
	   }
	   
	   count = 0;
	   for (int row = currentRow, col = currentCol; row > 0 && col > 0; row--, col--) {
	      if (cells[row][col].content == theSeed) {
	         count++;
	         if (count == WIN) return true;  // found
	      } else {
	         count = 0; // reset and count again if not consecutive
	      }
	   }
	   
	   // REVERSE DIAGONAL
	   count = 0;
	   for (int row = currentRow, col = currentCol; row > 0 && col < COLS; row--, col++) {
	      if (cells[row][col].content == theSeed) {
	         count++;
	         if (count == WIN) return true;  // found
	      } else {
	         count = 0; // reset and count again if not consecutive
	      }
	   }
	   
	   count = 0;
	   for (int row = currentRow, col = currentCol; row < ROWS && col > 0; row++, col--) {
	      if (cells[row][col].content == theSeed) {
	         count++;
	         if (count == WIN) return true;  // found
	      } else {
	         count = 0; // reset and count again if not consecutive
	      }
	   }
	   return false;
   }
 
   /** Paint itself */
   public void paint(Graphics g) {
      for (int row = 0; row < ROWS; row++) {
         for (int col = 0; col < COLS; col++) {
            cells[row][col].paint(g);   // each cell paints itself
         }
      }
   }
}