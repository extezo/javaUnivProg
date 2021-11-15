import java.util.Arrays;
import java.util.Stack;

public class Tasks4 {
	public static void main(String[] args) {
		textProcessor(10, 7, "hello my name is Bessie and this is my essay");
		System.out.println(Arrays.toString(toCluster("((()))(())()()(()())")));
		System.out.println(toCamelCase("hello_edabit"));
		System.out.println(overTime(16, 18, 30, 1.8));
		System.out.println(BMI("154 pounds", "2 meters"));
		System.out.println(toStarShorthand(""));
		System.out.println(doesRhyme("and frequently do?", "you gotta move."));
		System.out.println(trouble("666789", "12345667"));
		System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
	}

	static void textProcessor(int n, int k, String s) {
		Stack<String> essay = new Stack<>();
		for (String word : s.split(" "))
			if (!essay.isEmpty() && word.length() + essay.peek().replaceAll("\\s+", "").length() <= k) {
				String t = essay.pop();
				essay.push(t + " " + word);
			} else
				essay.push(word);
		essay.forEach(line -> {
			System.out.println(line);
		});
	}

	static String[] toCluster(String s) {
		int balance = 0;
		int last = 0;
		String[] clusters = new String[s.length()];
		clusters[0] = "";
		for (char c : s.toCharArray()) {
			clusters[last] += c;
			if (c == '(')
				balance++;
			else
				balance--;
			if (balance == 0)
				clusters[++last] = "";
		}
		return Arrays.copyOf(clusters, last);
	}

	static String toCamelCase(String s) {
		String[] words = s.split("_");
		String result = words[0];
		for (int i = 1; i < words.length; i++) {
			char first = words[i].charAt(0);
			result += Character.toUpperCase(first) + words[i].substring(1);
		}
		return result;
	}

	static String toSnakeCase(String s) {
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(chars[i])) {
				chars[i - 1] = '_';
				chars[i] = Character.toLowerCase(chars[i]);
			}
		}
		return new String(chars);
	}

	static String overTime(double start, double end, double rate, double mltplr) {
		if (end > 17)
			return String.format("%.2f$", rate * ((17 - start) + (end - 17) * mltplr));
		else
			return String.format("%.2f$", (end - start) * rate);
	}

	static String BMI(String weight, String height) {
		double weightInKilos = Double.parseDouble(weight.split(" ")[0]);
		double heightInMeters = Double.parseDouble(height.split(" ")[0]);
		if (weight.split(" ")[1].contains("pound"))
			weightInKilos *= 0.453592;
		if (height.split(" ")[1].contains("inch"))
			heightInMeters *= 0.0254;
		double bmi = weightInKilos / (heightInMeters * heightInMeters);
		if (bmi < 18.5)
			return String.format("%.1f Underweight", bmi);
		else if (bmi < 24.9)
			return String.format("%.1f Normal weight", bmi);
		return String.format("%.1f Overweight", bmi);
	}

	static String toStarShorthand(String s) {
		int count = 1;
		String stars = "";
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i - 1))
				count++;
			if (s.charAt(i) != s.charAt(i - 1)) {

				stars += s.charAt(i - 1) + (count == 1 ? "" : "*" + count);
				count = 1;
			}
			if (i == s.length() - 1) {
				stars += s.charAt(i) + (count == 1 ? "" : "*" + count);

			}

		}
		return stars;
	}

	static boolean doesRhyme(String s1, String s2) {
		String lastWord1 = s1.split(" ")[s1.split(" ").length - 1].toLowerCase();
		String lastWord2 = s2.split(" ")[s2.split(" ").length - 1].toLowerCase();
		int vowelCount1 = 0;
		int vowelCount2 = 0;
		for (char c : lastWord1.toCharArray())
			if (c == 'e' || c == 'y' || c == 'u' || c == 'i' || c == 'o' || c == 'a')
				vowelCount1++;
		for (char c : lastWord2.toCharArray())
			if (c == 'e' || c == 'y' || c == 'u' || c == 'i' || c == 'o' || c == 'a')
				vowelCount2++;
		if (vowelCount1 != vowelCount2)
			return false;
		for (char c1 : lastWord1.toCharArray())
			if (c1 == 'e' || c1 == 'y' || c1 == 'u' || c1 == 'i' || c1 == 'o' || c1 == 'a') {
				boolean existsInSecond = false;
				for (char c2 : lastWord2.toCharArray())
					if (c2 == c1) {
						existsInSecond = true;
						break;
					}
				if (!existsInSecond)
					return false;
			}
		return true;
	}

	static boolean trouble(String a, String b) {
		char[] n1 = a.toCharArray();
		char[] n2 = b.toCharArray();
		for (int i = 0; i < n1.length - 3; i++)
			if (n1[i] == n1[i + 1] && n1[i + 1] == n1[i + 2])
				for (int j = 0; j < n2.length - 2; j++)
					if (n1[i] == n2[j] && n2[j] == n2[j + 1])
						return true;
		return false;
	}

	static int countUniqueBooks(String stringSequence, char bookEnd) {
		char[] chars = stringSequence.toCharArray();
		int unique = 0;
		for (int i = 0; i < chars.length; i++)
			if (chars[i] == bookEnd)
				for (int j = i + 1; j < chars.length; j++) {
					if (j == i + 1 && chars[j] == bookEnd) {
						i = j;
						break;
					}
					else if (j == i + 1 && chars[j] != bookEnd)
						unique++;
					if (chars[j + 1] == bookEnd) {
						i = j + 1;
						break;
					}
					if (chars[j] != chars[j + 1])
						unique++;
				}
		return unique;

	}

}
