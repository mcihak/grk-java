package b;

import java.util.Random;

public class Orloj {
	private int GetRandomNumber(int limit) {
		Random random = new Random();
		int    number = random.nextInt(limit);
		
		return number;
	}
	
	private boolean ShowOrNotShow () {
		boolean canShow = false;
		if ( this.GetRandomNumber(10) > 5 )
			canShow = true;
		
		return canShow;
	}
	
	protected String ShowRandomNumber () {
		String text = "";
		if ( this.ShowOrNotShow() )
			text = ": random number: "+this.GetRandomNumber(100);
		return text;
	}
	
	
}
