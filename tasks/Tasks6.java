import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

public class Tasks6 {
    public static void main(String[] args) {
        System.out.println(bell(7));
        System.out.println(translateWord("flag"));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println(validColor("rgba(255,255,255,0.555)"));
        System.out.println(stripUrlParams("https://edabit.com", "b"));
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")));
        System.out.println(ulam(16));
        System.out.println(longestNonRepeatingSubstring("abcda"));
        System.out.println(convertToRoman(2021));
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(palindromeDescendant(11211230));
    }

    static int bell(int n) {
        int result = 0;
        for (int m = 0; m <= n; m++)
            result += stirlingNumber(n, m);
        return result;
    }

    //Supplement function for bell()
    private static int stirlingNumber(int n, int k) {
        if (k > n) return 0;
        if (k == 0) if (n == 0) return 1;
        else return 0;
        return stirlingNumber(n - 1, k - 1) + k * stirlingNumber(n - 1, k);
    }

    static String translateWord(String word) {
        if (word.matches("^(?i:[aeiouy]).*")) return word + "yay";
        String result = "";
        int i = 0;
        while (!(word.charAt(i) + "").matches("^(?i:[aeiouy]).*")) {
            result += word.charAt(i);
            i++;
        }
        result = word.substring(i) + result + "ay";
        return result;
    }

    static String translateSentence(String sen) {
        char[] chars = sen.toCharArray();
        String word = "";
        String result = "";
        for (char c : chars) {
            if (Character.isAlphabetic(c)) word += c;
            else {
                if (word.length() != 0) {
                    if (Character.isUpperCase(word.charAt(0)))
                        result += (translateWord(word).charAt(0) + "").toUpperCase() + translateWord(word).substring(1).toLowerCase();
                    else result += translateWord(word);
                    word = "";
                }
                result += c;
            }
        }
        return result;
    }

    static boolean validColor(String color) {
        String number = "(([0-1]?[0-9]?[0-9])|([2][0-4][0-9])|(25[0-5]))";
        String alpha = "((0\\.[0-9]+)|1)";
        if (color.matches("rgba.*")) return color.matches("rgba\\((" + number + ",?){3}" + alpha + "\\)");
        else return color.matches("rgb\\((" + number + ",?){3}\\)");
    }

    static String stripUrlParams(String url, String... paramsToStrip) {
        if (!url.contains("?")) return url;
        String[] params = url.split("\\?")[1].split("&");
        final String[] result = {"%s?".formatted(url.split("\\?")[0])};
        HashMap<String, Integer> resultParams = new HashMap<>();
        for (String parameter : params) {
            String key = parameter.split("=")[0];
            int value = Integer.parseInt(parameter.split("=")[1]);
            if (resultParams.containsKey(key)) resultParams.replace(key, value);
            else resultParams.put(key, value);
        }
        for (String parameter : paramsToStrip)
            if (resultParams.containsKey(parameter)) resultParams.remove(parameter);

        resultParams.forEach((k, v) -> result[0] += k + "=" + v + "&");
        return result[0].substring(0, result[0].length() - 1);
    }


    static String[] getHashTags(String s) {
        ArrayList<String> longest = new ArrayList<>();
        String word = "";
        for (char c : (s + " ").toCharArray()) {
            if (Character.isAlphabetic(c)) {
                word += c;
                continue;
            }
            if (longest.size() < 3) {
                longest.add(word);
                word = "";
                continue;
            }
            if (longest.contains(word)) {
                word = "";
                continue;
            }
            longest.sort(Comparator.comparingInt(String::length));
            for (String w : longest)
                if (w.length() == word.length()) {
                    word = "";
                    continue;
                }
            for (String w : longest)
                if (w.length() < word.length()) {
                    longest.remove(0);
                    longest.add(word);
                    break;
                }

            word = "";
        }
        longest.sort(Comparator.comparingInt(String::length));
        Collections.reverse(longest);
        String[] result = new String[longest.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = "#" + longest.get(i).toLowerCase();
        return result;
    }

    static int ulam(int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);

        for (int i = 3; arr.size() < n; i++) {
            int count = 0;
            m:
            for (int j = 0; j < arr.size() - 1; j++)
                for (int k = j + 1; k < arr.size(); k++) {
                    if (arr.get(j) + arr.get(k) == i) count++;
                    if (count > 1) break m;
                }
            if (count == 1) arr.add(i);

        }
        return arr.get(arr.size() - 1);
    }

    public static String longestNonRepeatingSubstring(String str) {
        int n = str.length();
        String result = "";

        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                if (areDistinct(str, i, j)) if (result.length() < str.substring(i, j + 1).length()) {
                    result = str.substring(i, j + 1);
                }


        return result;
    }

    //Supplement function for longestNonRepeatingSubstring()
    public static Boolean areDistinct(String str, int i, int j) {
        boolean[] visited = new boolean[26];

        for (int k = i; k <= j; k++) {
            if (visited[str.charAt(k) - 'a']) return false;

            visited[str.charAt(k) - 'a'] = true;
        }
        return true;
    }

    static String convertToRoman(int n) {
        String result = "";
        char[][] dict = {{'X', 'V', 'I'}, {'C', 'L', 'X'}, {'M', 'D', 'C'}, {' ', ' ', 'M'}};

        for (int digit = 3; digit >= 0; digit--) {
            int currentDigit = (n / (int) Math.pow(10, digit) == 0) ? 0 : Integer.parseInt("" + new StringBuffer(n + "").reverse().charAt(digit));
            switch (currentDigit) {
                case 0:
                case 1:
                case 2:
                case 3:
                    for (int i = 0; i < currentDigit; i++)
                        result += dict[digit][2];
                    break;
                case 4:
                    result += dict[digit][2] + "" + dict[digit][1];
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                    result += dict[digit][1];
                    for (int i = 5; i < currentDigit; i++)
                        result += dict[digit][2];
                    break;
                case 9:
                    result += dict[digit][2] + "" + dict[digit][0];
                    break;
            }
        }
        return result;
    }

    static boolean formula(String str) {
        String[] exprs = str.split(" ");
        int[] result = new int[str.split("=").length];
        int m = 0;
        if (!Character.isDigit(exprs[0].charAt(0))) return false;
        else result[m] = Integer.parseInt(exprs[0]);
        ;
        for (int i = 0; i < exprs.length; i++) {
            switch (exprs[i]) {
                case "+":
                    result[m] += Integer.parseInt(exprs[i + 1]);
                    break;
                case "-":
                    result[m] -= Integer.parseInt(exprs[i + 1]);
                    break;
                case "*":
                    result[m] *= Integer.parseInt(exprs[i + 1]);
                    break;
                case "/":
                    result[m] /= Integer.parseInt(exprs[i + 1]);
                    break;
                case "=":
                    m++;
                    result[m] = Integer.parseInt(exprs[i + 1]);
                    break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (result[i] != result[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean palindromeDescendant(int n) {
        String number = "" + n;
        while (number.length() >= 2) {
            boolean isPalindrome = true;
            for (int i = 0; i < number.length() / 2; i++)
                if (number.charAt(i) != number.charAt(number.length() - 1 - i)) isPalindrome = false;
            if (isPalindrome) return true;
            String child = "";
            for (int i = 0; i < number.length(); i += 2)
                child += "" + (Integer.parseInt(number.charAt(i) + "") + Integer.parseInt(number.charAt(i + 1) + ""));
            number = child;
        }
        return false;
    }

}
