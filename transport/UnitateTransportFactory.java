package transport;

/**
 * Clasa ce implementeaza Design Pattern-ul Factory pentru initializarea unei unitati
 * de transport de un anumit tip
 * 
 * @author BOGDAN TEACA
 */
public class UnitateTransportFactory {
    /**
     * Returneaza unitatea de transport de tipul cerut ca parametru
     * 
     * @param tip = tipul unitatii de transport
     * @param nume = numele unitatii de transport
     * @return = unitatea de transport de tipul "tip"
     */
    public static UnitateTransport getUnitateTransport(String tip, String nume) {
        switch (tip) {
        case "a":
            return new Autobuz(nume);
        case "t":
            return new Troleibuz(nume);
        case "v":
            return new Tramvai(nume);
        default:
            return null;
        }
    }
}
