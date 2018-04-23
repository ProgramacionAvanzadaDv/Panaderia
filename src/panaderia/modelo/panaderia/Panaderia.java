package panaderia.modelo.panaderia;

import panaderia.modelo.impresor.ArchivoImpresor;
import panaderia.modelo.impresor.ConsolaImpresor;
import panaderia.modelo.impresor.Impresor;

import java.util.*;

public class Panaderia {

    Queue<Cliente> cola = new LinkedList<>();
    Impresor logArchivo = new ArchivoImpresor("panaderia.log");
    Impresor logConsola = new ConsolaImpresor();
    List<Panadero> panaderos = new ArrayList<>();

    public void entroPanadero(Panadero panadero) {
        panaderos.add(panadero);
    }

    public void salioPanadero(Panadero panadero) {
        panaderos.remove(panadero);
    }

    public void entrar(Cliente cliente) {
        cola.offer(cliente);
        logConsola.escribir("Bienvenido " + cliente.getNombreCompleto() + ".");
        logArchivo.escribir(cliente.toString() + " entro al local.");
        tratarDeAtender();
    }

    public void tratarDeAtender() {
        for(Panadero panadero : panaderos) {
            if(!panadero.estaOcupado()) {
                Cliente cliente = cola.poll();
                if(cliente == null) {
                    logArchivo.escribir("No hay nadie mas para atender.");
                    return;
                }
                panadero.atender(cliente);
                return;
            }
        }
        logArchivo.escribir("Todos los panaderos estan ocupados.");
    }

    public Queue<Cliente> enEspera() {
        return cola;
    }

    public void abrir() {
        logArchivo.escribir("La Panaderia abrio.");
        entroPanadero(new Panadero(this,5));
        entroPanadero(new Panadero(this,2));
        esperarClientes();
    }

    private void esperarClientes() {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        while(true) {
            logConsola.escribir("Ingrese su nombre y apellido");
            String nombreCompleto = sc.next();
            logConsola.escribir("Ingrese su dni");
            int dni = sc.nextInt();
            logConsola.escribir("Ingrese su pago");
            double pago = sc.nextDouble();
            Cliente cliente = new Cliente(nombreCompleto, dni, pago);
            entrar(cliente);
            System.out.flush();
        }
    }

    public void cerrar() {
        logArchivo.escribir("La Panaderia cerro.");
        for(Panadero panadero : panaderos) {
            salioPanadero(panadero);
        }
    }
}