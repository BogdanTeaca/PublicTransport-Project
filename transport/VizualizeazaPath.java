package transport;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import utile.Time;

/**
 * Clasa ce cuprinde functii pentru reprezentarea grafica a drumurilor si rutelor
 * 
 * @author BOGDAN TEACA
 */
public class VizualizeazaPath {
    /**
     * Functie pentru reprezentarea grafica a unui drum
     */
	public static void vizualizeazaDrum(Drum drum) {
		List<Pozitie> noduri = drum.getNoduri();

		for (Pozitie n : noduri) {
			System.out.print(vizualizareAux(n.getNod().getNume(), " ", 10));
		}

		System.out.println();
		printeazaRepetaCaracter(" ", 9);
		boolean first = true;

		for (Pozitie n : noduri) {
			String simbol = n.getNod().getSimbol();

			if (first == false) {
				printeazaRepetaCaracter("-", 18);
			}

			System.out.print(simbol);
			first = false;
		}

		System.out.println("     <-->    " + drum.getNume());
		printeazaRepetaCaracter(" ", 5);

		for (int i = 0; i < noduri.size(); i++) {
			String distanta = "[" + noduri.get(i).getDistanta() + "m]";
			System.out.print(vizualizareAux(distanta, " ", 5) + " ");

			if (i < noduri.size() - 1) {
				Pozitie next = noduri.get(i + 1);
				distanta = (next.getDistanta() - noduri.get(i).getDistanta()) + "m";
				System.out.print(vizualizareAux(distanta, " ", 4));
			}
		}

		System.out.println();

		List<ArrayDeque<Drum>> coadaDrumuri = new ArrayList<ArrayDeque<Drum>>();

		for (int i = 0; i < noduri.size(); i++) {
			coadaDrumuri.add(new ArrayDeque<Drum>(noduri.get(i).getNod().getDrumuri()));
		}

		for (int i = 0; i < noduri.size(); i++) {
			for (ArrayDeque<Drum> cd : coadaDrumuri) {
				if (cd.contains(drum)) {
					cd.remove(drum);
				}
			}
		}

		int max = 0;
		int curentJ = 0;

		for (int j = 0; j < noduri.size(); j++) {
			ArrayDeque<Drum> cd = coadaDrumuri.get(j);

			if (cd.size() > max) {
				max = cd.size();
			}

			if (!cd.isEmpty()) {
				printeazaRepetaCaracter(" ", curentJ * 21);

				curentJ = 0;

				System.out.print(vizualizareAux(" | ", " ", 10));
			} else {
				curentJ++;
			}
		}

		System.out.println();
		String numeStrada = "";

		for (int i = 0; i < max; i++) {
			int stradaFound = 0;
			curentJ = 0;

			for (int j = 0; j < noduri.size(); j++) {
				ArrayDeque<Drum> cd = coadaDrumuri.get(j);

				if (!cd.isEmpty()) {
					numeStrada = cd.pop().getNume();
					stradaFound = 1;

					printeazaRepetaCaracter(" ", curentJ * 21);

					curentJ = 0;
					System.out.print(vizualizareAux(numeStrada, " ", 10));
				} else {
					curentJ++;
				}

				if (stradaFound == 1) {
					if (j == noduri.size() - 1) {
						System.out.println();
					}
				}
			}
		}
	}

