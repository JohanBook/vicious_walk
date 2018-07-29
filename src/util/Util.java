package util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import character.CharacterClass;

// A class containing some useful tools
public class Util {
	private static java.util.Random random = new java.util.Random(0);

	// Vector addition
	public static double[] add(double[] a, double[] b) {
		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++)
			c[i] = a[i] + b[i];
		return c;
	}

	// Scalar times a vector
	public static double[] multiply(double[] a, double x) {
		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++)
			c[i] = (int) (a[i] * x);
		return c;
	}

	public static double sum(double[] a) {
		double c = 0;
		for (double x : a)
			c += x;
		return c;
	}

	public static double[] sum(double[]... a) {
		double[] c = new double[a[0].length];
		for (double[] x : a)
			c = add(c, x);
		return c;
	}

	public static double[] subtract(double[] a, double[] b) {
		return add(a, multiply(b, -1));
	}

	// Scalar product
	public static double multiply(double[] a, double[] b) {
		int c = 0;
		for (int i = 0; i < a.length; i++)
			c += a[i] * b[i];
		return c;
	}

	public static double square(double[] a) {
		return multiply(a, a);
	}

	public static double[] abs(double[] a) {
		double[] c = new double[a.length];
		for (int i = 0; i < a.length; i++)
			c[i] = Math.abs(a[i]);
		return c;
	}

	// A function that generates a vector of random numbers
	public static double[] random(int length, int steps) {
		double[] c = new double[length];
		for (int i = 0; i < length; i++) {
			c[i] = 2 * steps * random.nextDouble();
			if (c[i] >= steps)
				c[i] = steps - c[i];
		}
		return c;
	}

	// Calculates a distance
	public static double distance(double[] a, double[] b) {
		return Math.sqrt(square(subtract(a, b)));
	}

	public static boolean isInRange(double[] a) {
		boolean result = true;
		for (double x : a)
			if (x < 0 || 1000 < x)
				result = false;
		return result;
	}

	// adds the values of b and a into a map c
	public static Map<CharacterClass, Double> addMaps(Map<CharacterClass, Double> a,
			Map<CharacterClass, Double> b) {
		Map<CharacterClass, Double> c = new TreeMap<CharacterClass, Double>();
		for (CharacterClass key : CharacterClass.values())
			c.put(key, a.get(key) + b.get(key));
		return c;
	}

	public static String[] mapToString(Map<?, Double> a, int numb, boolean normalize) {
		String[] string = { "", "" };
		double max = 0;
		for (Entry<?, Double> entry : a.entrySet()) {
			string[0] += entry.getKey() + "\t";
			max += entry.getValue() / numb;
		}
		double denominator = numb * (normalize ? max : 1);
		for (Entry<?, Double> entry : a.entrySet()) 
			string[1] += entry.getValue() / denominator + "\t";
		return string;
	}

	// Initiate a map to keep record of densities
	public static Map<CharacterClass, Double> initiateMap(double x0) {
		Map<CharacterClass, Double> map = new TreeMap<CharacterClass, Double>();
		for (CharacterClass type : CharacterClass.values())
			map.put(type, x0);
		return map;
	}
	
	public static Map<CharacterClass, Double> copyMap(Map<CharacterClass, Double> map) {
		Map<CharacterClass, Double> copy = new TreeMap<CharacterClass, Double>();
		for (CharacterClass key : CharacterClass.values())
			copy.put(key, map.get(key));
		return map;
	}

}
