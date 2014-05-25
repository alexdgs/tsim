/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.awt.Color;
import java.util.Arrays;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author A
 */


public class Grafico
{
    
    public void generarPieHabitaciones(int simple, int doble, int suite)
    {
       int total=simple+doble+suite;
       DefaultPieDataset pieH = new DefaultPieDataset();
       
       /*pieH.setValue("Suite. Jr", new Double((suite*100)/total));
       pieH.setValue("Simple", new Double((simple*100)/total));
       pieH.setValue("Doble", new Double((doble*100)/total));
       */
       
       pieH.setValue("Suite. Jr", new Double(suite));
       pieH.setValue("Simple", new Double(simple));
       pieH.setValue("Doble", new Double(doble));
       
       JFreeChart chart = ChartFactory.createPieChart3D("Cantidad de Habitaciones", pieH, true, true, false);
       PiePlot3D p=(PiePlot3D)chart.getPlot();
       p.setSectionPaint("Suite. Jr", new Color(51,0,102));
       p.setSectionPaint("Simple", new Color(0,0,153));
       p.setSectionPaint("Doble", new Color(0,116,215));
      
       p.setStartAngle(290);
        
       p.setForegroundAlpha(0.5f);
        
       ChartFrame frame= new ChartFrame("Cantidad de Habitaciones", chart);
       frame.setVisible(true);
       frame.setSize(450, 500);
       frame.setLocationRelativeTo(null);
    }
    
