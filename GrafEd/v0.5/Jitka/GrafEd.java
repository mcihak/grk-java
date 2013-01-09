
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;

public class GrafEd extends Frame {

    Platno platno = new Platno();
    Panel panel = new Panel();
    List<Utvar> seznam = new ArrayList<Utvar>();

    public String openFile(Frame f, String title, String defDir) {
        FileDialog fd = new FileDialog(f, title, FileDialog.LOAD);
        fd.setDirectory(defDir);
        fd.setLocation(50, 50);
        fd.show();
        return fd.getFile();
    }

    public String saveFile(Frame f, String title, String defDir, String fileType) {
        FileDialog fd = new FileDialog(f, title, FileDialog.SAVE);
        fd.setFile(fileType);
        fd.setDirectory(defDir);
        fd.setLocation(50, 50);
        fd.show();
        return fd.getFile();
    }

    public void about(Frame f, String title) {
        JDialog fd = new JDialog(f, title);
        fd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        fd.setLocation(50, 50);
        fd.setSize(50, 50);
        fd.show();
    }

    public List<Utvar> deserialize(String nazevSouboru) throws Exception {
        List<Utvar> seznam = null;

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(nazevSouboru));
        seznam = (List<Utvar>) in.readObject();

        return seznam;
    }

    public void serialize(String nazevSouboru) throws Exception {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nazevSouboru));
        os.writeObject(seznam);
        os.close();
    }

    public GrafEd() {
        super.setTitle(getClass().getName());
        this.setSize(800, 600);
        this.add(platno, BorderLayout.CENTER);
        this.add(panel, BorderLayout.NORTH);

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
        final GrafEd editor = new GrafEd();
        editor.setVisible(true);

        MenuBar myMenu = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu help = new Menu("Help");
        myMenu.add(file);
        myMenu.add(edit);
        myMenu.add(help);
        editor.setMenuBar(myMenu);
        MenuItem save = new MenuItem("Save");
        MenuItem open = new MenuItem("Open");
        MenuItem exit = new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_X));
        MenuItem subedit = new MenuItem("Edit", new MenuShortcut(KeyEvent.VK_Z));
        MenuItem about = new MenuItem("About");
        file.add(save);
        file.add(open);
        file.addSeparator();
        file.add(exit);
        edit.add(subedit);
        help.add(about);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File novy = new File(editor.saveFile(editor, "Ulozit", "D:/", ".txt"));
                System.out.println("Ukladaci soubor: " + novy.getAbsolutePath());
                try {
                    editor.serialize(novy.getAbsolutePath());
                } catch (Exception ex) {
                }
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File soubor = new File(editor.openFile(editor, "Otevrit", "D:/"));
                System.out.println("Oteviraci soubor: " + soubor.getAbsolutePath());
                try {
                    editor.seznam = editor.deserialize(soubor.getAbsolutePath());
                    editor.platno.repaint();
                } catch (Exception ex) {
                }
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editor.about(editor, "About");
            }
        });
    }
}
