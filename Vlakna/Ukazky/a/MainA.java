package a;

/**
 * @author lipovmar
 * Easy example of threads.
 */
public class MainA {
	public static void main(String[] args) {
		Vlakno v1 = new Vlakno("I  "); // Create the thread
		Vlakno v2 = new Vlakno("II ");
		Vlakno v3 = new Vlakno("III");

		v3.start(); // Start the thread
		v2.start();
		v1.start();

		try {
			v3.join(); // Waiting for end of another thread
			v2.join();
			v1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("This is the end of proces.");
	}

}
