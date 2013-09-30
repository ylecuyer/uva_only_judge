import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		String line = null;

		ArrayList<Integer> numbers_list = new ArrayList<Integer>();

		while ((line = bf.readLine()) != null) {
			
			numbers_list.add(Integer.parseInt(line));
			
		}

		int[] numbers = new int[numbers_list.size()];

		for(int i = 0; i < numbers.length; i++)
			numbers[i] = numbers_list.get(i);

		int config_max = (int) (Math.pow(2, numbers.length) - 1);

		int best_config = 0;
		int max_length = 0;

		for (int config = 0; config <= config_max; config++) {

			System.out.println(config + " / " + config_max);
			
			int length = config_length(config);

			if (length < max_length)
				continue;
			
			if (isStricklyIngreasing(config, numbers) && length > max_length) {

				max_length = length;
				best_config = config;

			}

		}

		System.out.println(max_length);
		System.out.println("-");

		for (int j = 0; j < numbers.length; j++) {

			if ((best_config&(1<<j)) > 0) {

				int current = numbers[j];

				System.out.println(current);

			}

		}

	}

	private static boolean isStricklyIngreasing(int config, int[] numbers) {

		int last = 0;
		boolean first = true;

		for (int j = 0; j < numbers.length; j++) {

			if ((config&(1<<j)) > 0) {

				int current = numbers[j];

				if (first) {

					last = current;
					first = false;
				}
				else {

					if (current <= last)
						return false;
					else 
						last = current;
				}

			}

		}

		return true;
	}

	private static int config_length(int config) {
		int length = 0;

		while(config > 0) {

			if ((config&1) > 0)
				length++;

			config >>= 1;
		}

		return length;
	}

}
