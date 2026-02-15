package controlador;

import conexion.JDBC;
import dao.AlojamientoDAO;
import modelo.Alojamiento;
import servicio.GeneradorConsultaAlojamientos.Consulta;
import vista.VistaConsulta;
import vista.VistaResultados;

import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import vista.VistaFinalizacion;

public class ControladorResultados {

    private VistaResultados vista;
    private List<Alojamiento> resultados;      // visibles
    private List<Alojamiento> seleccionados;   // pendientes
    private JDBC jdbc;

    public ControladorResultados(VistaResultados vista, Consulta consulta, JDBC jdbc) {
        this.vista = vista;
        this.jdbc = jdbc;
        this.seleccionados = new ArrayList<>();
        cargarResultados(consulta);
        inicializar();
    }

    private void cargarResultados(Consulta consulta) {

        AlojamientoDAO dao = new AlojamientoDAO(jdbc);
        resultados = dao.buscar(consulta);

        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"REFERENCIA", "NOMBRE", "PROVINCIA", " "}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Alojamiento a : resultados) {
            modelo.addRow(new Object[]{
                    a.getReferencia(),
                    a.getNombre(),
                    a.getProvincia(),
                    "Alquilar"
            });
        }

        vista.getTablaResultados().setModel(modelo);
        vista.getFinalizarAlquilerButton().setEnabled(false);
    }

    private void inicializar() {

        // Click en columna "Alquilar"
        vista.getTablaResultados().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = vista.getTablaResultados().rowAtPoint(e.getPoint());
                int columna = vista.getTablaResultados().columnAtPoint(e.getPoint());

                if (fila != -1 && columna == 3) {
                    alquilarFila(fila);
                }
            }
        });

        vista.getVolverPrincipalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volver();
            }
        });

        vista.getFinalizarAlquilerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarAlquiler();
            }
        });
    }

    private void alquilarFila(int fila) {

        Alojamiento a = resultados.get(fila);

        seleccionados.add(a);
        resultados.remove(fila);

        ((DefaultTableModel) vista.getTablaResultados().getModel())
                .removeRow(fila);

        vista.getFinalizarAlquilerButton()
                .setEnabled(!seleccionados.isEmpty());
    }

    private void finalizarAlquiler() {

        AlojamientoDAO dao = new AlojamientoDAO(jdbc);

        for (Alojamiento a : seleccionados) {
            dao.marcarComoAlquilado(a.getReferencia());
        }

        int totalAlojamientos = seleccionados.size();
        int totalPersonas = 0;

        for (Alojamiento a : seleccionados) {
            totalPersonas += a.getCapacidad();
        }

        VistaFinalizacion vistaFinal =
                new VistaFinalizacion(totalAlojamientos, totalPersonas);

        vistaFinal.setVisible(true);
        vista.dispose();
    }


    private void volver() {

        // Rollback implícito: no se toca la BD
        seleccionados.clear();

        VistaConsulta vistaConsulta = new VistaConsulta();
        new ControladorConsulta(vistaConsulta, jdbc);
        vistaConsulta.setVisible(true);
        vista.dispose();
    }
}
