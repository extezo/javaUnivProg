import java.util.Arrays;

public class Tasks2 {
	public static void main(String[] args) {
		System.out.println(repeat("mice", 4));
		int[] arr = { 10, 4, 1, 4, -10, -50, 32, 21 };
		System.out.println(differenceMaxMin(arr));
		int[] arr2 = { 1, 2, 3, 4 };
		System.out.println(isAvgWhole(arr2));
		int[] arr3 = { 1, 2, 3 };
		System.out.println(Arrays.toString(cumulativeSum(arr3)));
		System.out.println(getDecimalPlaces("400"));
		System.out.println(Fibonacci(12));
		System.out.println(isValid("853a7"));
		System.out.println(isStrangePair("sparkling", "roots"));
		System.out.println(isPrefix("Alexey", "random-"));
		System.out.println(isSuffix("Jove", "-papka"));
		System.out.println(boxSeq(5));
	}

	static String repeat(String s, int n) {
		String ans = "";
		for (int i = 0; i < s.length(); i++)
			for (int j = 0; j < n; j++)
				ans += s.charAt(i);
		return ans;
	}

	static int differenceMaxMin(int[] arr) {
		int min = arr[0], max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
			if (arr[i] > max)
				max = arr[i];
		}
		return Math.abs(max - min);
	}

	static boolean isAvgWhole(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++)
			sum += arr[i];
		return (sum % arr.length == 0) ? true : false;
	}

	static int[] cumulativeSum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			arr[i] = sum;
		}
		return arr;
	}

	static int getDecimalPlaces(String s) {
		if (s.split("[.]").length == 1)
			return 0;
		return s.split("[.]")[1].length();
	}

	static int Fibonacci(int n) {
		int n0 = 0;
		int n1 = 1;
		int ans = -1;
		for (int i = 0; i < n; i++) {
			ans = n0 + n1;
			n1 += n0;
			n0 = n1 - n0;
		}
		return ans;
	}

	static boolean isValid(String s) {
		for (char c : s.toCharArray())
			if (!Character.isDigit(c))
				return false;
		if (s.length() > 5)
			return false;
		if (!s.equals(s.replace(' ', '@')))
			return false;
		return true;
	}

	static boolean isStrangePair(String a, String b) {
		return a.charAt(0) == b.charAt(b.length() - 1) && a.charAt(a.length() - 1) == b.charAt(0);
	}

	static boolean isPrefix(String s, String pref) {
		return s.startsWith(pref.substring(0, pref.length() - 1));
	}

	static boolean isSuffix(String s, String suf) {
		return s.endsWith(suf.substring(1, suf.length()));
	}

	static int boxSeq(int step) {
		return (step + 1) / 2 * 3 - step / 2;
	}
}