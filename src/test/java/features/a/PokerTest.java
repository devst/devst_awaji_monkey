package features.a;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import features.Poker;

import org.junit.Before;
import org.junit.Test;

public class PokerTest {

	private Poker sut;

	@Before
	public void setUp() {
		sut = new features.a.Poker();
	}

	/**
	 * 引数が5個以外の場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testIrregalNumberOfCards() {
		assertRuntimeExceptionThrown();
		assertRuntimeExceptionThrown("SA", "S2", "S3", "S4");
		assertRuntimeExceptionThrown("SA", "S2", "S3", "S4", "S5", "S6");
	}

	/**
	 * カードの表記法が誤っている場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testIrregalCardNotation() {
		assertRuntimeExceptionThrown("S1", "H2", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("S11", "H2", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("SB", "H2", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("NA", "H2", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("sA", "H2", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("Sa", "H2", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("SＡ", "H2", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("SAA", "H2", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("SA", "H20", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("SA", "H2", "D31", "C4", "S5");
		assertRuntimeExceptionThrown("SA", "H2", "D3", "C42", "S5");
		assertRuntimeExceptionThrown("SA", "H2", "D3", "C4", "S53");
	}

	/**
	 * カードが重複している場合、RuntimeExceptionを投げる。
	 */
	@Test
	public void testDuplicateCards() {
		assertRuntimeExceptionThrown("SA", "SA", "D3", "C4", "S5");
		assertRuntimeExceptionThrown("SA", "H2", "SA", "C4", "S5");
		assertRuntimeExceptionThrown("SA", "H2", "D3", "SA", "S5");
		assertRuntimeExceptionThrown("SA", "H2", "D3", "C4", "SA");
		assertRuntimeExceptionThrown("SA", "H2", "H2", "C4", "S5");
		assertRuntimeExceptionThrown("SA", "H2", "D3", "H2", "S5");
		assertRuntimeExceptionThrown("SA", "H2", "D3", "S5", "S5");
	}

	/**
	 * フラッシュの場合。
	 */
	@Test
	public void testFlush() {
		assertThat(sut.poker("S2", "SK", "S5", "SA", "SQ"), is("フラッシュ"));
		assertThat(sut.poker("H3", "H2", "H8", "H5", "H4"), is("フラッシュ"));
		assertThat(sut.poker("D4", "D5", "DJ", "D9", "D10"), is("フラッシュ"));
		assertThat(sut.poker("C5", "C6", "CA", "CK", "C2"), is("フラッシュ"));
	}

	/**
	 * ストレートの場合。
	 */
	@Test
	public void testStraight() {
		assertThat(sut.poker("SA", "H2", "D3", "C4", "S5"), is("ストレート"));
		assertThat(sut.poker("H7", "D6", "C5", "S4", "H3"), is("ストレート"));
		assertThat(sut.poker("D9", "SK", "C10", "HQ", "HJ"), is("ストレート"));
		assertThat(sut.poker("CK", "CJ", "HA", "C10", "CQ"), is("ストレート"));
	}

	/**
	 * ストレートフラッシュの場合。
	 */
	@Test
	public void testStraightFlush() {
		assertThat(sut.poker("SA", "S2", "S3", "S4", "S5"), is("ストレートフラッシュ"));
		assertThat(sut.poker("H7", "H6", "H5", "H4", "H3"), is("ストレートフラッシュ"));
		assertThat(sut.poker("D9", "DK", "D10", "DQ", "DJ"), is("ストレートフラッシュ"));
		assertThat(sut.poker("CK", "CJ", "C9", "C10", "CQ"), is("ストレートフラッシュ"));
	}

	/**
	 * ロイヤルストレートフラッシュの場合。
	 */
	@Test
	public void testRoyalStraightFlush() {
		assertThat(sut.poker("SA", "S10", "SJ", "SQ", "SK"),
				is("ロイヤルストレートフラッシュ"));
		assertThat(sut.poker("HK", "HQ", "HJ", "H10", "HA"),
				is("ロイヤルストレートフラッシュ"));
		assertThat(sut.poker("DA", "DK", "D10", "DQ", "DJ"),
				is("ロイヤルストレートフラッシュ"));
		assertThat(sut.poker("CK", "CJ", "CA", "C10", "CQ"),
				is("ロイヤルストレートフラッシュ"));
	}

