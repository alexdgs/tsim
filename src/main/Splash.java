/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class Splash extends JFrame implements Runnable{
    
    /** Creates new form Splash */
    public Splash() {
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/splash.png"));
        label.setIcon(icon);
        add(label);
        setUndecorated(true);
        setSize(icon.getIconWidth(),icon.getIconHeight());
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        pack();
    }
    
    @Override
    public void run(){
        try{
            this.setVisible(true);
            System.out.println("Showing");
            Thread.sleep(1000);
            this.dispose();
        } catch (InterruptedException ex){
            System.out.print(ex);
        }
    }
}

