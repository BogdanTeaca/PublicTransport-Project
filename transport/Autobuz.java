package transport;

/**
 * Clasa autobuz ce extinde clasa Unitate de Transport
 * 
 * @author BOGDAN TEACA
 */
public class Autobuz extends UnitateTransport {
    /**
     * Constructor ce initializeaza viteza unitatii de transport de tip autobuz
     * @param nume = numele autobuzului
     */
    public Autobuz(final String nume) {
        super(nume);

        viteza = 10;
        tip = UnitateTransportTip.autobuz;
    }
}
