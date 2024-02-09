import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob1260 {

	static int N, M, V;
	static int[][] Arr;
	static int[] visited;
	static Queue<Integer> que;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드 수
		M = Integer.parseInt(st.nextToken()); // 간선 수
		V = Integer.parseInt(st.nextToken()); // 탐색 시작 지점

		Arr = new int[N + 1][N + 1];

		// 연결된 노드 인접행렬로 표현
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			Arr[a][b] = 1;
			Arr[b][a] = 1;
		}

		// DFS
		visited = new int[N + 1];
		visited[V] = 1;
		DFS(V);

		System.out.println();

		// BFS
		visited = new int[N + 1];
		visited[V] = 1;
		BFS(V);

	}

	public static void DFS(int node) {
		System.out.print(node + " ");
		for (int i = 1; i <= N; i++) {
			// 현재 위치인 노드와 연결된 노드이고, 방문한 적 없다면
			if (Arr[node][i] == 1 && visited[i] == 0) {
				visited[i] = 1; // 방문 처리
				DFS(i); // i를 탐색 시작
			}
		}
	}

	public static void BFS(int V) {
		Queue<Integer> que = new LinkedList<>();
		que.add(V); // 첫 시작 노드 큐에 넣고 시작

		// que가 빌 때까지
		while (!que.isEmpty()) {
			// 맨 앞 뽑히는 노드 다음 탐색
			int node = que.poll();
			System.out.print(node + " ");

			// 반복문 돌면서 맨 앞 뽑혔던 노드랑 연결되고 방문한 적 없느 노드들 큐에 넣기
			for (int i = 1; i <= N; i++) {
				if (Arr[node][i] == 1 && visited[i] == 0) {
					visited[i] = 1;
					que.add(i);
				}
			}
		}

	}

}