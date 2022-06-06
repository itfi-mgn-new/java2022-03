package lesson15;

import org.junit.Assert;
import org.junit.Test;

public class RabbitTest {
	@Test
	public void test() {
		Assert.assertTrue(Rabbit.isLessThan(10, 20));
	}
	
	@Test
	public void testValue() {
		Rabbit.setValue("123");
		Assert.assertEquals("123", Rabbit.getValue());
		Rabbit.setValue("456");
		Assert.assertEquals("456", Rabbit.getValue());
		
		try {Rabbit.setValue(null);
			Assert.fail("Mandatory exception was not detected (null 1-st argument)");
		} catch (IllegalArgumentException exc) {
		}
		try {Rabbit.setValue("");
			Assert.fail("Mandatory exception was not detected (empty 1-st argument)");
		} catch (IllegalArgumentException exc) {
		}
	}
}
