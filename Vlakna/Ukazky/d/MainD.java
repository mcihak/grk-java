package d;

/**
 * @author lipovmar
 * Example 1
 * -> Diligent uncle and lazy nephew
 * Thread-nephew is blocked (messing around),
 * until the thread-uncle finishes a work (make one hundred million) - then dies. 
 * Nephew can find his uncle state by method IsAlive(). 
 * If he is impatient, he can call the method join(timeout) - but gets only part of the money.
 */
public class MainD { // MainD represents nephew
	static Uncle u = new Uncle(); // create uncle and call constructor
	
	public static void main(String[] args) throws InterruptedException {
		u.join();	// blocked and waiting when uncle die
		// u.join(100); wait only 100 millisecond
		
		System.out.println( u.money ); // heritage
	}
}
