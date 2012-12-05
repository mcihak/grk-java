public class TestMovable {
	public static void main(String[] args) {
		Movable m = new MovableRectangle(2, 1, 5, 5, 20, 2);
		System.out.println(m);
		m.moveRight();
		m.moveDown();
		System.out.println(m);
	}
}