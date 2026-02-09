package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaResultados;

/**
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class ControladorResultados {

    private final VistaResultados vista;

    public ControladorResultados(VistaResultados vista) {
        this.vista = vista;
        inicializar();
    }

    private void inicializar() {
        vista.getVolverPrincipalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverPrincipal();
            }
        });

        vista.getFinalizarAlquilerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarAlquiler();
            }
        });
    }

    private void volverPrincipal() {
        vista.dispose();
    }

    private void finalizarAlquiler() {
        // TODO: abrir VistaFinalizacion y su controlador
    }
}
