package character;

import util.Random;

public class LevelUp {
	
	Random random = new Random();

	public static void getLevelUpBonus(Character character, int level) {

		if (character.getType() == Class.PALADIN)
			Paladin(character, level);
		if (character.getType() == Class.FIGHTER)
			Fighter(character, level);
		if (character.getType() == Class.ROGUE)
			Rogue(character, level);
		if (character.getType() == Class.RANGER)
			Ranger(character, level);
		if (character.getType() == Class.WIZARD)
			Wizard(character, level);
		if (character.getType() == Class.CLERIC)
			Cleric(character, level);

		if (character.getType() == Class.SKELETON)
			Skeleton(character, level);
		if (character.getType() == Class.ORC)
			Orc(character, level);

	}

	private static void Paladin(Character character, int level) {
		if (level == 0) {
			character.STR = 18;
			character.DEX = 11;
			character.CON = 13;
			character.WIS = 8;
			character.INT = 8;
			character.CHA = 14;
			
			character.base_hp=5;
			character.current_hp=5;

			character.AC = 10 + 9 + 4
					+ (character.DEX - 10) / 2; // H.Armor,
			// H.Shield
			character.Flat = 10 + 9 + 4;
			character.Touch = 10 + (character.DEX - 10) / 2;
			character.ReflexSave = 0;

			character.BaseAttack1[0] = 1;
			character.weaponDC = 18;

			character.smiteEvil = 1;
			character.smiteEvilMax = 1;

			character.levelUp();
		}
		if (level == 1) {
			character.BaseAttack1[0] += 1;
			character.layOnHnadsDC = 16;
			character.layOnHandsnumbMax = 3;
			character.ReflexSave += 2;
			character.levelUp();
		}
		if (level == 2) {
			character.BaseAttack1[0] += 1;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 3) {
			character.BaseAttack1[0] += 1;
			character.layOnHnadsDC = 26;
			character.STR++;
			character.layOnHandsnumbMax++;
			character.smiteEvilMax++;
			character.levelUp();
		}
		if (level == 4) {
			character.BaseAttack1[0] += 1;
			character.STR++; // +1 Enhancement Bonus Bond
			character.levelUp();
		}
		if (level == 5) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] = 0;
			character.layOnHnadsDC = 36;
			character.ReflexSave++;
			character.layOnHandsnumbMax++;
			character.levelUp();
		}
		if (level == 6) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.smiteEvilMax++;
			character.levelUp();
		}
		if (level == 7) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.layOnHnadsDC = 46;
			character.STR++;
			character.STR++; // +1 Enhancement Bonus Bond
			character.layOnHandsnumbMax++;
			character.levelUp();
		}
		if (level == 8) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 9) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.layOnHnadsDC = 56;
			character.layOnHandsnumbMax++;
			character.smiteEvilMax++;
			character.levelUp();
		}

	}

	private static void Fighter(Character character, int level) {
		if (level == 0) {
			character.STR = 18;
			character.DEX = 16;
			character.CON = 13;
			character.WIS = 8;
			character.INT = 11;
			character.CHA = 8;

			character.base_hp=5;
			character.current_hp=5;
			
			character.ReflexSave = 0;

			character.AC = 10 + 7 + (character.DEX - 10) / 2;
			character.Flat = 10 + 7;
			character.Touch = 10 + (character.DEX - 10) / 2;

			character.BaseAttack1[0] = 2;// +1 Weapon Focus
			character.weaponDC = 26;
			character.levelUp();
		}

		if (level == 1) {
			character.BaseAttack1[0] += 1;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 2) {
			character.BaseAttack1[0] += 1;
			character.AC++;
			character.levelUp();
		}
		if (level == 3) {
			character.BaseAttack1[0] += 1;
			character.STR++;
			character.STR += 2; // +2 Weapon Specialization
			character.levelUp();
		}
		if (level == 4) {
			character.BaseAttack1[0] += 2;
			character.STR++; // +1 Weapon Training
			character.levelUp();
		}
		if (level == 5) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] = 1;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 6) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.AC++;
			character.levelUp();
		}
		if (level == 7) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.STR++;
			character.levelUp();
		}
		if (level == 8) {
			character.BaseAttack1[0] += 2;
			character.BaseAttack1[1] += 2;
			character.ReflexSave++;
			character.STR++; // +1 Weapon Training
			character.levelUp();
		}
		if (level == 9) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;

			character.levelUp();
		}
	}

	private static void Rogue(Character character, int level) {
		if (level == 0) {
			character.STR = 13;
			character.DEX = 18;
			character.CON = 11;
			character.WIS = 8;
			character.INT = 14;
			character.CHA = 8;

			character.base_hp=4;
			character.current_hp=4;
			
			character.ReflexSave = 2;

			character.AC = 10 + 3 + (character.DEX - 10) / 2;
			character.Flat = 10 + 3 + (character.DEX - 10) / 2;
			character.Touch = 10 + (character.DEX - 10) / 2;

			character.BaseAttack1[0] = -2;
			character.BaseAttack2[0] = -2;
			character.weaponDC = 14;
			character.sneakDC = 16;
			character.levelUp();
		}
		if (level == 1) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 2) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.sneakDC = 26;
			character.levelUp();
		}
		if (level == 3) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.DEX++;
			character.AC++;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 4) {
			character.sneakDC = 36;
			character.levelUp();
		}
		if (level == 5) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 6) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.sneakDC = 46;
			character.levelUp();
		}
		if (level == 7) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.BaseAttack1[1] = -2;
			character.BaseAttack2[1] = -2;
			character.DEX++;
			character.AC++;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 8) {
			character.sneakDC = 56;
			character.levelUp();
		}
		if (level == 9) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.BaseAttack1[1]++;
			character.BaseAttack2[1]++;
			character.ReflexSave++;
			character.levelUp();
		}
	}

	private static void Ranger(Character character, int level) {
		if (level == 0) {
			character.STR = 14;
			character.DEX = 18;
			character.CON = 11;
			character.WIS = 13;
			character.INT = 8;
			character.CHA = 8;

			character.base_hp=5;
			character.current_hp=5;
			
			character.ReflexSave = 2;

			character.AC = 10 + 6 + (character.DEX - 10) / 2;
			character.Flat = 10 + 6;
			character.Touch = 10 + (character.DEX - 10) / 2;

			character.BaseAttack1[0] = -1;
			character.BaseAttack2[0] = -1;
			character.weaponDC = 18;
			character.levelUp();
		}
		if (level == 1) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 2) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.levelUp();
		}
		if (level == 3) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.DEX++;
			character.AC++;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 4) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.levelUp();
		}
		if (level == 5) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.BaseAttack1[1] = -2;
			character.BaseAttack2[1] = -2;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 6) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.BaseAttack1[1]++;
			character.BaseAttack2[1]++;
			character.levelUp();
		}
		if (level == 7) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.BaseAttack1[1]++;
			character.BaseAttack2[1]++;
			character.DEX++;
			character.AC++;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 8) {
			character.levelUp();
		}
		if (level == 9) {
			character.BaseAttack1[0]++;
			character.BaseAttack2[0]++;
			character.BaseAttack1[1]++;
			character.BaseAttack2[1]++;
			character.ReflexSave++;
			character.levelUp();
		}
	}

	private static void Wizard(Character character, int level) {

		if (level == 0) {
			character.STR = 8;
			character.DEX = 14;
			character.CON = 13;
			character.WIS = 8;
			character.INT = 18;
			character.CHA = 11;

			character.base_hp=3;
			character.current_hp=3;
			
			character.ReflexSave = 0;
			character.SpellBonus = 4;

			character.AC = 10 + 4 + (character.DEX - 10) / 2; // Mage
																					// armor
			character.Flat = 10 + 4;
			character.Touch = 10 + (character.DEX - 10) / 2;

			character.BaseAttack1[0] = 0;
			character.weaponDC = 14;
			character.Spell[0] = 13;
			character.Spell[1] = 14; // Burning hands
			character.Spell[2] = 46; // Scorching Ray Touch
			character.Spell[3] = 16; // Lightning Bolt
			character.Spell[4] = 46; // Not good enough damage
			character.Spell[5] = 16; // Cone of Cold

			character.SpellnumbMax[0] = 20;
			character.SpellnumbMax[1] = 2;
			character.Spellnumb[0] = 20;
			character.Spellnumb[1] = 2;
			character.levelUp();
		}
		if (level == 1) {
			character.BaseAttack1[0]++;
			character.SpellnumbMax[1]++;
			character.Spell[1] = 24;
			character.levelUp();
		}
		if (level == 2) {
			character.SpellnumbMax[2] = 3;
			character.Spell[1] = 34;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 3) {
			character.BaseAttack1[0]++;
			character.SpellnumbMax[1]++;
			character.SpellnumbMax[2]++;
			character.Spell[1] = 44;
			character.INT++;
			character.levelUp();
		}
		if (level == 4) {
			character.SpellnumbMax[3] = 3;
			character.Spell[3] = 56;
			character.Spell[1] = 54;
			character.levelUp();
		}
		if (level == 5) {
			character.BaseAttack1[0]++;
			character.SpellnumbMax[2]++;
			character.SpellnumbMax[3]++;
			character.Spell[3] = 66;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 6) {
			character.SpellnumbMax[1]++;
			// character.SpellnumbMax[4] = 3;
			character.Spell[2] = 86;
			character.Spell[3] = 76;
			character.levelUp();
		}
		if (level == 7) {
			character.BaseAttack1[0]++;
			character.SpellnumbMax[1]++;
			character.SpellnumbMax[3]++;
			character.Spell[3] = 86;
			// character.SpellnumbMax[4]++;
			character.INT++;
			character.SpellBonus++;
			character.levelUp();
		}
		if (level == 8) {
			character.SpellnumbMax[2]++;
			character.SpellnumbMax[5] = 3;
			character.Spell[3] = 96;
			character.Spell[5] = 96;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 9) {
			character.BaseAttack1[0]++;
			// character.SpellnumbMax[4]++;
			character.SpellnumbMax[5]++;
			character.Spell[3] = 106;
			character.Spell[5] = 106;
			character.levelUp();
		}
	}

	private static void Cleric(Character character, int level) {

		if (level == 0) {
			character.STR = 13;
			character.DEX = 8;
			character.CON = 14;
			character.WIS = 18;
			character.INT = 8;
			character.CHA = 11;

			character.base_hp=4;
			character.current_hp=4;
			
			character.ReflexSave = 0;
			character.SpellBonus = 4;

			character.AC = 10 + 9 + 4
					+ (character.DEX - 10) / 2;
			character.Flat = 10 + 9 + 4;
			character.Touch = 10 + (character.DEX - 10) / 2;

			character.BaseAttack1[0] = 0;
			character.Spell[3] = 38; // Searing Light Touch
			character.Spell[5] = 96; // Divine Fire

			character.weaponDC = 18;
			character.channelEnergyDC = 16;
			character.levelUp();
		}
		if (level == 1) {
			character.BaseAttack1[0]++;
			character.levelUp();
		}
		if (level == 2) {
			character.BaseAttack1[0]++;
			character.ReflexSave++;
			character.channelEnergyDC = 26;
			character.levelUp();
		}
		if (level == 3) {
			character.AC++;
			character.BaseAttack1[0]++;
			character.WIS++;
			character.AC++;
			character.levelUp();
		}
		if (level == 4) {
			character.SpellnumbMax[3] = 3;
			character.channelEnergyDC = 36;
			character.levelUp();
		}
		if (level == 5) {
			character.BaseAttack1[0]++;
			character.SpellnumbMax[3]++;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 6) {
			character.BaseAttack1[0]++;
			character.channelEnergyDC = 46;
			character.levelUp();
		}
		if (level == 7) {
			character.AC++;
			character.BaseAttack1[0]++;
			character.BaseAttack1[1] = 0;
			character.SpellnumbMax[3]++;
			character.WIS++;
			character.SpellBonus++;
			character.AC++;
			character.levelUp();
		}
		if (level == 8) {
			character.SpellnumbMax[5] = 3;
			character.ReflexSave++;
			character.channelEnergyDC = 56;
			character.levelUp();
		}
		if (level == 9) {
			character.BaseAttack1[0]++;
			character.BaseAttack1[1]++;
			character.SpellnumbMax[5]++;
			character.Spell[5] = 106;
			character.levelUp();
		}
	}

	private static void Skeleton(Character character, int level) {

		if (level == 0) {
			character.STR = 14;
			character.DEX = 14;
			character.CON = 10;
			character.WIS = 8;
			character.INT = 3;
			character.CHA = 10;

			character.base_hp=3;
			character.current_hp=3;
			
			character.ReflexSave = 0;

			character.AC = 10 + 4 + (character.DEX - 10) / 2;
			character.Flat = 10 + 4;
			character.Touch = 10 + (character.DEX - 10) / 2;

			character.BaseAttack1[0] = 0;
			character.weaponDC = 18;
			character.levelUp();
		}
		if (level == 1) {
			character.BaseAttack1[0]++;
			character.levelUp();
		}
		if (level == 2) {

			character.levelUp();
		}
		if (level == 3) {
			character.BaseAttack1[0]++;
			character.levelUp();
		}
		if (level == 4) {
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 5) {
			character.BaseAttack1[0]++;
			character.levelUp();
		}
		if (level == 6) {

			character.levelUp();
		}
		if (level == 7) {
			character.BaseAttack1[0]++;
			character.levelUp();
		}
		if (level == 8) {
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 9) {
			character.BaseAttack1[0]++;
			character.levelUp();
		}
	}

	private static void Orc(Character character, int level) {

		if (level == 0) {
			character.STR = 16;
			character.DEX = 14;
			character.CON = 13;
			character.WIS = 8;
			character.INT = 11;
			character.CHA = 8;

			character.base_hp=5;
			character.current_hp=5;
			
			character.ReflexSave = 0;

			character.AC = 10 + 6 + (character.DEX - 10) / 2;
			character.Flat = 10 + 6;
			character.Touch = 10 + (character.DEX - 10) / 2;

			character.BaseAttack1[0] = 1;
			character.weaponDC = 26;
			character.levelUp();
		}
		if (level == 1) {
			character.BaseAttack1[0] += 1;
			character.levelUp();
		}
		if (level == 2) {
			character.BaseAttack1[0] += 1;
			character.levelUp();
		}
		if (level == 3) {
			character.BaseAttack1[0] += 1;
			character.STR++;
			character.levelUp();
		}
		if (level == 4) {
			character.BaseAttack1[0] += 1;
			character.levelUp();
		}
		if (level == 5) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] = 1;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 6) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.AC++;
			character.levelUp();
		}
		if (level == 7) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.STR++;
			character.levelUp();
		}
		if (level == 8) {
			character.BaseAttack1[0] += 2;
			character.BaseAttack1[1] += 2;
			character.ReflexSave++;
			character.levelUp();
		}
		if (level == 9) {
			character.BaseAttack1[0] += 1;
			character.BaseAttack1[1] += 1;
			character.levelUp();
		}
	}

}
