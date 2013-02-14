
public class RotaceMatice {
	public static void main(String[] args){
		char [] [] pismenka = new char[5] [5];
		char [] [] rotovanaPismenka = new char[5] [5];
		int pismenko = 97;
		for (int i = 0; i<5; i++){
			for (int j = 0; j<5; j++){
				pismenka [i][j] = (char)pismenko;
				pismenko ++;
			}
		}
		
		for (int i = 0; i<5; i++){
			for (int j = 0; j<5; j++){
				System.out.print(pismenka[i][j] + " ");
				}
			System.out.println("");
			}
		
		for (int i = 0; i<5; i++){
			for (int j = 0; j<5; j++){
				rotovanaPismenka[j][4-i] = pismenka [i][j];
				
			}
		}
		
		System.out.println("");

		for (int i = 0; i<5; i++){
			for (int j = 0; j<5; j++){
				System.out.print(rotovanaPismenka[i][j] + " ");
				}
			System.out.println("");
			}

		
	}
}
