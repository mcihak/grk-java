public class Rectangle extends Shape {
  protected double width;
  protected double length;
  
  public Rectangle() {
    super(); 
  }
  public Rectangle(double _width, double _length) {
    super();
    this.width = _width;
    this.length = _length;
  }
  public Rectangle(double _width, double _length, String _color, boolean _filled) {
    super(_color, _filled);
    this.width = _width;
    this.length = _length;
  }
  
  public double getWidth() {
    return this.width;
  }
  public void setWidth(double _width) {
    this.width = _width;
  }
  public double getLength() {
    return this.length;
  }
  public void setLength(double _length) {
    this.length = _length;
  }
  
  public double getArea() {
    return width * length;
  }
  public double getPerimeter() {
    return (width * 2) + (length * 2);
  }
  public String toString() {
      String filledString = null;
      if (filled)
        filledString = "Filled";
      else
        filledString = "Non-filled";  
      return filledString + " rectangle colored " + color + " of width " + String.valueOf(width)
                          + " and length " + String.valueOf(length);
  }
}