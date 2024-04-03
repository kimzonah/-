import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<int[]> que = new LinkedList<>();
	static int[] hr = { -2, -2, -1, -1, 1, 1, 2, 2 }; // 말처럼 이동할때 좌표
	static int[] hc = { -1, 1, -2, 2, -2, 2, -1, 1 };
	static int[] dr = { -1, 1, 0, 0 }; // 기본 4방향
	static int[] dc = { 0, 0, -1, 1 };
	static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];

		for (int r = 0; r < H; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < W; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		que.add(new int[] { 0, 0, 0, 0 }); // r좌표, c좌표, 말처럼 이동한 횟수, 몇번째 동작인지
		visited[0][0][0] = true;
		result = -1;
		BFS();
		System.out.println(result);

	}

	static void BFS() {

		while (!que.isEmpty()) {

			int[] now = que.poll();
			int r = now[0];
			int c = now[1];
			int k = now[2];
			int cnt = now[3];
			
			if(r==H-1 && c==W-1) {
				result = cnt;
				break;
			}

			// 말처럼 이동한 횟수가 K보다 작으면
			// 1. 말처럼도 이동해보기
			if (k < K) {

				for (int d = 0; d < 8; d++) {

					int nr = r + hr[d];
					int nc = c + hc[d];

					// 경계 넘어가면 그냥 넘기기
					if (nr < 0 || nr >= H || nc < 0 || nc >= W)
						continue;

					// 경계 안에 있다면
					// 장애물이 없고, k+1번째 말 이동으로 가본 적 없는 곳이라면
					if (map[nr][nc] != 1 && !visited[nr][nc][k + 1]) {
						que.add(new int[] {nr, nc, k+1, cnt+1});
						visited[nr][nc][k+1] = true;
					}

				}

			}
			
			// 2. 그냥으로도 이동하기
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 경계 넘어가면 그냥 넘기기
				if (nr < 0 || nr >= H || nc < 0 || nc >= W)
					continue;
				
				// 경계 안에 있다면
				// 장애물이 없고, 가본 적 없는 곳이라면
				if(map[nr][nc]!=1 && !visited[nr][nc][k]) {
					que.add(new int[] {nr, nc, k, cnt+1});
					visited[nr][nc][k] = true;
				}
			}
		}

	}

}
