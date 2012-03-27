package features.monkey;


public class Tsurukamezan implements features.Tsurukamezan {

	@Override
	public String tsurukame(int i, int j) {
		
		/**
		 * i: 鶴と亀の個体数
		 * j: 鶴と亀の足本数
		 * x: 鶴の個体数
		 * y: 亀の個体数
		**/
		int x = 0;
		int y = 0;
		String result = "";
		
		/**
		 * 足の本数が2の倍数でない場合はエラー
		**/
		if ((j % 2) > 0) {
			throw new RuntimeException();
		}
		/**
		 * 個体数の2倍が足の本数以上の場合はエラー
		**/
		if ((i *2) >= j) {
			throw new RuntimeException();
		}
		
		x = (4 * i - j) / 2;
		y = j /2 - i;
		result = "鶴" + x + "羽、亀" + y + "匹";
		return result;
	}
}
