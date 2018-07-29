////////////////////////////////////////////////
// Collision.java
// Johan Book
////////////////////////////////////////////////

package react;

import character.Character;
import util.Util;

// A class for determining whether a reaction do happen or not.
public class Collision
{

    // Check if attacker will attack defender
    // Returns null if no reaction happens
    // otherwise returns reference to loser
    public static Character check(Character attacker, Character defender)
    {
	double distance = Util.distance(attacker.getPosition(), defender.getPosition());
	if (distance <= attacker.getRange())
	    return character.Combat.fight(attacker, defender);
	else
	    return null;
    }
}