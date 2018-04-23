package panaderia.modelo.panaderia;

import java.util.*;

public class Panaderia {

    Queue<Cliente> cola = new LinkedList<>();
    List<Panadero> panaderos = new ArrayList<>();

    public void entroPanadero(Panadero panadero) {
        panaderos.add(panadero);
    }

    public void salioPanadero(Panadero panadero) {
        panaderos.remove(panadero);
    }

    public void entrar(Cliente cliente) {
        cola.offer(cliente);
        tratarDeAtender();
    }

    public void tratarDeAtender() {
        for(Panadero panadero : panaderos) {
            if(!panadero.estaOcupado()) {
                Cliente cliente = cola.poll();
                if(cliente == null) {
                    return;
                }
                panadero.atender(cliente);
                return;
            }
        }
    }

    public Queue<Cliente> enEspera() {
        return cola;
    }

    public void abrir() {
        entroPanadero(new Panadero(this,5));
        entroPanadero(new Panadero(this,2));
        esperarClientes();
    }

    private void esperarClientes() {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        while(true) {
            String nombreCompleto = sc.next();
            int dni = sc.nextInt();
            double pago = sc.nextDouble();
            Cliente cliente = new Cliente(nombreCompleto, dni, pago);
            entrar(cliente);
        }
    }

    public void cerrar() {
        for(Panadero panadero : panaderos) {
            salioPanadero(panadero);
        }
    }
}