package io.github.VoidAndCaffeine.voids_mod_loader;

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

	@Override
	public void onInitialize(ModContainer mod) {
		String[][] modsV = FileUtilities.processVFile();
		VMLlog.info("Hello Quilt world from {}!", modsV[0][0]);

		
	}
}
