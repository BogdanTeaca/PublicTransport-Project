package transport;

/**
 * Clasa tramvai ce extinde clasa Unitate de Transport
 * 
 * @author BOGDAN TEACA
 */
public class Tramvai extends UnitateTransport {
    /**
     * Constructor ce initializeaza viteza unitatii de transport de tip tramvai
     * @param nume = numele tramvaiului
     */
    public Tramvai(final String nume) {
        super(nume);

        viteza = 20;
        tip = UnitateTransportTip.tramvai;
    }
}
