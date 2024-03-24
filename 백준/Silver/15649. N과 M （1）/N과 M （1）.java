import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] result;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		result = new int[M + 1];
		visited = new boolean[N + 1];

		perm(1);
	}

	static void perm(int idx) {

		if (idx == M + 1) {
			for (int i = 1; i <= M; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= N; i++) {

			if (!visited[i]) {

				result[idx] = i;
				visited[i] = true;
				perm(idx + 1);
				visited[i] = false;
			}
		}

	}

}
