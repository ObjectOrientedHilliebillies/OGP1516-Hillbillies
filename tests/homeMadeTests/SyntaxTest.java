package homeMadeTests;

import org.junit.Test;
import ogp.framework.util.Util;

public class SyntaxTest {
	@Test
	public final void test(){
		
	double a = 0.499999;
	double b = 0.6;
	
    System.out.println(Util.fuzzyGreaterThanOrEqualTo(a, b));
	}
}
