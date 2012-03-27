package features.monkey;

import java.util.Arrays;

public class Poker implements features.Poker {

	enum Suit {
		Spade, Heart, Diamond, Club
	}

	enum Lank {
		Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
	}

	final int NUMBER_OF_CARDS = 5;
	final String[] SUIT_NOTATIONS = { "S", "H", "D", "C" };
	final String[] LANK_NOTATIONS = { "A", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "J", "Q", "K", };

	@Override
	public String poker(String... cards) {
		if (cards.length != NUMBER_OF_CARDS) {
			throw new RuntimeException();
		}

		Card[] hand = new Card[NUMBER_OF_CARDS];
		int i = 0;
		for (String card : cards) {
			hand[i] = cardFromString(card);
			i++;
		}
		Arrays.sort(hand);

		if (isDuplicate(hand)) {
			throw new RuntimeException();
		}

		boolean isFlush = isFlush(hand);
		boolean isStraight = isStraight(hand);
		boolean isRoyalStraight = isRoyalStraight(hand);

		if (isRoyalStraight && isFlush) {
			return "ロイヤルストレートフラッシュ";
		}

		if (isStraight && isFlush) {
			return "ストレートフラッシュ";
		}

		if (isFlush) {
			return "フラッシュ";
		}

		if (isStraight) {
			return "ストレート";
		}

		if (isFourOfAKind(hand)) {
			return "フォー・オブ・ア・カインド";
		}

		if (isFullHouse(hand)) {
			return "フルハウス";
		}

		if (isThreeOfAKind(hand)) {
			return "スリー・オブ・ア・カインド";
		}

		if (isTwoPair(hand)) {
			return "ツーペア";
		}

		if (isOnePair(hand)) {
			return "ワンペア";
		}

		return "ノーペア";
	}

