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
    
    // textová pole pro souøadnice
    final TextField xTF = new TextField(3);
    panel.add(xTF);
    final TextField yTF = new TextField(3);
    panel.add(yTF);
    
    // tlaèítko Pøidej útvar
    Button pridejBT = new Button("Pøidej útvar");
    panel.add(pridejBT);
    pridejBT.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        seznam.add(new Bod(Integer.parseInt(xTF.getText()), Integer.parseInt(yTF.getText()), Color.RED));
        platno.repaint();
      }  
    });

    // obsluha tlaèítka pro zavírání okna
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(1);
      }
    });
  }

  // nakreslení všech útvarù v seznamu
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