package dao;

import conexion.JDBC;
import modelo.Alojamiento;
import modelo.Ubicacion;
import servicio.GeneradorConsultaAlojamientos.Consulta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlojamientoDAO {

    private JDBC jdbc;

    public AlojamientoDAO(JDBC jdbc) {
        this.jdbc = jdbc;
    }

    public List<Alojamiento> buscar(Consulta consulta) {

        List<Alojamiento> lista = new ArrayList<>();

        try (PreparedStatement ps =
                     jdbc.getConexion().prepareStatement(consulta.getSql())) {

            int index = 1;
            for (Object param : consulta.getParametros()) {
                ps.setObject(index++, param);
            }

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Alojamiento a = new Alojamiento();
                    a.setReferencia(rs.getInt("referencia"));
                    a.setNombre(rs.getString("nombre"));
                    a.setPoblacion(rs.getString("poblacion"));
                    a.setProvincia(rs.getString("provincia"));
                    a.setCapacidad(rs.getInt("capacidad"));
                    a.setTipo(rs.getInt("tipo"));
                    a.setUbicacion(Ubicacion.valueOf(rs.getString("ubicacion")));
                    a.setAlquilado(rs.getBoolean("alquilado"));

                    lista.add(a);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void marcarComoAlquilado(int referencia) {

        String sql = "UPDATE alojamientos SET alquilado = 1 WHERE referencia = ?";

        try (PreparedStatement ps =
                     jdbc.getConexion().prepareStatement(sql)) {

            ps.setInt(1, referencia);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
