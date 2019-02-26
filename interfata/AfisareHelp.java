package interfata;

/**
 * Clasa ce contine informatii si instructiuni pentru comenzile ce pot fi executate in aplicatie
 * 
 * @author BOGDAN TEACA
 */
public class AfisareHelp {
    /**
     * Afiseaza toate comenzile posibile si instructiunile acestora
     */
    public static void afisareComenzi() {
        helpGeneral();
        helpIntersectii();
        helpDrumuri();
        helpStatii();
        helpRute();
        helpUnitati();
    }

    /**
     * Afiseaza comenzile de help, save, load, exit si reset
     */
    public static void helpGeneral() {
        System.out.println("Comenzi Generale");
        System.out.println("   <help>");
        System.out.println("      Functie: Afiseaza toate comenzile posibile si instructiuni de folosire ale acestora.");
        System.out.println("   <help g>");
        System.out.println("      Functie: Afiseaza comenzile generale si instructiunile lor de folosire.");
        System.out.println("   <help i>");
        System.out.println("      Functie: Afiseaza comenzile pentru intersectii si instructiunile lor de folosire.");
        System.out.println("   <help d>");
        System.out.println("      Functie: Afiseaza comenzile pentru drumuri si instructiunile lor de folosire.");
        System.out.println("   <help s>");
        System.out.println("      Functie: Afiseaza comenzile pentru statii si instructiunile lor de folosire.");
        System.out.println("   <help r>");
        System.out.println("      Functie: Afiseaza comenzile pentru rute si instructiunile lor de folosire.");
        System.out.println("   <help u>");
        System.out.println("      Functie: Afiseaza comenzile pentru unitati de transport si instructiunile lor de folosire.");
        System.out.println("   <exit>");
        System.out.println("      Functie: Incheie programul.");
        System.out.println("   <reset>");
        System.out.println("      Functie: Creaza o retea de transport public nou.");
        System.out.println("   <save \"nume_fisier\">");
        System.out.println("      Functie: Salveaza reteaua de transport public curenta intr-un fisier.");
        System.out.println("   <load \"nume_fisier\">");
        System.out.println("      Functie: Incarca o retea de transport public dintr-un fisier.");
        System.out.println();
    }

    /**
     * Afiseaza comenzile pentru intersectii
     */
    public static void helpIntersectii() {
        System.out.println("Comenzi pentru Intersectii");
        System.out.println("   <i add>");
        System.out.println("      Functie: Creaza o intersectie noua ce nu apartine de niciun drum.");
        System.out.println("   <i add \"nume_drum\" distanta>");
        System.out.println("      Functie: Creaza o intersectie noua pe un drum la o anumita distanta.");
        System.out.println("      Exemplu: i add \"Str. Eminescu\" 40");
        System.out.println("   <i show>");
        System.out.println("      Functie: Afiseaza toate intersectiile.");
        System.out.println();
    }

    /**
     * Afiseaza comenzile pentru drumuri
     */
    public static void helpDrumuri() {
        System.out.println("Comenzi pentru Drumuri");
        System.out.println("   <d add \"nume_drum\" lungime id_inters1 id_inters2>");
        System.out.println("      Functie: Construieste un drum nou intre doua intersectii.");
        System.out.println("      Exemplu: d add \"Str. Eminescu\" 150 17 25");
        System.out.println("   <d show>");
        System.out.println("   <d show \"nume_drum\">");
        System.out.println();
    }

    /**
     * Afiseaza comenzile pentru statii
     */
    public static void helpStatii() {
        System.out.println("Comenzi pentru Statii");
        System.out.println("   <s add \"nume_statie\" \"nume_drum\" distanta>");
        System.out.println("      Functie: Creaza o statie noua pe un drum la o anumita distanta.");
        System.out.println("      Exemplu: s add \"Preciziei\" \"Str. Eminescu\" 70");
        System.out.println("   <s show>");
        System.out.println("      Functie: Afiseaza toate statiile.");
        System.out.println();
    }

    /**
     * Afiseaza comenzile pentru rute
     */
    public static void helpRute() {
        System.out.println("Comenzi pentru Rute");
        System.out.println("   <r add tip_transport \"nume_ruta\" [parametri]>");
        System.out.println("      Functie: Creaza o ruta noua care trece prin nodurile precizate in parametri.");
        System.out.println("      [tip_transport]: a = autobuz, t = troleibuz, v = tramvai");
        System.out.println("      [parametri]: Primul parametru: numele primei statii");
        System.out.println("      [parametri]: Dupa un parametru statie, este obligatoriu numele unei statii"
                + " sau intersectii care se afla pe acelasi drum.");
        System.out.println("      [parametri]: Dupa un parametru intersectie, este obligatoriu numele unui drum"
                + " care este legat de intersectia precedenta.");
        System.out.println("      [parametri]: Dupa un parametru drum, este obligatoriu numele unei statii"
                + " sau intersectii care se afla pe drumul respectiv.");
        System.out.println("      [parametri]: Ultimul parametru: numele ultimei statii");
        System.out.println("      Exemplu: r add a r311 Preciziei 2 \"Str. Eminescu\" \"Piata Unirii\"");
        System.out.println("   <r show>");
        System.out.println("      Functie: Afiseaza toate rutele.");
        System.out.println("   <r show \"nume_ruta\">");
        System.out.println("      Functie: Afiseaza reprezentarea grafica, doar cu statii, a rutei.");
        System.out.println("   <r zoom \"nume_ruta\">");
        System.out.println("      Functie: Afiseaza reprezentarea grafica in detaliu a rutei.");
        System.out.println("   <r orar \"nume_ruta\">");
        System.out.println("      Functie: Afiseaza orarul rutei respective.");
        System.out.println("   <r give \"nume_ruta\" \"nume_unitate\">");
        System.out.println("      Functie: Transfera o unitate de transport la ruta respectiva.");
        System.out.println();
    }

    /**
     * Afiseaza comenzile pentru unitati
     */
    public static void helpUnitati() {
        System.out.println("Comenzi pentru Unitati de Transport");
        System.out.println("   <u add tip_transport \"nume_unitate\"");
        System.out.println("      Functie: Creaza o unitate de transport noua");
        System.out.println("      [tip_transport]: a = autobuz, t = troleibuz, v = tramvai");
        System.out.println("   <u add tip_transport \"nume_unitate\"");
        System.out.println("      Functie: Creaza o unitate de transport noua");
        System.out.println("   <u orar \"nume_unitate\">");
        System.out.println("      Functie: Afiseaza orarul unitatii de transport respective.");
        System.out.println();
    }
}
