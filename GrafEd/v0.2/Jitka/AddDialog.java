

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class AddDialog extends Dialog {

    public AddDialog(final GrafED owner, String title) {
        super(owner, title);
        this.setSize(300, 100);
        

        Panel souradnice = new Panel();
        this.add(souradnice, BorderLayout.CENTER);
        this.setLocation(250, 200);
        
        Label x = new Label("x =");
        souradnice.add(x);
        final TextField xTF = new TextField(3);
        souradnice.add(xTF);
        Label y = new Label("   y =");
        souradnice.add(y);
        final TextField yTF = new TextField(3);
        souradnice.add(yTF);

        Button ok = new Button("OK");
        souradnice.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.getSeznam().add(new Bod(Integer.parseInt(xTF.getText()), Integer.parseInt(yTF.getText()), Color.RED));
                owner.getPlatno().repaint();
                dispose();
                
            }
        });
        
        Button storno = new Button("Storno");
        souradnice.add(storno);
        storno.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
    });
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               dispose();
            }
        });
    }
}
