// Combat.java
// Ola Olson
// 2015-10-03

package character;

import character.LevelUp;
import util.Random;

public class Combat {

	static Random random = new Random();

	// test combat
	public static void main(String[] args) {

		double[] position = new double[1];

		Character attacker = new Character(Class.ROGUE, position);
		Character defender = new Character(Class.RANGER, position);
		// LevelUp.getLevelUpBonus(attacker, attacker.level);
		// LevelUp.getLevelUpBonus(defender, defender.level);
		// LevelUp.getLevelUpBonus(attacker, attacker.level);
		// LevelUp.getLevelUpBonus(defender, defender.level);
		// attacker.heal();
		// defender.heal();

		System.out.println("Att: " + attacker.type + " " + attacker.level
				+ ", HP: " + attacker.current_hp + ", Max_HP: "
				+ attacker.base_hp + ", HP/lvl: " + attacker.type.hp_per_level
				+ ", STR: " + attacker.STR + ", DEX: " + attacker.DEX
				+ ", CON: " + attacker.CON + ", WIS: " + attacker.WIS
				+ ", INT: " + attacker.INT + ", CHA: " + attacker.CHA);
		System.out.println("Def: " + defender.type + " " + defender.level
				+ ", HP: " + defender.current_hp + ", Max_HP: "
				+ defender.base_hp + ", HP/lvl: " + defender.type.hp_per_level
				+ ", STR: " + defender.STR + ", DEX: " + defender.DEX
				+ ", CON: " + defender.CON + ", WIS: " + defender.WIS
				+ ", INT: " + defender.INT + ", CHA: " + defender.CHA);
		Character looser = fight(attacker, defender);
		System.out.println("Looser: " + looser);
	}

	public static Character fight(Character attacker, Character defender) {

		boolean smite = false;
		// Checks if paladin have bonus
		if (attacker.type == Class.PALADIN
				&& (defender.type == Class.SKELETON || defender.type == Class.ORC)) {
			if (attacker.smiteEvil > 0) {
				smite = true;
				attacker.smiteEvil--;
			}

		} else if (defender.type == Class.PALADIN
				&& (attacker.type == Class.SKELETON || attacker.type == Class.ORC)) {
			if (attacker.smiteEvil > 0) {
				smite = true;
				defender.smiteEvil--;
			}
		}
		// System.out.println("Smite: " + smite);
		// System.out.println("Ranged");
		// First strike if ranged attacker
		if (attacker.type == Class.ROGUE)
			sneakAttack(attacker, defender);
		else if (attacker.type.isRanged)
			attack(attacker, defender, smite);

		if (defender.isDead())
			return Victory(attacker, defender);
		
		// System.out.println("Initiative");
		// roll initiative
		if (d20() + (attacker.DEX - 10) / 2 < d20() + (defender.DEX - 10) / 2
				&& defender.current_hp > 0)
			attack(defender, attacker, smite);

		if (attacker.isDead())
			return Victory(defender, attacker);
		
		// System.out.println("Fight");
		// Back and forth blows
		for (int i = 0; i < 50; i++) {

			if (attacker.isDead())
				return Victory(defender, attacker);
			
			attack(attacker, defender, smite);

			if (defender.isDead())
				return Victory(attacker, defender);
			
			attack(defender, attacker, smite);

		}
		// System.out.println("Error, more than 50 rounds: Att lvl= "+
		// attacker.level + ", Def lvl= " + defender.level);
		return null;
	}

	private static Character Victory(Character alive, Character not_alive) {

		// Cleric always gets full hp after combat
		if (alive.type == Class.CLERIC)
			alive.current_hp = alive.getMaxHP();
		LevelUp.getLevelUpBonus(alive, alive.level);
		return not_alive;
	}

