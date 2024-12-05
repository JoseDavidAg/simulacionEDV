package funcion;

import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class GraficaSimulacion {
ChartPanel chartPanel;
ChartPanel chartPanel2;
    // Este método toma los datos de la tabla y los grafica en un panel proporcionado
    public void graficarEspVida(JTable JTableCSV, JPanel jpane1) {
        DefaultTableModel model = (DefaultTableModel) JTableCSV.getModel();
        int rowCount = model.getRowCount();

        // Asegurarnos de que la tabla no esté vacía
        if (rowCount == 0) {
            System.err.println("La tabla está vacía. Carga un archivo CSV primero.");
            return;
        }

        // Crear series para la gráfica
        XYSeries seriesNormal = new XYSeries("Esperanza de Vida Normal");
        XYSeries seriesCalculada = new XYSeries("Esperanza de Vida Calculada");

        // Agregar los datos de la tabla a las series
        for (int i = 0; i < rowCount; i++) {
            try {
                // Leer el año de la tabla
                int anio = (int) Double.parseDouble((String) model.getValueAt(i, 0));

                // Leer los valores de la tabla para esperanza de vida
                double esperanzaVidaNormal = Double.parseDouble((String) model.getValueAt(i, 2)); 
                double esperanzaVidaCalculada = Double.parseDouble((String) model.getValueAt(i, 7));

                // Agregar los datos a las series
                seriesNormal.add(anio, esperanzaVidaNormal);
                seriesCalculada.add(anio, esperanzaVidaCalculada);

            } catch (NumberFormatException e) {
                // Imprimir un error y continuar con la siguiente fila si los datos no son válidos
                System.err.println("Error: El valor del año o los datos no son válidos en la fila " + i);
                continue; // Saltar a la siguiente fila
            }
        }

        // Crear el dataset para la gráfica
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesNormal);
        dataset.addSeries(seriesCalculada);

        // Crear la gráfica con el dataset
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Proyección de Esperanza de Vida",   // Título de la gráfica
                "Año",                              // Eje X (Año)
                "Esperanza de Vida",                // Eje Y (Esperanza de Vida)
                dataset                             // Datos de la gráfica
        );

        // Crear un panel para la gráfica
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600)); // Tamaño del panel
        //metodo para imprimir
        // Limpiar el panel existente
        jpane1.removeAll();
        jpane1.setLayout(new java.awt.BorderLayout());
        jpane1.add(chartPanel); // Agregar la gráfica al panel
        jpane1.validate(); // Validar el panel para actualizar los cambios
        
      
    }
    
    // Este método toma los datos de la tabla y los grafica en un panel proporcionado
    public void graficarTasasDeNatalidad(JTable JTableCSV, JPanel jpane1) {
        DefaultTableModel model = (DefaultTableModel) JTableCSV.getModel();
        int rowCount = model.getRowCount();

        // Verificar si la tabla está vacía
        if (rowCount == 0) {
            System.err.println("La tabla está vacía. Carga un archivo CSV primero.");
            return;
        }

        // Crear series para la gráfica
        XYSeries seriesTasaNatalidad = new XYSeries("Tasa de Natalidad");
        XYSeries seriesTasaNatalidadCalculada = new XYSeries("Tasa de Natalidad Calculada");

        try {
            // Agregar los datos de la tabla a las series
            for (int i = 0; i < rowCount; i++) {
                try {
                    // Leer el año desde la columna 0 de la tabla
                    int anio = (int) Double.parseDouble((String) model.getValueAt(i, 0));

                    // Obtener los valores de la tabla
                    double tasaNatalidad = Double.parseDouble((String) model.getValueAt(i, 1)); // Columna 1
                    double tasaNatalidadCalculada = Double.parseDouble((String) model.getValueAt(i, 6)); // Columna 6

                    // Agregar los valores a las series
                    seriesTasaNatalidad.add(anio, tasaNatalidad);
                    seriesTasaNatalidadCalculada.add(anio, tasaNatalidadCalculada);

                } catch (NumberFormatException e) {
                    // Imprimir un error si el año o los valores son inválidos y continuar con la siguiente fila
                    System.err.println("Error en los datos de la fila " + i + ": " + e.getMessage());
                    continue;
                }
            }

            // Crear el dataset para la gráfica
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(seriesTasaNatalidad);
            dataset.addSeries(seriesTasaNatalidadCalculada);

            // Crear la gráfica con el dataset
            JFreeChart chart = ChartFactory.createXYLineChart(
                "Proyección de Tasa de Natalidad", // Título de la gráfica
                "Año",                            // Eje X (Año)
                "Tasa de Natalidad",              // Eje Y (Tasa de Natalidad)
                dataset                           // Datos de la gráfica
            );

            // Crear un panel para la gráfica
            chartPanel2 = new ChartPanel(chart);
            chartPanel2.setPreferredSize(new java.awt.Dimension(800, 600)); // Tamaño del panel

            // Limpiar el panel existente
            jpane1.removeAll();
            jpane1.setLayout(new java.awt.BorderLayout());
            jpane1.add(chartPanel2); // Agregar la gráfica al panel
            jpane1.validate();      // Validar el panel para actualizar los cambios

        } catch (Exception e) {
            System.err.println("Error al procesar los datos para la gráfica: " + e.getMessage());
        }
    }
    
    public void imprimir(){
        chartPanel.createChartPrintJob();
   
    }
    
     public void imprimir2(){
        chartPanel2.createChartPrintJob();
    }
    
}

