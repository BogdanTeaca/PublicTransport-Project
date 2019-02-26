package interfata;
import java.util.List;

/**
 * Interfata ce este implementata de familiile de comenzi din Catalogul de comenzi
 * 
 * @author BOGDAN TEACA
 */
public interface Comanda {
    /**
     * Comanda trebuie sa aiba metoda executaComanda care implementeaza functia comenzii
     * 
     * @param param = Lista de parametrii din comanda
     */
    void executaComanda(List<String> param);
}
