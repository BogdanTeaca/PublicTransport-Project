package transport;
import java.util.ArrayList;

/**
 * Clasa pentru o statie al retelei de transport public. Statia are un nume, simbol si apartine
 * unui drum
 * 
 * @author BOGDAN TEACA
 */
public class Statie extends Nod {
	private String simbol;
	private String nume;
	private Drum drumProprietar;

	/**
	 * Constructor ce initializeaza numele statiei, drumul pe care se afla statia si
	 * distanta la care se afla statia pe drumul proprietar
	 * 
	 * @param nume = Numele statiei
	 * @param drumProprietar = Drumul pe care se afla statia
	 * @param distanta = Distanta de la capatul drumului proprietar pana la statie
	 */
	public Statie(String nume, Drum drumProprietar, int distanta) {
		this.simbol = " O ";
		this.nume = nume;
		this.drumProprietar = drumProprietar;

		drumProprietar.adaugaStatie(this, distanta);
	}

	/**
	 * Functie ce seteaza drumul proprietar al statiei
	 */
	public void adaugaDrum(final Drum drum) {
		this.drumProprietar = drum;
	}

	/**
	 * Getter pentru drumul proprietar al statiei
	 */
	public Drum getDrumProprietar() {
		return drumProprietar;
	}

	/**
	 * Functie care verifica daca doua statii sunt aceleasi
	 */
	public boolean equals(final Statie statie) {
		if (this.nume.equals(statie.getNume())) {
			return true;
		}

		return false;
	}

	/**
	 * Getter pentru numele statiei
	 */
	@Override
	public String getNume() {
		return nume;
	}
	
	/**
	 * Getter pentru simbolul statiei din reprezentarea grafica a drumurilor si rutelor
	 */
	@Override
    public String getSimbol() {
        return simbol;
    }

	@Override
	public ArrayList<Drum> getDrumuri() {
		ArrayList<Drum> drum = new ArrayList<Drum>();
		drum.add(drumProprietar);

		return drum;
	}

	@Override
	public Drum gasesteDrum(final String numeDrum) {
		if (this.drumProprietar.getNume().equals(numeDrum)) {
			return drumProprietar;
		}

		return null;
	}
}
