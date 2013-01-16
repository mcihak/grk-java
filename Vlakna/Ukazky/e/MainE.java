package e;

/**
 * @author lipovmar
 * Example 2
 * -> Shameful bank - unsynchronized
 */
public class MainE {
	public static void main(String[] args) {
		new Clerk().start();
		new Clerk().start();
		
		for ( int i=1; true; i++ )
			System.out.println( i+".:"+ Accounts.dump());
		
	}
}
