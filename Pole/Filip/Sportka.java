
public class Sportka {
	public static void main(String[] args){
		int [] tah = new int[6];
		int cisloSportky;
		boolean sameNumber = false;
		
		for(int i = 0; i<tah.length; i++){
			cisloSportky = (int)(Math.random()*49+1);
			
			for(int j =0; j<tah.length; j++){
				if (tah[j]==cisloSportky){
					sameNumber = true;		
				}
			}
			if(sameNumber ==true){
				sameNumber = false;
				i--;
			}
			else if(sameNumber == false){
				tah[i] = cisloSportky;
			}
			
			
		}
		for(int i = 0; i<tah.length; i++){
		System.out.println(" " + tah[i] + " ");
		}
	}
}
