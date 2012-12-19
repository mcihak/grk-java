import java.awt.Color;
import java.awt.Graphics;


public class Circle extends Utvar {

  	
		private Bod stred;
		int R1 , R2;
		

		  public Circle(int SX, int SY, int R1, int R2, Color barva) {
		    super(barva);
		    this.R1 = R1;
		    this.R2 = R2;

		    this.stred = new Bod(SX,SY, barva);
		    }
		  

		  @Override
		  public void nakresli(Graphics g) {
		    g.setColor(this.getBarva());
		    g.drawOval(this.stred.getX(), this.stred.getY(), this.R1, this.R2);
		  }

	}
