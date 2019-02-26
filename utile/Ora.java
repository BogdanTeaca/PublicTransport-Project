package utile;

import java.io.Serializable;

/**
 * Clasa ora in care se tin minte intregii ora si minut
 * 
 * @author BOGDAN TEACA
 */
public class Ora implements Comparable<Ora>, Serializable {
	private int ora;
	private int min;

	/**
	 * Constructor ce initializeaza ora si minutul folosind parametrii ora si min
	 */
	public Ora(int ora, int min) {
		this.ora = ora;
		this.min = min;
	}

	/**
	 * Constructor ce initializeaza ora si minutul folosind echivalentul in secunde
	 * 
	 * @param unix = numarul de secunde trecute de la ora 00:00
	 */
	public Ora(int unix){
	    this.ora = unix / 3600;
	    this.min = (unix - this.ora * 3600) / 60;
	}
	
	/**
	 * Returneaza echivalentul orei si minutului in secunde incepand de la ora 00:00
	 * 
	 * @return = echivalentul orei in secunde
	 */
	public int toUnix(){
	    return ora * 3600 + min * 60;
	}

	/**
	 * Getter pentru ora
	 */
	public int getOra() {
	    return ora;
	}

	/**
     * Getter pentru minut
     */
	public int getMin(){
	    return min;
	}

	public void setOra(int ora, int min) {
		if (ora >= 0 && ora <= 23 && min >= 0 && min <= 59) {
			this.ora = ora;
			this.min = min;
		} else {
			System.out.println("Eroare: Ora nu este valida.");
		}
	}

	/**
	 * Returneaza ora si minutul in format HH:MM
	 */
	public String getOraToString() {
		String timp = "";

		if (ora < 10) {
			timp += "0" + ora;
		} else {
			timp += "" + ora;
		}

		timp += ":";

		if (min < 10) {
			timp += "0" + min;
		} else {
			timp += "" + min;
		}

		return timp;
	}

	/**
	 * Suprascrierea functiei compareTo pentru ca orele sa poata fi comparate mai usor
	 */
    @Override
    public int compareTo(Ora ora) {
        if (this.ora < ora.getOra()) {
            return 1;
        } else if (this.ora > ora.getOra()) {
            return -1;
        } else {
            if (this.min < ora.getMin()) {
                return 1;
            } else if (this.min > ora.getMin()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
