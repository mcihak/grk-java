
public class HodyMinci {
	public static void main (String[] args){
		int[] hody = new int[50];
		int pocetRubu=0;
		int pocetLicu=0;
		int rub = 0;
		int lic = 1;
		int hod;
	
		for(int i =0; i < hody.length; i++){
			hod = (int)(Math.random()*2);
			hody[i]=hod;
		}
		
		for(int j = 0; j<hody.length; j++){
			if(hody[j]==lic){
				pocetLicu +=1;
			}
			if(hody[j]==rub){
				pocetRubu +=1;
			}
		}
		
		System.out.println("Procentuelní zastoupení rubù bylo: " + (100*pocetRubu)/(pocetRubu+pocetLicu) + " a lícù: " + (100*pocetLicu)/(pocetRubu+pocetLicu));
	
	}
}
