package f;

public class ClerkSolid extends Thread {
	public void run() {
		while( true )
			AccountsSolid.move( (int)((Math.random() - 0.5 )*100) );
	}
}
