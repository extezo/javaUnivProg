
public class Palindrome {
	//����� ����� � ���������
	public static void main(String[] args) {
		for (String s : args) {
			System.out.println(isPalindrome(s));
		}
	}
	//������� ��������� ������
	public static String reverseString(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++)
			res = "%s%s".formatted(s.charAt(i), res); //������������ ������ � ������ ������
		return res;
	}
	//�������� ������ �� ������� ����������
	public static boolean isPalindrome(String s) {
		return s.equals(reverseString(s));
	}
}
