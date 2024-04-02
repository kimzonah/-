import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, B, min;
	static int[] height, sel;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			height = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			min = Integer.MAX_VALUE;
			select(0, 0);
			
			sb.append("#"+t+" "+min+"\n");
		}
		System.out.println(sb);
		
	}
	
	// idx : height배열 인덱스 , sum : 더한값
	static void select(int idx, int sum) {
		
		
		// 모든 사람 탐색 했다면
		if(idx == N) {
//			System.out.println(sum);
			if(sum >= B) {
				min = Math.min(min, (sum-B));
			}
			return;
		}
		
		// 현재 사람을 탑에 포함한 경우
		select(idx+1, sum+height[idx]);
		
		// 현재 사람을 탑에 포함하지 않은 경우
		select(idx+1, sum);
		
	}
	
	
}
