package features.a;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class RomanConverterTest {

	private RomanConverter sut;

	@Before
	public void setUp() {
		sut = new features.a.RomanConverter();
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
		assertThat(sut.toArabic("MMM"), is(3000));
		assertThat(sut.toArabic("MM"), is(2000));
		assertThat(sut.toArabic("M"), is(1000));
	}

	/**
	 * 100の倍数の場合。
	 */
	@Test
	public void testForHundreds() {
		assertThat(sut.toArabic("MMMCM"), is(3900));
		assertThat(sut.toArabic("MMMDCCC"), is(3800));
		assertThat(sut.toArabic("MMMDCC"), is(3700));
		assertThat(sut.toArabic("MMMDC"), is(3600));
		assertThat(sut.toArabic("MMMD"), is(3500));
		assertThat(sut.toArabic("MMMCD"), is(3400));
		assertThat(sut.toArabic("MMMCCC"), is(3300));
		assertThat(sut.toArabic("MMMCC"), is(3200));
		assertThat(sut.toArabic("MMMC"), is(3100));
	}

	/**
	 * 10の倍数の場合。
	 */
	@Test
	public void testForTens() {
		assertThat(sut.toArabic("MMMCMXC"), is(3990));
		assertThat(sut.toArabic("MMMCMLXXX"), is(3980));
		assertThat(sut.toArabic("MMMCMLXX"), is(3970));
		assertThat(sut.toArabic("MMMCMLX"), is(3960));
		assertThat(sut.toArabic("MMMCML"), is(3950));
		assertThat(sut.toArabic("MMMCMXL"), is(3940));
		assertThat(sut.toArabic("MMMCMXXX"), is(3930));
		assertThat(sut.toArabic("MMMCMXX"), is(3920));
		assertThat(sut.toArabic("MMMCMX"), is(3910));
	}

	/**
	 * 1の倍数の場合。
	 */
	@Test
	public void testForOnes() {
		assertThat(sut.toArabic("MMMCMXCIX"), is(3999));
		assertThat(sut.toArabic("MMMCMXCVIII"), is(3998));
		assertThat(sut.toArabic("MMMCMXCVII"), is(3997));
		assertThat(sut.toArabic("MMMCMXCVI"), is(3996));
		assertThat(sut.toArabic("MMMCMXCV"), is(3995));
		assertThat(sut.toArabic("MMMCMXCIV"), is(3994));
		assertThat(sut.toArabic("MMMCMXCIII"), is(3993));
		assertThat(sut.toArabic("MMMCMXCII"), is(3992));
		assertThat(sut.toArabic("MMMCMXCI"), is(3991));
	}

	/**
	 * 適当にいくつかの例。(Wikipediaに書かれていた例。)
	 */
	@Test
	public void testForSomeCases() {
		assertThat(sut.toArabic("XI"), is(11));
		assertThat(sut.toArabic("XII"), is(12));
		assertThat(sut.toArabic("XIV"), is(14));
		assertThat(sut.toArabic("XVIII"), is(18));
		assertThat(sut.toArabic("XXIV"), is(24));
		assertThat(sut.toArabic("XLIII"), is(43));
		assertThat(sut.toArabic("XCIX"), is(99));
		assertThat(sut.toArabic("CDXCV"), is(495));
		assertThat(sut.toArabic("MDCCCLXXXVIII"), is(1888));
		assertThat(sut.toArabic("MCMXLV"), is(1945));
		assertThat(sut.toArabic("MMMCMXCIX"), is(3999));
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
	 * argにtoArabicメソッドを適用し、RuntimeExceptionが投げられることを確認する。
	 * 
	 * @param arg toArabicメソッドを適用する引数。
	 */
	private void assertRuntimeExceptionThrown(String arg) {
		try {
			this.sut.toArabic(arg);
			fail("例外が投げられなかった。");
		} catch (Exception e) {
			if (!RuntimeException.class.equals(e.getClass())) {
				fail("RuntimeException以外の例外が投げられた。");
			}
			// success.
		}
	}

}
