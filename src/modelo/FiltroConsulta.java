package modelo;

/**
 * Representa los criterios de búsqueda seleccionados por el usuario
 *
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class FiltroConsulta {

    private String provincia;
    private TipoDeAlojamiento tipo;
    private Integer capacidadMinima;
    private boolean enPoblacion;
    private boolean aislado;

    public FiltroConsulta() {
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public TipoDeAlojamiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeAlojamiento tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidadMinima() {
        return capacidadMinima;
    }

    public void setCapacidadMinima(Integer capacidadMinima) {
        this.capacidadMinima = capacidadMinima;
    }

    public boolean isEnPoblacion() {
        return enPoblacion;
    }

    public void setEnPoblacion(boolean enPoblacion) {
        this.enPoblacion = enPoblacion;
    }

    public boolean isAislado() {
        return aislado;
    }

    public void setAislado(boolean aislado) {
        this.aislado = aislado;
    }
}
