import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList; 

public class GrafEd extends Frame {
  
  Platno platno = new Platno();
  Panel panel = new Panel();
  List<Utvar> seznam = new ArrayList<Utvar>();
  private Dialog pridejDialog;
  private Dialog upravDialog;
  
  // textová pole pro souřadnice
  private TextField xPridejTF;
  private TextField yPridejTF;
  private TextField xUpravTF;
  private TextField yUpravTF;
  
  private int vybranyBod = -1;

  public GrafEd() {
    super.setTitle(getClass().getName());
    this.setSize(800, 600);
    this.add(platno, BorderLayout.CENTER);    
    this.add(panel, BorderLayout.SOUTH);
    this.vytvorDialogy();

    // tlačítko Přidej
    Button pridejBT = new Button("Pridej");
    panel.add(pridejBT);
    pridejBT.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
    	pridejDialog.pack();
		pridejDialog.setVisible(true);
        platno.repaint();
      }  
    });
    
    // tlačítko Vyber
    Button vyberBT = new Button("Vyber");
    panel.add(vyberBT);
    vyberBT.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
    	if (seznam.size() > 1) {
    		++vybranyBod;
    		if (vybranyBod > seznam.size() - 1)
    			vybranyBod = 0;
    	}
        platno.repaint();
      }  
    });
    
    // tlačítko Uprav
    Button upravBT = new Button("Uprav");
    panel.add(upravBT);
    upravBT.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
    	xUpravTF.setText(String.valueOf(seznam.get(vybranyBod).getX()));
    	yUpravTF.setText(String.valueOf(seznam.get(vybranyBod).getY()));
    	upravDialog.pack();
    	upravDialog.setVisible(true);
        platno.repaint();
      }  
    });
    

    // obsluha tlačítka pro zavírání okna
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(1);
      }
    });
  }
  
  public void vytvorDialogy() {
	// pridej
	xPridejTF = new TextField(3);
	yPridejTF = new TextField(3);
	pridejDialog = new Dialog(this, "Zadejte souradnice bodu", true);
    pridejDialog.setLayout( new FlowLayout() );
    final Button okPridejBT = new Button ("OK");
	okPridejBT.addActionListener ( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			seznam.add(new Bod(Integer.parseInt(xPridejTF.getText()), Integer.parseInt(yPridejTF.getText()), Color.RED));
			vybranyBod = seznam.size() - 1;
			pridejDialog.setVisible(false);
		}
	});
	final Button stornoPridejBT = new Button ("Storno");
	stornoPridejBT.addActionListener ( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			pridejDialog.setVisible(false);
		}
	});
	pridejDialog.add(new Label ("x ="));
	pridejDialog.add(xPridejTF);
	pridejDialog.add(new Label ("y ="));
	pridejDialog.add(yPridejTF);
	pridejDialog.add(okPridejBT);
	pridejDialog.add(stornoPridejBT);
	
	// uprav
	xUpravTF = new TextField(3);
	yUpravTF = new TextField(3);
	upravDialog = new Dialog(this, "Uprav souradnice bodu", true);
	upravDialog.setLayout( new FlowLayout() );
    final Button okUpravBT = new Button ("OK");
    okUpravBT.addActionListener ( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			seznam.get(vybranyBod).setX(Integer.parseInt(xUpravTF.getText()));
			seznam.get(vybranyBod).setY(Integer.parseInt(yUpravTF.getText()));
			upravDialog.setVisible(false);
		}
	});
    final Button stornoUpravBT = new Button ("Storno");
    stornoUpravBT.addActionListener ( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
			upravDialog.setVisible(false);
		}
	});
    upravDialog.add(new Label ("x ="));
	upravDialog.add(xUpravTF);
	upravDialog.add(new Label ("y ="));
	upravDialog.add(yUpravTF);
	upravDialog.add(okUpravBT);
	upravDialog.add(stornoUpravBT);
  }

  // nakreslení všech útvarů v seznamu
  class Platno extends Canvas { 
    @Override 
    public void paint(Graphics g) {    
      for (Utvar utvar : seznam) {
        utvar.nakresli(g);
      } 
      if (vybranyBod > -1) {
    	  g.setColor(Color.BLUE);
    	  g.drawOval(seznam.get(vybranyBod).getX(), 
    			  	 seznam.get(vybranyBod).getY(), 
    			  	 10, 10);
      }
    }  
  }  
  
  public static void main(String[] args) {
    new GrafEd().setVisible(true);
  }
}