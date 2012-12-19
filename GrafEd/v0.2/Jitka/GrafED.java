

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;


public class GrafED extends Frame {

    Platno platno = new Platno();
    Panel panel = new Panel();
    List<Utvar> seznam = new ArrayList<Utvar>();

    public GrafED() {
        super.setTitle(getClass().getName());
        this.setSize(800, 600);
        this.add(platno, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);


        final GrafED okno = this;

        // tlačítko Pridej útvar
        Button pridejBT = new Button("Pridej bod");
        panel.add(pridejBT);
        pridejBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog d = new AddDialog(okno, "Zadejte souradnice bodu");
                d.setVisible(true);
            }
        });

        // obsluha tlačítka pro zavírání okna
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    // nakreslení všech útvarů v seznamu
    class Platno extends Canvas {

        @Override
        public void paint(Graphics g) {
            for (Utvar utvar : seznam) {
                utvar.nakresli(g);
            }
        }
    }

    public static void main(String[] args) {
        new GrafED().setVisible(true);
    }

    public List<Utvar> getSeznam() {
        return seznam;
    }

    public Platno getPlatno() {
        return platno;
    }
    
     
}
