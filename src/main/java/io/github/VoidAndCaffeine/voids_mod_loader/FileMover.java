package main.java.io.github.VoidAndCaffeine.voids_mod_loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

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

    public static void moveToMods(String[][] mods){

        try {
        for (int i = 0; i < mods.length; i++) {
                File stagedFile = new File(VMlStaging,mods[i][1]);
                File destFile = new File(modsFolder,mods[i][1]);
                System.out.println("created directory files");
                if (stagedFile.exists()) {
                    if(!destFile.exists()){
                        destFile.createNewFile();
                    }
                    System.out.println("created destination file");

                    Files.move(stagedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("moved file" + mods[i][0]);

                    stagedFile.delete();
                    if(mods[i][0].equals("dummy file")){
                        destFile.delete();
                    }
                    System.out.println("deleted origional");
                }
        }
        } catch (Exception e) {
                System.out.println("error in moveToMods");

        }

    }

    public static void main(String[] args) {

        try {
            
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            // TODO: handle exception
        }

        String[][] mods = vFileIN();
        moveToMods(mods);
           
    }
}