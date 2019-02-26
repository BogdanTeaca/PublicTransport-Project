package transport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utile.Ora;

/**
 * Clasa abstracta ce implementeaza functionalitatea unei unitati de transport.
 * Unei unitati de transport ii este asociat un orar, nume, ruta si viteza, insa
 * valoarea vitezei ramane a fi stabilita de subclase
 * 
 * @author BOGDAN TEACA
 */
public abstract class UnitateTransport implements Serializable{
    /**
     * Clasa interna utila in crearea orarului unei unitati de transport: asociaza
     * unei statii o lisa de ore
     */
    protected class ElementOrarUnitate implements Serializable {
        private List<Ora> ore;
        private Nod statie;

        /**
         * Constructor ce initializeaza statia si lista de ore
         */
        public ElementOrarUnitate(final Nod statie) {
            this.ore = new ArrayList<Ora>();
            this.statie = statie;
        }

        /**
         * Metoda ce adauga o ora la lista de ore a statiei
         */
        public void adaugaOra(final Ora ora) {
            this.ore.add(ora);
        }

        /**
         * Getter pentru statie
         */
        public Nod getStatie() {
            return statie;
        }

        /**
         * Getter pentru lista de ore
         */
        public List<Ora> getOre() {
            return ore;
        }
    }

    protected UnitateTransportTip tip;
	protected int viteza;
	private Ruta ruta;
	private Ora oraIncepereProgram;
	private List<ElementOrarUnitate> orarDus;
	private List<ElementOrarUnitate> orarIntors;
	private String nume;

	/**
	 * Constructor ce initializeaza numele unitatii de transport
	 */
	public UnitateTransport(String nume){
	    this.nume = nume;
    }

	/**
	 * Metoda pentru adaugarea unei rute la unitatea de transport
	 */
	public void adaugaLaRuta(Ruta ruta) {
	    if (ruta.getTip().equals(tip)) {
	        this.ruta = ruta;
	        updateazaOrar();
	    } else {
	        System.out.println("Eroare: Ruta si unitatea de transport trebuie sa fie de "
	                + "acelasi tip.");
	    }
	}

	/**
	 * Metoda pentru calcularea orei de plecare a unitatii de transport
	 */
	public void calculeazaPlecare() {
	    for (int i = 0; i < ruta.getUnitati().size(); i++) {
	        if (this.equals(ruta.getUnitati().get(i))) {
                oraIncepereProgram = new Ora(Oras.oraIncepereProgram.toUnix() + i * ruta.getIntervalPlecare());
                return;
            }
	    }
	}

	/**
	 * Metoda pentru actualizarea orarului unitatii de transport
	 */
	public void updateazaOrar() {
	    calculeazaPlecare();
        this.orarDus = calculeazaOrar(Directie.dus);
        this.orarIntors = calculeazaOrar(Directie.intors);
	}

	/**
	 * Metoda pentru calcularea orarului unitatii de transport in directia "dir"
	 * 
	 * @param dir = Directia rutei (directia dus sau directia intors)
	 */
	public List<ElementOrarUnitate> calculeazaOrar(Directie dir) {
	    List<ElementOrarUnitate> orarNou = new ArrayList<ElementOrarUnitate>();

	    for (int i = 0; i < ruta.getStatii().size(); i++) {
	        Moment s = ruta.getStatii().get(i);
	        ElementOrarUnitate elemNou = new ElementOrarUnitate(s.getNod());

	        int cursa = 1;
	        int oraCurenta = 0;
	        while (oraCurenta < Oras.oraIncheiereProgram.toUnix()) {
	            if (dir.equals(Directie.dus)) {
	                oraCurenta = oraIncepereProgram.toUnix() + ruta.getDurataDusIntors() * (cursa - 1)
	                        + s.getDurataDus();
	            } else if (dir.equals(Directie.intors)) {
	                oraCurenta = oraIncepereProgram.toUnix() + ruta.getDurataDusIntors() * (cursa - 1)
                            + s.getDurataIntors() + ruta.getDurataDusIntors() / 2;
	            }

	            Ora ora = new Ora(oraCurenta);
                elemNou.adaugaOra(ora);
	            cursa++;
	        }

	        orarNou.add(elemNou);
        }

	    return orarNou;
	}

	/**
	 * Getter pentru numele unitatii de transport
	 */
	public String getNume() {
	    return nume;
	}

	/**
	 * Getter pentru orarul in directia dus a unitatii de transport
	 */
	public List<ElementOrarUnitate> getOrarDus() {
	    return orarDus;
	}

	/**
     * Getter pentru orarul in directia intors a unitatii de transport
     */
	public List<ElementOrarUnitate> getOrarIntors() {
        return orarIntors;
    }

	/**
	 * Metoda pentru afisarea informatiilor despre unitatea de transport. Metoda utila pentru
	 * afisarea tuturor unitatilor de transport impreuna cu informatii despre acestea.
	 */
	public String printInfo() {
	    if (this.ruta == null) {
	        return nume + "\t\t" + tip + "\t\t-fara ruta-";
	    } else {
	        return nume + "\t\t" + tip + "\t\t" + ruta.getNume();
	    }
	}

	/**
	 * Metoda pentru afisarea orarului unitatii de transport
	 */
	public void afiseazaOrar() {
	    String numeStatie1 = ruta.getStatii().get(0).getNod().getNume();
	    String numeStatie2 = ruta.getStatii().get(ruta.getStatii().size() - 1).getNod().getNume();

	    System.out.println("Orar pentru directia " + numeStatie1 + " -> " + numeStatie2 + ":");

	    for (ElementOrarUnitate e : orarDus) {
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

	    for (ElementOrarUnitate e : orarIntors) {
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
