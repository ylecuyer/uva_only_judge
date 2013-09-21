import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;



class Main {

	private static final int UNDEFINED = -1;
	private static final int BLACK = 1;
	private static final int WHITE = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		do {


			int nb_nodes = Integer.parseInt(bf.readLine());		

			if (nb_nodes == 0) break;
			
			int[] colors = new int[nb_nodes];
			int[] parents = new int[nb_nodes];
			boolean[] pending = new boolean[nb_nodes];

			for(int k = 0; k < nb_nodes; k++) {
				colors[k] = UNDEFINED;
				parents[k] = -1;
				pending[k] = false;
			}

			int[][] graph = new int[nb_nodes][nb_nodes];

			for(int i = 0; i < nb_nodes; i++)
				for(int j = 0; j < nb_nodes; j++)
					graph[i][j] = 0;

			int nb_arcs = Integer.parseInt(bf.readLine());

			for (int k = 0; k < nb_arcs; k++) {

				String line = bf.readLine();

				String[] elts = line.split(" ");

				int node1 = Integer.parseInt(elts[0]);
				int node2 = Integer.parseInt(elts[1]);

				graph[node1][node2] = 1;
				graph[node2][node1] = 1;

			}

			LinkedList<Integer> queue = new LinkedList<Integer>();

			queue.add(0);
			pending[0] = true;

			while (queue.size() != 0) {

				int node = queue.remove();

				//System.out.println("Analyzing: " + node);

				for (int k = 0; k < nb_nodes; k++) {

					if (graph[node][k] == 1) {

						if (pending[k] == false){

							//System.out.println("Adding " + k + " to queue [parent = " + node + "]");
							queue.add(k);
							parents[k] = node;
							pending[k] = true;
						}

					}

				}

				if (parents[node] == -1) {
					colors[node] = BLACK;
					//System.out.println("Coloring " + node + " in " + "BLACK");
				}
				else {

					if (colors[parents[node]] == BLACK) {
						colors[node] = WHITE;
						//System.out.println("Coloring " + node + " in " + "WHITE");
					}
					else {
						colors[node] = BLACK;
						//System.out.println("Coloring " + node + " in " + "BLACK");
					}
				}

			}

			boolean bicolorable = true;
			for(int i = 0; i < nb_nodes && bicolorable; i++)
				for(int j = 0; j < nb_nodes && bicolorable; j++) {

					if (graph[i][j] == 1) {

						if (colors[i] == colors[j]) {

							System.out.println("NOT BICOLORABLE.");
							bicolorable = false;
						}

					}
				}

			if (bicolorable) 
				System.out.println("BICOLORABLE.");

		} while(true);
	}
}
