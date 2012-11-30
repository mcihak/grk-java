public abstract class Shape {
  protected String color;
  protected boolean filled;
  
  public Shape() {
    this.color = "None";
    this.filled = false; 
  }
  public Shape(String _color, boolean _filled) {
    this.color = _color;
    this.filled = _filled;
  }
  public String getColor() {
    return this.color;
  }
  public void setColor(String _color) {
    this.color = _color;
  }
  public boolean isFilled() {
    return this.filled;
  }
  public void setFilled(boolean _filled) {
    this.filled = _filled;
  }
  
  public abstract double getArea();
  public abstract double getPerimeter();
  public abstract String toString();
}