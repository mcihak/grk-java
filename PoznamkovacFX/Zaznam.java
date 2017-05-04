package poznamkovacfx;

import java.time.LocalDate;

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
public class Zaznam implements Comparable<Zaznam> {

    private LocalDate datum;
    private String text;

    public Zaznam(LocalDate datum, String text) {
        this.datum = datum;
        this.text = text;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return datum.getYear() + "-" + datum.getMonthValue() + "-" + datum.getDayOfMonth() + ": " + text;
    }

    @Override
    public int compareTo(Zaznam z) {
        return datum.compareTo(z.getDatum());
    }

}
