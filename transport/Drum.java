package transport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clasa drum care reprezinta o strada. Drumul are o lungime, are la capete cate o intersectie,
 * iar in interior poate avea mai multe intersectii si statii
 * 
 * @author BOGDAN TEACA
 */
public class Drum implements Serializable {
	private String nume;
	private ArrayList<Pozitie> noduri; // nodurile sunt intersectiile si statiile de pe drum

	/**
	 * Constructor ce initializeaza numele, lungimea si capetele drumului
	 * 
	 * @param nume = numele drumului
	 * @param lungime = lungimea drumului in metri
	 * @param inter1 = intersectia capat din stanga (pozitionata la 0 metri pe drum)
	 * @param inter2 = intersectia capat din dreapta
	 */
	public Drum(String nume, int lungime, Intersectie inter1, Intersectie inter2) {
		this.nume = nume;
		this.noduri = new ArrayList<Pozitie>();

		adaugaIntersectie(inter1, 0);
		adaugaIntersectie(inter2, lungime);
	}

	/**
	 * Functie ce adauga o statie pe drumul curent
	 * 
	 * @param statie = statia ce trebuie adaugata
	 * @param distanta = distanta in metri la care trebuie adaugata statia pe drum
	 */
	public void adaugaStatie(Statie statie, int distanta) {
		this.noduri.add(new Pozitie(statie, distanta));
		statie.adaugaDrum(this);

		Collections.sort(noduri, new PozitieComparator());
	}

	/**
     * Functie ce adauga o intersectie pe drumul curent
     * 
     * @param intersectie = intersectia ce trebuie adaugata
     * @param distanta = distanta in metri la care trebuie adaugata intersectia pe drum
     */
	public void adaugaIntersectie(Intersectie intersectie, int distanta) {
		this.noduri.add(new Pozitie(intersectie, distanta));
		intersectie.adaugaDrum(this);

		Collections.sort(noduri, new PozitieComparator());
	}

	/**
	 * Getter pentru numele drumului
	 */
	public String getNume() {
		return nume;
	}

	/**
	 * Functie equals care verifica daca drumurile sunt aceleasi
	 */
	public boolean equals(Drum drum) {
		if (this.nume.equals(drum.getNume())) {
			return true;
		}

		return false;
	}

	/**
	 * Functie ce gaseste si returneaza un nod anume (intersectie sau statie) impreuna cu
	 * pozitia sa (distanta in metri) de pe drumul curent
	 */
	public Pozitie gasestePozitie(String numeNod) {
		for (int i = 0; i < noduri.size(); i++) {
			if (noduri.get(i).getNod().getNume().equals(numeNod)) {
				return noduri.get(i);
			}
		}

		return null;
	}

	/**
	 * Functie ce gaseste si returneaza un nod anume (intersectie sau statie) de pe drumul curent
	 */
	public Nod gasesteNod(String numeNod) {
		for (int i = 0; i < noduri.size(); i++) {
			if (noduri.get(i).getNod().getNume().equals(numeNod)) {
				return noduri.get(i).getNod();
			}
		}

		return null;
	}

	/**
	 * Functie ce returneaza distanta la care se afla un nod anume pe drumul curent
	 */
	public int getDistanta(Nod n) {
		return noduri.get(noduri.indexOf(gasestePozitie(n.getNume()))).getDistanta();
	}

	/**
	 * Getter pentru nodurile de pe drumul curent
	 */
	public List<Pozitie> getNoduri() {
		return noduri;
	}
}
