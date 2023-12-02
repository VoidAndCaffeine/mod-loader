package io.github.VoidAndCaffeine.voids_mod_loader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * FileUtilities
 */
public class FileUtilities {
	public static final org.slf4j.Logger VMLlog = VoidsModLoader.VMLlog;
    public static boolean downloadMod(@NotNull Mod mod){
		File file = mod.getFile();
        try {
            downloadFile(mod.getUrl(), file);
            return true;
        } catch (MalformedURLException e) {
            VMLlog.error("[VML] no network connection (or void messed up the url, but probably the internet thing -- definitely -- 100%)");;
        } catch (IOException f){
			VMLlog.error("[VML] io exception in downloadFile, you shouldn't see this. if you do, panic, then @me -void");;
        }
        return false;
    }

    public static boolean isWindows(){
		try{
			return System.getProperty("os.name").startsWith("Windows");
		}catch (Exception e){
			VMLlog.error("[VML] Failed to get OS assuming unix compatible");
			return false;
		}
    }

    public static void downloadFile(URL url, @NotNull File file) throws MalformedURLException, FileNotFoundException, IOException{
		if(file.createNewFile()){
			VMLlog.info("[VML] Creating new file: " + file.getName());
		}else {
			VMLlog.info("[VML] File " + file.getName() + " already exists, skipping");
		}
		VMLlog.info("[VML] Opening url stream");
        InputStream in = url.openStream();

		VMLlog.info("[VML] Reading stream to file");
        ReadableByteChannel readableByteChannel = Channels.newChannel(in);
        FileOutputStream fi = new FileOutputStream(file);
        fi.getChannel().transferFrom(readableByteChannel,0,Long.MAX_VALUE);

		VMLlog.info("[VML] Closing url stream");
		in.close();
    }

    public static @NotNull HashMap<String,Mod> obIN(File file){
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream oin = new ObjectInputStream(fin);

			return (HashMap<String, Mod>) oin.readObject();
        } catch (Exception e) {
			return new HashMap<String,Mod>();
        }
    }
	public static void deleteMod(Mod mod){
		throw new UnsupportedOperationException();
	}
	public static void update(File vFile){
		HashMap<String,Mod> mods = obIN( vFile);

        for(Map.Entry<String,Mod> name: mods.entrySet()){
			if(name.getValue().getNeedsUpdate() || name.getValue().getFile().exists()){
				VMLlog.info("[VML] Mod: " + name.getKey() + " needs an update, updating...");
				downloadMod(name.getValue());
			}else {
				VMLlog.info("[VML] Mod: " + name + " was checked but did not need an update.");
			}
		}
	}

	public static boolean compare(File newVFile, File oldVFile){
		boolean updated = false;
		HashMap<String,Mod> nv = obIN(newVFile);
		HashMap<String,Mod> ov = obIN(oldVFile);

		if(ov == null){
			VMLlog.error("[VML] old vfile was null, forcing full update");
			return true;
		}

		assert nv != null;
        for(Map.Entry<String, Mod> name : ov.entrySet()){
			String key = name.getKey();
			Mod mod = nv.get(key);

			if(mod == null){
				VMLlog.info("[VML] Mod: "+name.getKey()+" not found in new Vfile, deleting...");
				deleteMod(name.getValue());
			} else if (!mod.getUpdated()){
				mod.setNeedsUpdate();
			}else {
				return true;
			}

		}
		for(Map.Entry<String, Mod> name : nv.entrySet()){
            if (!ov.containsKey(name.getKey())) {
				return true;
            }

			if(!name.getValue().getDestFile().exists()){
				return true;
			}
		}

		return false;
	}
}
