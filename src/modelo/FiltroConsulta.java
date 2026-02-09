package modelo;

public class FiltroConsulta {

    private Integer codigoProvincia;
    private Integer codigoTipoAlojamiento;
    private boolean ubicacionEnPoblacion;
    private boolean ubicacionAislada;
    private Integer capacidadMinima;

    public Integer getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(Integer codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public Integer getCodigoTipoAlojamiento() {
        return codigoTipoAlojamiento;
    }

    public void setCodigoTipoAlojamiento(Integer codigoTipoAlojamiento) {
        this.codigoTipoAlojamiento = codigoTipoAlojamiento;
    }

    public boolean isUbicacionEnPoblacion() {
        return ubicacionEnPoblacion;
    }

    public void setUbicacionEnPoblacion(boolean ubicacionEnPoblacion) {
        this.ubicacionEnPoblacion = ubicacionEnPoblacion;
    }

    public boolean isUbicacionAislada() {
        return ubicacionAislada;
    }

    public void setUbicacionAislada(boolean ubicacionAislada) {
        this.ubicacionAislada = ubicacionAislada;
    }

    public Integer getCapacidadMinima() {
        return capacidadMinima;
    }

    public void setCapacidadMinima(Integer capacidadMinima) {
        this.capacidadMinima = capacidadMinima;
    }
}
