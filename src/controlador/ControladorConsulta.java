package controlador;

import conexion.JDBC;
import dao.TipoDeAlojamientoDAO;
import modelo.TipoDeAlojamiento;
import servicio.GeneradorConsultaAlojamientos;
import servicio.GeneradorConsultaAlojamientos.Consulta;
import vista.VistaConsulta;
import vista.VistaResultados;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorConsulta {

    private VistaConsulta vista;
    private JDBC jdbc;
    private TipoDeAlojamientoDAO tipoDAO;

    public ControladorConsulta(VistaConsulta vista, JDBC jdbc) {
        this.vista = vista;
        this.jdbc = jdbc;
        this.tipoDAO = new TipoDeAlojamientoDAO(jdbc);
        inicializar();
    }

    private void inicializar() {

        cargarTiposDeAlojamiento();

        vista.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void cargarTiposDeAlojamiento() {
        vista.getTipoDeAlojamientoComboBox().removeAllItems();
        for (TipoDeAlojamiento tipo : tipoDAO.obtenerTiposDeAlojamiento()) {
            vista.getTipoDeAlojamientoComboBox().addItem(tipo);
        }
        vista.getTipoDeAlojamientoComboBox().setSelectedIndex(-1);
    }

    private void buscar() {

        String provincia = vista.getProvinciaTxt().getText();

        Integer tipo = null;
        if (vista.getTipoDeAlojamientoComboBox().getSelectedItem() != null) {
            tipo = ((TipoDeAlojamiento)
                    vista.getTipoDeAlojamientoComboBox().getSelectedItem())
                    .getCodigo();
        }

        boolean poblacion = vista.getEnPoblacionCheckBox().isSelected();
        boolean aislado = vista.getAisladoCheckBox().isSelected();

        Integer capacidad = null;
        if (!vista.getCapacidadTxt().getText().trim().isEmpty()) {
            capacidad = Integer.parseInt(vista.getCapacidadTxt().getText());
        }

        Consulta consulta = GeneradorConsultaAlojamientos.generarConsulta(
                provincia, tipo, poblacion, aislado, capacidad
        );

        VistaResultados vistaResultados = new VistaResultados();
        new ControladorResultados(vistaResultados, consulta, jdbc);

        vistaResultados.setVisible(true);
        vista.dispose();
    }
}
