package modelo;

public class ResumenAlquiler {

    private int numeroAlojamientos;
    private int totalPlazas;

    public void registrarAlquiler(int plazas) {
        numeroAlojamientos++;
        totalPlazas += plazas;
    }

    public int getNumeroAlojamientos() {
        return numeroAlojamientos;
    }

    public int getTotalPlazas() {
        return totalPlazas;
    }
}
