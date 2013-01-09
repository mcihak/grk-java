import java.awt.*;
import java.io.Serializable;

public abstract class Utvar implements Serializable {
  private Color barva;
  protected int x;
  protected int y;
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
   
  public Utvar (Color barva, int _x, int _y) {
    this.barva = barva;
    this.x = _x;
    this.y = _y;
  }
   
  public void setBarva(Color barva) {
    this.barva = barva; 
  }  
  
  public Color getBarva() {
    return this.barva;
  }
                     
  abstract public void nakresli(Graphics g);
}