

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] sel;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sel = new int[M];
		
		find(1,0);
		
	}
	
	static void find(int nidx, int sidx) {
		
		if(sidx==M) {
			for(int i=0; i<M; i++) {
				System.out.print(sel[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=nidx; i<=N; i++) {
			sel[sidx] = i;
			find(i+1, sidx+1);
		}
		
	}

}
