public class Circle extends Shape {
  private double radius;
  private double PI = 3.14;
  
  public Circle() {
    super(); 
  }
  public Circle(double _radius) {
    super();
    this.radius = _radius;
  }
  public Circle(double _radius, String _color, boolean _filled) {
    super(_color, _filled);
    this.radius = _radius;
  }
  
  public double getRadius() {
    return this.radius;
  }
  public void setRadius(double _radius) {
    this.radius = _radius;
  }
  
  public double getArea() {
    return PI * radius * radius;
  }
  public double getPerimeter() {
    return PI * radius * 2;
  }
  public String toString() {
      String filledString = null;
      if (filled)
        filledString = "Filled";
      else
        filledString = "Non-filled";  
      return filledString + " circle colored " + color + " of radius " + String.valueOf(radius);
  }
}