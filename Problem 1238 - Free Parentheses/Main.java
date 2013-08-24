import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


class Main {

	public static void main(String[] args) throws ScriptException, IOException {
		

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		boolean stop = false;
		
		do {
			
			String str = bf.readLine();

			if (str == null)
				stop = true;
			
			System.out.println(count(str));		
		}
		while (!stop);
	}

	private static int nth_index_of(int n, char c, String str) {
		
		int pos = str.indexOf(c, 0);
		
	    while (n-- > 0 && pos != -1)
	        pos = str.indexOf(c, pos+1);
	    
	    return pos;
	}
	
	private static int pos_nth_sign(int n, String str) {
		
		Matcher matcher = Pattern.compile("\\+|-").matcher(str);
		
		matcher.find();
		
		while (n-- > 0) 
			matcher.find();
		
		return matcher.start();
		
	}
	
	private static int count(String str) {
		
		int N = str.split(" (\\+|-) ").length;
		
		//System.out.println("N = " + N);
		
		int signs = str.split("-").length - 1;		
		
		//System.out.println("Signs = " + signs);

		if (signs == 0)
			return 1;
		
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("JavaScript");
		
		int[] limits = new int[signs];
		int[] positions = new int[signs];

		for(int i = 0; i < signs; i++) {
		
			int index = nth_index_of(i, '-', str);
			
			for (int j = 0; j < N - 1; j++)
				if (pos_nth_sign(j, str) == index) positions[i] = j;

			int sub_signs = str.substring(0, index - 1).split(" (\\+|-) ").length;
			
			limits[i] = N - sub_signs;
			
			//System.out.println("Positions["+i+"] = " + positions[i]);
			//System.out.println("Limits["+i+"] = " + limits[i]);
		}
		

		int[] state = new int[signs];
		
		
		boolean has_next = false;
		
		
		ArrayList<Double> results = new ArrayList<Double>();
		
		for (int i = 0; i < signs; i++) {
			
			state[i] = 0;
			
		}
		
		do {
			
			StringBuilder builder = new StringBuilder(str);

			
			for (int j = 0; j < signs; j++) {
				
					if (state[j] == limits[j])
						continue;
				
					int i = state[j];
					
					if (i == 0) {
						
						if (positions[j] != 0)
							builder.insert(pos_nth_sign(positions[j] - 1, builder.toString()) + 1, "(");
						else
							builder.insert(0, "(");
							
						if (positions[j] != N -2)
							builder.insert(pos_nth_sign(positions[j] + 1, builder.toString()), ")");
						else 
							builder.append(")");

					}
					else if (i == limits[j] - 1) {
						
						builder.insert(pos_nth_sign(positions[j], builder.toString()) + 1, "(");
						builder.append(")");
						
					}
					else {
						
						builder.insert(pos_nth_sign(positions[j], builder.toString()) + 1, "(");
						builder.insert(pos_nth_sign(positions[j] + i + 1, builder.toString()), ")");
						
					}
				
			} // end for j
			
			
			//System.out.println("[" + state[0] + ", " + state[1] + ", " + state[2] + "]");

			
			//System.out.print(builder.toString());

			Double res;
			try {
				res = (Double) engine.eval(builder.toString());
				
			
				
				if (results.indexOf(res) == -1)
					results.add(res);
				
				//System.out.println("  = " + res + " --  " + results.indexOf(res));

			} catch (NumberFormatException | ScriptException e1) {
				
			}
			
			state[state.length - 1]++;
			
			for(int k = state.length - 1; k > 0; k--) {
				
				if (state[k] == limits[k] + 1) {
					state[k] = 0;
					state[k-1]++;
				}
				
			}
			
			if (state[0] == limits[0] + 1)
				has_next = false;
			else 
				has_next = true;
			
			//System.out.println(has_next);
			
		}
		while (has_next);
			

		
		return results.size();
	}

}
