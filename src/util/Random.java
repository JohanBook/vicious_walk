package util;

public class Random
{
    private static final java.util.Random random = new java.util.Random();

    public static int nextInt(int n)
    {
	if (n <= 0)
	    return 0;
	else
	    return random.nextInt(n);
    }

    public static character.CharacterClass getRandomType()
    {
	return character.CharacterClass.values()[random.nextInt(character.CharacterClass.values().length)];
    }

}