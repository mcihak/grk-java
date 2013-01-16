package f;

public class AccountsSolid {
	static private long a1 = 0,
						a2 = 1000;
	
	static void move(int x) {
		synchronized ( AccountsSolid.class ) {
			a1 -= x;
			a2 += x;
		}
	}
	
	/*
	static synchronized void move ( int x ) {
		a1 -= x;
		a2 += x;
	}
	*/
	
	
	static String dump() {
		return a1 + " + " + a2 + " = " + (a1+a2);
	}
}
