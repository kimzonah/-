import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; t++) {

			sb.append("#" + t);

			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			int[][] adj = new int[V + 1][V + 1]; // 연결 정보를 담아줄 배열
			int pre[] = new int[V + 1]; // 선행되어야 하는 일의 수 체크

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E ; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				adj[a][b] = 1;
				pre[b]++;
			}

			Queue<Integer> que = new LinkedList<>();

			// 선행되어야 하는 일이 없는 번호를 먼저 다 넣어주기
			for (int i = 1; i <= V; i++) {
				if (pre[i] == 0)
					que.add(i);
			}

			// 선행수가 0인 애들 하나씩 뽑아주면서
			while (!que.isEmpty()) {
				int now = que.poll();
				sb.append(" " + now);
				// 현재 노드랑 연결된 애들중에서 현재 일 한 처리
				for (int i = 1; i <= V; i++) {
					if (adj[now][i] == 1) {
						pre[i]--;
						adj[now][i] = 0;
						// 선행해야 할 일이 더 안남았다면 큐에 넣어주기
						if (pre[i] == 0) {
							que.add(i);
						}
					}
				}

			}

			sb.append("\n");
		}
		
		System.out.println(sb);

	}

}
