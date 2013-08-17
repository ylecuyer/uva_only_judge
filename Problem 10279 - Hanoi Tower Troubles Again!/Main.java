
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class Main {

	public static void main(String[] args) throws IOException {

		int N = 0;
		int T = 0;
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				
		T = Integer.parseInt(bf.readLine());
				
		for (int i = 0; i < T; i++) {
			
			N = Integer.parseInt(bf.readLine());
			
			int max = find_max(N);
			
			System.out.println(max);
			
		}
		
		
		
	}

	private static int find_max(int N) {
		
		ArrayList<Integer>[] pegs= new ArrayList[N];
		
		for (int i = 0; i < N; i++)
			pegs[i] = new ArrayList<Integer>();
		
		int k = 0;
		
		boolean has_been_placed = false;
		
		do {
			
			k++;
			
			has_been_placed = place(k, pegs, N);
			
		} while (has_been_placed);
		
		
		return k-1;
	}

	private static boolean place(int k, ArrayList<Integer>[] pegs, int N) {

		for (int i = 0; i < N; i++) {
		
			if (pegs[i].isEmpty()) {
				
				pegs[i].add(k);
				
				return true;
			}
			
			int last = pegs[i].get(pegs[i].size() - 1);

			double s = Math.sqrt(last + k);
			
			if (s - Math.ceil(s) == 0) {
				
				pegs[i].add(k);
				
				return true;
			}
		
		}
		 
		return false;
	}

}
