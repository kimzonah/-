import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int C, N, count;
	static boolean[] visited;
	static int[][] Computer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		C = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		Computer = new int[C+1][C+1];
		visited = new boolean[C+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			Computer[r][c] = 1;
			Computer[c][r] = 1;
		}
		
		count = 0;
		visited[1] = true;
		DFS(1);
		System.out.println(count);
		
	}
	
	static void DFS(int n) {
		for(int i=1; i<=C; i++) {
			if(Computer[n][i]==1 && visited[i]==false) {
				visited[i] = true;
				count++;
				DFS(i);
			}
		}
	}

}
