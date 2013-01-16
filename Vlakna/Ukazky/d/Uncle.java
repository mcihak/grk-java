package d;

public class Uncle extends Thread {
	int money = 0;
	
	public Uncle () {
		this.start(); // constructor starts itself
	}
	
	public void run() {
		while( (++money) < 100000000 ); // work
	}
	
}
