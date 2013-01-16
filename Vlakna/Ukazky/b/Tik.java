package b;

public class Tik extends Thread {
	public void run() {
		try {
			while( true ) {
				System.out.println( "Tik" );
				sleep(1000);
			}
		}
		catch ( InterruptedException ex ) {
			
		}
	}
}
