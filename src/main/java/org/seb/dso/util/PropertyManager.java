package org.seb.dso.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

	private static Properties prop = new Properties();
	private static InputStream input = null;
	public static int BASE_DMG = Integer.valueOf(getPropertyManager().getProperty("base.dmg"));
	public static int BASE_HP = Integer.valueOf(getPropertyManager().getProperty("base.hp"));
	public static double TWOHAND_MIN_DMG = Double
	.valueOf(getPropertyManager().getProperty("twohand.min.dmg"));
	public static double TWOHAND_MAX_DMG = Double
	.valueOf(getPropertyManager().getProperty("twohand.max.dmg"));
	public static int BASE_ARMOR_CLOAK = Integer
	.valueOf(getPropertyManager().getProperty("base.armor.cloak"));
	public static int BASE_ARMOR_BELT = Integer
	.valueOf(getPropertyManager().getProperty("base.armor.belt"));
	public static int BASE_ARMOR_HELMET = Integer
	.valueOf(getPropertyManager().getProperty("base.armor.helmet"));
	public static int BASE_ARMOR_PAULDRONS = Integer
	.valueOf(getPropertyManager().getProperty("base.armor.pauldrons"));
	public static int BASE_ARMOR_TORSO = Integer
	.valueOf(getPropertyManager().getProperty("base.armor.torso"));
	public static int BASE_ARMOR_GLOVES = Integer
	.valueOf(getPropertyManager().getProperty("base.armor.gloves"));
	public static int BASE_ARMOR_BOOTS = Integer
	.valueOf(getPropertyManager().getProperty("base.armor.boots"));
	public static int BASE_CRITICAL44 = Integer
	.valueOf(getPropertyManager().getProperty("base.critical.44"));

	public PropertyManager() throws IOException {
		super();
		input = new FileInputStream("base.properties");

		// load a properties file
		prop.load(input);
	}

	public String getProperty(String name) {
		return prop.getProperty(name);

	}

	public static PropertyManager getPropertyManager() {
		try {
			return new PropertyManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
