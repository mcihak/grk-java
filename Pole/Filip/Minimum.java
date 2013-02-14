
public class Minimum {
	public static void main(String[] args){
		double [] cisla = new double[20];
		double min = 100;
		for(int i = 0;i < 20;i++){
			cisla[i] = Math.random();
			
		}
		
		for(int i = 0;i<cisla.length;i++){
			System.out.print(" " + cisla[i] + " ");
		}
		
		for(int i=0; i<cisla.length; i++){
			if (cisla[i]<min){
				min = cisla[i];
			}
		}
		System.out.println("");
		System.out.println("Nejmenší hodnota z pole je: " + min);


	}
}
