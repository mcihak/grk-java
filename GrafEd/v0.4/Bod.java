import java.awt.*;

public class Bod extends Utvar {
  public Bod(int x, int y, Color barva) {
    super(barva, x, y);
  }
  
  @Override
  public void nakresli(Graphics g) {  
    g.setColor(this.getBarva());
    g.fillOval(x, y, 10, 10);
  }  
}