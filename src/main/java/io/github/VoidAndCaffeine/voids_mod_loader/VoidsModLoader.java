package io.github.VoidAndCaffeine.voids_mod_loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.io.github.VoidAndCaffeine.voids_mod_loader.FileUtilities;
import main.java.io.github.VoidAndCaffeine.voids_mod_loader.UpdateNotification;

public class VoidsModLoader implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger VMLlog = LoggerFactory.getLogger("Void's Mod Loader");
    private static File VMlStaging = new File("VMLStaging");

	private URL vFileURL;

	private String[][] mods;
	private String[][] localModsMD;
	private File dummyFile = new File(VMlStaging, "mods.versions");
	private File vFileV = new File("mods.versions");
	private File moverFile = new File("FileMover.java");
	private boolean doUpdate;
	private boolean forceUpdate = false;

	@Override
	public void onInitialize(ModContainer mod) {
		checkUpdates();
	
	}

	public void checkUpdates() {
		try {

            if(!VMlStaging.exists()){
                VMlStaging.mkdirs();
            }
			
		mods = FileUtilities.processVFile();
		VMLlog.info("[VML] Imported vfile from github");

		if(vFileV.exists()){
			localModsMD = FileUtilities.obIN(vFileV);
			String verF = localModsMD[0][2];
			if(Integer.parseInt(mods[0][2]) <= Integer.parseInt(verF)){
				doUpdate = false;
				VMLlog.info("[VML] no updates found.");
				VMLlog.info("[VML] local vfile version {}, remote vfile version {}",Integer.parseInt(verF),Integer.parseInt(mods[0][2]));
			} else{
				doUpdate = true;
				VMLlog.info("[VML] updates found.");
			}
		} else{
			VMLlog.info("[VML] Performing first install.");
			forceUpdate = true;
		}

		if(new File("FORCEUPDATE.txt").exists()){
			VMLlog.info("[VML] FORCEUPDATE.txt found, forcing a full update of all mods");
			forceUpdate = true;
		}

		if(doUpdate || forceUpdate){
			downloadUpdates(mods);
    		vFileURL = new URL("https://github.com/VoidAndCaffeine/mods-versions-file/raw/main/mods.versions");
			URL moverURL = new URL("https://github.com/VoidAndCaffeine/mod-loader/raw/1.20/src/main/java/io/github/VoidAndCaffeine/voids_mod_loader/FileMover.java");
			FileUtilities.downloadFile(vFileURL, vFileV);
			FileUtilities.downloadFile(moverURL, moverFile);

			if (FileUtilities.isWindows()) {
				VMLlog.info("[VML] Detected os is Windows.");
				VMLlog.info("[VML] Checking for FFMPEG");

				File ffmpegZipFile = new File("FFMPEG.exe");

				if(!ffmpegZipFile.exists()){
					VMLlog.info("[VML] FFMPEG.exe is missing, Downloading now");
					URL ffmpegURL = new URL("https://github.com/VoidAndCaffeine/mods-versions-file/raw/main/ffmpeg.exe");
					FileUtilities.downloadFile(ffmpegURL, ffmpegZipFile);
					VMLlog.info("[VML] FFMPEG is now installed");
				}else{
					VMLlog.info("[VML] FFMPEG is already installed.");
				}
					VMLlog.info("[VML] Launching file mover on a 3 second wait.");
				Process fileMover = new ProcessBuilder("java","FileMover.java").start();
					VMLlog.info("[VML] Closing to allow for safe movement of mods to mods folder.");
				System.exit(0);
			} else {
				VMLlog.info("[VML] Detected os is NOT Windows, assuming POSIX compatibily.");
				Process fileMover = new ProcessBuilder("java","FileMover.java").start();
				System.exit(0);
			}
		}
		
		if(dummyFile.exists()){
			UpdateNotification.updatedScreen t = new UpdateNotification.updatedScreen("Mods have been updated but automatic install failed. \n\n Press [OK] to close Minecraft. \n Then go to your minecraft folder. \n move **everything** from the VMLStaging folder to the mods folder \n Lastly restart minecraft \n\n @me on discord if you have any issues -void\n");
			t.dispose();
			System.exit(0);
		}

		} catch (Exception e) {

				VMLlog.error("[VML] Something went wrong and idk what :D -void {}", e);
			// TODO: handle exception
		}
		//VMLlog.info("Hello Quilt world from {}!", modsV[0][0]);
		/*
		if (!(modsVNew[0][0].equals(""))) {
			VMLlog.info("[VML] Successfully updated modlist.");
		} else {
			VMLlog.error("[VML] Modlist was not successfully updated.");
			
		}*/
		
		
	}

	/*
	private String[][] findUpdates(){
		String[][] toReturn = new String[Integer.parseInt(mods[0][0])][4];
		int nextEmpty = 0;
		for (int i = 1; i < mods.length; i++) {
			if(Integer.parseInt(mods[i][3]) == 1){
				toReturn[nextEmpty] = mods[i];
				nextEmpty++;
			}
		}

		return toReturn;

		String[][] buffer = new String[modsVNew.length][4];
		int nextEmpty = 0;
		if(modsVNew.length>modsVOld.length){
			for (int i = modsVOld.length -1 ; i < modsVNew.length; i++) {
				buffer[nextEmpty] = modsVNew[i];
				nextEmpty++;
			}

		}

		for (int i = 0; i < modsVOld.length; i++) {
			if (Integer.parseInt(modsVNew[i][2])>Integer.parseInt(modsVOld[i][2])) {
				buffer[nextEmpty] = modsVNew[i];
			}
		}

		String[][] toReutrn = new String[nextEmpty][4];
		for (int i = 0; i < toReutrn.length; i++) {
			toReutrn[i]= buffer[i];
		}
		return toReutrn;
	}
	*/

	private void downloadUpdates(String[][] updates){

		for (int i = 0; i < updates.length; i++) {
			try {
				URL modurl = new URL(updates[i][3]);
				if(FileUtilities.downloadMod(modurl, updates[i][1])){

				}
				VMLlog.info("[VML] Successfuly downloaded mod | {} | update", updates[i][0]);
			} catch (MalformedURLException e) {
				VMLlog.error("[VML] Check your wifi -void | error on mod {}! \n {}", updates[i][0],e);
			}
		}
	}
}
