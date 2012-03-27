package features.a;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
	
	private Calculator sut; 

	@Before
	public void setup() {
		sut = new Calculator();
	}
	
	@Test
	public void testPlus() {
		assertThat(sut.execute("1+1"), is("2"));
	}
	
	@Test
	public void testMinus() {
		assertThat(sut.execute("1-1"), is("0"));
	}
	
	@Test
	public void testMultiply() {
		assertThat(sut.execute("1*1"), is("1"));
	}
	
	@Test
	public void testDivide() {
		assertThat(sut.execute("1/1"), is("1"));
	}
	
	@Test
	public void testDivide_rounddown() {
		assertThat(sut.execute("1/3"), is("0.333"));
	}
	
	@Test
	public void testDivide_roundup() {
		assertThat(sut.execute("1/6"), is("0.167"));
	}
	
	@Test
	public void testDivide_scale() {
		assertThat(sut.execute("1/2"), is("0.5"));
	}
	
	@Test(expected = RuntimeException.class)
	public void testErrorPattern1() {
		sut.execute("1~2");
	}
	
	@Test(expected = RuntimeException.class)
	public void testErrorPattern2() {
		sut.execute("+12");
	}
	
	@Test(expected = RuntimeException.class)
	public void testErrorPattern3() {
		sut.execute("12-");
	}
	
	@Test(expected = RuntimeException.class)
	public void testZeroValue_left() {
		sut.execute("0+1");
	}
	
	@Test(expected = RuntimeException.class)
	public void testZeroValue_right() {
		sut.execute("1+0");
	}

}
