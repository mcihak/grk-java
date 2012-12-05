public class MovableCircle implements Movable {

	private int radius;
	private MovablePoint center;
	
	public MovableCircle(int _x, int _y, int _xSpeed, int _ySpeed, int _radius) {
		this.center = new MovablePoint(_x, _y, _xSpeed, _ySpeed);
		this.radius = _radius;
	}
	
	@Override
	public void moveDown() {
		center.moveDown();
	}

	@Override
	public void moveLeft() {
		center.moveLeft();
	}

	@Override
	public void moveRight() {
		center.moveRight();
	}

	@Override
	public void moveUp() {
		center.moveUp();
	}
	
	public String toString() {
		return "-----------Circle-----------\n"
					+ "[Position] x: " + center.x + " y: " + center.y + "\n"
					+ "[Speed] x: " + center.xSpeed + " y: " + center.ySpeed
					+ "[Radius]: " + this.radius;
	}
}