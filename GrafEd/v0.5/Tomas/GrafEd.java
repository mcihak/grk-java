import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GrafEd extends Frame implements ActionListener {

  public static final int platnoSirka = 800;
  public static final int platnoVyska = 600;
  
  private Platno platno = new Platno();
  public Platno getPlatno() {
	  return this.platno;
  }
  private Panel panel = new Panel();
  private Choice barvaChoice;
  public Choice getBarvaChoice() {
	  return this.barvaChoice;
  }
  
  private MenuGrafEd menu = new MenuGrafEd(this);
  private BodySpravce body = new BodySpravce(this);

  public GrafEd() {
    super.setTitle(getClass().getName());
    this.setSize(platnoSirka, platnoVyska);
    this.add(platno, BorderLayout.CENTER);    
    this.add(panel, BorderLayout.SOUTH);
    
    barvaChoice = new Choice();
    barvaChoice.add("Červená");
    barvaChoice.add("Zelená");
    barvaChoice.add("Modrá");
    panel.add(barvaChoice);
    
    platno.addMouseListener(new MouseListener() {
    	private boolean pressed = false;
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			Bod existujici = body.nachaziSeNa(e.getX(), e.getY());
			if (existujici == null)
				body.pridej(e.getX(), e.getY());
			else
				body.vyber(existujici);
		}
		public void mouseReleased(MouseEvent e) {
			body.zrusVyber();
		}
    });
    
    platno.addMouseMotionListener(new MouseMotionListener() {
		public void mouseDragged(MouseEvent e) {
			body.posun(e.getX(), e.getY());
		}
		public void mouseMoved(MouseEvent e) {}
    });

    // obsluha tlaèítka pro zavírání okna
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(1);
      }
    });
  }
  
  public void actionPerformed( ActionEvent e ) {
    String cmd = e.getActionCommand();
    if( cmd.equals( "open" ) ) {
    	body.otevrit(new FileDialogGrafEd().loadFile(this, "Otevřít soubor", "D:", "*.ge"));
    }
    else if( cmd.equals( "save" ) ) {
    	body.ulozit(new FileDialogGrafEd().saveFile(this, "Uložit soubor", "D:", "*.ge"));
    }
    else if( cmd.equals( "exit" ) ) {
    	System.exit(1);
    }
    else if( cmd.equals( "about" ) ) {
    	System.out.println( "about" );
    }
  }

  // nakreslení všech útvarù v seznamu
  class Platno extends Canvas { 
    @Override 
    public void paint(Graphics g) {    
    	body.nakresli(g);
    }  
  }  
  
  public static void main(String[] args) {
    new GrafEd().setVisible(true);
  }
}