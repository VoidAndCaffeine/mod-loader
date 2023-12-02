package io.github.VoidAndCaffeine.voids_mod_loader;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * FileUtilities
 */
public class FileUtilities {
	public static final org.slf4j.Logger VMLlog = VoidsModLoader.VMLlog;
	private static HashMap<String,Mod> modsFile;
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
			VMLlog.info("[VML] File " + file.getName() + " already exists, skipping file creation");
		}
		VMLlog.info("[VML] Opening url stream");
        InputStream in = url.openStream();

		VMLlog.info("[VML] Reading stream to file "+file.getName());
        ReadableByteChannel readableByteChannel = Channels.newChannel(in);
        FileOutputStream fi = new FileOutputStream(file);
        fi.getChannel().transferFrom(readableByteChannel,0,Long.MAX_VALUE);

		VMLlog.info("[VML] Closing url stream for file: "+file.getName());
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
		modsFile = obIN( vFile);
        for(Map.Entry<String,Mod> name: modsFile.entrySet()){
			if(name.getValue().getNeedsUpdate() || !name.getValue().getDestFile().exists()){
				VMLlog.info("[VML] Mod: " + name.getKey() + " needs an update, updating...");
				downloadMod(name.getValue());
				modsFile.get(name.getKey()).setNeedsUpdate();
			}else {
				VMLlog.info("[VML] Mod: " + name + " was checked but did not need an update.");
			}
		}
	}


	public static void saveVfile() throws IOException {
		FileOutputStream fs = new FileOutputStream("mods.versions");
		ObjectOutputStream out = new ObjectOutputStream(fs);
		out.writeObject(modsFile);
		out.close();
	}
	public static String takeHashSHA256(Path modPath){
		try {
			byte[] fileBites = Files.readAllBytes(modPath);
			byte[] hash = MessageDigest.getInstance("SHA256").digest(fileBites);
			return new BigInteger(1,hash).toString(16);
		}catch (Exception e){
			VMLlog.error("[VML] Exception While Hashing",e);
			return null;
		}
	}
	public static String takeHashSHA1(Path modPath){
		try {
			byte[] fileBites = Files.readAllBytes(modPath);
			byte[] hash = MessageDigest.getInstance("SHA1").digest(fileBites);
			return new BigInteger(1,hash).toString(16);
		}catch (Exception e){
			VMLlog.error("[VML] Exception While Hashing",e);
			return null;
		}
	}
	public static boolean compare(File newVFile, File oldVFile){
		HashMap<String,Mod> nv = obIN(newVFile);
		HashMap<String,Mod> ov = obIN(oldVFile);

		if(ov == null){
			VMLlog.error("[VML] old vfile was null, forcing full update");
			return true;
		}

		assert nv != null;
        for(Map.Entry<String, Mod> name : ov.entrySet()){
			Mod mod = name.getValue();
			String hashSHA1;

			if(mod == null){
				VMLlog.info("[VML] Mod: "+name.getKey()+" not found in new Vfile, deleting...");
				deleteMod(name.getValue());
			}
			hashSHA1 = takeHashSHA1(mod.getDestFile().toPath());

			if(hashSHA1==null){
				VMLlog.info("[VML] hash was null updating all");
				return true;
			}
			if(mod.getSha1().equals(hashSHA1)){
				VMLlog.info("[VML] Mod: "+name.getKey()+" does not need an update");
				nv.get(name.getKey()).setNeedsUpdate();
			}else {
				VMLlog.info("[VML] Mod: "+name.getKey()+" needs an update, updating all");
				return true;
			}
		}

		for(Map.Entry<String, Mod> name : nv.entrySet()){
            if (!ov.containsKey(name.getKey())) {
				VMLlog.info("[VML] Mod: "+name.getKey()+" does not exist, updating all");
				return true;
            }
			if(!name.getValue().getDestFile().exists()){
				VMLlog.info("[VML] Mod: "+name.getKey()+" is missing, updating all");
				return true;
			}
			VMLlog.info("[VML] Mod : "+name.getKey()+" was checked but it exists and does not need an update");
		}
		VMLlog.info("[VML] All mods checked and none need updates");
		return false;
	}
}
