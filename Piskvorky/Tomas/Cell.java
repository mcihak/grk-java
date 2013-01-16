import java.awt.Color;
import java.awt.Graphics;

/**
 * The Cell class models each individual cell of the game board.
 */
public class Cell {  // save as Cell.java
   // package access
   Seed content; // content of this cell of type Seed.
                 // take a value of Seed.EMPTY, Seed.CROSS, or Seed.NOUGHT
   int row, col; // row and column of this cell, not used in this program
 
   /** Constructor to initialize this cell */
   public Cell(int row, int col) {
      this.row = row;
      this.col = col;
      clear();  // clear content
   }
 
   /** Clear the cell content to EMPTY */
   public void clear() {
      content = Seed.EMPTY;
   }
 
   /** Paint itself */
   public void paint(Graphics g) {
	  g.setColor(Color.GRAY);
	  g.drawRect(col * Board.CELL_WIDTH, row * Board.CELL_HEIGHT, Board.CELL_WIDTH, Board.CELL_HEIGHT);
      switch (content) {
         case CROSS:
        	 g.setColor(Color.RED);
        	 g.drawLine(col * Board.CELL_WIDTH + Board.CELL_WIDTH / 4, 
        			 row * Board.CELL_HEIGHT + Board.CELL_HEIGHT / 4, 
        			 (int)(col * Board.CELL_WIDTH + Board.CELL_WIDTH / 1.3), 
        			 (int)(row * Board.CELL_HEIGHT + Board.CELL_HEIGHT / 1.3));
        	 g.drawLine(col * Board.CELL_WIDTH + Board.CELL_WIDTH - Board.CELL_WIDTH / 4, 
        			 row * Board.CELL_HEIGHT + Board.CELL_HEIGHT / 4, 
        			 (int)(col * Board.CELL_WIDTH + Board.CELL_WIDTH / 4), 
        			 (int)(row * Board.CELL_HEIGHT + Board.CELL_HEIGHT / 1.3));
        	 break;
         case NOUGHT:
        	 g.setColor(Color.BLUE);
        	 g.drawOval(col * Board.CELL_WIDTH + Board.CELL_WIDTH / 4, 
        			 row * Board.CELL_HEIGHT + Board.CELL_HEIGHT / 4, 
        			 Board.CELL_WIDTH / 2, Board.CELL_HEIGHT / 2); 
        	 break;
         case EMPTY: break;
      }
   }
}