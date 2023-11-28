package io.github.VoidAndCaffeine.voids_mod_loader;

import java.net.URI;

public class Mod {
	private URI uri;
	private String name;
	private String fileName;
	private int version;
	private boolean updated;
	private boolean needsUpdate;
	public Mod(URI newUri,String newName,String newFileName,int newVersion,boolean newUpdated){
		uri = newUri;
		name = newName;
		fileName = newFileName;
		version = newVersion;
		updated = newUpdated;
		needsUpdate = true;
	}

	// getters
	public URI getUri() {
		return uri;
	}
	public String getName() {
		return name;
	}
	public String getFileName() {
		return fileName;
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
	public boolean setNeedsUpdate(){
		return needsUpdate;
	}
}
