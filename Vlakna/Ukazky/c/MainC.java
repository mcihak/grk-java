package c;

/**
 * @author lipovmar
 * Sharing data between threads.
 */
public class MainC {
	public static void main(String[] args) {
		VlaknoShare v1 = new VlaknoShare();
		Thread t1 = new Thread(v1),
			   t2 = new Thread(v1); // Both threads must be over the same object which is Runnable - v1
		
		t2.start();
		t1.start();
	}
}
