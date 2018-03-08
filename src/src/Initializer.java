// Initializer.java
// Ola Olsson
// 2015-10-03

package src;

import java.util.ArrayList;
import java.util.Random;

import character.Character;
import character.Class;
import settings.Settings;

public class Initializer {

	/*
	 * for each small_cube within World_Cube (M^n small_cubes) it is an
	 * nrMolecules/(M^n) chance to place nrMoleculeType in small_cube 1<n<3 =>
	 * evenly placed... n>4 => randomly placed.
	 */

	private Random random = new Random();

	private int numbPALADIN;
	private int numbFIGHTER;
	private int numbROGUE;
	private int numbRANGER;
	private int numbWIZARD;
	private int numbCLERIC;
	private int numbSKELETON;
	private int numbORC;

	private double M2;

	private final Settings settings;

	public Initializer(Settings settings) {
		this.settings = settings;
		numbPALADIN = settings.numbPALADIN;
		numbFIGHTER = settings.numbFIGHTER;
		numbROGUE = settings.numbROGUE;
		numbRANGER = settings.numbRANGER;
		numbWIZARD = settings.numbWIZARD;
		numbCLERIC = settings.numbCLERIC;
		numbSKELETON = settings.numbSKELETON;
		numbORC = settings.numbORC;

		M2 = Math.pow(settings.M, settings.n);
	}

	public ArrayList<Character> getInitalCharacters() {
		ArrayList<Character> molecules = new ArrayList<Character>();

		// Place molecules evenly (max 3 dimensions)

		// 1D
		if (settings.n == 1) {

			for (int x = 0; x < settings.M; x++) {
				double[] position = new double[settings.n];
				position[0] = (int) settings.pixel / 2 + x * settings.pixel;
				chanceOfCharacterPlacement_Evenly(molecules, position);
			}
		}
		// 2D
		else if (settings.n == 2) {

			for (int x = 0; x < settings.M; x++) {
				for (int y = 0; y < settings.M; y++) {
					double[] position = new double[settings.n];
					position[0] = (int) settings.pixel / 2 + x * settings.pixel;
					position[1] = (int) settings.pixel / 2 + y * settings.pixel;
					chanceOfCharacterPlacement_Evenly(molecules, position);
				}
			}
		}
		// 3D
		else if (settings.n == 3) {
			for (int x = 0; x < settings.M; x++) {
				for (int z = 0; z < settings.M; z++) {
					for (int y = 0; y < settings.M; y++) {
						double[] position = new double[settings.n];
						position[0] = (int) settings.pixel / 2 + x
								* settings.pixel;
						position[1] = (int) settings.pixel / 2 + y
								* settings.pixel;
						position[2] = (int) settings.pixel / 2 + y
								* settings.pixel;
						chanceOfCharacterPlacement_Evenly(molecules, position);
					}
				}
			}
		}

		return molecules;
	}

	// For evenly placed molecules
	private void chanceOfCharacterPlacement_Evenly(
			ArrayList<Character> molecules, double[] position) {

		// Create molecule if random nr [0,1] < nr small_cubes/nrMolecules

		if (random.nextDouble() <= numbPALADIN / M2 && numbPALADIN > 0) {
			molecules.add(new Character(Class.PALADIN, position));
			numbPALADIN--;
		}

		else if (random.nextDouble() <= numbFIGHTER / M2 && numbFIGHTER > 0) {
			molecules.add(new Character(Class.FIGHTER, position));
			numbFIGHTER--;
		}

		else if (random.nextDouble() <= numbROGUE / M2 && numbROGUE > 0) {
			molecules.add(new Character(Class.ROGUE, position));
			numbROGUE--;
		}

		else if (random.nextDouble() <= numbRANGER / M2 && numbRANGER > 0) {
			molecules.add(new Character(Class.RANGER, position));
			numbRANGER--;
		}

		else if (random.nextDouble() <= numbWIZARD / M2 && numbWIZARD > 0) {
			molecules.add(new Character(Class.WIZARD, position));
			numbWIZARD--;
		}

		else if (random.nextDouble() <= numbCLERIC / M2 && numbCLERIC > 0) {
			molecules.add(new Character(Class.CLERIC, position));
			numbCLERIC--;
		}

		else if (random.nextDouble() <= numbSKELETON / M2 && numbSKELETON > 0) {
			molecules.add(new Character(Class.SKELETON, position));
			numbSKELETON--;
		}

		else if (random.nextDouble() <= numbORC / M2 && numbORC > 0) {
			molecules.add(new Character(Class.ORC, position));
			numbORC--;
		}

		M2--;

	}

}
