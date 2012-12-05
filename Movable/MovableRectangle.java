public class MovableRectangle implements Movable {
	
	private MovablePoint topLeft;
	private MovablePoint bottomRight;
	
	public MovableRectangle(int _x1, int _y1, int _x2, int _y2, 
			int _xSpeed, int _ySpeed) {
		this.topLeft = new MovablePoint(_x1, _y1, _xSpeed, _ySpeed);
		this.bottomRight = new MovablePoint(_x2, _y2, _xSpeed, _ySpeed);
	}

	@Override
	public void moveDown() {
		topLeft.moveDown();
		bottomRight.moveDown();
	}

	@Override
	public void moveLeft() {
		topLeft.moveLeft();
		bottomRight.moveLeft();
	}

	@Override
	public void moveRight() {
		topLeft.moveRight();
		bottomRight.moveRight();
	}

	@Override
	public void moveUp() {
		topLeft.moveUp();
		bottomRight.moveUp();
	}
	
	public String toString() {
		return "-----------Rectangle-----------\n"
					+ "[Position - top left] x: " + topLeft.x + " y: " + topLeft.y + "\n"
					+ "[Position - bottom right] x: " + bottomRight.x + " y: " + bottomRight.y + "\n"
					+ "[Speed] x: " + topLeft.xSpeed + " y: " + topLeft.ySpeed + "\n";
	}
}