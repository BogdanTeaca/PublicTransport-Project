package transport;

import java.io.Serializable;

/**
 * Clasa ce asociaza unui nod (statie sau intersectie) distanta de la capatul
 * unui drum pana la nod
 * 
 * @author BOGDAN TEACA
 */
public class Pozitie implements Serializable {
	private Nod nod;
	private int distanta;

	/**
	 * Constructor ce initializeaza nodul si distanta pana la acesta
	 */
	public Pozitie(Nod nod, int distanta) {
		this.nod = nod;
		this.distanta = distanta;
	}

	/**
	 * Getter pentru nod
	 */
	public Nod getNod() {
		return nod;
	}

	/**
	 * Getter pentru distanta pana la nod
	 */
	public int getDistanta() {
		return distanta;
	}
}
