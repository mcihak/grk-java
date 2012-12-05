public class ResizableCircle extends Circle implements Resizable {
	public ResizableCircle(double _radius) {
		super(_radius);
	}

	@Override
	public double resize(int percent) {
		radius *= (percent * 0.01);
		return radius;
	}
}