package settings;

import java.awt.Dimension;

public class Settings {

	// world start settings (world size is (M*x)^n pixels)
	// Cube shaped world with n dimensions, with M^n smaller cubes in it to
	// place initial molecules in
	public final int M = 100;

	// each small_cube to place molecules in, are of size pixel*pixel*pixel...
	// (Should be at least 2*radius of largest molecule)
	public final int pixel = 10;
	public final int n = 2; // n dimensions.

	public final int numbPALADIN = 200;
	public final int numbFIGHTER = 200;
	public final int numbROGUE = 200;
	public final int numbRANGER = 200;
	public final int numbWIZARD = 200;
	public final int numbCLERIC = 200;
	public final int numbSKELETON = 200;
	public final int numbORC = 200;

	public final int initialTotalNumbCharacters = numbPALADIN + numbFIGHTER + numbROGUE
			+ numbRANGER + numbWIZARD + numbCLERIC + numbSKELETON + numbORC;

	// number of time steps
	public static final int steps = 1000;

	// ================ Methods ================
	// Returns a 2D-dimension
	public Dimension getDimensions() {
		return new Dimension(M * pixel, M * pixel);
	}
	

}
