import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, cnt;
	static boolean able;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1 }; // 우상, 우, 우하
	static int[] dc = { 1, 1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}

		cnt = 0;
		visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			visited[r][0] = true;
			able = false;
			putPipe(r, 0);
		}

		System.out.println(cnt);

	}

	static void putPipe(int r, int c) {
		// 세 방향으로 하나씩 보내보기
		for (int d = 0; d < 3; d++) {

			int nr = r + dr[d];
			int nc = c + dc[d];

			// 빵집까지 연결 성공했다면 true로 바꿔주고 카운트 +1 이후의 경우는 보지 않음
			if (nc == C - 1) {
				able = true;
				cnt++;
				return;
			}

			// 경계 넘거나 벽이거나 이미 가본 길이라면 다음 방향으로
			if (nr < 0 || nr >= R || map[nr][nc] == 'x' || visited[nr][nc]) {
				continue;
			}

			// 갈 수 있는 곳이면 방문 처리 해주고 이동
			visited[nr][nc] = true;
			putPipe(nr, nc);
			
			// 리턴해서 돌아왔을 때 true면 이후의 경우는 보지 않음
			if(able) return;

		}

	}

}
