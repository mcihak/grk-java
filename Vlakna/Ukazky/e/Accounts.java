package e;

public class Accounts {
	static long a1 = 0,
				a2 = 1000;
	
	static void move( int x ) { // there is a problem
		a1 -= x;
		a2 += x;
	}
	
	static String dump() {
		return a1 + " + " + a2 + " = " + (a1+a2);
	}
}