    public void generarGraficoBarras(Object [][] tabla, int opcion)
    {
        String [] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre","Noviembre", "Diciembre"};
        String [] tipo ={"Simple Actual", "Simple Mejorada", "Doble Actual", "Doble Mejorada", "Suite Jr. Actual", "Suite Jr. Mejorada"};
        
        int [] primeraOpcion = {7, 16, 8, 17, 9, 18};
        int [] segundaOpcion = {4, 11, 5, 12, 6, 13};
        int [] terceraOpcion ={};
        
        int [] posiciones =new int [6];
        
        if(opcion==1) posiciones=Arrays.copyOfRange(primeraOpcion, 0, 6);
        else
        {
            if(opcion==2) posiciones=Arrays.copyOfRange(segundaOpcion, 0, 6);
            else posiciones=Arrays.copyOfRange(terceraOpcion, 0, 6);
        }
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(int i=0; i<12; i++)
            for(int j=0; j<6; j++)
                dataset.setValue(new Double(""+tabla[i][posiciones[j]]), tipo[j], meses[i]);
            
        
        String titulo="Incremento de habitaciones - Comparación de demanda instatisfecha Actual con Mejorado";
        if(opcion==2)titulo="Incremento de precios - Comparación de ingresos Actual con Mejorado";
        else if(opcion==3)titulo="";
        
        JFreeChart chart = ChartFactory.createBarChart3D( titulo, "Meses", "Valores", dataset, PlotOrientation.VERTICAL, true, true, false );
        
        ChartFrame frame= new ChartFrame(titulo, chart);
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);   
    }
    public void generarGraficoBarrasSegundaOpcion(Object [][] tabla)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(new Double((double)tabla[0][4]), "Simple Actual", "Enero");
        dataset.setValue(new Double((double)tabla[0][11]), "Simple Mejorada", "Enero");
        dataset.setValue(new Double((double)tabla[0][5]), "Doble Actual", "Enero");
        dataset.setValue(new Double((double)tabla[0][12]), "Doble Mejorado", "Enero");
        dataset.setValue(new Double((double)tabla[0][6]), "Suite Jr. Actual", "Enero");
        dataset.setValue(new Double((double)tabla[0][13]), "Suite Jr. Mejorado", "Enero");
        
        dataset.setValue(new Double((double)tabla[1][4]), "Simple Actual", "Febrero");
        dataset.setValue(new Double((double)tabla[1][11]), "Simple Mejorada", "Febrero");
        dataset.setValue(new Double((double)tabla[1][5]), "Doble Actual", "Febrero");
        dataset.setValue(new Double((double)tabla[1][12]), "Doble Mejorado", "Febrero");
        dataset.setValue(new Double((double)tabla[1][6]), "Suite Jr. Actual", "Febrero");
        dataset.setValue(new Double((double)tabla[1][13]), "Suite Jr. Mejorado", "Febrero");
        
        dataset.setValue(new Double((double)tabla[2][4]), "Simple Actual", "Marzo");
        dataset.setValue(new Double((double)tabla[2][11]), "Simple Mejorada", "Marzo");
        dataset.setValue(new Double((double)tabla[2][5]), "Doble Actual", "Marzo");
        dataset.setValue(new Double((double)tabla[2][12]), "Doble Mejorado", "Marzo");
        dataset.setValue(new Double((double)tabla[2][6]), "Suite Jr. Actual", "Marzo");
        dataset.setValue(new Double((double)tabla[2][13]), "Suite Jr. Mejorado", "Marzo");
        
        dataset.setValue(new Double((double)tabla[3][4]), "Simple Actual", "Abril");
        dataset.setValue(new Double((double)tabla[3][11]), "Simple Mejorada", "Abril");
        dataset.setValue(new Double((double)tabla[3][5]), "Doble Actual", "Abril");
        dataset.setValue(new Double((double)tabla[3][12]), "Doble Mejorado", "Abril");
        dataset.setValue(new Double((double)tabla[3][6]), "Suite Jr. Actual", "Abril");
        dataset.setValue(new Double((double)tabla[3][13]), "Suite Jr. Mejorado", "Abril");
        
        dataset.setValue(new Double((double)tabla[4][4]), "Simple Actual", "Mayo");
        dataset.setValue(new Double((double)tabla[4][11]), "Simple Mejorada", "Mayo");
        dataset.setValue(new Double((double)tabla[4][5]), "Doble Actual", "Mayo");
        dataset.setValue(new Double((double)tabla[4][12]), "Doble Mejorado", "Mayo");
        dataset.setValue(new Double((double)tabla[4][6]), "Suite Jr. Actual", "Mayo");
        dataset.setValue(new Double((double)tabla[4][13]), "Suite Jr. Mejorado", "Mayo");
        
        dataset.setValue(new Double((double)tabla[5][4]), "Simple Actual", "Junio");
        dataset.setValue(new Double((double)tabla[5][11]), "Simple Mejorada", "Junio");
        dataset.setValue(new Double((double)tabla[5][5]), "Doble Actual", "Junio");
        dataset.setValue(new Double((double)tabla[5][12]), "Doble Mejorado", "Junio");
        dataset.setValue(new Double((double)tabla[5][6]), "Suite Jr. Actual", "Junio");
        dataset.setValue(new Double((double)tabla[5][13]), "Suite Jr. Mejorado", "Junio");
        
        dataset.setValue(new Double((double)tabla[6][4]), "Simple Actual", "Julio");
        dataset.setValue(new Double((double)tabla[6][11]), "Simple Mejorada", "Julio");
        dataset.setValue(new Double((double)tabla[6][5]), "Doble Actual", "Julio");
        dataset.setValue(new Double((double)tabla[6][12]), "Doble Mejorado", "Julio");
        dataset.setValue(new Double((double)tabla[6][6]), "Suite Jr. Actual", "Julio");
        dataset.setValue(new Double((double)tabla[6][13]), "Suite Jr. Mejorado", "Julio");
        
        dataset.setValue(new Double((double)tabla[7][4]), "Simple Actual", "Agosto");
        dataset.setValue(new Double((double)tabla[7][11]), "Simple Mejorada", "Agosto");
        dataset.setValue(new Double((double)tabla[7][5]), "Doble Actual", "Agosto");
        dataset.setValue(new Double((double)tabla[7][12]), "Doble Mejorado", "Agosto");
        dataset.setValue(new Double((double)tabla[7][6]), "Suite Jr. Actual", "Agosto");
        dataset.setValue(new Double((double)tabla[7][13]), "Suite Jr. Mejorado", "Agosto");
        
        dataset.setValue(new Double((double)tabla[8][4]), "Simple Actual", "Septiembre");
        dataset.setValue(new Double((double)tabla[8][11]), "Simple Mejorada", "Septiembre");
        dataset.setValue(new Double((double)tabla[8][5]), "Doble Actual", "Septiembre");
        dataset.setValue(new Double((double)tabla[8][12]), "Doble Mejorado", "Septiembre");
        dataset.setValue(new Double((double)tabla[8][6]), "Suite Jr. Actual", "Septiembre");
        dataset.setValue(new Double((double)tabla[8][13]), "Suite Jr. Mejorado", "Septiembre");
        
        dataset.setValue(new Double((double)tabla[9][4]), "Simple Actual", "Octubre");
        dataset.setValue(new Double((double)tabla[9][11]), "Simple Mejorada", "Octubre");
        dataset.setValue(new Double((double)tabla[9][5]), "Doble Actual", "Octubre");
        dataset.setValue(new Double((double)tabla[9][12]), "Doble Mejorado", "Octubre");
        dataset.setValue(new Double((double)tabla[9][6]), "Suite Jr. Actual", "Octubre");
        dataset.setValue(new Double((double)tabla[9][13]), "Suite Jr. Mejorado", "Octubre");
        
        dataset.setValue(new Double((double)tabla[10][4]), "Simple Actual", "Noviembre");
        dataset.setValue(new Double((double)tabla[10][11]), "Simple Mejorada", "Noviembre");
        dataset.setValue(new Double((double)tabla[10][5]), "Doble Actual", "Noviembre");
        dataset.setValue(new Double((double)tabla[10][12]), "Doble Mejorado", "Noviembre");
        dataset.setValue(new Double((double)tabla[10][6]), "Suite Jr. Actual", "Noviembre");
        dataset.setValue(new Double((double)tabla[10][13]), "Suite Jr. Mejorado", "Noviembre");
        
        dataset.setValue(new Double((double)tabla[11][4]), "Simple Actual", "Diciembre");
        dataset.setValue(new Double((double)tabla[11][11]), "Simple Mejorada", "Diciembre");
        dataset.setValue(new Double((double)tabla[11][5]), "Doble Actual", "Diciembre");
        dataset.setValue(new Double((double)tabla[11][12]), "Doble Mejorado", "Diciembre");
        dataset.setValue(new Double((double)tabla[11][6]), "Suite Jr. Actual", "Diciembre");
        dataset.setValue(new Double((double)tabla[11][13]), "Suite Jr. Mejorado", "Diciembre");
        
        String titulo="Incremento de precios - Comparación Actual con Mejorado";
        
        JFreeChart chart = ChartFactory.createBarChart3D( titulo, "Tipo", "Valores", dataset, PlotOrientation.VERTICAL, true, true, false );
        
        ChartFrame frame= new ChartFrame(titulo, chart);
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        
    }

    
    
    
}
