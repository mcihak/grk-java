import java.awt.*;

public class Bod extends Utvar {

private int x;
private int y;


  public Bod(int x, int y, Color barva) {
    super(barva);
    this.x = x;
    this.y = y;
  }
  
  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }

  @Override
  public void nakresli(Graphics g) {
    g.setColor(this.getBarva());
    g.fillOval(this.x, this.y, 10, 10);
  }
}