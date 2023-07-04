package main.java.io.github.VoidAndCaffeine.voids_mod_loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * FileUtilities
 */
public class FileUtilities {
    public static final org.slf4j.Logger VMLlog = LoggerFactory.getLogger("Void's Mod Loader");
    private static File VMlStaging = new File("VMLStaging");
    private static File modsFolder = new File("mods");

    public static boolean downloadMod(URL url,String fileName){
            File file = new File(VMlStaging,fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }

            downloadFile(url, file);

            return true;
        } catch (MalformedURLException e) {
            VMLlog.error("no network connection (or void messed up the url, but probably the internet thing -- definately -- 100%)");;
        } catch (IOException f){
            VMLlog.error("io exception in downloadFile, you souldent see this. if you do, panic, then @me -void");;
        }
        return false;
    }
    public static void downloadFile(URL url,File file) throws FileNotFoundException, IOException{
        if(!file.exists()){
            file.createNewFile();
        }

        InputStream in = url.openStream();
        ReadableByteChannel readableByteChannel = Channels.newChannel(in);
        FileOutputStream fi = new FileOutputStream(file);
        fi.getChannel().transferFrom(readableByteChannel,0,1 << 24);
       
    }

    public static String[][] obIN(File file) throws FileNotFoundException, IOException{
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fin);
            return (String[][]) oin.readObject();
    
        } catch (Exception e) {
            return null;
        }
    }

    public static String[][] processVFile(){
        try {
            URL vFileURL = new URL("https://raw.githubusercontent.com/VoidAndCaffeine/mods-versions-file/main/mods.versions");

            //VMLlog.info("file found");;
            ObjectInputStream in = new ObjectInputStream(vFileURL.openStream());
            VMLlog.info("[VML] Created Object input stream");;
            
            String[][] working = (String[][]) in.readObject();
            VMLlog.info("[VML] Stream to string");;
            return working;
        } catch (IOException g) {
            VMLlog.error("io exception in processVFile, you souldent see this. if you do, panic, then @me -- void");;
        } catch (ClassNotFoundException h) {
            VMLlog.error("ClassNotFoundException in processVFile, you souldent see this. if you do, panic, then @me -- void");;
        }
        String[][] notWorking = {{"1","2","3","4"}};
        return notWorking;
    }

}