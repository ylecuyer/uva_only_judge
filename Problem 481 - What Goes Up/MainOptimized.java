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

		
		int[] LIS = new int[numbers.length];
		int[] backtrack = new int[numbers.length];
		
		int max_length = 1;
		int best_end = 1;
		
		for (int i = 0; i < numbers.length; i++) {
			
		
			LIS[i] = 1;
			backtrack[i] = -1;
			
			for (int j = 0; j < i; j++) {
				
				if (LIS[j] + 1 > LIS[i] && numbers[j] < numbers[i]) {
					
					LIS[i] = LIS[j] + 1;
					backtrack[i] = j;
					
				}
				
			}
			
			if (LIS[i] > max_length) {
				
				max_length = LIS[i];
				best_end = i;
				
			}
			
		}
		
		System.out.println("" + max_length);
		
		System.out.println("-");
	
		int[] res = new int[max_length];
		
		int i = 0;
		do {
			

			res[i++] = numbers[best_end];
			
			best_end = backtrack[best_end];
		} while (best_end != -1);
		
		for (int k = max_length-1; k >= 0; k--)
			System.out.println("" + res[k]);

		
	}

}
