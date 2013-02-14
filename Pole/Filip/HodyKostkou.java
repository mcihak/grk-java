
public class HodyKostkou {
	public static void main(String[] args){
		int [] hody = new int[100];
		int [] cetnosti = new int [6];
		int cetnost = 0;
		
		for(int i =0; i< hody.length; i++){
			hody[i] = (int)(Math.random()*6)+1;
		}
		
		for(int j = 1; j <= cetnosti.length; j++){
			for(int k = 0; k<hody.length; k++){
				if(hody[k]==j){
					cetnost +=1;
				}
			}
			cetnosti[j-1] = cetnost;
			cetnost = 0;
		}
		
		System.out.println("výsledek  1   2   3   4   5   6");
		System.out.print("èetnost");
		for(int j = 0; j < cetnosti.length; j++){
		System.out.print("  " + cetnosti[j]);
		}
	}
}
