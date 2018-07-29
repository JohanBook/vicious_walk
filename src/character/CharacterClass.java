////////////////////////////////////////////////////////////////
// Class.java
// A class representing a roleplaying character class
// Johan Book
// 2015-10-03
////////////////////////////////////////////////////////////////

package character;

import java.awt.Color;

import util.Colors;

public enum CharacterClass
{

    // All used classes with their stats
    // speed, hp per level, is ranged
    PALADIN(20, 7, false), //
    FIGHTER(30, 7, false), //
    ROGUE(25, 5, true), //
    RANGER(35, 6, true), //
    WIZARD(30, 5, true), //
    CLERIC(30, 7, false), //
    SKELETON(30, 3, false), //
    ORC(20, 6, false); //

    final int hp_per_level;
    final int speed;
    final boolean isRanged;

    private CharacterClass(int speed, int hp_per_level, boolean isRanged)
    {
	this.speed = speed;
	this.hp_per_level = hp_per_level;
	this.isRanged = isRanged;
    }

    public Color getColor()
    {
	return Colors.getColor(this);
    }

    public int getSpeed()
    {
	return speed / 7;
    }

    public int getDamage(int level)
    {
	return 0;// base_damage + level * damage_per_level; TODO
    }

    public boolean isRanged()
    {
	return isRanged;
    }

    public static String classesToString()
    {
	String string = "";
	for (CharacterClass key : CharacterClass.values())
	    string += key + "\t";
	return string;
    }

}
