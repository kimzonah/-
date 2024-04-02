import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		
		// sum이 B이상이라면
		// 지금이 B이상이면서 차이가 가장 작을 때니까 min 갱신해주고 중단
		if(sum >= B) {
			min = Math.min(min, sum-B);
			return;
		}
		
		// 모든 사람 탐색 했다면
		if(idx == N)
			return;
		
		// 현재 사람을 탑에 포함한 경우
		select(idx+1, sum+height[idx]);
		
		// 현재 사람을 탑에 포함하지 않은 경우
		select(idx+1, sum);
		
	}
	
	
}
