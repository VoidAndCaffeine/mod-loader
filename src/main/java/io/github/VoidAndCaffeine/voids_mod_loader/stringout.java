package io.github.VoidAndCaffeine.voids_mod_loader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import io.github.VoidAndCaffeine.voids_mod_loader.Mod;
import java.util.HashMap;
/**
 * stringout
 */

public class stringout {
	private static HashMap<String, Mod> mods = new HashMap<String, Mod>();
	private static File VMlStaging = new File("VMLStaging");
    public static void stringOut() throws IOException, URISyntaxException {
		mods.put("Void's Mod Loader", new Mod(
			new URI("https://github.com/VoidAndCaffeine/mod-loader/releases/download/2.0/voids_mod_loader.jar").toURL(),
			new File(VMlStaging,"voids_mod_loader.jar"),
			2000,
			true)
		);

		mods.put("Quilted Fabric api", new Mod(
			new URI("https://cdn.modrinth.com/data/qvIfYCYJ/versions/GjvWb8WQ/qfapi-7.4.0_qsl-6.1.2_fapi-0.90.0_mc-1.20.1.jar").toURL(),
			new File(VMlStaging,"qfapi.jar"),
			7061201,
			true)
		);

		mods.put("Entity Culling", new Mod(
			new URI("https://github.com/tr7zw/EntityCulling/releases/download/1.6.2-1.20/entityculling-fabric-1.6.2-mc1.20.jar").toURL(),
			new File(VMlStaging,"entityCulling.jar"),
			162120,
			true)
		);

		mods.put("Ferrite Core", new Mod(
			new URI("https://cdn.modrinth.com/data/uXXizFIs/versions/FCnCG6PS/ferritecore-6.0.0-fabric.jar").toURL(),
			new File(VMlStaging,"ferriteCore.jar"),
			600,
			true)
		);

		mods.put("Immediately Fast", new Mod(
			new URI("https://cdn.modrinth.com/data/5ZwdcRci/versions/4IDo27OL/ImmediatelyFast-1.1.15%2B1.20.1.jar").toURL(),
			new File(VMlStaging,"immediatelyFast.jar"),
			1111201,
			true)
		);

		mods.put("Krypton", new Mod(
			new URI("https://cdn.modrinth.com/data/fQEb0iXm/versions/jiDwS0W1/krypton-0.2.3.jar").toURL(),
			new File(VMlStaging,"krypton.jar"),
			23,
			true)
		);

		mods.put("Lazy DFU", new Mod(
			new URI("https://cdn.modrinth.com/data/hvFnDODi/versions/0.1.3/lazydfu-0.1.3.jar").toURL(),
			new File(VMlStaging,"lazyDFU.jar"),
			13,
			true)
		);

		mods.put("Lithium", new Mod(
			new URI("https://cdn.modrinth.com/data/gvQqBUqZ/versions/ZSNsJrPI/lithium-fabric-mc1.20.1-0.11.2.jar").toURL(),
			new File(VMlStaging,"lithium.jar"),
			11212001,
			true)
		);

		mods.put("Fast Animations", new Mod(
			new URI("https://cdn.modrinth.com/data/yHf7SALy/versions/5lHXCowj/lunade-fastanim-1.6-1.20.jar").toURL(),
			new File(VMlStaging,"fastAnim.jar"),
			16120,
			true)
		);

		mods.put("Memory Leak Fix", new Mod(
			new URI("https://cdn.modrinth.com/data/NRjRiSSD/versions/kLyFtyv2/memoryleakfix-fabric-1.17%2B-1.1.1.jar").toURL(),
			new File(VMlStaging,"memLeakFix.jar"),
			111117,
			true)
		);

		mods.put("Midnightlib", new Mod(
			new URI("https://cdn.modrinth.com/data/codAaoxh/versions/YXz8kIQl/midnightlib-fabric-1.4.1.1.jar").toURL(),
			new File(VMlStaging,"midnightlib.jar"),
			1411,
			true)
		);

		mods.put("Modern Fix", new Mod(
			new URI("https://cdn.modrinth.com/data/nmDcB62a/versions/QVSzLhRT/modernfix-fabric-5.1.1%2Bmc1.20.1.jar").toURL(),
			new File(VMlStaging,"VMlStaging,modernFix.jar"),
			5111201,
			true)
		);

		mods.put("Mod Menu", new Mod(
			new URI("https://cdn.modrinth.com/data/mOgUt4GM/versions/zv46i3PW/modmenu-7.1.0.jar").toURL(),
			new File(VMlStaging,"modMenu.jar"),
			710,
			true)
		);

		mods.put("Not Enough Animations", new Mod(
			new URI("https://cdn.modrinth.com/data/MPCX6s5C/versions/6auoqhMp/notenoughanimations-fabric-1.6.4-mc1.20.jar").toURL(),
			new File(VMlStaging,"notEnoughAnimations.jar"),
			164120,
			true)
		);

		mods.put("Ok Zoomer", new Mod(
			new URI("https://github.com/EnnuiL/OkZoomer/releases/download/5.0.0-beta.12%2B1.20/ok_zoomer-5.0.0-beta.12+1.20.jar").toURL(),
			new File(VMlStaging,"okZoomer.jar"),
			500120,
			true)
		);

		mods.put("Reese's Sodium Options", new Mod(
			new URI("https://cdn.modrinth.com/data/Bh37bMuy/versions/SgSIhHQO/reeses_sodium_options-1.5.1%2Bmc1.20-build.74.jar").toURL(),
			new File(VMlStaging,"reesesSodiumOptions.jar"),
			151120,
			true)
		);

		mods.put("Replay Mod", new Mod(
			new URI("https://cdn.modrinth.com/data/Nv2fQJo5/versions/akFkhrL8/replaymod-1.20.1-2.6.13.jar").toURL(),
			new File(VMlStaging,"replayMod.jar"),
			26131201,
			true)
		);

		mods.put("Shulker Box Tooltip", new Mod(
			new URI("https://cdn.modrinth.com/data/2M01OLQq/versions/gVxjsEiQ/shulkerboxtooltip-fabric-4.0.4%2B1.20.1.jar").toURL(),
			new File(VMlStaging,"shulkerBoxTooltip.jar"),
			4041201,
			true)
		);

		mods.put("Sodium extra", new Mod(
			new URI("https://cdn.modrinth.com/data/PtjYWJkn/versions/kTxAMqAj/sodium-extra-0.4.20%2Bmc1.20-build.103.jar").toURL(),
			new File(VMlStaging,"sodiumExtra.jar"),
			420120,
			true)
		);

		mods.put("Sodium", new Mod(
			new URI("https://cdn.modrinth.com/data/AANobbMI/versions/vgceLbdH/sodium-fabric-mc1.20-0.4.10%2Bbuild.27.jar").toURL(),
			new File(VMlStaging,"sodium.jar"),
			410120,
			true)
		);

		mods.put("Starlight", new Mod(
			new URI("https://cdn.modrinth.com/data/H8CaAYZC/versions/XGIsoVGT/starlight-1.1.2%2Bfabric.dbc156f.jar").toURL(),
			new File(VMlStaging,"starlight.jar"),
			112,
			true)
		);

		mods.put("SuperMartijn642's Core Lib", new Mod(
			new URI("https://cdn.modrinth.com/data/rOUBggPv/versions/vAha0sB8/supermartijn642corelib-1.1.9-fabric-mc1.20.jar").toURL(),
			new File(VMlStaging,"SMjCoreLib.jar"),
			119,
			true)
		);

		mods.put("SuperMartijn642's Config Lib", new Mod(
			new URI("https://cdn.modrinth.com/data/LN9BxssP/versions/AnZ97CRs/supermartijn642configlib-1.1.6-fabric-mc1.20.jar").toURL(),
			new File(VMlStaging,"SMjConfigLib.jar"),
			116120,
			true)
		);

		mods.put("Simple Voice Chat", new Mod(
			new URI("https://cdn.modrinth.com/data/9eGKb6K1/versions/rcd2eEe9/voicechat-fabric-1.20.1-2.4.13.jar").toURL(),
			new File(VMlStaging,"voiceChat.jar"),
			24131201,
			true)
		);

		mods.put("Chatheads", new Mod(
			new URI("https://www.curseforge.com/api/v1/mods/407206/files/4590669/download").toURL(),
			new File(VMlStaging,"chatheads.jar"),
			0,
			true)
		);

		mods.put("Cloth Config", new Mod(
			new URI("https://cdn.modrinth.com/data/9s6osm5g/versions/y0kQixP8/cloth-config-11.0.99-fabric.jar").toURL(),
			new File(VMlStaging,"clothConfig.jar"),
			11099,
			true)
		);

		mods.put("Collective", new Mod(
			new URI("https://cdn.modrinth.com/data/e0M1UDsY/versions/EbteiHrL/collective-1.20.1-6.62.jar").toURL(),
			new File(VMlStaging,"collective.jar"),
			6621201,
			true)
		);

		mods.put("Iris", new Mod(
			new URI("https://cdn.modrinth.com/data/YL57xq9U/versions/URWeWMAt/iris-mc1.20-1.6.4.jar").toURL(),
			new File(VMlStaging,"irisMod.jar"),
			1641201,
			true)
		);

		mods.put("No Chat Reports", new Mod(
			new URI("https://cdn.modrinth.com/data/qQyHxfxd/versions/HeZZR2kF/NoChatReports-FABRIC-1.20.1-v2.2.2.jar").toURL(),
			new File(VMlStaging,"noChatReports.jar"),
			2221201,
			true)
		);

		mods.put("Indium Mod", new Mod(
			new URI("https://github.com/comp500/Indium/releases/download/1.0.21%2Bmc1.20.1/indium-1.0.21+mc1.20.1.jar").toURL(),
			new File(VMlStaging,"indium.jar"),
			10211201,
			true)
		);

		mods.put("Continuity", new Mod(
			new URI("https://cdn.modrinth.com/data/1IjD5062/versions/ImUFj5Gl/continuity-3.0.0-beta.2%2B1.20.jar").toURL(),
			new File(VMlStaging,"continuity.jar"),
			300120,
			true)
		);

		saveStringFile();
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
        } catch (Exception e) {
            System.out.println("there was an exception");
        }
    }
}
