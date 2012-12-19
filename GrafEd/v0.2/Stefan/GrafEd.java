import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList; 

public class GrafEd extends Frame {
  Frame f = new Frame();
  Platno platno = new Platno();
  Panel panel = new Panel();
  List<Utvar> seznam = new ArrayList<Utvar>();
  
  

  public GrafEd() {
    super.setTitle(getClass().getName());
    this.setSize(800, 600);
    this.add(platno, BorderLayout.CENTER);    
    this.add(panel, BorderLayout.SOUTH);

    // tlacitko Pridej útvar
    Button pridejBT = new Button("Pridej utvar");
    panel.add(pridejBT);
    pridejBT.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
    	  showDialog(f);
       
      }  
    });

    // obsluha tlacitka pro zavirani okna
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(1);
      }
    });
  }

  // nakresleni vsech utvaru v seznamu
  class Platno extends Canvas { 
    @Override 
    public void paint(Graphics g) {    
      for (Utvar utvar : seznam) {
        utvar.nakresli(g);
      }      
    }  
  }
  
  private void showDialog(Frame owner) {
	  final Dialog d = new Dialog(owner, "The Dialog", true); // true for modal
	  d.setLayout(new FlowLayout());
	  d.add(new Label("x="));
	  final TextField xTF = new TextField(3);
	  d.add(xTF);
	  d.add(new Label("y="));
	  final TextField yTF = new TextField(3);
	  d.add(yTF);
	  Button ok = new Button("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seznam.add(new Bod(Integer.parseInt(xTF.getText()), Integer.parseInt(yTF.getText()), Color.RED));
		        platno.repaint();
				d.setVisible(false);
		    }
		});
		d.add(ok);
		d.pack();
		
		  Button st = new Button("Storno");
			st.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					d.setVisible(false);
			    }
			});
			d.add(st);
			d.pack();
			
  	
		// locate dialog to the center
		Dimension dd = d.getSize();
		Dimension pd = owner.getSize();
		Point pl = owner.getLocation();
		d.setLocation(
		    pl.x + ((int) (pd.getWidth() - dd.getWidth()))+400,
		    pl.y + ((int) (pd.getHeight() - dd.getHeight()))+300
		);
		d.setVisible(true);

		
  }
  

  
  public static void main(String[] args) {
    new GrafEd().setVisible(true);
  }
}
