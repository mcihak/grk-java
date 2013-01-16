package e;

public class Clerk extends Thread {
	public void run() {
		while( true )
			Accounts.move( (int)((Math.random() - 0.5 )*100) );
	}
}
