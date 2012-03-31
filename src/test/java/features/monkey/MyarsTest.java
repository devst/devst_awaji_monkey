package features.monkey;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class MyarsTest {

	@Test
	public void 正三角形() throws Exception {
		String actual = new Myers().getName(1, 1, 1);
		assertThat(actual, is("正三角形"));
	}

	@Test(expected = RuntimeException.class)
	public void 三角形にならない_1() throws Exception {
		new Myers().getName(2, 1, 1);
	}

	@Test(expected = RuntimeException.class)
	public void 三角形にならない_2() throws Exception {
		new Myers().getName(1, 2, 1);
	}

	@Test(expected = RuntimeException.class)
	public void 三角形にならない_3() throws Exception {
		new Myers().getName(1, 1, 2);
	}

	@Test
	public void 二等辺三角形_1() throws Exception {
		String actual = new Myers().getName(5, 5, 3);
		assertThat(actual, is("二等辺三角形"));
	}

	@Test
	public void 二等辺三角形_2() throws Exception {
		String actual = new Myers().getName(5, 3, 5);
		assertThat(actual, is("二等辺三角形"));
	}

	@Test
	public void 二等辺三角形_3() throws Exception {
		String actual = new Myers().getName(3, 5, 5);
		assertThat(actual, is("二等辺三角形"));
	}

	@Test
	public void 不等辺三角形() throws Exception {
		String actual = new Myers().getName(3, 4, 5);
		assertThat(actual, is("不等辺三角形"));
	}

	@Test(expected = RuntimeException.class)
	public void マイナス値() throws Exception {
		new Myers().getName(-1, -1, -1);
	}
}
