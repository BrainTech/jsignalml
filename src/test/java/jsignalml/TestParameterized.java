package jsignalml;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * JUnit Parameterized Test
 * @author mkyong
 *
 */
@RunWith(value = Parameterized.class)
public class TestParameterized {

	private int number;
	private int expected;

	public TestParameterized(int number, int expected) {
		this.number = number;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 4, decrease(5) } };
		return Arrays.asList(data);
	}

	@Test
	public void pushTest() {
		System.out.println("Parameterized Number is : " + number);
		assertEquals(expected, number);
	}

	private static int decrease(int num){
		return num-1;
	}
}
