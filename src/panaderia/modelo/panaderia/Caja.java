package panaderia.modelo.panaderia;

import java.util.HashMap;
import java.util.Map;

public class Caja {
    private static Map<String, Double> facturacionPorCliente = new HashMap<>();
    public static void cobrar(Cliente cliente) {
        //cobra al cliente
        Double facturacion = facturacionPorCliente.get(cliente.getNombreCompleto());
        if(facturacion != null) {
            facturacionPorCliente.put(cliente.getNombreCompleto(),
                    facturacion + cliente.getPago());
        }
    }
}
