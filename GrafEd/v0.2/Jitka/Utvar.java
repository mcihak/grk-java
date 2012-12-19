

import java.awt.*;

public abstract class Utvar {
  private Color barva;
   
  public Utvar (Color barva) {
    this.barva = barva;
  }
   
  public void setBarva(Color barva) {
    this.barva = barva; 
  }  
  
  public Color getBarva() {
    return this.barva;
  }
                     
  abstract public void nakresli(Graphics g);
}
