import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Main {

	private static boolean has_been_found = false;
	
	public static void main(String[] args) throws IOException {

		int L = 0; // 1 <= L <= 26
		int n = 0;
		
		char seq[] = new char[80];
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		do {
		
			String line = bf.readLine();
						
			String[] elts = line.split(" ");
			
			n = Integer.parseInt(elts[0]);
			L = Integer.parseInt(elts[1]);
			
			if (n != 0 && L != 0) {
				has_been_found = false;
			
				for(int i = 0; i < 80; seq[i++] = 'X');
				place(seq, 0, n, L);
			
				System.out.println(n);
			}
			
		} while (n != 0 && L != 0);
	}

	private static boolean place(char[] seq, int k, int n, int L) {
		
		for(char i = 'A'; i < 'A' + L; i++) {
			
			seq[k] = i;

			
			if (!is_easy(new String(seq).substring(0, k+1))) {
												
				if (k < n-1) place(seq, k+1, n, L);
				else {
					print(seq);
					has_been_found = true;
				}
	
				
				seq[k+1] = 'X';
			}
		
			if (has_been_found) break;
		}

				
		return false;
	}

	private static void print(char[] seq) {
		
		for(int i = 0; i < 80; i++) {
			if (seq[i] == 'X')
				break;
			
			System.out.print(seq[i]);
			
			
			if ((i+1)%4 == 0 && seq[i+1] != 'X') System.out.print(" ");
		}
		
		System.out.println("");
	}

	private static boolean is_easy(String str) {
		

		
		int length = str.length();
		
		int half_length = length/2;
				
		//System.out.println("Is easy? " + str);
		
		for (int i = 1; i <= half_length; i++)
			if (_is_easy(str, i)) return true;
		
		return false;
	}

	private static boolean _is_easy(String str, int k) {
		
		String pattern = "(.{"+k+"})\\1";
		
		Matcher matcher = Pattern.compile(pattern).matcher(str);
		
		if (matcher.find())
			return true;
		
		return false;
	}

}
