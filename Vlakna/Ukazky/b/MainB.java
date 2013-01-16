package b;

/**
 * @author lipovmar
 * 2 way how use the threads
 * 1) Define a descendant of the Thread class, 
 *    override the method run() for the desired functionality,
 *    than construct the object and enable it with the inherited method start().
 *    -> This solution is suitable only for very simple cases.
 *    
 * 2) Define a class that implements the Runnable interface 
 *    and override the method run () for the desired functionality.
 *    Construct the object. 
 *    Construct a Thread object with a parameter related to our Runnable object. 
 *    Then start a Thread object.
 *    -> This solution is more general, 
 *       Runnable object (which is not thread) can be complex, 
 *       because it can conveniently inherit from a wealthy class.
 */
public class MainB {
	public static void main(String[] args) {
		// first example
		Tik t = new Tik();
			t.start();
		// shorthand: new Tik().start();
	    // Because Tik's Thread, using the methods start, it will be queued on CPU time.
			
		
		// second example
		Runnable r  = new Tak();
		Thread   th = new Thread(r);
			th.start();
		// shorthand: new Thread( new Tak() ).start();
		// Tak is not Thread, but has a method run,
		// which is called by not covered Thread method, 
		// because Tak is her target.
	}
}
