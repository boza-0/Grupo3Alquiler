package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaConsulta;

/**
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class ControladorConsulta {

    private final VistaConsulta vista;

    public ControladorConsulta(VistaConsulta vista) {
        this.vista = vista;
        inicializar();
    }

    private void inicializar() {
        vista.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
    }

    private void buscar() {
        // TODO:
        // 1) Leer filtros de VistaConsulta
        // 2) Construir FiltroConsulta
        // 3) Ejecutar consulta
        // 4) Abrir VistaResultados + ControladorResultados
    }
}
