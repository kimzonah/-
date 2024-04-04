import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA수영장DP {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		int[] ticket = new int[4]; // 0:일일권, 1:한달권, 2:세달권, 3:일년권
		int[] plan = new int[13];
		int[] dp;

		for (int t = 1; t <= T; t++) {
			dp = new int[13];

			// 이용권 가격 정보 받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}

			// 월별 수영 계획 입력 받기
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < 13; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i < 13; i++) { // 1~12월

				int min = Integer.MAX_VALUE;

				// 일일권 사기
				min = Math.min(dp[i - 1] + ticket[0] * plan[i], min);

				// 한달권 사기
				if (i >= 1)
					min = Math.min(dp[i - 1] + ticket[1], min);
				
				// 세달권 사기
				if (i >= 3)
					min = Math.min(dp[i - 3] + ticket[2], min);

				dp[i] = min;

			}

			sb.append("#" + t + " " + Math.min(dp[12], ticket[3]) + "\n");

		}

		System.out.println(sb);

	}

}
