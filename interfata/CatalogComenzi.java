package interfata;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import transport.Drum;
import transport.Intersectie;
import transport.Oras;
import transport.Ruta;
import transport.Statie;
import transport.UnitateTransport;
import transport.UnitateTransportFactory;
import transport.VizualizeazaPath;

/**
 * Clasa ce contine implementarea tuturor comenzilor posibile din aplicatie
 * 
 * @author BOGDAN TEACA
 */
public class CatalogComenzi {
    /**
     * Mesaj de eroare care se afiseaza atunci cand comanda nu este valida
     */
    private final String comandaInvalida = "Eroare: Comanda nu este valida. Tastati 'help'"
            + " pentru a afisa toate comenzile";

    private Intersectie inters;
    private Intersectie inters2;
    private Drum drum;
    private Statie statie;
    private Ruta ruta;
    private UnitateTransport unitate;
    private int dist = 0;
    private String nume = "";

    /**
     * Clasa interna ce contine implementarea comenzilor generale din aplicatie
     */
    public class ComandaGeneral implements Comanda {
        @Override
        public void executaComanda(final List<String> param) {
            if (param.get(0).equals("help")) {
                if (param.size() == 1) {
                    AfisareHelp.afisareComenzi();
                } else if (param.size() == 2) {
                    switch (param.get(1)) {
                    case "g":
                        AfisareHelp.helpGeneral();
                        break;
                    case "i":
                        AfisareHelp.helpIntersectii();
                        break;
                    case "d":
                        AfisareHelp.helpDrumuri();
                        break;
                    case "s":
                        AfisareHelp.helpStatii();
                        break;
                    case "r":
                        AfisareHelp.helpRute();
                        break;
                    case "u":
                        AfisareHelp.helpUnitati();
                        break;
                    default:
                        System.out.println(comandaInvalida);
                        break;
                    }
                } else {
                    System.out.println(comandaInvalida);
                }
            } else if (param.get(0).equals("exit")) {
                if (param.size() == 1) {
                    System.exit(0);
                } else {
                    System.out.println(comandaInvalida);
                }
            } else if (param.get(0).equals("reset")) {
                if (param.size() == 1) {
                    TransportPublic.oras = new Oras();

                    System.out.println("Reteaua de transport public a fost resetata.");
                } else {
                    System.out.println(comandaInvalida);
                }
            } else if (param.get(0).equals("save")) {
                if (param.size() == 2) {
                    try {
                        FileOutputStream fisierOutput = new FileOutputStream(param.get(1));
                        ObjectOutputStream obiectOutput = new ObjectOutputStream(fisierOutput);

                        obiectOutput.writeObject(TransportPublic.oras);
                        obiectOutput.writeObject(Intersectie.lastId);

                        obiectOutput.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(comandaInvalida);
                }
            } else if (param.get(0).equals("load")) {
                if (param.size() == 2) {
                    try {
                        FileInputStream fisierInput = new FileInputStream(param.get(1));
                        ObjectInputStream obiectInput = new ObjectInputStream(fisierInput);

                        TransportPublic.oras = (Oras) obiectInput.readObject();
                        Intersectie.lastId = (int) obiectInput.readObject();

                        obiectInput.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(comandaInvalida);
                }
            }
        }
    }

    /**
     * Clasa interna ce contine implementarea comenzilor pentru intersectii
     */
    public class ComandaIntersectii implements Comanda {
        @Override
        public void executaComanda(final List<String> param) {
            if (param.get(0).equals("i")) {
                if (param.size() == 2) {
                    switch (param.get(1)) {
                    case "add":
                        inters = new Intersectie();
                        TransportPublic.oras.intersectii.add(inters);
                        System.out.println(inters.getNume());

                        break;
                    case "show":
                        if (TransportPublic.oras.intersectii.isEmpty()) {
                            System.out.println("- Inca nu exista nicio intersectie -");
                        } else {
                            for (Intersectie i : TransportPublic.oras.intersectii) {
                                System.out.println(i.getNume());
                            }
                        }

                        break;
                    default:
                        System.out.println(comandaInvalida);
                        break;
                    }
                } else if (param.size() == 4) {
                    if (param.get(1).equals("add")) {
                        drum = TransportPublic.oras.gasesteDrum(param.get(2));
                        dist = (isInteger(param.get(3)) ? Integer.parseInt(param.get(3)) : 0);

                        if (drum == null) {
                            System.out.println("Eroare: Drumul \"" + param.get(2) + "\" nu exista.");
                        } else if (dist <= 0) {
                            System.out.println("Eroare: Distanta trebuie sa fie un numar intreg pozitiv.");
                        } else {
                            inters = new Intersectie(drum, dist);
                            TransportPublic.oras.intersectii.add(inters);
                            VizualizeazaPath.vizualizeazaDrum(drum);
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else {
                    System.out.println(comandaInvalida);
                }
            }
        }
    }

    /**
     * Clasa interna ce contine implementarea comenzilor pentru drumuri
     */
    public class ComandaDrumuri implements Comanda {
        @Override
        public void executaComanda(final List<String> param) {
            if (param.get(0).equals("d")) {
                if (param.size() == 2) {
                    if (param.get(1).equals("show")) {
                        if (TransportPublic.oras.drumuri.isEmpty()) {
                            System.out.println("- Inca nu exista niciun drum -");
                        } else {
                            for (Drum d : TransportPublic.oras.drumuri) {
                                System.out.println(d.getNume());
                            }
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else if (param.size() == 3) {
                    if (param.get(1).equals("show")) {
                        drum = TransportPublic.oras.gasesteDrum(param.get(2));

                        if (drum == null) {
                            System.out.println("Eroare: Drumul \"" + param.get(2) + "\" nu exista.");
                        } else {
                            VizualizeazaPath.vizualizeazaDrum(drum);
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else if (param.size() == 6) {
                    if (param.get(1).equals("add")) {
                        nume = param.get(2);
                        drum = TransportPublic.oras.gasesteDrum(nume);
                        inters = TransportPublic.oras.gasesteIntersectie("(" + param.get(4) + ")");
                        inters2 = TransportPublic.oras.gasesteIntersectie("(" + param.get(5) + ")");
                        dist = (isInteger(param.get(3)) ? Integer.parseInt(param.get(3)) : 0);

                        if (drum != null) {
                            System.out.println("Eroare: Drumul \"" + param.get(3) + "\" exista deja.");
                        } else if (inters == null) {
                            System.out.println("Eroare: Intersectia (" + param.get(4) + ") nu exista.");
                        } else if (inters2 == null) {
                            System.out.println("Eroare: Intersectia (" + param.get(5) + ") nu exista.");
                        } else if (param.get(4).equals(param.get(5))) {
                            System.out.println("Eroare: Intersectiile nu trebuie sa fie identice.");
                        } else if (dist <= 0) {
                            System.out.println("Eroare: Distanta trebuie sa fie un numar intreg pozitiv.");
                        } else {
                            drum = new Drum(nume, dist, inters, inters2);
                            TransportPublic.oras.drumuri.add(drum);
                            VizualizeazaPath.vizualizeazaDrum(drum);
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                }
            }
        }
    }

    /**
     * Clasa interna ce contine implementarea comenzilor pentru statii
     */
    public class ComandaStatii implements Comanda {
        @Override
        public void executaComanda(List<String> param) {
            if(param.get(0).equals("s")){
                if (param.size() == 2) {
                    if (param.get(1).equals("show")) {
                        if (TransportPublic.oras.statii.isEmpty()) {
                            System.out.println("- Inca nu exista nicio statie -");
                        } else {
                            for (Statie s : TransportPublic.oras.statii) {
                                System.out.println(s.getNume());
                            }
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else if (param.size() == 5) {
                    if (param.get(1).equals("add")) {
                        nume = param.get(2);
                        statie = TransportPublic.oras.gasesteStatie(nume);
                        drum = TransportPublic.oras.gasesteDrum(param.get(3));
                        dist = (isInteger(param.get(4)) ? Integer.parseInt(param.get(4)) : 0);

                        if (statie != null) {
                            System.out.println("Eroare: Statia \"" + param.get(2) + ") exista deja.");
                        } else if (drum == null) {
                            System.out.println("Eroare: Drumul \"" + param.get(3) + "\" nu exista.");
                        } else if (dist <= 0) {
                            System.out.println("Eroare: Distanta trebuie sa fie un numar intreg pozitiv.");
                        } else {
                            statie = new Statie(nume, drum, dist);
                            TransportPublic.oras.statii.add(statie);
                            VizualizeazaPath.vizualizeazaDrum(drum);
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else {
                    System.out.println(comandaInvalida);
                }
            }
        }
    }

    /**
     * Clasa interna ce contine implementarea comenzilor pentru rute
     */
    public class ComandaRute implements Comanda {
        @Override
        public void executaComanda(final List<String> param) {
            if (param.get(0).equals("r")) {
                if (param.size() == 2) {
                    if (param.get(1).equals("show")) {
                        if (TransportPublic.oras.rute.isEmpty()) {
                            System.out.println("- Inca nu exista nicio ruta -");
                        } else {
                            for (Ruta r : TransportPublic.oras.rute) {
                                System.out.println(r.getNume());
                            }
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else if (param.size() == 3) {
                    if (param.get(1).equals("show")) {
                        ruta = TransportPublic.oras.gasesteRuta(param.get(2));

                        if (ruta == null) {
                            System.out.println("Eroare: Ruta \"" + param.get(2) + "\" nu exista.");
                        } else {
                            VizualizeazaPath.vizualizeazaRuta(ruta);
                        }
                    } else if (param.get(1).equals("zoom")) {
                        ruta = TransportPublic.oras.gasesteRuta(param.get(2));

                        if (ruta == null) {
                            System.out.println("Eroare: Ruta \"" + param.get(2) + "\" nu exista.");
                        } else {
                            VizualizeazaPath.vizualizeazaCale(ruta);
                        }
                    } else if (param.get(1).equals("orar")) {
                        ruta = TransportPublic.oras.gasesteRuta(param.get(2));

                        if (ruta == null) {
                            System.out.println("Eroare: Ruta \"" + param.get(2) + "\" nu exista.");
                        } else {
                            ruta.afiseazaOrar();
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else if (param.size() == 4) {
                    if (param.get(1).equals("give")) {
                        ruta = TransportPublic.oras.gasesteRuta(param.get(2));
                        unitate = TransportPublic.oras.gasesteUnitate(param.get(3));

                        if (ruta == null) {
                            System.out.println("Eroare: Ruta nu exista.");
                        } else if (unitate == null) {
                            System.out.println("Eroare: Unitatea de transport nu exista.");
                        } else {
                            ruta.adaugaUnitate(unitate);
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else if (param.size() > 5) {
                    if (param.get(1).equals("add")) {
                        Statie s = TransportPublic.oras.gasesteStatie(param.get(4));

                        ruta = new Ruta(param, s);

                        if (ruta.isNull == false) {
                            TransportPublic.oras.rute.add(ruta);
                            VizualizeazaPath.vizualizeazaRuta(ruta);
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else {
                    System.out.println(comandaInvalida);
                }
            }
        }
    }

    /**
     * Clasa interna ce contine implementarea comenzilor pentru unitatile de transport
     */
    public class ComandaUnitati implements Comanda {
        @Override
        public void executaComanda(final List<String> param) {
            if (param.get(0).equals("u")) {
                if (param.size() == 2) {
                    if (param.get(1).equals("show")) {
                        if (TransportPublic.oras.unitati.isEmpty()) {
                            System.out.println("- Inca nu exista nicio unitate de transport -");
                        } else {
                            for (UnitateTransport u : TransportPublic.oras.unitati) {
                                System.out.println(u.printInfo());
                            }
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else if (param.size() == 3) {
                    if (param.get(1).equals("orar")) {
                        unitate = TransportPublic.oras.gasesteUnitate(param.get(2));

                        if (unitate == null) {
                            System.out.println("Eroare: Unitatea " + param.get(2) + " nu exista.");
                        } else {
                            unitate.afiseazaOrar();
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else if (param.size() == 4) {
                    if (param.get(1).equals("add")) {
                        if (param.get(2).equals("a") || param.get(2).equals("t") || param.get(2).equals("v")) {
                            unitate = UnitateTransportFactory.getUnitateTransport(param.get(2), param.get(3));

                            TransportPublic.oras.unitati.add(unitate);

                            System.out.println(unitate.getNume());
                        } else {
                            System.out.println("Eroare: Tipul de transport " + param.get(2) + " nu exista.");
                        }
                    } else {
                        System.out.println(comandaInvalida);
                    }
                } else {
                    System.out.println(comandaInvalida);
                }
            }
        }
    }

    /**
     * Clasa interna ce se ocupa cu afisarea unui mesaj de eroare daca familia de
     * comenzi nu este valida
     */
    public class ComandaGresita implements Comanda {
        @Override
        public void executaComanda(final List<String> param) {
            if (!param.get(0).equals("help") && !param.get(0).equals("exit")
                && !param.get(0).equals("save") && !param.get(0).equals("load")
                && !param.get(0).equals("reset") && !param.get(0).equals("i")
                && !param.get(0).equals("d") && !param.get(0).equals("s")
                && !param.get(0).equals("r") && !param.get(0).equals("u")) {

                System.out.println(comandaInvalida);
            }
        }
    }

    /**
     * Verifica daca String-ul poate fi transformat in intreg
     * 
     * @param s = string-ul ce trebuie verificat
     * @return = daca String-ul poate fi transformat in intreg 
     */
    private boolean isInteger(final String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }

        return true;
    }
}
