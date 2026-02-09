package servicio;

import java.util.ArrayList;
import java.util.List;

public class ConsultaPreparada {

    private String sql;
    private List<Object> parametros;

    public ConsultaPreparada() {
        parametros = new ArrayList<Object>();
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParametros() {
        return parametros;
    }

    public void addParametro(Object parametro) {
        parametros.add(parametro);
    }
}
