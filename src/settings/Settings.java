package settings;

import java.awt.Dimension;

import character.CharacterClass;

public class Settings
{

    // world start settings (world size is (M*x)^n pixels)
    // Cube shaped world with n dimensions, with M^n smaller cubes in it to
    // place initial molecules in
    public final int M = 100;

    // each small_cube to place molecules in, are of size pixel*pixel*pixel...
    // (Should be at least 2*radius of largest molecule)
    public final int pixel = 10;
    public final int n = 2; // n dimensions.

    public final int numbPerGroup = 200;

    public final int getInitialNumbCharacters()
    {
	return numbPerGroup * CharacterClass.values().length;
    }

    // number of time steps
    public static final int steps = 1000;

    // ================ Methods ================
    // Returns a 2D-dimension
    public Dimension getDimensions()
    {
	return new Dimension(M * pixel, M * pixel);
    }

}
