package transport;
import java.util.ArrayList;

/**
 * Clasa intersectie care mosteneste clasa nod. Intersectiile au un id si leaga mai multe
 * drumuri intre ele. Intersectiile pot fi la capetele drumului sau in interiorul unui drum.
 * 
 * @author BOGDAN TEACA
 */
public class Intersectie extends Nod{
    /**
     * Simbolul este afisat la reprezentarea grafica a intersectiei pe drum
     * Simbolul poate fi # sau X. # = intersectia nu este conectata la niciun
     * drum sau este conectata la un drum, iar X = intersectia este conectata
     * la cel putin doua drumuri
     */
	private String simbol;
	private final int id;
	public static int lastId = 1;
	private ArrayList<Drum> drumuri;

	/**
	 * Constructor care initializeaza id-ul, drumurile si simbolul intersectiei.
	 */
	public Intersectie() {
		this.drumuri = new ArrayList<Drum>();
		this.simbol = " # ";
		this.id = lastId;
		Intersectie.lastId++;
	}

	/**
	 * Constructor care initializeaza intersectia si adauga intersectia la un drum
	 * 
	 * @param drum = Drumul unde trebuie adaugata intersectia
	 * @param distanta = Distanta la care trebuie adaugata intersectia pe drum
	 */
	public Intersectie(Drum drum, int distanta) {
		this();

		if (!this.drumuri.contains(drum)) {
			this.drumuri.add(drum);
		}

		drum.adaugaIntersectie(this, distanta);
	}

	/**
	 * Functie ce adauga un drum la lista de drumuri a intersectiei si actualizeaza
	 * simbolul intersectiei daca este cazul
	 */
	public void adaugaDrum(final Drum drum) {
		if (!this.drumuri.contains(drum)) {
			this.drumuri.add(drum);
			updateazaSimbol();
		}
	}
	
	/**
	 * Getter pentru simbolul intersectiei
	 */
	@Override
    public String getSimbol() {
        return simbol;
    }

	/**
	 * Getter pentru id-ul intersectiei
	 */
	public int getId() {
		return id;
	}

	/**
	 * Functia getNume suprascrisa din superclasa Nod pentru a obtine numele intersectiei
	 * care este id-ul acesteia intre paranteze.
	 */
	@Override
	public String getNume() {
		return "(" + id + ")";
	}

	/**
	 * Actualizeaza simbolul intersectiei daca este nevoie (daca intersectia leaga cel putin
	 * doua drumuri
	 */
	public void updateazaSimbol() {
		if (drumuri.size() < 2) {
			this.simbol = " # ";
		} else {
			this.simbol = " X ";
		}
	}

	/**
	 * Gaseste si returneaza un drum conectat la intersectie
	 */
	@Override
	public Drum gasesteDrum(String numeDrum) {
		for (int i = 0; i < drumuri.size(); i++) {
			if (drumuri.get(i).getNume().equals(numeDrum)) {
				return drumuri.get(i);
			}
		}

		return null;
	}

	/**
	 * Getter pentru lista de drumuri conectate la intersectie
	 */
	@Override
	public ArrayList<Drum> getDrumuri() {
		return drumuri;
	}
	
	/**
	 * Functie ce reseteaza contorul lastId. Folosit in caz de resetarea retelei de transport
	 */
	public static void resetLastId() {
		lastId = 1;
	}
}
