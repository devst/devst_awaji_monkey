package features.a;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator implements features.Calculator {

	/**
	 * 計算式のパターン.
	 */
	private static final Pattern CALC_PATTERN = Pattern
			.compile("^([0-9]+)([\\+\\-\\*\\/])([0-9]+)$");

	/**
	 * 計算結果のフォーマッター
	 */
	private static final DecimalFormat RESULT_FORMATTER = new DecimalFormat("#.###");
	/**
	 * 演算子別の計算実行機のマップ.
	 */
	private Map<String, CalculateExecuter> calculateExecuters;

	/**
	 * コンストラクタ.
	 * 
	 * <pre>
	 * 演算子別の計算実行機のマップを組み立てます.
	 * </pre>
	 */
	public Calculator() {
		calculateExecuters = new HashMap<String, CalculateExecuter>();
		calculateExecuters.put("+", new CalculateExecuter() {
			@Override
			public BigDecimal execute(Integer a, Integer b) {
				return BigDecimal.valueOf(a + b);
			};
		});
		calculateExecuters.put("-", new CalculateExecuter() {
			@Override
			public BigDecimal execute(Integer a, Integer b) {
				return BigDecimal.valueOf(a - b);
			};
		});
		calculateExecuters.put("*", new CalculateExecuter() {
			@Override
			public BigDecimal execute(Integer a, Integer b) {
				return BigDecimal.valueOf(a * b);
			};
		});
		calculateExecuters.put("/", new CalculateExecuter() {
			@Override
			public BigDecimal execute(Integer a, Integer b) {
				return BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 3,
						BigDecimal.ROUND_HALF_UP);
			};
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String execute(String arg0) {
		String result = null;
		Matcher m = CALC_PATTERN.matcher(arg0);
		argumentCheck(m.matches(), "数式が正しくありません.[%s]", arg0);
		Integer a = Integer.valueOf(m.group(1));
		Integer b = Integer.valueOf(m.group(3));
		argumentCheck(a > 0, "左辺が正の整数ではありません.[%s]", arg0);
		argumentCheck(b > 0, "右辺が正の整数ではありません.[%s]", arg0);
		String operator = m.group(2);
		result = RESULT_FORMATTER.format(calculateExecuters.get(operator).execute(a, b));
		return result;
	}

	/**
	 * 引数チェック.
	 * 
	 * <pre>
	 * 判定結果がfalseの場合、引数例外を発行します。
	 * </pre>
	 * 
	 * @param isValid
	 *            判定結果
	 * @param message
	 *            例外メッセージ
	 * @param args
	 *            例外メッセージ引数
	 * @throws IllegalArgumentException
	 *             引数例外
	 */
	private void argumentCheck(boolean isValid, String message, Object... args) {
		if (!isValid) {
			throw new IllegalArgumentException(String.format(message, args));
		}
	}

	/**
	 * 計算実行機のインターフェース.
	 */
	interface CalculateExecuter {
		/**
		 * 計算実行メソッドのインターフェース.
		 * 
		 * @param a
		 *            　値１
		 * @param b
		 *            　値２
		 * @return 値１と値２を計算した結果
		 */
		BigDecimal execute(Integer a, Integer b);
	}
}
