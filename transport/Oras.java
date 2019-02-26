package transport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utile.Ora;

/**
 * Orasul (sau Reteaua de transport public propriu-zisa) care retine toate intersectiile,
 * statiile, drumurile, rutele si unitatile de transport
 * 
 * @author BOGDAN TEACA
 */
public class Oras implements Serializable {
	public List<Nod> noduri;
	public List<Intersectie> intersectii;
	public List<Drum> drumuri;
	public List<Statie> statii;
	public List<Ruta> rute;
	public List<UnitateTransport> unitati;
	public static Ora oraIncepereProgram; // ora de incepere a programului de transport public
	public static Ora oraIncheiereProgram; // ora de incheiere a programului de transport public

	/**
	 * Constructor pentru oras care initializeaza toate componentele acesteia
	 */
	public Oras() {
	    noduri = new ArrayList<Nod>();
	    intersectii = new ArrayList<Intersectie>();
	    drumuri = new ArrayList<Drum>();
	    statii = new ArrayList<Statie>();
	    rute = new ArrayList<Ruta>();
	    unitati = new ArrayList<UnitateTransport>();
	    oraIncepereProgram = new Ora(6, 0);
	    oraIncheiereProgram = new Ora(23, 0);

	    Intersectie.resetLastId();
	}

	/**
	 * Functie care gaseste si returneaza o intersectie din reteaua de transport public
	 */
	public Intersectie gasesteIntersectie(String nume) {
		for (int i = 0; i < intersectii.size(); i++) {
			if (intersectii.get(i).getNume().equals(nume)) {
				return intersectii.get(i);
			}
		}

		return null;
	}

	/**
     * Functie care gaseste si returneaza un drum din reteaua de transport public
     */
	public Drum gasesteDrum(String nume) {
		for (int i = 0; i < drumuri.size(); i++) {
			if (drumuri.get(i).getNume().equals(nume)) {
				return drumuri.get(i);
			}
		}

		return null;
	}

	/**
     * Functie care gaseste si returneaza o statie din reteaua de transport public
     */
	public Statie gasesteStatie(String nume) {
		for (int i = 0; i < statii.size(); i++) {
			if (statii.get(i).getNume().equals(nume)) {
				return statii.get(i);
			}
		}

		return null;
	}

	/**
     * Functie care gaseste si returneaza o ruta din reteaua de transport public
     */
	public Ruta gasesteRuta(String nume) {
		for (int i = 0; i < rute.size(); i++) {
			if (rute.get(i).getNume().equals(nume)) {
				return rute.get(i);
			}
		}

		return null;
	}

	/**
     * Functie care gaseste si returneaza o unitate de transport din reteaua de transport public
     */
	public UnitateTransport gasesteUnitate(String nume) {
        for (int i = 0; i < unitati.size(); i++) {
            if (unitati.get(i).getNume().equals(nume)) {
                return unitati.get(i);
            }
        }

        return null;
    }
}
