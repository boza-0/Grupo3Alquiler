package dao;

import infraestructura.JDBC;
import modelo.Provincia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla provincias
 * 
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class ProvinciaDAO {

    private JDBC jdbc;

    public ProvinciaDAO(JDBC jdbc) {
        this.jdbc = jdbc;
    }

    public List<Provincia> obtenerProvincias() {

        List<Provincia> lista = new ArrayList<>();

        try {
            jdbc.setSentenciaSQL(
                "SELECT codigo, nombre FROM provincias ORDER BY codigo"
            );
            jdbc.ejecutarConsulta();

            ResultSet rs = jdbc.getCursor();

            while (rs.next()) {
                Provincia p = new Provincia();
                p.setCodigo(rs.getInt("codigo"));
                p.setNombre(rs.getString("nombre"));
                lista.add(p);
            }

            jdbc.cerrarCursor();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
