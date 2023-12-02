package io.github.VoidAndCaffeine.voids_mod_loader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import io.github.VoidAndCaffeine.voids_mod_loader.Mod;
import java.util.HashMap;
import java.util.Map;

/**
 * stringout
 */

public class stringout {
	private static HashMap<String, Mod> mods = new HashMap<String, Mod>();
	private static File VMlStaging = new File("VMLStaging");
    public static void stringOut() throws IOException, URISyntaxException {
		mods.put("Void's Mod Loader", new Mod(
			new URI("https://github.com/VoidAndCaffeine/mod-loader/releases/download/2.2.3/voids_mod_loader.jar").toURL(),
			new File(VMlStaging,"voids_mod_loader.jar"),
			true)
		);

		mods.put("Quilted Fabric api", new Mod(
			new URI("https://cdn.modrinth.com/data/qvIfYCYJ/versions/GjvWb8WQ/qfapi-7.4.0_qsl-6.1.2_fapi-0.90.0_mc-1.20.1.jar").toURL(),
			new File(VMlStaging,"qfapi.jar"),
			true)
		);

		mods.put("Entity Culling", new Mod(
			new URI("https://github.com/tr7zw/EntityCulling/releases/download/1.6.2-1.20.2/entityculling-fabric-1.6.2-mc1.20.1.jar").toURL(),
			new File(VMlStaging,"entityCulling.jar"),
			true)
		);

		mods.put("Ferrite Core", new Mod(
			new URI("https://cdn.modrinth.com/data/uXXizFIs/versions/unerR5MN/ferritecore-6.0.1-fabric.jar").toURL(),
			new File(VMlStaging,"ferriteCore.jar"),
			true)
		);

		mods.put("Immediately Fast", new Mod(
			new URI("https://cdn.modrinth.com/data/5ZwdcRci/versions/yciHw2oP/ImmediatelyFast-1.2.7%2B1.20.2.jar").toURL(),
			new File(VMlStaging,"immediatelyFast.jar"),
			true)
		);

		mods.put("Krypton", new Mod(
			new URI("https://cdn.modrinth.com/data/fQEb0iXm/versions/jiDwS0W1/krypton-0.2.3.jar").toURL(),
			new File(VMlStaging,"krypton.jar"),
			true)
		);

		mods.put("Lithium", new Mod(
			new URI("https://cdn.modrinth.com/data/gvQqBUqZ/versions/ZSNsJrPI/lithium-fabric-mc1.20.1-0.11.2.jar").toURL(),
			new File(VMlStaging,"lithium.jar"),
			true)
		);

		mods.put("Fast Animations", new Mod(
			new URI("https://cdn.modrinth.com/data/yHf7SALy/versions/5lHXCowj/lunade-fastanim-1.6-1.20.jar").toURL(),
			new File(VMlStaging,"fastAnim.jar"),
			true)
		);

		mods.put("Memory Leak Fix", new Mod(
			new URI("https://cdn.modrinth.com/data/NRjRiSSD/versions/dGlflhb6/memoryleakfix-fabric-1.17%2B-1.1.2.jar").toURL(),
			new File(VMlStaging,"memLeakFix.jar"),
			true)
		);

		mods.put("Midnightlib", new Mod(
			new URI("https://cdn.modrinth.com/data/codAaoxh/versions/YXz8kIQl/midnightlib-fabric-1.4.1.1.jar").toURL(),
			new File(VMlStaging,"midnightlib.jar"),
			true)
		);

		mods.put("Modern Fix", new Mod(
			new URI("https://cdn.modrinth.com/data/nmDcB62a/versions/cuMfXHwk/modernfix-fabric-5.9.3%2Bmc1.20.1.jar").toURL(),
			new File(VMlStaging,"modernFix.jar"),
			true)
		);

		mods.put("Mod Menu", new Mod(
			new URI("https://cdn.modrinth.com/data/mOgUt4GM/versions/lEkperf6/modmenu-7.2.2.jar").toURL(),
			new File(VMlStaging,"modMenu.jar"),
			true)
		);

		mods.put("Not Enough Animations", new Mod(
			new URI("https://cdn.modrinth.com/data/MPCX6s5C/versions/6auoqhMp/notenoughanimations-fabric-1.6.4-mc1.20.jar").toURL(),
			new File(VMlStaging,"notEnoughAnimations.jar"),
			true)
		);

		mods.put("Ok Zoomer", new Mod(
			new URI("https://cdn.modrinth.com/data/aXf2OSFU/versions/XM9JShU4/ok_zoomer-5.0.0-beta.13%2B1.20.1.jar").toURL(),
			new File(VMlStaging,"okZoomer.jar"),
			true)
		);

		mods.put("Reese's Sodium Options", new Mod(
			new URI("https://cdn.modrinth.com/data/Bh37bMuy/versions/hCsMUZLa/reeses_sodium_options-1.6.5%2Bmc1.20.1-build.95.jar").toURL(),
			new File(VMlStaging,"reesesSodiumOptions.jar"),
			true)
		);

		mods.put("Replay Mod", new Mod(
			new URI("https://cdn.modrinth.com/data/Nv2fQJo5/versions/akFkhrL8/replaymod-1.20.1-2.6.13.jar").toURL(),
			new File(VMlStaging,"replayMod.jar"),
			true)
		);

		mods.put("Shulker Box Tooltip", new Mod(
			new URI("https://cdn.modrinth.com/data/2M01OLQq/versions/gVxjsEiQ/shulkerboxtooltip-fabric-4.0.4%2B1.20.1.jar").toURL(),
			new File(VMlStaging,"shulkerBoxTooltip.jar"),
			true)
		);

		mods.put("Sodium extra", new Mod(
			new URI("https://cdn.modrinth.com/data/PtjYWJkn/versions/80a0J5Cn/sodium-extra-0.5.1%2Bmc1.20.1-build.112.jar").toURL(),
			new File(VMlStaging,"sodiumExtra.jar"),
			true)
		);

		mods.put("Sodium", new Mod(
			new URI("https://cdn.modrinth.com/data/AANobbMI/versions/4OZL6q9h/sodium-fabric-mc1.20.1-0.5.3.jar").toURL(),
			new File(VMlStaging,"sodium.jar"),
			true)
		);

		mods.put("SuperMartijn642's Core Lib", new Mod(
			new URI("https://cdn.modrinth.com/data/rOUBggPv/versions/XU7hILi0/supermartijn642corelib-1.1.15-fabric-mc1.20.1.jar").toURL(),
			new File(VMlStaging,"SMjCoreLib.jar"),
			true)
		);

		mods.put("SuperMartijn642's Config Lib", new Mod(
			new URI("https://cdn.modrinth.com/data/LN9BxssP/versions/Ur02nrUT/supermartijn642configlib-1.1.8a-fabric-mc1.20.jar").toURL(),
			new File(VMlStaging,"SMjConfigLib.jar"),
			true)
		);

		mods.put("Simple Voice Chat", new Mod(
			new URI("https://cdn.modrinth.com/data/9eGKb6K1/versions/R8lntTHT/voicechat-quilt-1.20.1-2.4.29.jar").toURL(),
			new File(VMlStaging,"voiceChat.jar"),
			true)
		);

		mods.put("Chatheads", new Mod(
			new URI("https://cdn.modrinth.com/data/Wb5oqrBJ/versions/KLuwIlLd/chat_heads-0.10.26-fabric-1.20.jar").toURL(),
			new File(VMlStaging,"chatheads.jar"),
			true)
		);

		mods.put("Cloth Config", new Mod(
			new URI("https://cdn.modrinth.com/data/9s6osm5g/versions/s7VTKfLA/cloth-config-11.1.106-fabric.jar").toURL(),
			new File(VMlStaging,"clothConfig.jar"),
			true)
		);

		mods.put("Collective", new Mod(
			new URI("https://cdn.modrinth.com/data/e0M1UDsY/versions/VwFgkeo3/collective-1.20.1-7.9.jar").toURL(),
			new File(VMlStaging,"collective.jar"),
			true)
		);

		mods.put("Iris", new Mod(
			new URI("https://cdn.modrinth.com/data/YL57xq9U/versions/DsjYuGMO/iris-mc1.20.1-1.6.10.jar").toURL(),
			new File(VMlStaging,"irisMod.jar"),
			true)
		);

		mods.put("No Chat Reports", new Mod(
			new URI("https://cdn.modrinth.com/data/qQyHxfxd/versions/HeZZR2kF/NoChatReports-FABRIC-1.20.1-v2.2.2.jar").toURL(),
			new File(VMlStaging,"noChatReports.jar"),
			true)
		);

		mods.put("Indium Mod", new Mod(
			new URI("https://cdn.modrinth.com/data/Orvt0mRa/versions/Lue6O9z9/indium-1.0.27%2Bmc1.20.1.jar").toURL(),
			new File(VMlStaging,"indium.jar"),
			true)
		);

		mods.put("Continuity", new Mod(
			new URI("https://cdn.modrinth.com/data/1IjD5062/versions/Z9FJWLMt/continuity-3.0.0-beta.4%2B1.20.1.jar").toURL(),
			new File(VMlStaging,"continuity.jar"),
			true)
		);
    }

	public static void getHashes(){
		VMlStaging.mkdirs();

		try {
			for (Map.Entry<String,Mod> entry:mods.entrySet()) {
				FileUtilities.downloadFile(entry.getValue().getUrl(),entry.getValue().getFile());

				mods.get(entry.getKey()).setSha1(FileUtilities.takeHashSHA1(entry.getValue().getFile().toPath()));
			}
		}catch (Exception e){
			System.out.println("something went wrong");
		}

	}

    public static void saveStringFile() throws IOException {
        FileOutputStream fs = new FileOutputStream("mods.versions");
        ObjectOutputStream out = new ObjectOutputStream(fs);
        out.writeObject(mods);
        out.close();
    }

    public static void main(String[] args) {
        try {
            stringOut();
			getHashes();
			saveStringFile();
        } catch (Exception e) {
            System.out.println("there was an exception");
        }
    }
}
