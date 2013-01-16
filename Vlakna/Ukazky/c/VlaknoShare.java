package c;

public class VlaknoShare implements Runnable {
	int sharedNumber = 0;
	
	public void run() {
		for( int i = 0; i < 100; i++, sharedNumber++ ) {
			System.out.println("Sdilena: "+sharedNumber+", i: "+i);
		}
	}

}
