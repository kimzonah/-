import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] ticket = new int[4];
	static int[] swimDay = new int[13];
	static boolean[] visited;
	static int min;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < 13; i++) {
				swimDay[i] = Integer.parseInt(st.nextToken());
			}

			min = ticket[3];
			DFS(1, 0);
			System.out.println("#" + t + " " + min);

		}
	}

	static void DFS(int month, int price) {

//        System.out.println(month);

		if (price >= min)
			return;

		if (month > 12) {
			if (min > price)
				min = price;
			return;
		}

		// 이번달에 수영을 안가면 다음달로
		if (swimDay[month] == 0) {
			DFS(month + 1, price);
		}
		// 이번달에 수영을 할 때만
		else {

			// 현재달 일일권 산다면
			DFS(month + 1, price + swimDay[month] * ticket[0]);

			// 현재달 한달권 산다면
			DFS(month + 1, price + ticket[1]);

			// 현재달 포함 세달권 산다면 세 달 뒤로
			DFS(month + 3, price + ticket[2]);
			

//			// 1년 이용권
//			DFS(12, price + ticket[3]);

		}

	}
}