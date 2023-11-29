package io.github.VoidAndCaffeine.voids_mod_loader;

import java.io.File;
import java.io.Serializable;
import java.net.URL;

public class Mod implements Serializable {

	private URL url;
	private File file;
	private int version;
	private boolean updated;
	private boolean needsUpdate;
	public Mod(URL newUrl, File newFile, int newVersion, boolean newUpdated){
		url = newUrl;
		file = newFile;
		version = newVersion;
		updated = newUpdated;
		needsUpdate = true;
	}

	@Override
	public String toString(){
		return "Mod{url="+url+",file="+file+",version="+version+",updated="+updated+",needsUpdate"+needsUpdate+"}";
	}

	// getters
	public URL getUrl() {
		return url;
	}
	public File getFile() {
		return file;
	}
	public int getVersion() {
		return version;
	}
	public boolean getUpdated(){
		return updated;
	}
	public boolean getNeedsUpdate(){
		return needsUpdate;
	}
	// setters
	public void setNeedsUpdate(){
		needsUpdate = false;
	}
}
