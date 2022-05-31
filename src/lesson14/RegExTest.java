package lesson14;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Pattern	p = Pattern.compile("a(\\d{1,3})ba(\\d{1,3})b");
		
		final Matcher	m = p.matcher("a1ba2b");
//		System.err.println("Match ? "+m.matches());
		System.err.println("Find: "+m.find());
		System.err.println("Group: "+m.group());
		System.err.println("Group[1]: "+m.group(1));
		System.err.println("Group[2]: "+m.group(2));
		System.err.println("Find: "+m.find());
		System.err.println("Group: "+m.group());
		System.err.println("Group[1]: "+m.group(1));
	}

}
