////////////////////////////////////////////////
// Collision.java
// Johan Book
////////////////////////////////////////////////

package react;

import character.Character;
import util.Pair;
import util.Util;

// A class for determining whether a reaction do happen or not.
public class Collision {

	// Check if a will attack b
	// Returns <false, null> if no reaction happens
	// Returns <true, c> if a and b fight and c is the one who dies
	public static Pair<Boolean, Character> check(Character a, Character b) {
		int collision_distance = a.getRange();

		// Determine if distance is less than collision distance
		double distance = Util.distance(a.getPosition(), b.getPosition());
		if (distance <= collision_distance)
			return new Pair<Boolean, Character>(true, character.Combat.fight(a,b));

		// Else return false
		return new Pair<Boolean, Character>(false, null);
	}	
}
