import static org.junit.Assert.*;
import org.junit.Test;
public class CALCULATORtest {
	@Test
	public void testSimplify() {
		//fail("111111Not yet implemented");
		CALCULATOR calculator=new CALCULATOR();
		Term[] n = new Term[10];
		for (int i = 0; i < 10; i++) { // ¹¹Ôì
			n[i] = new Term();
		}
		n[0].quantity[0] = "y";
		n[0].index[0] = 2;
		String result = calculator.Simplify("!simplify x=2 y=1", "y*y", n);
		assertEquals("1*1", result);
		System.out.println(result);
	}
}
