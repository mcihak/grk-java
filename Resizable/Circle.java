public class Circle implements GeometricObject {
	protected double radius;
	private static final double PI = 3.14;
	
	public Circle(double _radius) {
		this.radius = _radius;
	}

	@Override
	public double getArea() {
		return PI * radius * radius;
	}

	@Override
	public double getPerimeter() {
		return PI * (2 * radius);
	}
}