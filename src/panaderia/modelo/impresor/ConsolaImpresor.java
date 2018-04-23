package panaderia.modelo.impresor;

public class ConsolaImpresor implements Impresor {
    @Override
    public void escribir(String texto) {
        System.out.println(texto);
    }
}
