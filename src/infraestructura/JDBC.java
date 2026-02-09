package infraestructura;

import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Componente JDBC
 * 
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class JDBC {
    
    private static JDBC instancia;

    private Connection conexion;
    private String sentenciaSQL;
    private ResultSet cursor;
    
    private JDBC() {
        // conexión a la BD
    }
    
    public static JDBC getInstancia() {
        if (instancia == null) {
            instancia = new JDBC();
        }
        return instancia;
    }

    public boolean setConexion(String rutaProperties) {
        try {
            Properties propiedades = new Properties();
            propiedades.load(new FileInputStream(rutaProperties));

            String driver = propiedades.getProperty("driver");
            String url = propiedades.getProperty("url");
            String usuario = propiedades.getProperty("usuario");
            String password = propiedades.getProperty("password");

            Class.forName(driver);
            conexion = DriverManager.getConnection(url, usuario, password);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setSentenciaSQL(String strSQL) {
        this.sentenciaSQL = strSQL;
    }

    public boolean ejecutarConsulta() {
        try {
            Statement sentencia = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            cursor = sentencia.executeQuery(sentenciaSQL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ejecutarConsultaActualizable() {
        try {
            Statement sentencia = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            cursor = sentencia.executeQuery(sentenciaSQL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet getCursor() {
        return cursor;
    }

    public boolean cerrarCursor() {
        try {
            if (cursor != null) {
                cursor.close();
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
