package interfata;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import transport.Oras;

/**
 * Clasa principala ce se ocupa cu gestionarea comenzilor si a retelei de transport public
 * 
 * @author BOGDAN TEACA
 */
public class TransportPublic {
    public static Oras oras;
    private Scanner scanner;
    private List<String> param;
    private CatalogComenzi catalog;
    private List<Comanda> comenzi = new ArrayList<Comanda>();

    /**
     * Constructor ce initializeaza orasul (reteaua de transport public propriu-zisa),
     * adauga toate comenzile posibile in lista de comenzi si incepe procesul de citire
     * a comenzilor de la utilizator
     */
    public TransportPublic() {
        TransportPublic.oras = new Oras();
        this.scanner = new Scanner(System.in);
        this.param = new ArrayList<String>();

        catalog = new CatalogComenzi();

        comenzi.add(catalog.new ComandaGeneral());
        comenzi.add(catalog.new ComandaIntersectii());
        comenzi.add(catalog.new ComandaDrumuri());
        comenzi.add(catalog.new ComandaStatii());
        comenzi.add(catalog.new ComandaRute());
        comenzi.add(catalog.new ComandaUnitati());
        comenzi.add(catalog.new ComandaGresita());

        System.out.println("Info: Interactiunea cu reteaua de transport public este realizata\n"
                + "cu ajutorul comenzilor. Tastati 'help' pentru a afisa toate comenzile.");
        System.out.println();
        
        gestionareInput();
    }

    /**
     * Functie recursiva care face posibil ciclul "citeste comanda" - "gestioneaza comanda" de
     * pe parcursul rularii aplicatiei
     */
    public void gestionareInput() {
        param = citesteComanda();

        gestionareComanda(param);

        gestionareInput();
    }

    /**
     * Functie care citeste de la tastatura comanda, si o desparte intr-o lista de String-uri
     * pentru a putea fi usor de gestionat
     * 
     * @return = Parametrii comenzii (lista de string-uri din care este alcatuita comanda)
     */
    private List<String> citesteComanda() {
        System.out.print("> COMANDA: ");
        String input = scanner.nextLine().toLowerCase();
        
        // RegEx pentru a desparte comanda in String-uri (parametri) dupa spatii. Ce se afla in interiorul
        // ghilimelelor este luat ca un parametru in sine (chiar daca exista spatii in interiorul ghilimelelor)
        List<String> param = Arrays.asList(input.split("\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?"));
        
        System.out.println();

        return param;
    }

    /**
     * Functie ce executa comanda ce tocmai a fost citita de la tastatura
     * @param param = Parametrii comenzii
     */
    private void gestionareComanda(final List<String> param) {
        for (Comanda c : comenzi) {
            c.executaComanda(param);
        }

        System.out.println();
    }

    /**
     * Functie ce adauga o comanda in lista de comenzi posibile din aplicatie
     * 
     * @param com = Comanda ce trebuie adaugata in lista
     */
    public void adaugaComanda(final Comanda com) {
        comenzi.add(com);
    }

    /**
     * Functia Main a aplicatiei
     */
    public static void main(final String[] args) {
        new TransportPublic();
    }
}
