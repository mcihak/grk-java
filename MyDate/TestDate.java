

public class  TestDate {
   public static void main(String[] args) {
    MyDate d1 = new MyDate(2012, 2, 28, 2);
    System.out.println(d1);                    // Tuesday 28 Feb 2012
    System.out.println(d1.nextDay());                
    
    MyDate d4 = new MyDate(2000, 3, 1); 
    System.out.println(d4);
    System.out.println(d4.nextDay()); 

    
    //    
//    MyDate d5 = new MyDate(2011, 2, 29);  // Invalid year, month, or day!
//    
   } 
}