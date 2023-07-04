package main.java.io.github.VoidAndCaffeine.voids_mod_loader;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * UpdateNotification
 */
public class UpdateNotification {   
    public static class updatedScreen extends JFrame {
        public updatedScreen(){
            this.setTitle("Mod Update Complete");
            this.setLocationRelativeTo(null);
            this.setAlwaysOnTop(true);
            this.setSize(800, 600);
            this.setBackground(new Color(255,255,255,255));
            this.setVisible(true);

            JOptionPane.showMessageDialog(null,"Mods have been updated. \n\n Please close Minecraft. \n Then go to your minecraft folder. \n move **everything** from the VMLStaging folder to the mods folder \n Lastly restart minecraft \n\n @me on discord if you have any issues -void\n");
        }
    }
} 