import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList; 

public class GrafEd extends Frame {
  
  Platno platno = new Platno();
  Panel panel = new Panel();
  List<Utvar> seznam = new ArrayList<Utvar>();

  public GrafEd() {
    super.setTitle(getClass().getName());
    this.setSize(800, 600);
    this.add(platno, BorderLayout.CENTER);    
    this.add(panel, BorderLayout.SOUTH);
    
    // textov� pole pro sou�adnice
    final TextField xTF = new TextField(3);
    panel.add(xTF);
    final TextField yTF = new TextField(3);
    panel.add(yTF);
    
    // tla��tko P�idej �tvar
    Button pridejBT = new Button("P�idej �tvar");
    panel.add(pridejBT);
    pridejBT.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        seznam.add(new Bod(Integer.parseInt(xTF.getText()), Integer.parseInt(yTF.getText()), Color.RED));
        platno.repaint();
      }  
    });

    // obsluha tla��tka pro zav�r�n� okna
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(1);
      }
    });
  }

  // nakreslen� v�ech �tvar� v seznamu
  class Platno extends Canvas { 
    @Override 
    public void paint(Graphics g) {    
      for (Utvar utvar : seznam) {
        utvar.nakresli(g);
      }      
    }  
  }  
  
  public static void main(String[] args) {
    new GrafEd().setVisible(true);
  }
}