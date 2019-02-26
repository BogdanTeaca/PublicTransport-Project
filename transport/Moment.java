package transport;

import java.io.Serializable;

/**
 * Clasa ce asociaza unui nod duratele din fiecare dintre cele doua capete ale
 * unei rute pana la nod. Utila pentru calcularea orarului unei rute.
 * 
 * @author BOGDAN TEACA
 */
public class Moment implements Serializable {
    private Nod nod;
    private int durataDus;
    private int durataIntors;

    /**
     * Constructor ce initializeaza nodul si durata din primul capat al rutei pana la nod
     */
    public Moment(Nod nod, int durataDus) {
        this.nod = nod;
        this.durataDus = durataDus;
    }

    /**
     * Constructor ce initializeaza nodul si duratele din capetele rutei pana la nod
     */
    public Moment(Nod nod, int durataDus, int durataIntors) {
        this.nod = nod;
        this.durataDus = durataDus;
        this.durataIntors = durataIntors;
    }

    /**
     * Getter pentru nod
     */
    public Nod getNod() {
        return nod;
    }

    /**
     * Getter pentru durata de la primul capat de ruta pana la nod
     */
    public int getDurataDus() {
        return durataDus;
    }

    /**
     * Getter pentru durata de la al doilea capat de ruta pana la nod
     */
    public int getDurataIntors() {
        return durataIntors;
    }

    /**
     * Setter pentru durata de la al doilea capat de ruta pana la nod
     */
    public void setDurataIntors(final int durataIntors) {
        this.durataIntors = durataIntors;
    }
}
