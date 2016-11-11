import static org.junit.Assert.*;

import org.junit.Test;

public class CALCULATORtest2 {

	@Test
	public void testDerivative() {
		CALCULATOR calculator=new CALCULATOR();
		Term[] n = new Term[10];
		for (int i = 0; i < 10; i++) { // ¹¹Ôì
			n[i] = new Term();
		}
		n[0].quantity[0] = "x";
		n[0].index[0] = 1;
		n[0].quantity[1] = "xoo";
		n[0].index[1] = 1;
		n[0].quantity[2] = "y";
		n[0].index[2] = 1;
		n[1].quantity[0] = "x";
		n[1].index[0] = 1;
		n[1].quantity[1] = "xoo";
		n[1].index[1] = 1;
		String result = calculator.derivative("!d/d aaaa", "x*xoo*y+x*xoo", n);
		assertEquals("x*xoo*y+x*xoo", result);
		System.out.println(result);
	}

}
