package io.github.VoidAndCaffeine.voids_mod_loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VoidsModLoader implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger VMLlog = LoggerFactory.getLogger("Void's Mod Loader");
	private static File VMlStaging = new File("VMLStaging");
	private URL vFileURL;
	private URL moverURL;
	private File oldVFile = new File(VMlStaging, "mods.versions");
	private File newVFile = new File("mods.versions");
	private File moverFile = new File("FileMover.java");
	private boolean doUpdate = false;
	private boolean forceUpdate = false;

	@Override
	public void onInitialize(ModContainer mod) {
		checkUpdates();
	}

	public void checkUpdates(){
		try {
			vFileURL = new URI("https://github.com/VoidAndCaffeine/mod-loader/raw/1.20.1/mods.versions").toURL();
			moverURL = new URI("https://github.com/VoidAndCaffeine/mod-loader/raw/1.20.1/src/main/java/io/github/VoidAndCaffeine/voids_mod_loader/FileMover.java").toURL();

			if(!VMlStaging.mkdirs()){
				VMLlog.info("[VML] Creating new VMLStaging directory");
			}
			if(new File("FORCEUPDATE.txt").exists()){
				VMLlog.info("[VML] FORCEUPDATE.txt found, forcing a full update of all mods");
				forceUpdate = true;
			}
			if(newVFile.exists()){
				newVFile.renameTo(oldVFile);
				FileUtilities.downloadFile(vFileURL, newVFile);
				FileUtilities.downloadFile(moverURL, moverFile);
				if(FileUtilities.compare(newVFile,oldVFile)){
					doUpdate = true;
				}
			} else{
				VMLlog.info("[VML] Performing first install.");
				forceUpdate = true;
				FileUtilities.downloadFile(vFileURL, newVFile);
				FileUtilities.downloadFile(moverURL, moverFile);
			}
			if (doUpdate||forceUpdate){
				VMLlog.info("[VML] Updates needed, updating...");
				FileUtilities.update(newVFile);
				VMLlog.info("[VML] Updates completed, installing...");
				install();
			}else {
				VMLlog.info("[VML] No updates needed allowing normal launch process.");
			}
		}catch (MalformedURLException l){
			VMLlog.error("[VML] the hardcoded url is wrong",l);
		}catch (URISyntaxException i){
			VMLlog.error("[VML] the hardcoded uri is wrong",i);
		}catch (Exception e){
			VMLlog.error("[VML] Something went wrong and idk what",e);
		}
	}

	private void install(){
		try {
			if (FileUtilities.isWindows()) {
				VMLlog.info("[VML] Detected os is Windows.");
				VMLlog.info("[VML] Checking for FFMPEG");
				File ffmpegFile = new File("FFMPEG.exe");
				if(!ffmpegFile.exists()){
					VMLlog.info("[VML] FFMPEG.exe is missing, Downloading now");
					URL ffmpegURL = new URI("https://github.com/VoidAndCaffeine/mods-versions-file/raw/main/ffmpeg.exe").toURL();
					FileUtilities.downloadFile(ffmpegURL, ffmpegFile);
					VMLlog.info("[VML] FFMPEG is now installed");
				}else{
					VMLlog.info("[VML] FFMPEG is already installed.");
				}
				Process fileMover = new ProcessBuilder("java","FileMover.java").start();
				VMLlog.info("[VML] Closing to allow for safe movement of mods to mods folder.");
				System.exit(0);
			} else {
				VMLlog.info("[VML] Detected os is NOT Windows, assuming POSIX compatibily.");
				Process fileMover = new ProcessBuilder("java","FileMover.java").start();
				System.exit(0);
			}
		}catch (MalformedURLException l){
			VMLlog.error("[VML] the hardcoded url is wrong",l);
		}catch (Exception e){
			VMLlog.error("[VML] Something went wrong and idk what",e);
		}
	}
}
