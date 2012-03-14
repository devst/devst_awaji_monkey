package answers.a;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import answers.Answer5;

import org.junit.Before;
import org.junit.Test;

public class Answer5Test {

	private Answer5 sut;

	@Before
	public void setUp() {
		sut = new answers.a.Answer5();
	}

	/**
	 * 引数がnullの場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testNullArgument() {
		assertRuntimeExceptionThrown(null);
	}

	/**
	 * 引数が空文字列の場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testEmptyStringArgument() {
		assertRuntimeExceptionThrown("");
	}

	/**
	 * 1000の倍数の場合。
	 */
	@Test
	public void testForThousands() {
		assertThat(sut.toInt("MMM"), is(3000));
		assertThat(sut.toInt("MM"), is(2000));
		assertThat(sut.toInt("M"), is(1000));
	}

	/**
	 * 100の倍数の場合。
	 */
	@Test
	public void testForHundreds() {
		assertThat(sut.toInt("MMMCM"), is(3900));
		assertThat(sut.toInt("MMMDCCC"), is(3800));
		assertThat(sut.toInt("MMMDCC"), is(3700));
		assertThat(sut.toInt("MMMDC"), is(3600));
		assertThat(sut.toInt("MMMD"), is(3500));
		assertThat(sut.toInt("MMMCD"), is(3400));
		assertThat(sut.toInt("MMMCCC"), is(3300));
		assertThat(sut.toInt("MMMCC"), is(3200));
		assertThat(sut.toInt("MMMC"), is(3100));
	}

	/**
	 * 10の倍数の場合。
	 */
	@Test
	public void testForTens() {
		assertThat(sut.toInt("MMMCMXC"), is(3990));
		assertThat(sut.toInt("MMMCMLXXX"), is(3980));
		assertThat(sut.toInt("MMMCMLXX"), is(3970));
		assertThat(sut.toInt("MMMCMLX"), is(3960));
		assertThat(sut.toInt("MMMCML"), is(3950));
		assertThat(sut.toInt("MMMCMXL"), is(3940));
		assertThat(sut.toInt("MMMCMXXX"), is(3930));
		assertThat(sut.toInt("MMMCMXX"), is(3920));
		assertThat(sut.toInt("MMMCMX"), is(3910));
	}

	/**
	 * 1の倍数の場合。
	 */
	@Test
	public void testForOnes() {
		assertThat(sut.toInt("MMMCMXCIX"), is(3999));
		assertThat(sut.toInt("MMMCMXCVIII"), is(3998));
		assertThat(sut.toInt("MMMCMXCVII"), is(3997));
		assertThat(sut.toInt("MMMCMXCVI"), is(3996));
		assertThat(sut.toInt("MMMCMXCV"), is(3995));
		assertThat(sut.toInt("MMMCMXCIV"), is(3994));
		assertThat(sut.toInt("MMMCMXCIII"), is(3993));
		assertThat(sut.toInt("MMMCMXCII"), is(3992));
		assertThat(sut.toInt("MMMCMXCI"), is(3991));
	}

	/**
	 * 適当にいくつかの例。(Wikipediaに書かれていた例。)
	 */
	@Test
	public void testForSomeCases() {
		assertThat(sut.toInt("XI"), is(11));
		assertThat(sut.toInt("XII"), is(12));
		assertThat(sut.toInt("XIV"), is(14));
		assertThat(sut.toInt("XVIII"), is(18));
		assertThat(sut.toInt("XXIV"), is(24));
		assertThat(sut.toInt("XLIII"), is(43));
		assertThat(sut.toInt("XCIX"), is(99));
		assertThat(sut.toInt("CDXCV"), is(495));
		assertThat(sut.toInt("MDCCCLXXXVIII"), is(1888));
		assertThat(sut.toInt("MCMXLV"), is(1945));
		assertThat(sut.toInt("MMMCMXCIX"), is(3999));
	}

	/**
	 * ローマ数字に使用されない文字が含まれていた場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testNonRomanNumeralCharacters() {
		assertRuntimeExceptionThrown("O");
		assertRuntimeExceptionThrown("iii");
		assertRuntimeExceptionThrown("200");
		assertRuntimeExceptionThrown("三千九百九十九");
	}

	/**
	 * ローマ数字に使用される文字だが、正しくない並びの場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testIncorrectSequences() {
		assertRuntimeExceptionThrown("IIII");
		assertRuntimeExceptionThrown("VIIII");
		assertRuntimeExceptionThrown("IIIV");
		assertRuntimeExceptionThrown("LXL");
		assertRuntimeExceptionThrown("MMMCMXCIXI");
	}

	/**
	 * argにtoIntメソッドを適用し、RuntimeExceptionが投げられることを確認する。
	 * 
	 * @param arg toIntメソッドを適用する引数。
	 */
	private void assertRuntimeExceptionThrown(String arg) {
		try {
			this.sut.toInt(arg);
			fail("例外が投げられなかった。");
		} catch (Exception e) {
			if (!RuntimeException.class.equals(e.getClass())) {
				fail("RuntimeException以外の例外が投げられた。");
			}
			// success.
		}
	}

}
