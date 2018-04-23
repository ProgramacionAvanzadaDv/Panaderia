package panaderia;

import panaderia.modelo.impresor.ArchivoImpresor;
import panaderia.modelo.impresor.ConsolaImpresor;
import panaderia.modelo.impresor.Impresor;
import panaderia.modelo.panaderia.Panaderia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Aplicacion {

    private static List<Impresor> impresores = new ArrayList<>();

    public static void main(String[] args) {
        initImpresores();
        Panaderia pana = new Panaderia();
        pana.abrir();
    }

    private static void initImpresores() {
        impresores.add(new ConsolaImpresor());
        impresores.add(new ArchivoImpresor("panaderia.log"));
    }
}
