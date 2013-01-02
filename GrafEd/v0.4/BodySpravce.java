import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BodySpravce {
	List<Bod> seznam = new ArrayList<Bod>();
	public List<Bod> getSeznam() {
		return this.seznam;
	}
	
	private GrafEd okno;
	private Bod vybranyBod;
	
	public BodySpravce(GrafEd _okno) {
		this.okno = _okno;
	}
	
	public void pridej(int x, int y) {
		Color color = null;
		switch (okno.getBarvaChoice().getSelectedIndex()) {
			case 0: color = Color.RED; break;
			case 1: color = Color.GREEN; break;
			case 2: color = Color.BLUE; break;
			default: color = Color.BLACK; break;
		}
		if (x >= 0 && x <= GrafEd.platnoSirka 
				&& y >= 0 && y <= GrafEd.platnoVyska) {
			Bod novyBod = new Bod(x, y, color);
			seznam.add(novyBod);
			vybranyBod = novyBod;
		}
		okno.getPlatno().repaint();
	}
	
	public boolean vyber(Bod bod) {
		vybranyBod = bod;
		okno.getPlatno().repaint();
		return false;
	}
	
	public boolean vyber(int x, int y) {
		Iterator<Bod> iter = seznam.iterator();
		while (iter.hasNext()) {
			Bod bod = iter.next();
			if (x >= bod.getX() && x <= bod.getX() + 10
					&& y >= bod.getY() && y <= bod.getY() + 10) {
				vybranyBod = bod;
				okno.getPlatno().repaint();
				return true;
			}
		}
		okno.getPlatno().repaint();
		return false;
	}
	
	public void zrusVyber() {
		vybranyBod = null;
		okno.getPlatno().repaint();
	}
	
	public void posun(int x, int y) {
		if (vybranyBod != null) {
			vybranyBod.setX(x);
			vybranyBod.setY(y);
		}
		okno.getPlatno().repaint();
	}
	
	public Bod nachaziSeNa(int x, int y) {
		Iterator<Bod> iter = seznam.iterator();
		while (iter.hasNext()) {
			Bod bod = iter.next();
			if (x >= bod.getX() && x <= bod.getX() + 10
					&& y >= bod.getY() && y <= bod.getY() + 10) {
				return bod;
			}
		}
		return null;
	}
	
	public void nakresli(Graphics g) {
		for (Bod bod : seznam) {
			bod.nakresli(g);
	    } 
		if (vybranyBod != null) {
			g.setColor(Color.BLACK);
			g.drawRect(vybranyBod.getX() - 2, vybranyBod.getY() - 2, 14, 14);
		}
	}
}