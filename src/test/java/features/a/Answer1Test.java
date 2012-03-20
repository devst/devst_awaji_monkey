package features.a;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import features.Answer1;

public class Answer1Test {

	private Answer1 sut;

	@Before
	public void setUp() {
		sut = new features.a.Answer1();
	}

	@Test
	public void testPlusCalc() {
		assertThat(this.sut.plus(1, 1), is(2));
	}

	@Test
	public void testMinusCalc() {
		assertThat(this.sut.plus(-1, -1), is(-2));
	}

	@Test
	public void testZero() {
		assertThat(this.sut.plus(0, 0), is(0));
	}

	@Test
	public void testMaxValueCalc() {
		assertThat(this.sut.plus(Integer.MAX_VALUE, Integer.MAX_VALUE), is(Integer.MAX_VALUE*2));
	}

	@Test
	public void testMinValueCalc() {
		assertThat(this.sut.plus(Integer.MIN_VALUE, Integer.MIN_VALUE), is(Integer.MIN_VALUE*2));
	}

}
