package dao;

import infraestructura.JDBC;
import modelo.TipoDeAlojamiento;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla tiposDeAlojamiento
 * 
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class TipoDeAlojamientoDAO {

    private JDBC jdbc;

    public TipoDeAlojamientoDAO(JDBC jdbc) {
        this.jdbc = jdbc;
    }

    public List<TipoDeAlojamiento> obtenerTiposDeAlojamiento() {
        List<TipoDeAlojamiento> listaTipos = new ArrayList<>();

        try {
            jdbc.setSentenciaSQL(
                "SELECT codigo, descripcion FROM tiposDeAlojamiento ORDER BY codigo"
            );
            jdbc.ejecutarConsulta();

            ResultSet rs = jdbc.getCursor();

            while (rs.next()) {
                TipoDeAlojamiento tipo = new TipoDeAlojamiento();
                tipo.setCodigo(rs.getInt("codigo"));
                tipo.setDescripcion(rs.getString("descripcion"));
                listaTipos.add(tipo);
            }

            jdbc.cerrarCursor();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaTipos;
    }
}
