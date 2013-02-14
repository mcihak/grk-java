import java.util.Scanner;


public class ZmenPrvek {
	public static void main (String[] args){
		int [] cisla = new int [50];
		int a = 1;
		int pozice;
		int noveCislo;
		Scanner sc = new Scanner(System.in);

		for(int i = 0;i<cisla.length;i++){
			cisla[i] = a;
			a += 2;
		}
		for(int i = 0;i<cisla.length;i++){
			System.out.print(" " + cisla[i] + " ");
		}
		
		System.out.println("");
		System.out.println("Na které pozici chcete zmìnit? ");
		pozice = sc.nextInt() -1;
		System.out.println("Jakým èíslem chcete nahradit? ");
		noveCislo = sc.nextInt();
		cisla[pozice] = noveCislo;
		
		for(int i = 0;i<50;i++){
			System.out.print(" " + cisla[i] + " ");
		}

	}
}
