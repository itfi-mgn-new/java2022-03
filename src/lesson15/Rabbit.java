package lesson15;

public class Rabbit {
	private static String value = "";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static boolean isLessThan(int x, int y) {
		return x < y;
	}

	public static String getValue() {
		return value;
	}

	public static void setValue(String value) {
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Value to set can't be null or empty");
		}
		else {
			Rabbit.value = value;
		}
	}
	

	
}

