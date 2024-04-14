import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// N개의 무개와 가치를 담아줄 배열
		int[] weights = new int[N+1];
		int[] values = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[N+1][K+1];
		
		// N개의 물건 중 1~i번째까지 물건을 고려해서
		for(int i=1; i<=N; i++) {
			
			// 0부터 K까지 가장 큰 가치로 채울 수 있는 경우들을 따져줄거임
			// 즉 i번째 물건이 새로운 고려대상이 되어서 i없이 채워놓은 최대값과 비교면서 i를 넣을지 말지 결정
			for(int w=0; w<=K; w++) {
				
				// 새로 넣을 i번째 물건이 지금 담아 볼 무게보다 작은면 -> 넣을 수 있다면
				if(weights[i]<=w) {
					// i없이 w만큼 채웠던 값과 i없이 i의 무게만큼 뺀(i 무게를 비워둔) 저장된 최대값에 i가치를 더한것 중 더 큰걸로 선택
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weights[i]]+values[i]);
				}
				
				// i번째가 어차피 w보다 무거워서 못 넣을 몰건이었다면 그냥 i없던 가장 최신 값으로 넣어줌
				else {
					dp[i][w] = dp[i-1][w];
				}
			}
		}
		
		System.out.println(dp[N][K]);
		
	}

}