	private static void attack(Character attack, Character defend, boolean smite) {
		boolean attackWeapon = true;
		int damage = 0;

		// Check if cleric meets undead, uses Channel Energy above lvl 3
		if (attack.type == Class.CLERIC && defend.type == Class.SKELETON
				&& attack.level > 2) {
			damage = dice(attack.channelEnergyDC);
			// System.out.println(attack.type + ": Attacks Skeleton");
		} else {

			// Check Best Spell
			if (attack.type == Class.WIZARD || attack.type == Class.CLERIC)
				for (int i = 5; i >= 0; i--) {
					// System.out.println("Spells lvl "+i+": "+attack.Spellnumb[i]);
					if (attack.Spellnumb[i] > 0) {
						attack.Spellnumb[i]--;
						// System.out.println(attack.type +
						// ": Cast Spell lvl: "+ i);
						// If Wiz Scorching Ray = touch
						if (i == 2 && attack.type == Class.WIZARD) {
							if (d20() + (attack.DEX - 10) / 2 >= defend.Touch)
								damage = dice(attack.Spell[i]);
						}

						// If Cleric Searing Light = touch
						else if (i == 3 && attack.type == Class.CLERIC) {
							if (d20() + (attack.DEX - 10) / 2 >= defend.Touch)
								damage = dice(attack.Spell[i]);
						} else {
							damage = dice(attack.Spell[i]);
							// Reflex Save
							if ((d20() + defend.ReflexSave + (defend.DEX - 10)
									/ 2 >= 10 + i + attack.SpellBonus))
								damage /= 2;
						}
						attackWeapon = false;
						break;
					}
				}
			if (attackWeapon) {
				// System.out.println(attack.type + ": Attacks with weapon");
				int attBonus;
				int dmgBonus = 0;

				if (attack.type.isRanged == true)
					attBonus = (attack.DEX - 10) / 2;
				else
					attBonus = (attack.STR - 10) / 2;

				// Checks if paladin have smite bonus
				if (attack.type == Class.PALADIN && smite == true) {
					attBonus += (attack.CHA - 10) / 2;
					dmgBonus += attack.level;
				}

				// Att main hand
				for (int i = 0; i < 2; i++) {
					if (attack.BaseAttack1[i] > -50)
						if (d20() + attack.BaseAttack1[i] + attBonus >= defend.AC)
							damage = dice(attack.weaponDC) + (attack.STR - 10)
									/ 2 + dmgBonus;
				}
				// Att off hand
				for (int i = 0; i < 2; i++) {
					if (attack.BaseAttack2[i] > -50)
						if (d20() + attack.BaseAttack2[i] + attBonus >= defend.AC)
							damage = dice(attack.weaponDC) + (attack.STR - 10)
									/ 2 + dmgBonus;
				}
			}
			// Paladin heals with Lay On Hands
			if (attack.type == Class.PALADIN
					&& attack.current_hp < attack.getMaxHP() - 6
					&& attack.layOnHnadsnumb > 0) {
				attack.layOnHnadsnumb--;
				attack.current_hp += dice(attack.layOnHnadsDC);
				if (attack.current_hp > attack.getMaxHP())
					attack.current_hp = attack.getMaxHP();
			}
		}
		// System.out.println(defend.type + ", HP: " + defend.current_hp+
		// ", Takes damage: " + damage);
		defend.current_hp -= damage;
		// System.out.println(defend.type + ", HP: " + defend.current_hp);
	}

	private static void sneakAttack(Character attack, Character defend) {
		// System.out.println("Sneak: ");
		int damage = 0;
		// Att main hand
		for (int i = 0; i < 2; i++) {
			if (attack.BaseAttack1[i] > -50)
				if (d20() + attack.BaseAttack1[i] + (attack.DEX - 10) / 2 >= defend.AC)
					damage = dice(attack.weaponDC) + (attack.STR - 10) / 2
							+ dice(attack.sneakDC);
		}
		// Att off hand
		for (int i = 0; i < 2; i++) {
			if (attack.BaseAttack2[i] > -50)
				if (d20() + attack.BaseAttack2[i] + (attack.DEX - 10) / 2 >= defend.AC)
					damage = dice(attack.weaponDC) + (attack.STR - 10) / 2
							+ dice(attack.sneakDC);
		}
		// System.out.println(defend.type + ", HP: " + defend.current_hp+
		// ", Takes damage: " + damage);
		defend.current_hp -= damage;
		// System.out.println(defend.type + ", HP: " + defend.current_hp);
	}

	@SuppressWarnings("static-access")
	private static int d20() {
		int k = random.nextInt(20) + 1;
		// System.out.println("Roll d20: " + k);
		return k;
	}

	@SuppressWarnings("static-access")
	static int dice(int value) {
		// System.out.println("Roll die: " + value);
		int nrDice = 0;
		if (value > 100) {
			nrDice = 10;
			value -= 100;
		} else if (value > 90) {
			nrDice = 9;
			value -= 90;
		} else if (value > 80) {
			nrDice = 8;
			value -= 80;
		} else if (value > 70) {
			nrDice = 7;
			value -= 70;
		} else if (value > 60) {
			nrDice = 6;
			value -= 60;
		} else if (value > 50) {
			nrDice = 5;
			value -= 50;
		} else if (value > 40) {
			nrDice = 4;
			value -= 40;
		} else if (value > 30) {
			nrDice = 3;
			value -= 30;
		} else if (value > 20) {
			nrDice = 2;
			value -= 20;
		} else {
			nrDice = 1;
			value -= 10;
		}
		int k = 0;
		for (int i = nrDice; i > 0; i--)
			k += random.nextInt(value) + 1;
		// System.out.println("Roll damage: " + k);
		return k;
	}

}
