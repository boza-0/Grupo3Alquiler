package app;

import controlador.ControladorConsulta;
import vista.VistaConsulta;

/**
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class AppRuralHome {

    public static void main(String[] args) {
        VistaConsulta vistaConsulta = new VistaConsulta();
        ControladorConsulta controladorConsulta =
                new ControladorConsulta(vistaConsulta);

        vistaConsulta.setVisible(true);
    }
}
