package features.a;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import features.Tsurukamezan;

public class TsurukamezanTest {

	private Tsurukamezan sut;

	@Before
	public void setUp() {
		sut = new features.a.Tsurukamezan();
	}


	@Test
	public void tsurukamekeisan() throws Exception {
		assertThat(sut.tsurukame(8, 26), is("鶴3羽、亀5匹"));
	}

	@Test(expected=RuntimeException.class)
	public void 足の本数が2の倍数でない() throws Exception {
		sut.tsurukame(3, 9);
	}
	@Test(expected=RuntimeException.class)
	public void 個体数の2倍が足の本数以上() throws Exception {
		sut.tsurukame(4, 4);
	}
}
