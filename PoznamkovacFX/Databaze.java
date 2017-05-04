package poznamkovacfx;

import java.time.LocalDate;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 *	       __          __                __            
 *	  ____/ /__ _   __/ /_  ____  ____  / /__ _________
 *	 / __  / _ \ | / / __ \/ __ \/ __ \/ //_// ___/_  /
 *	/ /_/ /  __/ |/ / /_/ / /_/ / /_/ / ,< _/ /__  / /_
 *	\__,_/\___/|___/_.___/\____/\____/_/|_(_)___/ /___/
 *                                                   
 *                                                           
 *      TUTORIÁLY  <>  DISKUZE  <>  KOMUNITA  <>  SOFTWARE
 * 
 *	Tento zdrojový kód je součástí tutoriálů na programátorské 
 *	sociální síti WWW.DEVBOOK.CZ	
 *	
 *	Kód můžete upravovat jak chcete, jen zmiňte odkaz 
 *	na www.devbook.cz :-) 
 */
public class Databaze {

    private ObservableList<Zaznam> zaznamy;

    public Databaze() {
        zaznamy = FXCollections.observableArrayList();
    }

    public void pridejZaznam(Zaznam z) {
        zaznamy.add(z);
        Collections.sort(zaznamy);
    }

    public void pridejZaznam(LocalDate datumCas, String text) {
        zaznamy.add(new Zaznam(datumCas, text));
        Collections.sort(zaznamy);
    }

    public ObservableList<Zaznam> getZaznam(LocalDate datum) {
        ObservableList<Zaznam> nalezene = FXCollections.observableArrayList();
        for (Zaznam z : zaznamy) {
            if (z.getDatum().equals(datum)) {
                nalezene.add(z);
            }
        }
        return nalezene;
    }

    public void vymazZaznamy(LocalDate datum) {
        ObservableList<Zaznam> nalezeno = getZaznam(datum);
        for (Zaznam z : nalezeno) {
            zaznamy.remove(z);
        }
    }

    public ObservableList<Zaznam> getVsechnyZaznamy() {
        return zaznamy;
    }
}
