package f;

/**
 * @author lipovmar
 * Example 3
 * -> Solid bank - synchronized
 */
public class MainF {
	public static void main(String[] args) {
		new ClerkSolid().start();
		new ClerkSolid().start();
		
		for ( int i=1; true; i++ )
			System.out.println( i+".:"+ AccountsSolid.dump());
		
	}
}
