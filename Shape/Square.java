public class Square extends Rectangle {

  public Square() {
    super(); 
  }
  public Square(double _side) {
    super(_side, _side);
  }
  public Square(double _side, String _color, boolean _filled) {
    super(_side, _side, _color, _filled);
  }
  
  public double getSide() {
    return width;
  }
  public void setSide(double _side) {
    width = _side;
    length = _side;
  }
  public void setWidth(double _side) {
    width = _side;
    length = _side;
  }
  public void setLength(double _side) {
    width = _side;
    length = _side;
  }

  public String toString() {
      String filledString = null;
      if (filled)
        filledString = "Filled";
      else
        filledString = "Non-filled";  
      return filledString + " square colored " + color + " of side " + String.valueOf(width);
  }
}