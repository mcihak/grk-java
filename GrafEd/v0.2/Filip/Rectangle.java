import java.awt.*;

public class Rectangle extends Utvar {
  
	private Bod levyHorni;
	private Bod pravyDolni;
	

	  public Rectangle(int LHX, int LHY, int RDX, int RDY, Color barva) {
	    super(barva);
	    this.levyHorni = new Bod(LHX,LHY, barva);
	    this.pravyDolni = new Bod(RDX, RDY, barva);
	  }
	  

	  @Override
	  public void nakresli(Graphics g) {
	    g.setColor(this.getBarva());
	    g.drawRect(this.levyHorni.getX(), this.levyHorni.getY(),this.pravyDolni.getX() - this.levyHorni.getX(),this.pravyDolni.getY()- this.levyHorni.getY());
	  }

}
