/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author A
 */


public class Grafico
{
    
    public void generarPieHabitaciones(int simple, int doble, int suite)
    {
       DefaultPieDataset pieH = new DefaultPieDataset();
       
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
       frame.setSize(600, 450);
       frame.setLocationRelativeTo(null);
    }
    
    public ChartPanel generarGraficoBarras(Object [][] tabla, int opcion)
    {
        String [] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre","Noviembre", "Diciembre"};
        String [] tipo = {"Simple Actual", "Simple Mejorada", "Doble Actual", "Doble Mejorada", "Suite Jr. Actual", "Suite Jr. Mejorada"};
        String [] tipoTerceraOpcion = {"Simple Precio Incrementado", "Simple Ingreso Incrementado", "Simple Mejor Opcion", "Doble Precio Incrementado", "Doble Ingreso Incrementado", "Doble Mejor Opcion",  "Suite Jr. Precio Incrementado", "Suite Jr. Ingreso Incrementado", "Suite Jr. Mejor Opcion"};
   
        int [] primeraOpcion = {7, 16, 8, 17, 9, 18};
        int [] segundaOpcion = {4, 11, 5, 12, 6, 13};
        int [] terceraOpcion ={16, 34, 43, 17, 35, 44, 18, 36, 45};
        
        if(opcion == 1)
            return generarGraficoBarras(tabla, true, meses, tipo, primeraOpcion, "Comparación de demanda insatisfecha Actual con Mejorado");
        else if(opcion == 2)
            return generarGraficoBarras(tabla, true, meses, tipo, segundaOpcion, "Comparación de ingresos Actual con Mejorado");
        else
            return generarGraficoBarras(tabla, false, meses, tipoTerceraOpcion, terceraOpcion, "Comparacion de ingresos Actual con Mejorado. Caso combinado");
    }
    
    public  ChartPanel generarGraficoBarras(Object [][] tabla,  boolean tresD, String [] meses, String [] tipo, int [] posiciones, String titulo)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
 
        for(int i=0; i<12; i++)
            for(int j=0; j<tipo.length; j++)
                dataset.setValue(new Double(""+tabla[i][posiciones[j]]), tipo[j], meses[i]);
        
        JFreeChart chart;
        
        if(tresD)
            chart = ChartFactory.createBarChart3D( titulo, "Meses", "Valores", dataset, PlotOrientation.VERTICAL, true, true, false );
        else
            chart = ChartFactory.createBarChart( titulo, "Meses", "Valores", dataset, PlotOrientation.VERTICAL, true, true, false );
  
        /*ChartFrame frame= new ChartFrame(titulo, chart);
        frame.setVisible(true);
        frame.setSize(1300, 700);
        frame.setLocationRelativeTo(null);  */
        
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }
    
    public ChartPanel generarGraficoDobleEje(Object [][] tabla, int opcion)
    {  
        int [] primeraOpcion = {7, 8, 9, 16, 17, 18};
        int [] segundaOpcion = {4, 5, 6, 11, 12, 13};
        int [] terceraOpcion ={16, 17, 18, 34, 35, 36, 43, 44, 45};
        
        if(opcion == 1)
           return generarGraficoDobleEje(tabla, primeraOpcion, "Comparación de demanda insatisfecha Actual con Mejorado");
        else if(opcion == 2)
           return generarGraficoDobleEje(tabla, segundaOpcion, "Comparación de ingresos Actual con Mejorado");
        else
           return generarGraficoDobleEjeOpcionCombinado(tabla, terceraOpcion, "Comparacion de ingresos Actual con Mejorado. Caso combinado");
    }
    
    public ChartPanel generarGraficoDobleEje(Object [][] tabla, int [] posiciones, String titulo)
    {
        XYSeries actual = new XYSeries("Actual");
        XYSeries mejorado = new XYSeries("Mejorado");
        
        double valor1=0;
        double valor2=0;
        
        for(int i=1; i<=12; i++)
        {
           valor1+=new Double(""+tabla[i-1][posiciones[0]])+new Double(""+tabla[i-1][posiciones[1]])+new Double(""+tabla[i-1][posiciones[2]]);
           valor2+=new Double(""+tabla[i-1][posiciones[3]])+new Double(""+tabla[i-1][posiciones[4]])+new Double(""+tabla[i-1][posiciones[5]]);
           
           actual.add(i, valor1);
           mejorado.add(i, valor2);
           
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        dataset.addSeries(actual);
        dataset.addSeries(mejorado);
        

        JFreeChart chart = ChartFactory.createXYLineChart(titulo,"Meses","Valores",dataset,PlotOrientation.VERTICAL, true, true, false);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        

        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, true);       
        
        
        plot.setRenderer(renderer);
        
        /*ChartFrame frame= new ChartFrame(titulo, chart);
        frame.setVisible(true);
        frame.setSize(1300, 700);
        frame.setLocationRelativeTo(null);*/
        
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }
    
    
    public ChartPanel generarGraficoDobleEjeOpcionCombinado(Object [][] tabla, int [] posiciones, String titulo)
    {
        
        XYSeries precio = new XYSeries("Precio Incrementado");
        XYSeries ingreso = new XYSeries("Ingreso Incrementado");
        XYSeries mejorOpcion = new XYSeries("Mejor Opcion");
        
        double valor1=0;
        double valor2=0;
        double valor3=0;
        
        for(int i=1; i<=12; i++)
        {
           valor1+=new Double(""+tabla[i-1][posiciones[0]])+new Double(""+tabla[i-1][posiciones[1]])+new Double(""+tabla[i-1][posiciones[2]]);
           valor2+=new Double(""+tabla[i-1][posiciones[3]])+new Double(""+tabla[i-1][posiciones[4]])+new Double(""+tabla[i-1][posiciones[5]]);
           valor3+=new Double(""+tabla[i-1][posiciones[6]])+new Double(""+tabla[i-1][posiciones[7]])+new Double(""+tabla[i-1][posiciones[8]]);
               
           precio.add(i, valor1);
           ingreso.add(i, valor2);
           mejorOpcion.add(i, valor3);

        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(precio);
        dataset.addSeries(ingreso);
        dataset.addSeries(mejorOpcion);

        
        JFreeChart chart = ChartFactory.createXYLineChart(titulo,"Meses","Valores",dataset,PlotOrientation.VERTICAL, true, true, false);
        
        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        

        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, true);   
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, true); 

        
        plot.setRenderer(renderer);
        
       /*ChartFrame frame= new ChartFrame(titulo, chart);
        ChartPanel panel = new ChartPanel(chart);
        frame.setVisible(true);
        frame.setSize(1300, 700);
        frame.setLocationRelativeTo(null);  
        */
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }
}
