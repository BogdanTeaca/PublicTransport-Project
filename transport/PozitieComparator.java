package transport;
import java.util.Comparator;

/**
 * Clasa ce implementeaza functia de comparare intre doua noduri "poz1" si "poz2" in functie
 * de distantele acestora pe un anumit drum. Utila pentru sortarea nodurilor pe un drum.
 * 
 * @author BOGDAN TEACA
 */
public class PozitieComparator implements Comparator<Pozitie> {
    public int compare(final Pozitie poz1, final Pozitie poz2) {
        return ((Integer) poz1.getDistanta()).compareTo(((Integer) poz2.getDistanta()));
    }
}
