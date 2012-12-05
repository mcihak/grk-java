public class MovablePoint implements Movable {
	
	int x;
	int y;
	int xSpeed;
	int ySpeed;
	
	public MovablePoint(int _x, int _y, int _xSpeed, int _ySpeed) {
		this.x = _x;
		this.y = _y;
		this.xSpeed = _xSpeed;
		this.ySpeed = _ySpeed;
	}

	@Override
	public void moveDown() {
		this.y += ySpeed;
	}

	@Override
	public void moveLeft() {
		this.x -= xSpeed;
	}

	@Override
	public void moveRight() {
		this.x += xSpeed;
	}

	@Override
	public void moveUp() {
		this.y -= ySpeed;
	}
	
	public String toString() {
		return "-----------Point-----------\n"
					+ "[Position] x: " + x + " y: " + y + "\n"
					+ "[Speed] x: " + xSpeed + " y: " + ySpeed;
	}
}