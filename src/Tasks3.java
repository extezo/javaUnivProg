public class Tasks3 {
	public static void main(String[] args) {
		System.out.println(solutions(1, 2, 1));
		System.out.println(findZip("May I zip the zip"));
		System.out.println(findZip("May I rar the zip"));
		System.out.println(flipEndChars("I am a scatman."));
		System.out.println(isValidHexCode("#FFAADD"));
		System.out.println(isValidHexCode("#PUDGER"));
		int[] a = { 1, 3, 4, 4, 4 }, b = { 2, 5, 7 };
		System.out.println(same(a, b));
		System.out.println(isKaprekar(3));
		System.out.println(isKaprekar(5));
		System.out.println(isKaprekar(297));
		System.out.println(longestZero("01100001011000"));
		System.out.println(longestZero("100100100"));
		System.out.println(longestZero("11111"));
		System.out.println(nextPrime(12));
		System.out.println(nextPrime(24));
		System.out.println(nextPrime(11));
		System.out.println(rightTriangle(3, 4, 5));
		System.out.println(rightTriangle(145, 105, 100));
		System.out.println(rightTriangle(70, 130, 110));
	}

	static int solutions(double a, double b, double c) {
		switch ((int) Math.signum(b * b - 4 * a * c)) {
		case 1:
			return 2;
		case 0:
			return 1;
		default:
			return 0;
		}
	}

	static int findZip(String s) {
		if (s.indexOf("zip") == s.lastIndexOf("zip"))
			return -1;
		return s.indexOf("zip", s.indexOf("zip") + 1);
	}

	static boolean checkPerfect(int n) {
		int sum = 0;
		for (int i = 1; i < n; i++)
			if (n % i == 0)
				sum += i;
		return sum == n;
	}

	static String flipEndChars(String s) {
		if (s.length() < 2)
			return "Incompatible.";
		if (s.charAt(0) == s.charAt(s.length() - 1))
			return "Two's a pair.";
		return s.charAt(s.length() - 1) + s.substring(1, s.length() - 2) + s.charAt(0);
	}

	static boolean isValidHexCode(String s) {
		if (s.charAt(0) != '#')
			return false;
		try {
			Integer.parseUnsignedInt(s.substring(1), 16);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	static boolean same(int[] a, int[] b) {
		String uniqueA = "", uniqueB = "";
		for (int i = 0; i < a.length; i++)
			if (!uniqueA.contains("" + a[i]))
				uniqueA += a[i];
		for (int i = 0; i < b.length; i++)
			if (!uniqueB.contains("" + b[i]))
				uniqueB += b[i];
		return uniqueA.length() == uniqueB.length();
	}

	static boolean isKaprekar(int n) {
		String s = "" + (n * n);
		if (s.length() == 1)
			if (n == 0 || n == 1)
				return true;
			else
				return false;
		int kapL = Integer.parseInt(s.substring(0, s.length() / 2));
		int kapR = Integer.parseInt(s.substring(s.length() / 2));
		return (kapL + kapR) == n;
	}

	static String longestZero(String s) {
		int longestLength = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				int t = 0;
				while (i < s.length() && s.charAt(i) == '0') {
					t++;
					i++;
				}
				if (t > longestLength)
					longestLength = t;
			}
		}
		String result = "";
		for (int i = 0; i < longestLength; i++)
			result += "0";
		return result;
	}

	static int nextPrime(int n) {
		boolean isPrime = true;
		for (int i = n; i < Integer.MAX_VALUE; i++) {
			for (int j = 2; j < i; j++)
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			if (isPrime)
				return i;
			isPrime = true;
		}
		return -1;
	}

	static boolean rightTriangle(int x, int y, int z) {
		return (x * x + y * y == z * z || y * y + z * z == x * x || x * x + z * z == y * y);
	}
}