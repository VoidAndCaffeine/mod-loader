package main.java.io.github.VoidAndCaffeine.voids_mod_loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
/**
 * FileUtilities
 */
public class FileUtilities {
    public static final org.slf4j.Logger VMLlog = LoggerFactory.getLogger("Void's Mod Loader");
    private static File VMlStaging = new File("VMLStaging");

    public static boolean downloadFile(URL url,String fileName){
            File file = new File(VMlStaging,fileName);
        try {

            if(!VMlStaging.exists()){
                VMlStaging.mkdirs();
            }
            if(!file.exists()){
                file.createNewFile();
            }

            InputStream in = url.openStream();
            ReadableByteChannel readableByteChannel = Channels.newChannel(in);
            FileOutputStream fi = new FileOutputStream(file);
            fi.getChannel().transferFrom(readableByteChannel,0,1 << 24);

            return true;

        } catch (MalformedURLException e) {
            VMLlog.error("no network connection (or void messed up the url, but probably the internet thing -- definately -- 100%)");;
        } catch (IOException f){
            VMLlog.error("io exception in downloadFile, you souldent see this. if you do, panic, then @me -- void");;
        }
        return false;

    }

    public static String[][] processVFile(int mode){
        try {
            URL vFileURL = new URL("https://raw.githubusercontent.com/VoidAndCaffeine/mods-versions-file/main/mods.versions");

            //VMLlog.info("file found");;
            InputStream workingStream = null;
            if(mode == 0){
                workingStream = vFileURL.openStream();
            }else if(mode == 1){
                File localVfile = new File(VMlStaging,"mods.versions");
                FileInputStream fin = new FileInputStream(localVfile);
                workingStream = fin;
            }
            ObjectInputStream in = new ObjectInputStream(workingStream);
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