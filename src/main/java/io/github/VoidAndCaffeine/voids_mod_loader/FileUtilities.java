package io.github.VoidAndCaffeine.voids_mod_loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * FileUtilities
 */
public class FileUtilities {
    public static final org.slf4j.Logger VMLlog = LoggerFactory.getLogger("Void's Mod Loader");
    private static File VMlStaging = new File("VMLStaging");
    private static File modsFolder = new File("mods");
    private static String OS = null;
	private static File[] exist = existing();

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

    public static boolean isWindows(){
        if(OS == null){
            OS = System.getProperty("os.name");
        }
        return OS.startsWith("Windows");
    }

    public static void downloadFile(URL url,File file) throws FileNotFoundException, IOException{
        if(!file.exists()){
            file.createNewFile();
        }

        InputStream in = url.openStream();
        ReadableByteChannel readableByteChannel = Channels.newChannel(in);
        FileOutputStream fi = new FileOutputStream(file);
        fi.getChannel().transferFrom(readableByteChannel,0,Long.MAX_VALUE);

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

	private static File[] existing(){
		return modsFolder.listFiles();
	}

	private static boolean checkMod(String mod) throws IOException {
        for (File file : exist) {
            if (file.getName().equals(mod)) {
                return true;
            }
        }
		return false;
	}


	public static String[][] processVFile(){
        try {
            URL vFileURL = new URL("https://github.com/VoidAndCaffeine/mods-versions-file/raw/main/mods.versions");

            //VMLlog.info("file found");;
            ObjectInputStream in = new ObjectInputStream(vFileURL.openStream());
            VMLlog.info("[VML] Created Object input stream");

            String[][] working = (String[][]) in.readObject();

			if(exist != null){
				for (String[] mod : working) {
					if (checkMod(mod[1])) {
						mod[4] = "false";
					}
					if (mod[2]){

					}
				}
			}

            VMLlog.info("[VML] Stream to string");
            return working;
        } catch (IOException g) {
            VMLlog.error("io exception in processVFile, you shouldn't see this. if you do, panic, then @me -- void");
        } catch (ClassNotFoundException h) {
            VMLlog.error("ClassNotFoundException in processVFile, you shouldn't see this. if you do, panic, then @me -- void");;
        }
        String[][] notWorking = {{"1","2","3","4"}};
        return notWorking;
    }

}
