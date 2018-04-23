package panaderia.modelo.panaderia;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Panaderia {

    Queue<Cliente> cola = new LinkedList<>();
    Logger logger = Logger.getLogger("panaderia.modelo.panaderia");
    List<Panadero> panaderos = new ArrayList<>();

    public void entroPanadero(Panadero panadero) {
        panaderos.add(panadero);
    }

    public void salioPanadero(Panadero panadero) {
        panaderos.remove(panadero);
    }

    public void entrar(Cliente cliente) {
        cola.offer(cliente);
        System.out.println("Bienvenido " + cliente.getNombreCompleto() + ".");
        logger.log(Level.INFO, cliente.toString() + " entro al local.");
        tratarDeAtender();
    }

    public void tratarDeAtender() {
        for(Panadero panadero : panaderos) {
            if(!panadero.estaOcupado()) {
                Cliente cliente = cola.poll();
                if(cliente == null) {
                    logger.log(Level.WARNING, "No hay nadie mas para atender.");
                    return;
                }
                panadero.atender(cliente);
                return;
            }
        }
        logger.log(Level.WARNING, "Todos los panaderos estan ocupados.");
    }

    public Queue<Cliente> enEspera() {
        return cola;
    }

    public void abrir() {
        logger.log(Level.INFO, "La Panaderia abrio.");
        entroPanadero(new Panadero(this,5));
        entroPanadero(new Panadero(this,2));
        esperarClientes();
    }

    private void esperarClientes() {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        while(true) {
            System.out.println("Ingrese su nombre y apellido");
            String nombreCompleto = sc.next();
            System.out.println("Ingrese su dni");
            int dni = sc.nextInt();
            System.out.println("Ingrese su pago");
            double pago = sc.nextDouble();
            Cliente cliente = new Cliente(nombreCompleto, dni, pago);
            entrar(cliente);
            System.out.flush();
        }
    }

    public void cerrar() {
        logger.log(Level.INFO, "La Panaderia cerro.");
        for(Panadero panadero : panaderos) {
            salioPanadero(panadero);
        }
    }
}