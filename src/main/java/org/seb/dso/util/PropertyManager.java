package org.seb.dso.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * TODO replace by Spring Container.
 * 
 * Singleton property manager to hold the base props for all four characters
 * classes.
 * 
 * @author Sebastian
 *
 */
public class PropertyManager {
	private static PropertyManager propertyManager = null;
	private Properties mageProps = new Properties();
	private Properties dkProps = new Properties();
	private String currentClass;

//	 public static int BASE_DMG =
//	 Integer.valueOf(getPropertyManager().getProperty("base.dmg"));
//	 public static int BASE_HP =
//	 Integer.valueOf(getPropertyManager().getProperty("base.hp"));
//	 public static double TWOHAND_MIN_DMG =
//	 Double.valueOf(getPropertyManager().getProperty("twohand.min.dmg"));
//	 public static double TWOHAND_MAX_DMG =
//	 Double.valueOf(getPropertyManager().getProperty("twohand.max.dmg"));
//	 public static int BASE_ARMOR_CLOAK =
//	 Integer.valueOf(getPropertyManager().getProperty("base.armor.cloak"));
//	 public static int BASE_ARMOR_BELT =
//	 Integer.valueOf(getPropertyManager().getProperty("base.armor.belt"));
//	 public static int BASE_ARMOR_HELMET =
//	 Integer.valueOf(getPropertyManager().getProperty("base.armor.helmet"));
//	 public static int BASE_ARMOR_PAULDRONS =
//	 Integer.valueOf(getPropertyManager().getProperty("base.armor.pauldrons"));
//	 public static int BASE_ARMOR_TORSO =
//	 Integer.valueOf(getPropertyManager().getProperty("base.armor.torso"));
//	 public static int BASE_ARMOR_GLOVES =
//	 Integer.valueOf(getPropertyManager().getProperty("base.armor.gloves"));
//	 public static int BASE_ARMOR_BOOTS =
//	 Integer.valueOf(getPropertyManager().getProperty("base.armor.boots"));

	protected PropertyManager() throws IOException {
		super();
		InputStream input = new FileInputStream("mage.properties");
		mageProps.load(input);
		input.close();

		input = new FileInputStream("dragonknight.properties");
		dkProps.load(input);
		input.close(); // why dont they close the freaking stream themselves?
	}

	/**
	 * TODO do the dwarf and ranger props
	 * 
	 * @param name
	 *            String property Name,
	 * @param c
	 *            String character class name
	 * @return
	 */
	public String getProperty(String name, String c) {
		if ("mage".equalsIgnoreCase(c))
			return mageProps.getProperty(name);
		else if ("ranger".equalsIgnoreCase(c))
			return null;
		else if ("dwarf".equalsIgnoreCase(c))
			return null;
		return dkProps.getProperty(name);

	}

	public String getProperty(String name) {
		return this.getProperty(name, this.currentClass);
	}
	
	public void setCurrentClass(String name){
		this.currentClass = name;
	}

	public static PropertyManager getPropertyManager() {
		if (null == propertyManager) {
			synchronized (PropertyManager.class) {
				if (null == propertyManager) {
					try {
						propertyManager = new PropertyManager();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return propertyManager;
	}

}
