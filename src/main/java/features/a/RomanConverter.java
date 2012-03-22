package features.a;

public class RomanConverter implements features.RomanConverter{

	@Override
	public int toArabic(String roman) {
		if (roman == null) {
			throw new RuntimeException();
		}

		if (roman.isEmpty()) {
			throw new RuntimeException();
		}

		final int[] POWER_OF_RADIX = { 1000, 100, 10, 1 };
		final String[] THOUSANDS_PLACE_DIGITS = { "MMM", "MM", "M" };
		final String[] HUNDREDS_PLACE_DIGITS = { "CM", "DCCC", "DCC", "DC",
				"D", "CD", "CCC", "CC", "C" };
		final String[] TENS_PLACE_DIGITS = { "XC", "LXXX", "LXX", "LX", "L",
				"XL", "XXX", "XX", "X" };
		final String[] ONES_PLACE_DIGITS = { "IX", "VIII", "VII", "VI", "V",
				"IV", "III", "II", "I" };
		final String[][] PLACES = { THOUSANDS_PLACE_DIGITS,
				HUNDREDS_PLACE_DIGITS, TENS_PLACE_DIGITS, ONES_PLACE_DIGITS };

		int result = 0;

		int placeIndex = 0;
		for (String[] place : PLACES) {
			int digitsIndex = 0;
			for (String DIGIT : place) {
				if (roman.startsWith(DIGIT)) {
					result += POWER_OF_RADIX[placeIndex]
							* (place.length - digitsIndex);
					roman = roman.substring(DIGIT.length());
					break;
				}
				digitsIndex++;
			}
			placeIndex++;
		}

		if (!roman.isEmpty()) {
			throw new RuntimeException();
		}

		return result;
	}

}
