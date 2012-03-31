package features.monkey;


public class FizzBuzz implements features.FizzBuzz {

	@Override
	public String fizzBuzz(int i) {
          if (i < 1) throw new RuntimeException();
		
		int residue3 = 0;
		int residue5 = 0;
		String result = "";
		
		residue3 = i % 3;
		residue5 = i % 5;

		/**
		 * 3で割り切れる数字の場合は"Fizz"
		 **/
		if (residue3 == 0){
			result = result + "Fizz";
		}

		/**
		 * 5で割り切れる数字の場合は"Buzz"
		 **/
		if (residue5 == 0){
			result = result + "Buzz";
		}

		/**
		 * 3, 5 のいずれでも割り切れない場合は、数字を戻す
		 **/
		if (result == ""){
			result = String.valueOf(i);
		}

		return result;
	}
}
