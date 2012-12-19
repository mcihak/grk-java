import java.awt.*;

public abstract class Utvar {
  private Color barva;
  protected int x;
  protected int y;
   
  public Utvar (Color barva, int x, int y) {
    this.barva = barva;
    this.x = x;
    this.y = y;  
  }
   
  public void setBarva(Color barva) {
    this.barva = barva; 
  }  
  
  public Color getBarva() {
    return this.barva;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  } 
                     
  abstract public void nakresli(Graphics g);
}