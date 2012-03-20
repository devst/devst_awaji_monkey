package features.a;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import features.Answer3;

public class Answer3Test {

	private Answer3 sut;

	@Before
	public void setUp() {
		sut = new features.a.Answer3();
	}

	@Test
	public void 三の倍数() throws Exception {
		assertThat(sut.fizzBuzz(3), is("Fizz"));
	}

	@Test
	public void 五の倍数() throws Exception {
		assertThat(sut.fizzBuzz(5), is("Buzz"));
	}

	@Test
	public void 三と五の倍数() throws Exception {
		assertThat(sut.fizzBuzz(15), is("FizzBuzz"));
	}

	@Test
	public void その他() throws Exception {
		assertThat(sut.fizzBuzz(4), is("4"));
		assertThat(sut.fizzBuzz(7), is("7"));
	}
}