	/**
	 * 手札に重複があるか調べる。手札はソートされているものとする。
	 * 
	 * @param hand
	 * @return 重複があればtrue、なければfalse
	 */
	private boolean isDuplicate(Card[] hand) {
		for (int i = 0; i < hand.length - 1; i++) {
			if (hand[i].equals(hand[i + 1])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 手札がフラッシュであるか判定する。 手札はソートされているものとする。
	 * 
	 * @param hand
	 * @return フラッシュであればtrue、そうでなければfalse
	 */
	private boolean isFlush(Card[] hand) {
		for (Card card : hand) {
			if (!hand[0].getSuit().equals(card.getSuit())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 手札がストレートであるか判定する。 手札はソートされているものとする。
	 * 
	 * @param hand
	 * @return ストレートであればtrue、そうでなければfalse
	 */
	private boolean isStraight(Card[] hand) {
		if (isRoyalStraight(hand)) {
			return true;
		}
		for (int i = 0; i < hand.length - 1; i++) {
			if (hand[i].getLank().ordinal() + 1 != hand[i + 1].getLank()
					.ordinal()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 手札がロイヤルストレートであるか判定する。 手札はソートされているものとする。
	 * ロイヤルストレートとは、10、Jack、Queen、King、Aceのストレートのこととする。 ポーカーの役としては存在しない。
	 * 
	 * @param hand
	 * @return 10、Jack、Queen、King、Aceのストレートであればtrue、そうでなければfalse
	 */
	private boolean isRoyalStraight(Card[] hand) {
		if (hand[0].getLank() == Lank.Ace && hand[1].getLank() == Lank.Ten
				&& hand[2].getLank() == Lank.Jack
				&& hand[3].getLank() == Lank.Queen
				&& hand[4].getLank() == Lank.King) {
			return true;
		}
		return false;
	}

	/**
	 * 手札がフォー・オブ・ア・カインドであるか判定する。 手札はソートされているものとする。
	 * 
	 * @param hand
	 * @return フォー・オブ・ア・カインドであればtrue、そうでなければfalse
	 */
	private boolean isFourOfAKind(Card[] hand) {
		if (hand[0].getLank().equals(hand[1].getLank())
				&& hand[1].getLank().equals(hand[2].getLank())
				&& hand[2].getLank().equals(hand[3].getLank())) {
			return true;
		}
		if (hand[1].getLank().equals(hand[2].getLank())
				&& hand[2].getLank().equals(hand[3].getLank())
				&& hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		return false;
	}

	/**
	 * 手札がフルハウスであるか判定する。 手札はソートされているものとする。
	 * 
	 * @param hand
	 * @return フルハウスであればtrue、そうでなければfalse
	 */
	private boolean isFullHouse(Card[] hand) {
		if (hand[0].getLank().equals(hand[1].getLank())
				&& hand[1].getLank().equals(hand[2].getLank())
				&& hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		if (hand[0].getLank().equals(hand[1].getLank())
				&& hand[2].getLank().equals(hand[3].getLank())
				&& hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		return false;
	}

	/**
	 * 手札がスリー・オブ・ア・カインドであるか判定する。 手札はソートされているものとする。
	 * 
	 * @param hand
	 * @return スリー・オブ・ア・カインドであればtrue、そうでなければfalse
	 */
	private boolean isThreeOfAKind(Card[] hand) {
		if (hand[0].getLank().equals(hand[1].getLank())
				&& hand[1].getLank().equals(hand[2].getLank())
				&& !hand[2].getLank().equals(hand[3].getLank())
				&& !hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		if (!hand[0].getLank().equals(hand[1].getLank())
				&& hand[1].getLank().equals(hand[2].getLank())
				&& hand[2].getLank().equals(hand[3].getLank())
				&& !hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		if (!hand[0].getLank().equals(hand[1].getLank())
				&& !hand[1].getLank().equals(hand[2].getLank())
				&& hand[2].getLank().equals(hand[3].getLank())
				&& hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		return false;
	}

	/**
	 * 手札がツーペアであるか判定する。 手札はソートされているものとする。
	 * 
	 * @param hand
	 * @return ツーペアであればtrue、そうでなければfalse
	 */
	private boolean isTwoPair(Card[] hand) {
		if (hand[0].getLank().equals(hand[1].getLank())
				&& !hand[1].getLank().equals(hand[2].getLank())
				&& hand[2].getLank().equals(hand[3].getLank())
				&& !hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		if (hand[0].getLank().equals(hand[1].getLank())
				&& !hand[1].getLank().equals(hand[2].getLank())
				&& !hand[2].getLank().equals(hand[3].getLank())
				&& hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		if (!hand[0].getLank().equals(hand[1].getLank())
				&& hand[1].getLank().equals(hand[2].getLank())
				&& !hand[2].getLank().equals(hand[3].getLank())
				&& hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		return false;
	}

	/**
	 * 手札がワンペアであるか判定する。 手札はソートされているものとする。
	 * 
	 * @param hand
	 * @return ワンペアであればtrue、そうでなければfalse
	 */
	private boolean isOnePair(Card[] hand) {
		if (hand[0].getLank().equals(hand[1].getLank())
				&& !hand[1].getLank().equals(hand[2].getLank())
				&& !hand[2].getLank().equals(hand[3].getLank())
				&& !hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		if (!hand[0].getLank().equals(hand[1].getLank())
				&& hand[1].getLank().equals(hand[2].getLank())
				&& !hand[2].getLank().equals(hand[3].getLank())
				&& !hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		if (!hand[0].getLank().equals(hand[1].getLank())
				&& !hand[1].getLank().equals(hand[2].getLank())
				&& hand[2].getLank().equals(hand[3].getLank())
				&& !hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		if (!hand[0].getLank().equals(hand[1].getLank())
				&& !hand[1].getLank().equals(hand[2].getLank())
				&& !hand[2].getLank().equals(hand[3].getLank())
				&& hand[3].getLank().equals(hand[4].getLank())) {
			return true;
		}
		return false;
	}

	/**
	 * カードの表記法に則った文字列からCardオブジェクトを生成する。
	 * 
	 * @param cardNotation
	 * @return Cardオブジェクト
	 * @exception RuntimeException カードの表記法が誤っている場合
	 */
	private Card cardFromString(String cardNotation) {
		if (!(cardNotation.length() == 2 || cardNotation.length() == 3)) {
			throw new RuntimeException();
		}

		Suit suit = null;
		String suitOfCard = cardNotation.substring(0, 1);
		int suitIndex = 0;
		for (String suitNotation : SUIT_NOTATIONS) {
			if (suitOfCard.equals(suitNotation)) {
				suit = Suit.values()[suitIndex];
			}
			suitIndex++;
		}
		if (suit == null) {
			throw new RuntimeException();
		}

		String lankOfCard = cardNotation.substring(1);
		Lank lank = null;
		int lankIndex = 0;
		for (String lankNotation : LANK_NOTATIONS) {
			if (lankOfCard.equals(lankNotation)) {
				lank = Lank.values()[lankIndex];
			}
			lankIndex++;
		}
		if (lank == null) {
			throw new RuntimeException();
		}

		return new Card(suit, lank);
	}

	private class Card implements Comparable<Card> {

		private Suit suit;
		private Lank lank;

		public Card(Suit suit, Lank lank) {
			this.setSuit(suit);
			this.setLank(lank);
		}

		public Suit getSuit() {
			return suit;
		}

		public void setSuit(Suit suit) {
			this.suit = suit;
		}

		public Lank getLank() {
			return lank;
		}

		public void setLank(Lank lank) {
			this.lank = lank;
		}

		public boolean equals(Card other) {
			return this.getSuit().equals(other.getSuit())
					&& this.getLank().equals(other.getLank());
		}

		/**
		 * このカードと指定されたカードの順序を比較する。 このカードのランクが指定されたカードのランクより小さい場合は負の整数、
		 * 大きい場合は正の整数を返す。 ランクが等しい場合は、このカードのスートが
		 * 指定されたカードのスートより小さい場合は負の整数、等しい場合はゼロ、 大きい場合は正の整数を返す。
		 * スートとランクの定義により、必ずしもカードの強さの順にならない。
		 * 
		 * @param other
		 *            比較対象のカード
		 * @return このカードが指定されたカードより小さい場合は負の整数、等しい場合はゼロ、大きい場合は正の整数
		 */
		@Override
		public int compareTo(Card other) {
			int compareLank = this.getLank().compareTo(other.getLank());
			if (compareLank != 0) {
				return compareLank;
			}
			return this.getSuit().compareTo(other.getSuit());
		}

	}

}
