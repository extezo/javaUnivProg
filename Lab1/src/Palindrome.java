
public class Palindrome {
	//Точка входа в программу
	public static void main(String[] args) {
		for (String s : args) {
			System.out.println(isPalindrome(s));
		}
	}
	//Функция разворота строки
	public static String reverseString(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++)
			res = "%s%s".formatted(s.charAt(i), res); //Присоединяем символ в начало строки
		return res;
	}
	//Проверка строки на наличие палиндрома
	public static boolean isPalindrome(String s) {
		return s.equals(reverseString(s));
	}
}
