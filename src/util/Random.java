package util;

public class Random {
	private static final java.util.Random random = new java.util.Random();

	public static int nextInt(int n) {
		if (n <= 0)
			return 0;
		return random.nextInt(n);
	}

	public static character.Class getRandomType() {
		return character.Class.values()[random.nextInt(character.Class.values().length)];
	}

}
