import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMain extends Frame implements ActionListener {
   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   
   private Board board;            // the game board
   private GameState currentState; // the current state of the game (of enum GameState)
   private Seed currentPlayer;     // the current player (of enum Seed)
   private String currentText = "Hello";
 
   public static final int platnoSirka = 800;
   public static final int platnoVyska = 600;
   
   private Platno platno = new Platno();
   public Platno getPlatno() {
 	  return this.platno;
   }
   private Panel panel = new Panel();
 
   /** Constructor to setup the game */
   public GameMain() {
	   
	  super.setTitle(getClass().getName());
      this.setSize(platnoSirka, platnoVyska);
      this.add(platno, BorderLayout.CENTER);    
      this.add(panel, BorderLayout.SOUTH);
      
      Button newGameBtn = new Button("New Game");
      panel.add(newGameBtn);
      newGameBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          initGame();
          platno.repaint();
        }  
      });
      
      platno.addMouseListener(new MouseListener() {
  		public void mouseClicked(MouseEvent e) {}
  		public void mouseEntered(MouseEvent e) {}
  		public void mouseExited(MouseEvent e) {}
  		public void mousePressed(MouseEvent e) {}
  		public void mouseReleased(MouseEvent e) {
  			if (currentState == GameState.PLAYING) {
  				nextMove(e.getX(), e.getY());
  				platno.repaint();
  			}
  		}
      });
      
      this.addWindowListener(new WindowAdapter() {
    	public void windowClosing(WindowEvent e) {
    	  System.exit(1);
      	}
      });
	   
      board = new Board();  // allocate game-board
      // Initialize the game-board and current status
      initGame();
   }
 
   /** Initialize the game-board contents and the current states */
   public void initGame() {
      board.init();  // clear the board contents
      currentPlayer = Seed.CROSS;       // CROSS plays first
      currentState = GameState.PLAYING; // ready to play
      currentText = "Player 'X' is playing";
   }
   
   public void nextMove(int x, int y) {
	   if (playerMove(currentPlayer, x, y)) {
		 updateGame(currentPlayer); // update currentState
	   	 currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
	     if (currentState == GameState.CROSS_WON) {
	    	 currentText = "'X' won! Bye!";
	     } else if (currentState == GameState.NOUGHT_WON) {
	    	 currentText = "'O' won! Bye!";
	     } else if (currentState == GameState.DRAW) {
	    	 currentText = "It's Draw! Bye!";
	     } else {
	    	 if (currentPlayer == Seed.CROSS)
	    	     currentText = "Player 'X' is playing";
	    	 else
	    	     currentText = "Player 'O' is playing";
	     }
	   }
   }
 
   /** The player with "theSeed" makes one move, with input validation.
       Update Cell's content, Board's currentRow and currentCol. */
   public boolean playerMove(Seed theSeed, int x, int y) {
     int col = x / Board.CELL_WIDTH;
     int row = y / Board.CELL_HEIGHT;
     if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
           && board.cells[row][col].content == Seed.EMPTY) {
        board.cells[row][col].content = theSeed;
        board.currentRow = row;
        board.currentCol = col;
        return true;
     } else {
        return false;
     }
   }
 
   /** Update the currentState after the player with "theSeed" has moved */
   public void updateGame(Seed theSeed) {
      if (board.hasWon(theSeed)) {  // check for win
         currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
      } else if (board.isDraw()) {  // check for draw
         currentState = GameState.DRAW;
      }
   }
   
   class Platno extends Canvas { 
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override 
	   public void paint(Graphics g) {
		   board.paint(g);
		   g.setColor(Color.BLACK);
		   g.setFont(new Font("Arial", Font.PLAIN, 20));
		   g.drawString(currentText, platnoSirka / 2 - 50, platnoVyska - 100);
	   }  
   }  
  
  public static void main(String[] args) {
	   new GameMain().setVisible(true);
  }

	@Override
	public void actionPerformed(ActionEvent arg0) {}
}