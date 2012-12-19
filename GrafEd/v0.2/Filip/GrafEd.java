import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GrafEd extends Frame {
  
    private static final int WIDTH = 300;
    private static final int HEIGHT = 250;
  
    
  Platno platno = new Platno();
  Panel panel = new Panel();
  List<Utvar> seznam = new ArrayList<Utvar>();

  public GrafEd() {
    super.setTitle(getClass().getName());
    this.setSize(800, 600);
    this.add(platno, BorderLayout.CENTER);
    this.add(panel, BorderLayout.SOUTH);
    
	final Frame f = new Frame();
	f.setTitle("Dialog Example");
	f.setSize(WIDTH, HEIGHT);

	Panel p1 = new Panel() {
	    public void paint(Graphics g) {
		int left = GrafEd.WIDTH/2; // don't use WIDTH shadowed by Panel class
		int top = GrafEd.HEIGHT/2; // same as above
		g.drawString("Dialog Example", left, top);
	    }
	};
	f.add("Center", p1);

	Panel p2 = new Panel();
	f.add("South", p2);
    
    
    // tlacitko Pridej bod
    Button pridejBT = new Button("Pridej utvar");
    panel.add(pridejBT);
    pridejBT.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
  		showDialog(f);
        platno.repaint();
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
  
  public static void main(String[] args) {
    new GrafEd().setVisible(true);
  }
  private void showDialog(Frame owner) {
	   	//Color barva ;

	    
		final Dialog d = new Dialog(owner, "The Dialog", true); // true for modal
	    d.setLayout(new GridLayout(4, 4, 3, 3));

		d.add(new Label("Vyberte útvar "));
		
		final Choice utv = new Choice();
		utv.addItem("Bod");
	    utv.addItem("Obdélník");
	    utv.addItem("Ovál");
	   
	    d.add(utv);
	    

		d.add(new Label("Kam chcete "));
		d.add(new Label("útvar přidat?"));
		
		d.add(new Label("X:"));
		
	    final TextField xTF = new TextField(3);
	    panel.add(xTF);
		d.add( xTF);
		d.pack();
		
		d.add(new Label("Y:"));

		
	    final TextField yTF = new TextField(3);
	    panel.add(yTF);
		d.add(yTF);
		d.pack();
		
		d.add(new Label("X2 / hlavní poloosa"));
		
	    final TextField x2TF = new TextField(3);
	    panel.add(x2TF);
		d.add(x2TF);
		d.pack();
		
		d.add(new Label("Y2 / vedlejší poloosa"));

		
	    final TextField y2TF = new TextField(3);
	    panel.add(y2TF);
		d.add(y2TF);
		d.pack();


		d.add(new Label("Vyberte barvu "));
		
		final Choice col = new Choice();
		col.addItem("Červevná");
	    col.addItem("Modrá");
	    col.addItem("Zelená");
	    col.addItem("Žlutá");
	    col.addItem("Černá");
	    d.add(col);
	    	      
	    
		Button ok = new Button("OK");
		ok.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Color barva = null;
		    	//výběr barvy
		    	if (col.getSelectedItem().equals("Červevná")){
		    		barva = Color.RED;
		    	}
		    	if (col.getSelectedItem().equals("Modrá")){
		    		barva = Color.BLUE;
		    	}
		    	if (col.getSelectedItem().equals("Zelená")){
		    		barva = Color.GREEN;
		    	}
		    	if (col.getSelectedItem().equals("Žlutá")){
		    		barva = Color.YELLOW;
		    	}
		    	if (col.getSelectedItem().equals("Černá")){
		    		barva = Color.BLACK;
		    	}
		    	
		    	
		    	//výběr útvaru
		    	if (utv.getSelectedItem().equals("Bod")){
					seznam.add(new Bod(Integer.parseInt(xTF.getText()), Integer.parseInt(yTF.getText()), barva));
		    	}
		    	if (utv.getSelectedItem().equals("Obdélník")){
		            seznam.add(new Rectangle(Integer.parseInt(xTF.getText()), Integer.parseInt(yTF.getText()), Integer.parseInt(x2TF.getText()), Integer.parseInt(y2TF.getText()), barva));

		    	}
		    	if (utv.getSelectedItem().equals("Ovál")){
		            seznam.add(new Circle(Integer.parseInt(xTF.getText()), Integer.parseInt(yTF.getText()), Integer.parseInt(x2TF.getText()), Integer.parseInt(y2TF.getText()), barva));
		    	}
		    	
		    	
          
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
		
		

		
		 d.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		    	  d.setVisible(false);
		      }
		    });
		
		// locate dialog to the center -- ?????
		Dimension dd = d.getSize();
		Dimension pd = owner.getSize();
		Point pl = owner.getLocation();
		d.setLocation(
		    pl.x + ((int) (pd.getWidth() - dd.getWidth()))/2,
		    pl.y + ((int) (pd.getHeight() - dd.getHeight()))/2
		);
		d.setVisible(true);
	    }
}
