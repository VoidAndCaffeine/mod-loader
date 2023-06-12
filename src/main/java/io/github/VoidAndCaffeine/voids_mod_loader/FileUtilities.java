package main.java.io.github.VoidAndCaffeine.voids_mod_loader;

import java.net.URL;
import java.io.ObjectInputStream;
/**
 * FileUtilities
 */
public class FileUtilities {

    public static String[][] processVFile(){
        try {
            URL vFileURL = new URL("https://raw.githubusercontent.com/VoidAndCaffeine/mods-versions-file/main/mods.versions");
            ObjectInputStream in = new ObjectInputStream(vFileURL.openStream());
            String[][] working = (String[][]) in.readObject();
            return working;

        } catch (Exception e) {
            String[][] broken = {{"1","2","3"},{"4","5","6"}};
            return broken;
        }
    }

}