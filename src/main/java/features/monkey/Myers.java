package features.monkey;

public class Myers implements features.Myers {

	@Override
	public String getName(int i, int j, int k) {
		if (i < 1 || j < 1 || k < 1) {
			throw new RuntimeException();
		}

		if (i + j == k || j + k == i || i + k == j) {
			throw new RuntimeException();
		}

		if (i == j && j == k) {
			return "正三角形";
		}
		if (i == j || j == k || i == k) {
			return "二等辺三角形";
		}
		return "不等辺三角形";
	}
}
