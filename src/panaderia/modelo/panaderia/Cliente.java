package panaderia.modelo.panaderia;

public class Cliente {
    private String nombreCompleto;
    private int dni;
    private double pago;

    public Cliente(String nombreCompleto, int dni, double pago) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.pago= pago;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public double getPago() {
        return pago;
    }

    public int getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return nombreCompleto;
    }
}
