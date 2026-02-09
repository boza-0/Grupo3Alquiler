package vista.modelotabla;

import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import modelo.ResultadoConsulta;

/**
 * author BERJANO MUÑOZ, RAFAEL
 * author BOZA VILLAR, RICARDO
 * author CALIXTO DEL HOYO, JUAN
 * author GARCÍA MARCHENA, ÁLVARO
 */
public class ModeloTablaResultadosConsulta extends AbstractTableModel {

    private final String[] nombresColumnas = {
        "Referencia",
        "Nombre",
        "Provincia",
        "Alquilar"
    };

    private List<ResultadoConsulta> resultados;

    public ModeloTablaResultadosConsulta(List<ResultadoConsulta> resultados) {
        this.resultados = resultados;
    }

    @Override
    public int getRowCount() {
        return resultados == null ? 0 : resultados.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public String getColumnName(int indiceColumna) {
        return nombresColumnas[indiceColumna];
    }

    @Override
    public Class<?> getColumnClass(int indiceColumna) {
        if (indiceColumna == 3) {
            return JButton.class;
        }
        return Object.class;
    }

    @Override
    public Object getValueAt(int indiceFila, int indiceColumna) {
        ResultadoConsulta resultado = resultados.get(indiceFila);

        switch (indiceColumna) {
            case 0:
                return resultado.getReferencia();
            case 1:
                return resultado.getNombre();
            case 2:
                return resultado.getProvincia();
            case 3:
                return new JButton("Alquilar");
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int indiceFila, int indiceColumna) {
        return indiceColumna == 3;
    }

    public ResultadoConsulta getResultadoEnFila(int indiceFila) {
        return resultados.get(indiceFila);
    }
}
