////////////////////////////////////////////////////////////////
// Class.java
// A class representing a roleplaying character class
// Johan Book
// 2015-10-03
////////////////////////////////////////////////////////////////

package character;

import java.awt.Color;

import util.Colors;

public enum Class {

	// All used classes with their stats
	PALADIN(Colors.paladin(), 20, 7, false), //
	FIGHTER(Colors.fighter(), 30, 7, false), //
	ROGUE(Colors.rogue(), 25, 5, true), //
	RANGER(Colors.ranger(), 35, 6, true), //
	WIZARD(Colors.wizard(), 30, 5, true), //
	CLERIC(Colors.cleric(), 30, 7, false), //
	SKELETON(Colors.skeleton(), 30, 3, false), //
	ORC(Colors.orc(), 20, 6, false); //

	final int hp_per_level;
	final int speed;
	private final Color color; // color used in the GUI for the particle
	final boolean isRanged;

	private Class(Color color, int speed, int hp_per_level, boolean isRanged) {
		this.color = color;
		this.speed = speed;
		this.hp_per_level = hp_per_level;
		this.isRanged = isRanged;

	}

	public Color getColor() {
		return color;
	}

	public int getSpeed() {
		return speed / 7;
	}

	public int getDamage(int level) {
		return 0;// base_damage + level * damage_per_level;
	}

	public boolean isRanged() {
		return isRanged;
	}

	public static String classesToString() {
		String string = "";
		for (Class key : Class.values())
			string += key + "\t";
		return string;
	}

}