	/**
	 * Functie pentru reprezentarea grafica a unei rute afisand toate statiile si intersectiile
	 * prin care trece ruta
	 */
	public static void vizualizeazaCale(Ruta ruta) {
		List<Pozitie> noduri = ruta.getNoduri();
		List<Drum> drumuri = ruta.getDrumuri();

		for (Pozitie n : noduri) {
			System.out.print(vizualizareAux(n.getNod().getNume(), " ", 10));
		}

		System.out.println();
		printeazaRepetaCaracter(" ", 9);
		boolean first = true;

		for (Pozitie n : noduri) {
			String simbol = n.getNod().getSimbol();

			if (first == false) {
				printeazaRepetaCaracter("=", 18);
			}

			System.out.print(simbol);
			first = false;
		}

		System.out.println("     <==>    Ruta " + ruta.getNume() + " (" + ruta.getTip() + ")");
		printeazaRepetaCaracter(" ", 5);

		for (int i = 0; i < noduri.size(); i++) {
			String distanta = "[" + noduri.get(i).getDistanta() + "m]";
			System.out.print(vizualizareAux(distanta, " ", 5) + " ");

			if (i < noduri.size() - 1) {
				Pozitie next = noduri.get(i + 1);
				distanta = (next.getDistanta() - noduri.get(i).getDistanta()) + "m";
				System.out.print(vizualizareAux(distanta, " ", 4));
			}
		}

		System.out.println();
		printeazaRepetaCaracter(" ", 11);

		for (int i = 0; i < drumuri.size() - 1; i++) {
			System.out.print(vizualizareAux(" | ", " ", 10));
		}

		System.out.println();
		printeazaRepetaCaracter(" ", 11);

		for (int i = 0; i < drumuri.size() - 1; i++) {
			System.out.print(vizualizareAux(drumuri.get(i).getNume(), " ", 10));
		}
	}

	/**
     * Functie pentru reprezentarea grafica a unei rute afisand numai statiile prin care
     * trece ruta
     */
	public static void vizualizeazaRuta(Ruta ruta) {
		List<Moment> statii = ruta.getStatii();

		for (Moment s : statii) {
			System.out.print(vizualizareAux(s.getNod().getNume(), " ", 10));
		}

		System.out.println();
		printeazaRepetaCaracter(" ", 9);
		boolean first = true;

		for (Moment s : statii) {
			String simbol = s.getNod().getSimbol();

			if (first == false) {
				printeazaRepetaCaracter("=", 18);
			}

			System.out.print(simbol);
			first = false;
		}

		System.out.println("     <==>    Ruta " + ruta.getNume() + " (" + ruta.getTip() + ")");
		printeazaRepetaCaracter(" ", 5);

		for (int i = 0; i < statii.size(); i++) {
			int calcDurata = statii.get(i).getDurataDus();
			String durata = "[" + Time.convertTime(calcDurata) + "]";
			System.out.print(vizualizareAux(durata, " ", 5) + " ");

			if (i < statii.size() - 1) {
				Moment next = statii.get(i + 1);

				calcDurata = next.getDurataDus() - statii.get(i).getDurataDus();
				durata = Time.convertTime(calcDurata) + "";

				System.out.print(vizualizareAux(durata, " ", 4));
			}
		}

		System.out.println();
	}

	/**
	 * Functie auxiliara pentru reprezentarea grafica. Primind un text, returneaza textul
	 * astfel incat acesta sa fie situat la mijloc intre simboluri specificate de parametrul
	 * "delim". Lungimea string-ului returnat sa fie "width"
	 * 
	 * @param text = textul ce trebuie situat in centru
	 * @param delim = simbolurile care se afla de-o parte si de alta a textului
	 * @param width = latimea textului returnat
	 * @return
	 */
	private static String vizualizareAux(String text, String delim, int width) {
		String out = "";
		int sideWidth = width - text.length() / 2;

		for (int i = 0; i < sideWidth; i++) {
			out = out + delim;
		}

		out = out + text + (text.length() % 2 == 0 ? delim : "");

		for (int i = 0; i < sideWidth; i++) {
			out = out + delim;
		}

		return out;
	}

	/**
	 * Functie ce printeaza un simbol de un anumit numar de ori pe aceeasi linie
	 */
	private static void printeazaRepetaCaracter(String simbol, int iteratii) {
		String output = "";

		for (int i = 0; i < iteratii; i++) {
			output += simbol;
		}

		System.out.print(output);
	}
}
