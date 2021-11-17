
public class Palindrome {
	//Точка входа в программу
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			String s = args[i];
			System.out.println(isPalindrome(s));
		}
	}
	//Функция разворота строки
	public static String reverseString(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++)
			res = s.charAt(i) + res; //Присоединяем символ в начало строки
		return res;
	}
	//Проверка строки на наличие палиндрома
	public static boolean isPalindrome(String s) {
		return s.equals(reverseString(s));
	}
}
