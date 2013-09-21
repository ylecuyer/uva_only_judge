import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		
		do {
			
			line = bf.readLine();
			
			if (line == null)
				break;
			
			String[] elts = line.split(" ");
			
			int N = Integer.parseInt(elts[0]);
			
			int nb_tracks = Integer.parseInt(elts[1]);
			
			int tracks[] = new int[nb_tracks];
			
			for(int i = 0; i < nb_tracks; i++) {
				
				tracks[i] = Integer.parseInt(elts[i+2]);
				
			}
					
			output(nb_tracks, tracks, N);
			
		} while(true);

		
	}

	
	private static void output(int nb_tracks, int tracks[], int N) {
		
		int config_max = (int) (Math.pow(2, nb_tracks) - 1);

		int max = 0;
		int best_config = 0;
		
		for (int config = 0; config <= config_max; config++) {
			
			
			int score = compute(nb_tracks, tracks, config);
			
			if (score >= max && score <= N) {
				
				max = score;
				best_config = config;
				
			}
			
			
		}
		
		for(int j = 0; j < nb_tracks; j++) {
			
			if ((best_config&(1<<j)) > 0)
				System.out.print(tracks[j] + " ");
		}
		
		System.out.println("sum:" + max);
		
	}
	
	private static int compute(int nb_tracks, int[] tracks, int config) {

		int score = 0;
		
		for (int j = 0; j < nb_tracks; j++) {
		
			if ((config&(1<<j)) > 0)
				score += tracks[j];
		}
			
			
		return score;
	}

}
