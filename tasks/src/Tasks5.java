import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class Tasks5 {
    public static void main(String[] args) {
        System.out.println(validateCard("1234567890123452"));
        System.out.println(numToEng(906));
        System.out.println(getSha256Hash("password123"));
    }

    public static boolean validateCard(String n) {
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

    public static String numToEng(int n) {
        String result = "";
        if (n == 0) return "zero";
        if (n == 10) return "ten";
        if (n == 100) return "one hundred";
        if (n / 10 != 1)
            switch (n % 10) {
                case 1:
                    result += "one";
                    break;
                case 2:
                    result += "two";
                    break;
                case 3:
                    result += "three";
                    break;
                case 4:
                    result += "four";
                    break;
                case 5:
                    result += "five";
                    break;
                case 6:
                    result += "six";
                    break;
                case 7:
                    result += "seven";
                    break;
                case 8:
                    result += "eight";
                    break;
                case 9:
                    result += "nine";
                    break;
            }
        if (n / 10 == 0)
            return result;
        switch ((n / 10) % 10) {
            case 1:
                switch (n % 10) {
                    case 1:
                        result += "eleven";
                        break;
                    case 2:
                        result += "twelve";
                        break;
                    case 3:
                        result += "thirteen";
                        break;
                    case 4:
                        result += "fourteen";
                        break;
                    case 5:
                        result += "fifteen";
                        break;
                    case 6:
                        result += "sixteen";
                        break;
                    case 7:
                        result += "seventeen";
                        break;
                    case 8:
                        result += "eighteen";
                        break;
                    case 9:
                        result += "nineteen";
                        break;
                }
                break;
            case 2:
                result = "twenty " + result;
                break;
            case 3:
                result = "thirty " + result;
                break;
            case 4:
                result = "fourty " + result;
                break;
            case 5:
                result = "fifty " + result;
                break;
            case 6:
                result = "sixty " + result;
                break;
            case 7:
                result = "seventy " + result;
                break;
            case 8:
                result = "eighty " + result;
                break;
            case 9:
                result = "ninety " + result;
                break;
        }
        if (n/100 == 0)
            return result;
        result = " hundred " + result;
        switch (n/100) {
            case 1:
                result = "one"+ result;
                break;
            case 2:
                result = "two"+ result;
                break;
            case 3:
                result = "three"+ result;
                break;
            case 4:
                result = "four"+ result;
                break;
            case 5:
                result = "five"+ result;
                break;
            case 6:
                result = "six"+ result;
                break;
            case 7:
                result = "seven"+ result;
                break;
            case 8:
                result = "eight"+ result;
                break;
            case 9:
                result = "nine"+ result;
                break;
        }
        return result;
    }

    public static String getSha256Hash(String s) {
        byte[] hash = s.getBytes(StandardCharsets.UTF_8);
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append(0);
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
