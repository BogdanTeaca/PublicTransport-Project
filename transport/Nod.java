package transport;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clasa abstracta care este extinsa de clasele Statie si Intersectie
 * 
 * @author BOGDAN TEACA
 */
public abstract class Nod implements Serializable {
	public Nod() { }
	public abstract void adaugaDrum(Drum drum);
	public abstract String getSimbol();
	public abstract String getNume();
	public abstract ArrayList<Drum> getDrumuri();
	public abstract Drum gasesteDrum(String numeDrum);
}
