package utile;

/**
 * Clasa Time care retine timpul in minute si secunde. Utila pentru calcularea duratei
 * dintre doua statii ale unei rute
 * 
 * @author BOGDAN TEACA
 */
public class Time {
	private int min;
	private int sec;

	/**
	 * Constructor care initializeaza minutul si secunda
	 */
	public Time(int min, int sec) {
		this.min = min;
		this.sec = sec;
	}

	/**
	 * Functie ce returneaza timpul in format "X m Y s", unde X = minute, Y = secunde
	 */
	public String getTimeToString() {
		return min + "m " + sec + "s";
	}

	/**
	 * Functie statica utila pentru convertirea unui numar de secunde in minute si secunde
	 */
	public static String convertTime(final int sec) {
		int localMin = sec / 60;
		int localSec = sec % 60;

		return localMin + "m " + localSec + "s";
	}
}
