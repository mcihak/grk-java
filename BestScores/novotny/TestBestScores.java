public class TestBestScores {
   public static void main(String[] args) {
      BestScores table = new BestScores();
      table.add("Penny", 96160);
      table.add("Alex", 132840);
      table.add("Liu", 91125);
      table.add("David", 99280);
      table.add("Natasha", 107315);
      System.out.println(table);    // vypiseme tabulku serazenou
                                    // podle skore sestupne 
      table.add("Max", 105790);     // pridame dalsiho hrace
      System.out.println(table);    // znovu vypise tabulku 
                                    // serazenou podle skore
   }
}