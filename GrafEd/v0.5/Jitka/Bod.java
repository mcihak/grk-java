
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Bod extends Utvar implements Serializable {

    private int x;
    private int y;

    public Bod() {
    }

    public Bod(int x, int y, Color barva) {
        super(barva);
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public void nakresli(Graphics g) {
        g.setColor(this.getBarva());
        g.fillOval(this.x, this.y, 10, 10);
    }

    @Override
    public Utvar deserialize() throws Exception {
        Bod bod = null;

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("./graf.se"));
        bod = (Bod) in.readObject();

        return bod;
    }

    @Override
    public void serialize() throws Exception {
        final Bod bod = this;

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("./graf.se"));
        os.writeObject(bod);
        os.close();
    }
}
