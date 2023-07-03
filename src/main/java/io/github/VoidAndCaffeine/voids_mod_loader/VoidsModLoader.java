package io.github.VoidAndCaffeine.voids_mod_loader;

import java.net.MalformedURLException;
import java.net.URL;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.io.github.VoidAndCaffeine.voids_mod_loader.FileUtilities;

public class VoidsModLoader implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger VMLlog = LoggerFactory.getLogger("Void's Mod Loader");
	private String[][] modsVNew;
	private String[][] modsVOld;

	@Override
	public void onInitialize(ModContainer mod) {
		modsVNew = FileUtilities.processVFile(0);
		modsVOld = FileUtilities.processVFile(1);

		//VMLlog.info("Hello Quilt world from {}!", modsV[0][0]);
		if (!(modsVNew[0][0].equals(""))) {
			VMLlog.info("[VML] Successfully updated modlist.");
		} else {
			VMLlog.error("[VML] Modlist was not successfully updated.");
			
		}
		
	}

	private String[][] findUpdates(){
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

	private void downloadUpdates(String[][] updates){

		for (int i = 0; i < updates.length; i++) {
			try {
				URL modurl = new URL(updates[i][3]);
				FileUtilities.downloadFile(modurl, updates[i][1]);
				VMLlog.info("[VML] Successfuly downloaded mod updates.");
			} catch (MalformedURLException e) {
				VMLlog.error("[VML] Check your wifi -void | error on mod {}!", updates[i][0]);
			}
		}
		try {
            URL vFileURL = new URL("https://raw.githubusercontent.com/VoidAndCaffeine/mods-versions-file/main/mods.versions");
			FileUtilities.downloadFile(vFileURL,"mods.versions");
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
}
