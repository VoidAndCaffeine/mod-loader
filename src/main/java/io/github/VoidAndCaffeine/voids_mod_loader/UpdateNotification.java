package main.java.io.github.VoidAndCaffeine.voids_mod_loader;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * UpdateNotification
 */
public class UpdateNotification {   
    public static class updatedScreen extends JFrame {
        public updatedScreen(String message){
            this.setTitle("Mod Update Complete");
            this.setLocationRelativeTo(null);
            this.setAlwaysOnTop(true);
            this.setSize(800, 600);
            this.setBackground(new Color(255,255,255,255));
            this.setVisible(true);

            JOptionPane.showMessageDialog(null,message);
        }
    }
} 