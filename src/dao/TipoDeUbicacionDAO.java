package dao;

import infraestructura.JDBC;
import modelo.TipoDeUbicacion;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla tiposDeUbicacion
 * 
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class TipoDeUbicacionDAO {

    private JDBC jdbc;

    public TipoDeUbicacionDAO(JDBC jdbc) {
        this.jdbc = jdbc;
    }

    public List<TipoDeUbicacion> obtenerTiposDeUbicacion() {

        List<TipoDeUbicacion> lista = new ArrayList<>();

        try {
            jdbc.setSentenciaSQL(
                "SELECT codigo, descripcion FROM tiposDeUbicacion ORDER BY codigo"
            );
            jdbc.ejecutarConsulta();

            ResultSet rs = jdbc.getCursor();

            while (rs.next()) {
                TipoDeUbicacion tipo = new TipoDeUbicacion();
                tipo.setCodigo(rs.getInt("codigo"));
                tipo.setDescripcion(rs.getString("descripcion"));
                lista.add(tipo);
            }

            jdbc.cerrarCursor();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
