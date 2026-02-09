package servicio;

import modelo.FiltroConsulta;

public class GeneradorSQLConsulta {

    public ConsultaPreparada generarConsulta(FiltroConsulta filtro) {

        ConsultaPreparada consulta = new ConsultaPreparada();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.referencia, a.nombre, p.nombre AS provincia, a.capacidad ");
        sql.append("FROM alojamientos a ");
        sql.append("JOIN provincias p ON a.provincia = p.codigo ");
        sql.append("WHERE a.alquilado = 0 ");

        if (filtro.getCodigoProvincia() != null) {
            sql.append("AND a.provincia = ? ");
            consulta.addParametro(filtro.getCodigoProvincia());
        }

        if (filtro.getCodigoTipoAlojamiento() != null) {
            sql.append("AND a.tipoDeAlojamiento = ? ");
            consulta.addParametro(filtro.getCodigoTipoAlojamiento());
        }

        boolean enPoblacion = filtro.isUbicacionEnPoblacion();
        boolean aislada = filtro.isUbicacionAislada();

        if (enPoblacion ^ aislada) {
            sql.append("AND a.tipoDeUbicacion = ? ");
            consulta.addParametro(enPoblacion ? 1 : 2);
        }

        if (filtro.getCapacidadMinima() != null && filtro.getCapacidadMinima() > 0) {
            sql.append("AND a.capacidad >= ? ");
            consulta.addParametro(filtro.getCapacidadMinima());
        }

        sql.append("ORDER BY a.referencia");

        consulta.setSql(sql.toString());
        return consulta;
    }
}
