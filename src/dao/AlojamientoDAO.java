package dao;

import infraestructura.JDBC;
import modelo.Alojamiento;
import modelo.Provincia;
import modelo.TipoDeAlojamiento;
import modelo.TipoDeUbicacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.ResultadoConsulta;
import servicio.ConsultaPreparada;

/**
 * DAO para la tabla alojamientos
 * 
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class AlojamientoDAO {

    private JDBC jdbc;

    public AlojamientoDAO(JDBC jdbc) {
        this.jdbc = jdbc;
    }

    public boolean insertar(Alojamiento a) {

        try {
            String sql =
                "INSERT INTO alojamientos " +
                "(nombre, poblacion, provincia, tipoDeAlojamiento, tipoDeUbicacion, capacidad, alquilado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = jdbc.getConexion().prepareStatement(sql);

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getPoblacion());
            ps.setInt(3, a.getProvincia().getCodigo());
            ps.setInt(4, a.getTipoDeAlojamiento().getCodigo());
            ps.setInt(5, a.getTipoDeUbicacion().getCodigo());
            ps.setInt(6, a.getCapacidad());
            ps.setBoolean(7, a.isAlquilado());

            ps.executeUpdate();
            ps.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Alojamiento> obtenerAlojamientos() {

        List<Alojamiento> alojamientos = new ArrayList<>();

        try {
            String sql =
                "SELECT a.referencia, a.nombre, a.poblacion, a.capacidad, a.alquilado, " +
                "p.codigo AS codigoProvincia, p.nombre AS nombreProvincia, " +
                "ta.codigo AS codigoTipoAlojamiento, ta.descripcion AS descripcionTipoAlojamiento, " +
                "tu.codigo AS codigoTipoUbicacion, tu.descripcion AS descripcionTipoUbicacion " +
                "FROM alojamientos a " +
                "JOIN provincias p ON a.provincia = p.codigo " +
                "JOIN tiposDeAlojamiento ta ON a.tipoDeAlojamiento = ta.codigo " +
                "JOIN tiposDeUbicacion tu ON a.tipoDeUbicacion = tu.codigo";

            PreparedStatement ps = jdbc.getConexion().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Provincia provincia = new Provincia(
                        rs.getInt("codigoProvincia"),
                        rs.getString("nombreProvincia")
                );

                TipoDeAlojamiento tipoDeAlojamiento = new TipoDeAlojamiento(
                        rs.getInt("codigoTipoAlojamiento"),
                        rs.getString("descripcionTipoAlojamiento")
                );

                TipoDeUbicacion tipoDeUbicacion = new TipoDeUbicacion(
                        rs.getInt("codigoTipoUbicacion"),
                        rs.getString("descripcionTipoUbicacion")
                );

                Alojamiento alojamiento = new Alojamiento();
                alojamiento.setReferencia(rs.getInt("referencia"));
                alojamiento.setNombre(rs.getString("nombre"));
                alojamiento.setPoblacion(rs.getString("poblacion"));
                alojamiento.setCapacidad(rs.getInt("capacidad"));
                alojamiento.setAlquilado(rs.getBoolean("alquilado"));
                alojamiento.setProvincia(provincia);
                alojamiento.setTipoDeAlojamiento(tipoDeAlojamiento);
                alojamiento.setTipoDeUbicacion(tipoDeUbicacion);

                alojamientos.add(alojamiento);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alojamientos;
    }
    
    public List<ResultadoConsulta> consultarDisponibles(ConsultaPreparada consulta) {

        List<ResultadoConsulta> resultados = new ArrayList<>();

        try {
            PreparedStatement ps =
                    jdbc.getConexion().prepareStatement(consulta.getSql());

            int indice = 1;
            for (Object parametro : consulta.getParametros()) {
                ps.setObject(indice++, parametro);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ResultadoConsulta r = new ResultadoConsulta();
                r.setReferencia(rs.getInt("referencia"));
                r.setNombre(rs.getString("nombre"));
                r.setProvincia(rs.getString("provincia"));
                r.setCapacidad(rs.getInt("capacidad"));

                resultados.add(r);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }

    public boolean alquilar(int referencia) {

        try {
            String sql =
                "UPDATE alojamientos " +
                "SET alquilado = 1 " +
                "WHERE referencia = ? AND alquilado = 0";

            PreparedStatement ps =
                    jdbc.getConexion().prepareStatement(sql);

            ps.setInt(1, referencia);

            int filas = ps.executeUpdate();
            ps.close();

            return filas == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
