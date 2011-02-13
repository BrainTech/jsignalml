import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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
		Object[][] data = new Object[][] { { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 } };
		return Arrays.asList(data);
	}
 
	@Test
	public void pushTest() {
		System.out.println("Parameterized Number is : " + number);
		assertEquals(expected, number);
	}
}
