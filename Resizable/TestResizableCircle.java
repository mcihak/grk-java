public class TestResizableCircle {
	public static void main(String[] args) {
		ResizableCircle c = new ResizableCircle(4);
		printCircle(c);
		c.resize(50);
		printCircle(c);
	}
	
	public static void printCircle(ResizableCircle c) {
		System.out.println("Area: " + c.getArea() + "\nPerimeter: " + c.getPerimeter() + "\n");
	}
}