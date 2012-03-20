package features.a;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import features.Answer6;

import org.junit.Before;
import org.junit.Test;

public class Answer6Test {

	private Answer6 sut;

	@Before
	public void setUp() {
		sut = new features.a.Answer6();
	}

	/**
	 * 引数が0の場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testZeroAmount() {
		assertRuntimeExceptionThrown(0);
	}

	/**
	 * 引数が負の数の場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testNegativeAmount() {
		assertRuntimeExceptionThrown(-1);
		assertRuntimeExceptionThrown(Integer.MIN_VALUE);
	}

	/**
	 * 500円玉のみで実現できる場合。
	 */
	@Test
	public void test500YenCoinsCase() {
		assertThat(this.sut.coinUsage(500), is("500円玉"));
		assertThat(this.sut.coinUsage(1000), is("500円玉2枚"));
		assertThat(this.sut.coinUsage(100000000), is("500円玉200000枚"));
	}
	
	/**
	 * 100円玉のみで実現できる場合。
	 */
	@Test
	public void test100YenCoinsCase() {
		assertThat(this.sut.coinUsage(100), is("100円玉"));
		assertThat(this.sut.coinUsage(200), is("100円玉2枚"));
		assertThat(this.sut.coinUsage(300), is("100円玉3枚"));
		assertThat(this.sut.coinUsage(400), is("100円玉4枚"));
	}
	
	/**
	 * 50円玉のみで実現できる場合。
	 */
	@Test
	public void test50YenCoinsCase() {
		assertThat(this.sut.coinUsage(50), is("50円玉"));
	}
	
	/**
	 * 10円玉のみで実現できる場合。
	 */
	@Test
	public void test10YenCoinsCase() {
		assertThat(this.sut.coinUsage(10), is("10円玉"));
		assertThat(this.sut.coinUsage(20), is("10円玉2枚"));
		assertThat(this.sut.coinUsage(30), is("10円玉3枚"));
		assertThat(this.sut.coinUsage(40), is("10円玉4枚"));
	}
	
	/**
	 * 5円玉のみで実現できる場合。
	 */
	@Test
	public void test5YenCoinsCase() {
		assertThat(this.sut.coinUsage(5), is("5円玉"));
	}
	
	/**
	 * 1円玉のみで実現できる場合。
	 */
	@Test
	public void test1YenCoinsCase() {
		assertThat(this.sut.coinUsage(1), is("1円玉"));
		assertThat(this.sut.coinUsage(2), is("1円玉2枚"));
		assertThat(this.sut.coinUsage(3), is("1円玉3枚"));
		assertThat(this.sut.coinUsage(4), is("1円玉4枚"));
	}

	/**
	 * 複数の種類のコインの組み合わせで実現できる場合。
	 */
	@Test
	public void test500() {
		// 6種類のコインの組み合わせ。
		/* 500*1, 100*2, 50*1, 10*3, 5*1, 1*4 */ ast(789, "500円玉と100円玉2枚と50円玉と10円玉3枚と5円玉と1円玉4枚");

		// 5種類のコインの組み合わせ。
		/* 500*2, 100*3, 50*1, 10*4, 5*1, 1*0 */ ast(1395, "500円玉2枚と100円玉3枚と50円玉と10円玉4枚と5円玉");
		/* 500*3, 100*4, 50*1, 10*1, 5*0, 1*1 */ ast(1961, "500円玉3枚と100円玉4枚と50円玉と10円玉と1円玉");
		/* 500*4, 100*1, 50*1, 10*0, 5*1, 1*2 */ ast(2157, "500円玉4枚と100円玉と50円玉と5円玉と1円玉2枚");
		/* 500*5, 100*2, 50*0, 10*2, 5*1, 1*3 */ ast(2728, "500円玉5枚と100円玉2枚と10円玉2枚と5円玉と1円玉3枚");
		/* 500*6, 100*0, 50*1, 10*3, 5*1, 1*4 */ ast(3089, "500円玉6枚と50円玉と10円玉3枚と5円玉と1円玉4枚");
		/* 500*0, 100*3, 50*1, 10*4, 5*1, 1*1 */ ast(396, "100円玉3枚と50円玉と10円玉4枚と5円玉と1円玉");

		// 4種類のコインの組み合わせ。
		/* 500*7, 100*4, 50*1, 10*1, 5*0, 1*0 */ ast(3960, "500円玉7枚と100円玉4枚と50円玉と10円玉");
		/* 500*8, 100*1, 50*1, 10*0, 5*1, 1*0 */ ast(4155, "500円玉8枚と100円玉と50円玉と5円玉");
		/* 500*9, 100*2, 50*0, 10*2, 5*1, 1*0 */ ast(4725, "500円玉9枚と100円玉2枚と10円玉2枚と5円玉");
		/* 500*1, 100*0, 50*1, 10*3, 5*0, 1*2 */ ast(582, "500円玉と50円玉と10円玉3枚と1円玉2枚");
		/* 500*0, 100*1, 50*1, 10*4, 5*0, 1*3 */ ast(193, "100円玉と50円玉と10円玉4枚と1円玉3枚");
	}

	/**
	 * 巨大な金額の場合。
	 */
	@Test
	public void testLargeAmount() {
		assertThat(this.sut.coinUsage(Integer.MAX_VALUE),
				is("500円玉4294967枚と100円玉と10円玉4枚と5円玉と1円玉2枚"));
	}
	
	/**
	 * formatメソッドのテスト。
	 */
	@Test
	public void testFormat() {
		int[] coins500 = { 1, 0, 0, 0, 0, 0 };
		assertThat(this.format(coins500), is("500円玉"));
		int[] coins100 = { 0, 1, 0, 0, 0, 0 };
		assertThat(this.format(coins100), is("100円玉"));
		int[] coins50 = { 0, 0, 1, 0, 0, 0 };
		assertThat(this.format(coins50), is("50円玉"));
		int[] coins52 = { 0, 0, 1, 0, 0, 2 };
		assertThat(this.format(coins52), is("50円玉と1円玉2枚"));
	}

	/**
	 * テスト対象クラスのprivateメソッドformatを呼び出す。
	 * 
	 * @param numbers
	 * @return
	 */
	private String format(int[] numbers) {
		String result = null;
		try {
			Method method = this.sut.getClass().getDeclaredMethod("format",
					int[].class);
			method.setAccessible(true);
			result = (String) method.invoke(sut, numbers);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		return result;
	}

	/**
	 * assertの省略記法。
	 * 
	 * @param amount
	 * @param coinUsage
	 */
	private void ast(int amount, String coinUsage) {
		assertThat(this.sut.coinUsage(amount), is(coinUsage));
	}

	/**
	 * argにcoinUsageメソッドを適用し、RuntimeExceptionが投げられることを確認する。
	 * 
	 * @param arg coinUsageメソッドを適用する引数。
	 */
	private void assertRuntimeExceptionThrown(int arg) {
		try {
			this.sut.coinUsage(arg);
			fail("例外が投げられなかった。");
		} catch (Exception e) {
			if (!RuntimeException.class.equals(e.getClass())) {
				fail("RuntimeException以外の例外が投げられた。");
			}
			// success.
		}
	}

}
