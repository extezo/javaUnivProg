import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.stream.Collectors;

public class Tasks5 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(72, 29, 7, 0, 3));
        System.out.println(canMove("Queen", "C4", "E6"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        sameVowelGroup("toe", "ocelot", "maniac");
        System.out.println(validateCard("1234567890123452"));
        System.out.println(numToEng(929));
        System.out.println(numToRus(626));
        System.out.println(getSha256Hash("password123"));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println(hexLattice(37));
    }

    static int[] encrypt(String message) {
        int[] codedMessage = new int[message.length()];
        codedMessage[0] = message.codePointAt(0);
        for (int i = 1; i < message.length(); i++)
            codedMessage[i] = message.codePointAt(i) - message.codePointAt(i - 1);
        return codedMessage;
    }

    static String decrypt(int... codedMessage) {
        String message = "" + (char) codedMessage[0];
        for (int i = 1; i < codedMessage.length; i++)
            message += (char) (message.codePointAt(i - 1) + codedMessage[i]);
        return message;
    }

    static boolean canMove(String figure, String fromS, String toS) {
        char[] from = fromS.toLowerCase().toCharArray();
        char[] to = toS.toLowerCase().toCharArray();
        if (from[0] < 'a' || from[0] > 'h' || from[1] < '1' || from[1] > '8')
            throw new InputMismatchException("Origin position incorrect");
        if (to[0] < 'a' || to[0] > 'h' || to[1] < '1' || to[1] > '8' || from[0] == to[0] && from[1] == to[1])
            throw new InputMismatchException("Destination position incorrect");
        int distance = (int) (Math.pow(from[0] - to[0], 2) + Math.pow(from[1] - to[1], 2));
        switch (figure) {
            case "Pawn":
                if (from[0] == to[0] && to[1] - from[1] == 1)
                    return true;
            case "Rook":
                if (from[0] == to[0] || from[1] == to[1])
                    return true;
            case "Knight":
                if (distance == 5)
                    return true;
            case "Bishop":
                if (Math.abs(from[0] - to[0]) == Math.abs(from[1] - to[1]))
                    return true;
            case "Queen":
                if (from[0] == to[0] || from[1] == to[1] || Math.abs(from[0] - to[0]) == Math.abs(from[1] - to[1]))
                    return true;
            case "King":
                if (distance < 3)
                    return true;
        }
        return false;
    }


    static boolean canComplete(String sInput, String sWord) {
        char[] input = sInput.toCharArray();
        char[] word = sWord.toCharArray();
        int i = 0;
        for (char c : word)
            if (c == input[i]) {
                i++;
            }
        return i == input.length;
    }

    static int sumDigProd(int... numbers) {
        int result = Arrays.stream(numbers).sum();
        while (result > 9) {
            int mlt = 1;
            while (result > 0) {
                mlt *= result % 10;
                result /= 10;
            }
            result = mlt;
        }
        return result;
    }

    public static String[] sameVowelGroup(String... strings) {
        String vowels = "";
        ArrayList<String> result = new ArrayList<String>();
        for (char c : strings[0].toCharArray())
            if (c == 'a' || c == 'e' || c == 'u' || c == 'i' || c == 'o' || c == 'y')
                vowels += c;
        m: for (String s : strings) {
            for (char c : vowels.toCharArray())
                if (!s.contains(c + ""))
                    continue m;
            result.add(s);
        }
        return result.toArray(new String[0]);
    }

    static boolean validateCard(String n) {
        int check = Integer.parseInt(n.charAt(n.length() - 1) + "");
        String num = n.substring(0, n.length() - 1);
        int[] reversed = new int[num.length()];
        for (int i = 0; i < num.length(); i++)
            reversed[num.length() - i - 1] = Integer.parseInt(num.charAt(i) + "");
        for (int i = 0; i < reversed.length; i += 2) {
            reversed[i] *= 2;
            if (reversed[i] > 9)
                reversed[i] -= 9;
        }
        return (10 - Arrays.stream(reversed).sum() % 10) == check;
    }

    static String numToEng(int n) {
        String[] firstDigit = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] secondDigit1 = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] secondDigit = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        if (n == 0)
            return "zero";
        else if (n < 10)
            return firstDigit[n];
        else if (n < 20)
            return secondDigit1[n % 10];
        else if (n < 100)
            return secondDigit[n / 10] + " " + firstDigit[n % 10];
        else return firstDigit[n / 100] + " hundred " + secondDigit[(n / 10) % 10] + " " + firstDigit[n % 10];
    }

    static String numToRus(int n) {
        String[] firstDigit = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        String[] secondDigit1 = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
                "шестнадцать", "семнадцать", "восемнадцать", "девятнадцатый"};
        String[] secondDigit = {"", "", "двадцать", "тридцать", "сорок", "пятьдесят",
                "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String[] thirdDigit = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        if (n == 0)
            return "zero";
        else if (n < 10)
            return firstDigit[n];
        else if (n < 20)
            return secondDigit1[n % 10];
        else if (n < 100)
            return secondDigit[n / 10] + " " + firstDigit[n % 10];
        else return thirdDigit[n / 100] + " " + secondDigit[(n / 10) % 10] + " " + firstDigit[n % 10];
    }

    static String getSha256Hash(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (int i = 0; i < encodedHash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedHash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    static String correctTitle(String title) {
        char[] chars = title.toCharArray();
        String result = "";
        String wordBuffer = "";
        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i]))
                wordBuffer += chars[i];
            else {
                if (!wordBuffer.equals("")) {
                    result += (wordBuffer.equalsIgnoreCase("and")
                            || wordBuffer.equalsIgnoreCase("the")
                            || wordBuffer.equalsIgnoreCase("of")
                            || wordBuffer.equalsIgnoreCase("in")) ?
                            wordBuffer.toLowerCase() :
                            wordBuffer.substring(0, 1).toUpperCase() + wordBuffer.substring(1).toLowerCase();
                    wordBuffer = "";
                }
                result += chars[i];
            }
        }
        return result;
    }

    static String hexLattice(int n) {
        int valid = 1;
        int size = 2;
        String result = "";
        while (valid < n)
            valid += 6 * (size++ - 1);
        if (valid != n)
            return "Invalid";
        size--;
        for (int i = 0; i < size; i++) {
            result += ("%" + (size - i) + "s").formatted("");
            for (int j = 0; j < size + i; j++)
                result += "o ";
            result += ("%" + (size - i) + "s").formatted("") + "\n";
        }
        for (int i = size-2; i >= 0; i--) {
            result += ("%" + (size - i) + "s").formatted("");
            for (int j = 0; j < size + i; j++)
                result += "o ";
            result += ("%" + (size - i) + "s").formatted("") + "\n";
        }
        return result;
    }
}
