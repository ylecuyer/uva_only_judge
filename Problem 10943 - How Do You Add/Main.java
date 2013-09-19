import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Main {

	private static int[][] data;
	
	public static void main(String[] args) throws IOException {
		
		data = new int[101][];
		
		for (int i = 0; i <= 100; i++) {
			data[i] = new int[101];
			for (int j = 0; j <= 100; j++) {
				data[i][j] = -1;
			}
		}
		
		int N = 0;
		int K = 0;

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		do {

			String line = bf.readLine();

			String[] elts = line.split(" ");

			N = Integer.parseInt(elts[0]);
			K = Integer.parseInt(elts[1]);

			if (N != 0 && K != 0) {

				int nb = f(N,K);

				System.out.println(""+nb);
			}

		} while(N != 0 && K != 0);



	}

	private static int f(int N, int K) {

		if (data[N][K] != -1) {
			return data[N][K];
		}

		if (0 == K) {
			return 0;
		}

		if (1 == K) {
			return 1;
		}

		int ways = 0;

		for (int i = 0; i <= N; i++)
			ways += f(N-i, K-1)%1000000;

		ways %= 1000000;
		
		data[N][K] = ways;

		return ways;
	}

}
