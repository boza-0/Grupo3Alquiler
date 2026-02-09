package modelo;

/**
 * 
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class Alojamiento {

    private int referencia;
    private String nombre;
    private String poblacion;
    private Provincia provincia;
    private TipoDeAlojamiento tipoDeAlojamiento;
    private TipoDeUbicacion tipoDeUbicacion;
    private int capacidad;
    private boolean alquilado;

    public Alojamiento() {
    }

    public int getReferencia() {
        return referencia;
    }
    
    public void setReferencia(int referencia) {
        if (this.referencia != 0) {
            throw new IllegalStateException("La referencia no puede modificarse");
        }
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public TipoDeAlojamiento getTipoDeAlojamiento() {
        return tipoDeAlojamiento;
    }

    public void setTipoDeAlojamiento(TipoDeAlojamiento tipoDeAlojamiento) {
        this.tipoDeAlojamiento = tipoDeAlojamiento;
    }

    public TipoDeUbicacion getTipoDeUbicacion() {
        return tipoDeUbicacion;
    }

    public void setTipoDeUbicacion(TipoDeUbicacion tipoDeUbicacion) {
        this.tipoDeUbicacion = tipoDeUbicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isAlquilado() {
        return alquilado;
    }

    public void setAlquilado(boolean alquilado) {
        this.alquilado = alquilado;
    }
}
