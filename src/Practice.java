
public class Practice {
	public static void main(String[] args) {
		System.out.println("convert(5): " + convert(5));
		System.out.println("points(13, 12): " + points(13, 12));
		System.out.println("footballPoints(3, 4, 2): " + footballPoints(3, 4, 2));
		
		System.out.println("divisibleByFive(10): " + divisibleByFive(10));
		System.out.println("divisibleByFive(6): " + divisibleByFive(6));
		
		System.out.println("convert(5): " + convert(5));
		
		System.out.println("and(true, false): " + and(true, false));
		System.out.println("and(true, true): " + and(true, true));
		
		System.out.println("howManyWalls(46, 5, 4): " + howManyWalls(46, 5, 4));
		System.out.println("squared(4): " + squared(4));
		System.out.println("profitableGamble(0.9, 1, 2): " + profitableGamble(0.9, 1, 2));
		System.out.println("frames(10, 1): " + frames(10, 1));
		System.out.println("mod(218, 5): " + mod(218, 5));

	}

	static int convert(int t) {
		return t * 60;
	}

	static int points(int a, int b) {
		return a * 2 + b * 3;
	}

	static int footballPoints(int w, int p, int l) {
		return w * 3 + p;
	}

	static boolean divisibleByFive(int n) {
		return n % 5 == 0;
	}

	static boolean and(boolean a, boolean b) {
		return a && b;
	}

	static int howManyWalls(int n, int w, int h) {
		return n / (w * h);
	}

	static int squared(int a) {
		return a * a;
	}

	static boolean profitableGamble(double prob, int prize, int pay) {
		return prob * prize > pay;
	}

	static int frames(int t, int fps) {
		return t * fps * 60;
	}

	static int mod(int a, int b) {
		return a - (a / b) * b;
	}
}
