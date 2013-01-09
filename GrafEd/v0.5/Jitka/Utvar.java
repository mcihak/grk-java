
import java.awt.*;
import java.io.Serializable;

public abstract class Utvar implements Serializable {

    private Color barva;

    public Utvar() {
    }

    public Utvar(Color barva) {
        this.barva = barva;
    }

    public void setBarva(Color barva) {
        this.barva = barva;
    }

    public Color getBarva() {
        return this.barva;
    }

    abstract void serialize() throws Exception;

    abstract Utvar deserialize() throws Exception;

    abstract public void nakresli(Graphics g);
}
