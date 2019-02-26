package transport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import utile.Ora;

/**
 * Clasa pentru implementarea rutei din reteaua de transport public.
 * 
 * @author BOGDAN TEACA
 */
public class Ruta implements Serializable {
    /**
     * Clasa interna privata utila in crearea orarului unei rute: asociaza unei statii
     * o multime ordonata de ore
     */
    private class ElementOrarRuta implements Serializable {
        private class ElementOrarComparator implements Comparator<Ora>, Serializable {
            public int compare(Ora ora1, Ora ora2) {
                return ora2.compareTo(ora1);
            }
        }

        private SortedSet<Ora> ore = new TreeSet<Ora>(new ElementOrarComparator());
        private Nod statie;

        /**
         * Constructor ce initializeaza statia si multimea sortata de ore folosind
         * comparatorul ElementOrarComparator
         */
        public ElementOrarRuta(final Nod statie) {
            this.ore = new TreeSet<Ora>(new ElementOrarComparator());
            this.statie = statie;
        }

        /**
         * Metoda pentru adaugarea unei ore la lista de ore a statiei
         */
        public void adaugaOra(Ora ora) {
            this.ore.add(ora);
        }

        /**
         * Getter pentru statie
         */
        public Nod getStatie() {
            return statie;
        }

        /**
         * Getter pentru multimea sortata de ore
         */
        public SortedSet<Ora> getOre() {
            return ore;
        }
    }

    /**
     * Variabila "isNull" folosita in cazul in care unul dintre parametri rutei nu este
     * valid in scopul de a se cunoaste in exteriorul clasei pe baza variabilei "isNull"
     * daca obiectul a fost creat cu succes sau nu
     */
	public boolean isNull = false;
	
	private UnitateTransportTip tip;
	private String nume;
	private List<Pozitie> noduri = new ArrayList<Pozitie>();
	private List<Moment> statii = new ArrayList<Moment>();
	private List<Drum> drumuri = new ArrayList<Drum>();
	private List<ElementOrarRuta> orarDus = new ArrayList<ElementOrarRuta>();
	private List<ElementOrarRuta> orarIntors = new ArrayList<ElementOrarRuta>();
	private List<UnitateTransport> unitati = new ArrayList<UnitateTransport>();
	private int distantaCurenta = 0;
	private int durataCurenta = 0;
	private int vitezaUnitateTransport;
	private int timpAsteptareInStatie = 20;
	private int durataDusIntors;
	private int intervalPlecare;

	/**
	 * Constructor ce initializeaza componentele rutei
	 * 
	 * @param param = Parametri ce trebuie verificati
	 * @param s = statia de inceput a rutei
	 */
	public Ruta(final List<String> param, final Statie s) {
		if (param.get(2).equals("a")) {
			tip = UnitateTransportTip.autobuz;
			vitezaUnitateTransport = 10;
		} else if (param.get(2).equals("t")) {
			tip = UnitateTransportTip.troleibuz;
			vitezaUnitateTransport = 15;
		} else if (param.get(2).equals("v")) {
		    tip = UnitateTransportTip.tramvai;
		    vitezaUnitateTransport = 20;
		} else {
			System.out.println("Eroare: Tipul de transport " + param.get(2) + " nu exista.");
			setIsNull();
		}

		this.nume = param.get(3);

		// numarul de parametri trebuie sa fie mai mare decat 5 (minim 2 statii)
		if (param.size() > 5) {
			boolean succes = creazaRutaVerificaStart(param, 4, s);

			this.durataDusIntors = statii.get(statii.size() - 1).getDurataDus() * 2;

			statii.get(statii.size() - 1).setDurataIntors(0);
			int durata = 0;

			for (int i = statii.size() - 1; i > 0; i--) {
			    durata += statii.get(i).getDurataDus() - statii.get(i - 1).getDurataDus();

			    statii.get(i - 1).setDurataIntors(durata);
			}
		} else {
			System.out.println("Eroare: Numarul de parametri este prea mic.");
			setIsNull();
		}
	}

