package servicio;

import java.util.ArrayList;
import java.util.List;

public class GeneradorConsultaAlojamientos {

    public static class Consulta {
        private final String sql;
        private final List<Object> parametros;

        public Consulta(String sql, List<Object> parametros) {
            this.sql = sql;
            this.parametros = parametros;
        }

        public String getSql() {
            return sql;
        }

        public List<Object> getParametros() {
            return parametros;
        }
    }

    public static Consulta generarConsulta(
            String provincia,
            Integer tipo,
            boolean poblacion,
            boolean aislado,
            Integer capacidad) {

        StringBuilder sql =
            new StringBuilder("SELECT * FROM alojamientos WHERE alquilado = 0");

        List<Object> parametros = new ArrayList<>();

        if (provincia != null && !provincia.trim().isEmpty()) {
            sql.append(" AND UPPER(provincia) = UPPER(?)");
            parametros.add(provincia.trim());
        }

        if (tipo != null) {
            sql.append(" AND tipo = ?");
            parametros.add(tipo);
        }

        if (poblacion ^ aislado) {
            sql.append(" AND ubicacion = ?");
            parametros.add(poblacion ? "POBLACION" : "AISLADO");
        }

        if (capacidad != null) {
            sql.append(" AND capacidad = ?");
            parametros.add(capacidad);
        }

        return new Consulta(sql.toString(), parametros);
    }
}
