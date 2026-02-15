package principal;

import conexion.JDBC;
import controlador.ControladorConsulta;
import vista.VistaConsulta;

public class AppRuralHome {

    public static void main(String[] args) {

        JDBC jdbc = JDBC.getInstancia();
        jdbc.setConexion("configuracion/bdrural.properties");

        VistaConsulta vista = new VistaConsulta();
        new ControladorConsulta(vista, jdbc);
        vista.setVisible(true);
    }
}
