package io.github.VoidAndCaffeine.voids_mod_loader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 * FileMover
 */
public class FileMover {
    private static File VMlStaging = new File("VMLStaging");
	private static File modsFolder = new File("mods");
	private static File dummyFile = new File(VMlStaging, "mods.versions");

    public static String[][] vFileIN(){
        try {
            FileInputStream fin = new FileInputStream(dummyFile);
            ObjectInputStream oin = new ObjectInputStream(fin);
            String[][] toReturn = (String[][]) oin.readObject();
            oin.close();
            fin.close();
            return toReturn;

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("error in vFileIN()");
            return null;
        }
    }

	private static void delQFAPI() throws IOException {
		File[] qfapis = modsFolder.listFiles(
			new FilenameFilter() {
				@Override
				public boolean accept(File file, String s) {
					return s.matches("qfapi-.*jar");
				}
			}
		);
		if(qfapis == null){
			return;
		}
        for (File qfapi : qfapis) {
            qfapi.delete();
        }
	}

    public static void moveToMods(String[][] mods){

        try {
			//delQFAPI();
            for (String[] mod : mods) {
                File stagedFile = new File(VMlStaging, mod[1]);
                File destFile = new File(modsFolder, mod[1]);
                System.out.println("created directory files");
                if (stagedFile.exists()) {
                    if (!destFile.exists()) {
                        destFile.createNewFile();
                    }
                    System.out.println("created destination file");

                    Files.move(stagedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("moved file" + mod[0]);

                    stagedFile.delete();
                    System.out.println("deleted original");
                }
            }
        } catch (Exception e) {
                System.out.println("error in moveToMods");
        }
    }

    public static class movingScreen extends JFrame {
        public movingScreen(String message){
            this.setTitle("Moving Mods");
            this.setLocationRelativeTo(null);
            this.setAlwaysOnTop(true);
            this.setSize(400, 200);
            this.setBackground(new Color(255,255,255,255));
            this.setVisible(true);

            JLabel j = new JLabel(message);
            this.add(j);
        }
    }


    public static class updatedScreen extends JFrame {
        public updatedScreen(String message){
            this.setTitle("Mod Update Complete");
            this.setLocationRelativeTo(null);
            this.setAlwaysOnTop(false);
            this.setSize(400, 200);
            this.setBackground(new Color(255,255,255,255));
            this.setVisible(true);

            JOptionPane.showMessageDialog(null,message);
        }
    }

    public static void main(String[] args) {

        movingScreen mScreen = new movingScreen("Please wait while your mods are installed.");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            // TODO: handle exception
        }

        String[][] mods = vFileIN();
        moveToMods(mods);

        mScreen.dispose();
        updatedScreen t = new updatedScreen("Your mods were successfully updated! \n Please relaunch your game. :)");
		t.dispose();
		System.exit(0);

    }
}
