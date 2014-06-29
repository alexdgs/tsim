/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Datos;
import view.Simulador;

/**
 *
 * @author User
 */
public class Archivador {
    JFileChooser fc;
    FileNameExtensionFilter filtro;
    Simulador s;
    
    public Archivador(Simulador s) {
        fc = new JFileChooser();
        filtro = new FileNameExtensionFilter("Archivos de Simulación (.tss)", "tss");
        fc.setFileFilter(filtro);
        this.s = s;
    }
    
    public Datos abrir() {
        Datos dat = null;
        fc.setCurrentDirectory(null);
        if(fc.showOpenDialog(s) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                FileInputStream fis = new FileInputStream(file);
                
                try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                    dat = (Datos)ois.readObject();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(s, "Error: el archivo está dañado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(s, "Error al abrir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(s, "Error al leer el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return dat;
    }
    
    public void guardar(Datos d) {
        fc.setCurrentDirectory(null);
        if(fc.showSaveDialog(s) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fc.getSelectedFile();
                FileOutputStream fos;
                String fileName = file.getName();
                
                // If user's file name contains extension, dont put it twice
                if(fileName.length() > 4 && fileName.substring(fileName.length() - 4).equals(".tss"))
                    fos = new FileOutputStream(file.getAbsolutePath());
                else
                    fos = new FileOutputStream(file.getAbsolutePath() + ".tss");
                
                try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    System.out.println("Writing");
                    oos.writeObject(d);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(s, "Error al crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(s, "Error al escribir el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
