import java.util.Arrays;
import java.util.InputMismatchException;

public class Tasks5 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(encrypt("Hello")));
		System.out.println(decrypt(72, 29, 7, 0, 3));
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
			throw new InputMismatchException("Beginning position incorrect");
		if (to[0] < 'a' || to[0] > 'h' || to[1] < '1' || to[1] > '8' || from[0] == to[0] && from[1] == to[1])
			throw new InputMismatchException("Destination position incorrect");
		switch (figure) {
		case "Pawn":
			if (from[0] == to[0] && to[1] - from[1] < 3 && to[1] - from[1] > 0)
				return true;
		case "Rook":
			if (from[0] == to[0] || from[1] == to[1])
				return true;
		case "Knight":
			if ()
		}
		return false;
	}
}