	/**
	 * Metoda pentru initializarea primei statii in ruta
	 */
	private boolean creazaRutaVerificaStart(List<String> param, int index, Statie s) {
		Drum d = null;

		if (s == null) {
			System.out.println("Eroare: Ruta trebuie sa inceapa cu o statie.");
			setIsNull();
			return false;
		} else {
			this.statii.add(new Moment(s, 0));
			this.noduri.add(new Pozitie(s, 0));
			d = s.getDrumProprietar();
			this.drumuri.add(d);

			if (param.size() > index + 1) {
				return creazaRutaVerificaNod(param, index + 1, d);
			} else {
				System.out.println("Eroare: Ruta trebuie sa se termine cu o statie.");
				setIsNull();
				return false;
			}
		}
	}

	/**
	 * Metoda pentru initializarea unui nod (statie sau intersectie) din ruta
	 */
	private boolean creazaRutaVerificaNod(List<String> param, int index, Drum d) {
		Nod s = d.gasesteNod(param.get(index));

		boolean firstTry = true;

		// se verifica daca nodul este o statie
		if (s == null) {
			firstTry = false;
		} else {
			distantaCurenta += Math.abs(d.getDistanta(s) - d.getDistanta(noduri.get(noduri.size() - 1).getNod()));
			durataCurenta = distantaCurenta / vitezaUnitateTransport + timpAsteptareInStatie * statii.size();
			this.statii.add(new Moment(s, durataCurenta));
			this.noduri.add(new Pozitie(s, distantaCurenta));

			this.drumuri.add(d);

			if (param.size() > index + 1) {
				return creazaRutaVerificaNod(param, index + 1, d);
			} else {
				return true;
			}
		}

		// daca nu este o statie, se verifica daca nodul este o intersectie
		if (firstTry == false) {
			Nod i = d.gasesteNod("(" + param.get(index) + ")");

			if (i == null) {
				System.out.println("Eroare: Intersectia sau statia \"" + param.get(index)
						+ "\" nu exista sau nu este conectata la ruta.");
				setIsNull();
				return false;
			} else {
				distantaCurenta += Math.abs(d.getDistanta(i) - d.getDistanta(noduri.get(noduri.size() - 1).getNod()));
				this.noduri.add(new Pozitie(i, distantaCurenta));

				if (param.size() > index + 1) {
					return creazaRutaVerificaDrum(param, index + 1, i);
				} else {
					System.out.println("Eroare: Ruta trebuie sa se termine cu o statie.");
					setIsNull();
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Metoda pentru initializarea unui drum din ruta
	 */
	private boolean creazaRutaVerificaDrum(List<String> param, int index, Nod i) {
		Drum d = null;

		d = i.gasesteDrum(param.get(index));

		if (d == null) {
			System.out.println("Eroare: Drumul \"" + param.get(index)
			+ "\" nu exista sau nu este conectat la ruta.");
			setIsNull();
			return false;
		} else {
			this.drumuri.add(d);

			if (param.size() > index + 1) {
				return creazaRutaVerificaNod(param, index + 1, d);
			} else {
				System.out.println("Eroare: Ruta trebuie sa se termine cu o statie.");
				setIsNull();
				return false;
			}
		}
	}

	/**
	 * Getter pentru drumurile din ruta
	 */
	public List<Drum> getDrumuri() {
		return drumuri;
	}

	/**
	 * Getter pentru numele rutei
	 */
	public String getNume() {
		return nume;
	}

	/**
	 * Metoda pentru adaugarea unei unitati de transport la ruta
	 */
	public void adaugaUnitate(UnitateTransport unitate) {
	    if (unitate.tip.equals(this.tip)) {
	        int intervalPlecare;

	        intervalPlecare = calcIntervalPlecare(unitati.size() + 1);

            this.unitati.add(unitate);
            this.intervalPlecare = intervalPlecare;

            unitate.adaugaLaRuta(this);

            for (UnitateTransport u : unitati) {
                u.updateazaOrar();
            }

            orarDus = calculeazaOrar(Directie.dus);
            orarIntors = calculeazaOrar(Directie.intors);
	    } else {
	        System.out.println("Eroare: Ruta si unitatea de transport trebuie sa fie de acelasi tip.");
	    }
	}

	/**
	 * Metoda pentru calcularea intervalului ideal de plecare dintre doua unitati de
	 * transport de pe ruta: unitatile de transport trebuie sa fie la distante (si
	 * intervale de timp) egale unul de celealalt
	 */
    public int calcIntervalPlecare(int nrUnitati) {
        return this.durataDusIntors / nrUnitati;
    }
    
    /**
     * Getter pentru unitatile de transport ale rutei
     */
	public List<UnitateTransport> getUnitati() {
	    return unitati;
	}

	/**
	 * Getter pentru intervalul de plecare intre doua unitati de transport
	 */
	public int getIntervalPlecare() {
	    return intervalPlecare;
	}

	/**
	 * Getter pentru durata dus-intors a rutei
	 */
	public int getDurataDusIntors() {
	    return durataDusIntors;
	}

	/**
	 * Getter pentru viteza unei unitati de transport de pe ruta
	 */
	public int getViteza() {
		return vitezaUnitateTransport;
	}

	/**
	 * Getter pentru timpul de asteptare a unei unitati de transport intr-o statie
	 */
	public int getAsteptare() {
		return timpAsteptareInStatie;
	}

	/**
	 * Metoda ce seteaza valoarea campului "isNull" la true in cazul in care unul dintre
	 * parametri nu este valid in scopul de a se cunoaste in exteriorul clasei pe baza
	 * variabilei "isNull" daca obiectul a fost creat cu succes sau nu
	 */
	private void setIsNull() {
		try {
			throw new IllegalArgumentException();
		} catch (IllegalArgumentException e) {
			isNull = true;
		}
	}

	/**
	 * Getter pentru nodurile rutei
	 */
	public List<Pozitie> getNoduri() {
		return noduri;
	}

	/**
	 * Getter pentru statiile rutei
	 */
	public List<Moment> getStatii() {
		return statii;
	}

	/**
	 * Getter pentru tipul de transport al rutei
	 */
	public UnitateTransportTip getTip() {
		return tip;
	}

	/**
	 * Metoda ce calculeaza orarul rutei
	 */
	public List<ElementOrarRuta> calculeazaOrar(final Directie dir) {
	    List<ElementOrarRuta> orarNou = new ArrayList<ElementOrarRuta>();

	    for (Moment s : statii) {
	        orarNou.add(new ElementOrarRuta(s.getNod()));
	    }

        for (int i = 0; i < unitati.size(); i++) {
            UnitateTransport u = unitati.get(i);

            for (int j = 0; j < u.getOrarDus().size(); j++) {
                if (dir.equals(Directie.dus)) {
                    for (Ora o : u.getOrarDus().get(j).getOre()) {
                        orarNou.get(j).adaugaOra(o);
                    }
                } else if (dir.equals(Directie.intors)) {
                    for (Ora o : u.getOrarIntors().get(j).getOre()) {
                        orarNou.get(j).adaugaOra(o);
                    }
                }
            }
        }

        return orarNou;
	}

	/**
	 * Metoda ce afiseaza orarul rutei
	 */
	public void afiseazaOrar() {
        String numeStatie1 = statii.get(0).getNod().getNume();
        String numeStatie2 = statii.get(statii.size() - 1).getNod().getNume();
        
        System.out.println("Orar pentru directia " + numeStatie1 + " -> " + numeStatie2 + ":");

        for (ElementOrarRuta e : orarDus) {
            System.out.println("Statia " + e.getStatie().getNume() + ":");

            int i = 0;
            for (Ora o : e.getOre()) {
                if (i != 0) {
                    System.out.print(", ");

                    if (i % 15 == 0) {
                        System.out.println();
                    }
                }

                System.out.print(o.getOraToString());

                i++;
            }

            System.out.println();
        }

        System.out.println();
        System.out.println("Orar pentru directia " + numeStatie2 + " -> " + numeStatie1 + ":");

        for (ElementOrarRuta e : orarIntors) {
            System.out.println("Statia " + e.getStatie().getNume() + ":");

            int i = 0;
            for (Ora o : e.getOre()) {
                if (i != 0) {
                    System.out.print(", ");

                    if (i % 15 == 0) {
                        System.out.println();
                    }
                }

                System.out.print(o.getOraToString());

                i++;
            }

            System.out.println();
        }
    }
}
