// Character.java
// Ola Olsson
// 2015-10-03

package character;

import java.awt.Color;

import character.LevelUp;
import util.Util;

public class Character
{

    final CharacterClass characterClass;
    private double[] position;
    double current_hp;
    int level = 0;

    // Store stats in some variables
    int STR, DEX, CON, WIS, INT, CHA;
    int AC, Flat, Touch, ReflexSave = 0, SpellBonus = 0;
    int base_hp;

    // Main Attack
    int[] BaseAttack1 = new int[] { 0, -100 };

    // Secondary Attack
    int[] BaseAttack2 = new int[] { -100, -100 };

    // Spells
    int[] Spell = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    int[] Spellnumb = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    int[] SpellnumbMax = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    int weaponDC;
    int sneakDC = 0;
    int layOnHnadsDC = 0, layOnHnadsnumb = 0, layOnHandsnumbMax = 0, smiteEvil = 0, smiteEvilMax = 0;
    int channelEnergyDC = 0;

    public Character(CharacterClass characterClass, double[] position)
    {
	this.position = position;
	this.characterClass = characterClass;
	LevelUp.getLevelUpBonus(this, 0);
    }

    public void move()
    {
	double[] newPosition = Util.add(position, Util.random(position.length, (int) characterClass.getSpeed()));
	if (Util.isInRange(newPosition))
	    position = newPosition;
    }

    // ==== Setters ====
    public void levelUp()
    {
	level++;
	current_hp += characterClass.hp_per_level;
	base_hp += characterClass.hp_per_level;
    }

    public void heal()
    {

	// heal 2*level with aid kit + 1d8 potion per day
	current_hp += Combat.dice(18) + 2 * level;
	if (current_hp > getMaxHP())
	    current_hp = getMaxHP();

	// reset spells
	if (characterClass == CharacterClass.WIZARD || characterClass == CharacterClass.CLERIC)
	    for (int i = 0; i <= 5; i++)
		Spellnumb = SpellnumbMax;

	// Paladin helas with Lay On Hands
	if (characterClass == CharacterClass.PALADIN)
	{
	    for (int i = 1; i < layOnHandsnumbMax; i++)
	    {
		if (current_hp < getMaxHP() && layOnHnadsnumb > 0)
		{
		    layOnHnadsnumb--;
		    current_hp += Combat.dice(layOnHnadsDC);
		    if (current_hp > getMaxHP())
			current_hp = getMaxHP();
		}
	    }
	    // Reset Paladin LayOnHands/day, Smite/day
	    layOnHnadsnumb = layOnHandsnumbMax;
	    smiteEvil = smiteEvilMax;
	}
	// ---
    }

    public void takeDamage(int x)
    {
	if (x <= 0)
	    return;
	else
	    current_hp -= x;
    }

    // ==== Getters ====
    public double[] getPosition()
    {
	return position;
    }

    public Color getColor()
    {
	return characterClass.getColor();
    }

    public CharacterClass getType()
    {
	return characterClass;
    }

    public boolean isDead()
    {
	if (current_hp <= 0)
	    return true;
	else
	    return false;

    }

    public int getMaxHP()
    {
	return base_hp;
    }

    public int getDamage()
    {
	return characterClass.getDamage(level);
    }

    public int getRange()
    {
	return characterClass.getSpeed();
    }

    public boolean isRanged()
    {
	return characterClass.isRanged();
    }

    @Override
    public String toString()
    {
	return characterClass.toString();
    }

}
