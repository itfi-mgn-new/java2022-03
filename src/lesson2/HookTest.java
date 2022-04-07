package lesson2;

import java.io.IOException;

public class HookTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Runtime.getRuntime().addShutdownHook(
				new Thread(()->System.err.println("sdsddssd"))
		);
		System.in.read();
	}

}
