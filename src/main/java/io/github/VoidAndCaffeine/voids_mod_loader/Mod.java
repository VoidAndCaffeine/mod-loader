package io.github.VoidAndCaffeine.voids_mod_loader;

import java.io.File;
import java.io.Serializable;
import java.net.URL;

public class Mod implements Serializable {

	private URL url;
	private File file;
	private String sha1;
	private String sha256;
	private boolean updated;
	private boolean needsUpdate;
	public Mod(URL newUrl, File newFile, boolean wasUpdated){
		url = newUrl;
		file = newFile;
		updated = wasUpdated;
		needsUpdate = true;
	}

	@Override
	public String toString(){
		return "Mod{url="+url+",file="+file+",sha1="+sha1+",sha256"+sha256+",needsUpdate"+needsUpdate+"}";
	}

	// getters
	public URL getUrl() {
		return url;
	}
	public File getFile() {
		return file;
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
	public void setSha1(String hash){
		sha1 = hash;
	}
	public void setSha256(String hash){
		sha256 = hash;
	}
}