	/**
	 * フォー・オブ・ア・カインドの場合。
	 */
	@Test
	public void testFourOfAKind() {
		assertThat(sut.poker("SA", "HA", "DA", "CA", "SK"),
				is("フォー・オブ・ア・カインド"));
		assertThat(sut.poker("H2", "S2", "C2", "DJ", "D2"),
				is("フォー・オブ・ア・カインド"));
		assertThat(sut.poker("D10", "H10", "D9", "S10", "C10"),
				is("フォー・オブ・ア・カインド"));
		assertThat(sut.poker("CK", "CQ", "DK", "HK", "SK"),
				is("フォー・オブ・ア・カインド"));
		assertThat(sut.poker("D7", "CJ", "DJ", "HJ", "SJ"),
				is("フォー・オブ・ア・カインド"));
	}

	/**
	 * フルハウスの場合。
	 */
	@Test
	public void testFullHouse() {
		assertThat(sut.poker("SA", "HA", "DA", "CK", "SK"), is("フルハウス"));
		assertThat(sut.poker("H2", "S2", "C3", "D3", "H3"), is("フルハウス"));
		assertThat(sut.poker("D9", "H9", "D10", "S9", "C10"), is("フルハウス"));
		assertThat(sut.poker("CK", "CQ", "DQ", "HK", "SQ"), is("フルハウス"));
		assertThat(sut.poker("D7", "CA", "C7", "HA", "S7"), is("フルハウス"));
	}

	/**
	 * スリー・オブ・ア・カインドの場合。
	 */
	@Test
	public void testThreeOfAKind() {
		assertThat(sut.poker("SA", "HA", "DA", "CK", "SQ"),
				is("スリー・オブ・ア・カインド"));
		assertThat(sut.poker("H2", "S4", "C3", "D3", "H3"),
				is("スリー・オブ・ア・カインド"));
		assertThat(sut.poker("D9", "H9", "D10", "S9", "CJ"),
				is("スリー・オブ・ア・カインド"));
		assertThat(sut.poker("C6", "CQ", "DQ", "H7", "SQ"),
				is("スリー・オブ・ア・カインド"));
		assertThat(sut.poker("D7", "C5", "C7", "H8", "S7"),
				is("スリー・オブ・ア・カインド"));
	}

	/**
	 * ツーペアの場合。
	 */
	@Test
	public void testTwoPair() {
		assertThat(sut.poker("SA", "H3", "D3", "CK", "SK"), is("ツーペア"));
		assertThat(sut.poker("H2", "S2", "C5", "D5", "H7"), is("ツーペア"));
		assertThat(sut.poker("D9", "H6", "D10", "S9", "C10"), is("ツーペア"));
		assertThat(sut.poker("CK", "CA", "DJ", "HK", "SA"), is("ツーペア"));
		assertThat(sut.poker("D7", "CA", "DA", "H4", "S7"), is("ツーペア"));
	}

	/**
	 * ワンペアの場合。
	 */
	@Test
	public void testOnePair() {
		assertThat(sut.poker("SA", "H2", "D3", "CK", "SK"), is("ワンペア"));
		assertThat(sut.poker("H2", "S2", "C3", "D5", "H7"), is("ワンペア"));
		assertThat(sut.poker("D9", "H6", "D10", "S3", "C10"), is("ワンペア"));
		assertThat(sut.poker("CK", "CQ", "DJ", "HK", "SA"), is("ワンペア"));
		assertThat(sut.poker("D7", "CA", "C6", "HA", "SJ"), is("ワンペア"));
	}

	/**
	 * ノーペアの場合。
	 */
	@Test
	public void testHighCards() {
		assertThat(sut.poker("SA", "H2", "D3", "CK", "SJ"), is("ノーペア"));
		assertThat(sut.poker("H2", "S4", "C3", "D5", "H7"), is("ノーペア"));
		assertThat(sut.poker("D9", "H6", "D10", "S3", "C5"), is("ノーペア"));
		assertThat(sut.poker("CK", "CQ", "DJ", "H7", "SA"), is("ノーペア"));
		assertThat(sut.poker("D7", "C9", "C6", "HA", "SJ"), is("ノーペア"));
	}

	/**
	 * 引数にanalyzeメソッドを適用し、RuntimeExceptionが投げられることを確認する。
	 * 
	 * @param arg
	 *            coinUsageメソッドを適用する引数。
	 */
	private void assertRuntimeExceptionThrown(String... cards) {
		try {
			this.sut.poker(cards);
			fail("例外が投げられなかった。");
		} catch (Exception e) {
			if (!RuntimeException.class.equals(e.getClass())) {
				fail("例外" + e.getClass().getName() + "が投げられた。");
			}
			// success.
		}
	}

}
