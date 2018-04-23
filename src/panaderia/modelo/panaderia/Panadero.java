package panaderia.modelo.panaderia;


import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Panadero {

    private int delay;
    private boolean estaOcupado;
    Panaderia panaderia;
    Logger logger = Logger.getLogger("panaderia.panadero");

    public Panadero(Panaderia panaderia, int delay) {
        this.panaderia = panaderia;
        this.delay = delay;
        estaOcupado = false;
    }

    public boolean estaOcupado() {
        return estaOcupado;
    }

    public void atender(Cliente cliente) {
        estaOcupado = true;
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
        exec.schedule(new Runnable() {
            public void run() {
                logger.log(Level.INFO, "Se atendio a " + cliente.toString() + ".");
                estaOcupado = false;
                Caja.cobrar(cliente);
                buscarAtender();
            }
        }, delay, TimeUnit.SECONDS);
    }

    private void buscarAtender() {
        if(!estaOcupado) {
            panaderia.tratarDeAtender();
        }
    }
}
