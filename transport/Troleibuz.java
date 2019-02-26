package transport;

/**
 * Clasa troleibuz ce extinde clasa Unitate de Transport
 * 
 * @author BOGDAN TEACA
 */
public class Troleibuz extends UnitateTransport {
    /**
     * Constructor ce initializeaza viteza unitatii de transport de tip troleibuz
     * @param nume = numele troleibuzului
     */
    public Troleibuz(final String nume) {
        super(nume);

        viteza = 15;
        tip = UnitateTransportTip.troleibuz;
    }
}
