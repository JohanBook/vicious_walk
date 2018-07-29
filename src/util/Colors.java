package util;

import java.awt.Color;

import character.CharacterClass;

// A class containing all used colors
public class Colors
{

    @SuppressWarnings("exports")
    public static Color getColor(CharacterClass characterClass)
    {
	switch (characterClass)
	{
	case CLERIC:
	    return new Color(255, 255, 255);
	case FIGHTER:
	    return new Color(150, 50, 0);
	case ORC:
	    return new Color(100, 255, 100);
	case PALADIN:
	    return new Color(255, 255, 0);
	case RANGER:
	    return new Color(0, 150, 0);
	case ROGUE:
	    return new Color(0, 0, 0);
	case SKELETON:
	    return new Color(190, 190, 190);
	case WIZARD:
	    return new Color(127, 0, 255);
	}

	return null;
    }
}
