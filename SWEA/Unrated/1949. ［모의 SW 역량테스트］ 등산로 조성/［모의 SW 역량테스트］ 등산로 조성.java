import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N, K, max;
	static int[][] map, copy;
	static boolean[][] visited;
	static ArrayList<int[]> topList;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static boolean canGo;
	static int top, low;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			// 최대값 찾아서 봉우리 담아 줄 리스트..
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			top = 1;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					top = Math.max(top, map[r][c]);
				}
			}

			copy = new int[N][N];
			copy();

			// 가장 높은 봉우리를 가진 위치 리스트에 담아주기
			topList = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == top)
						topList.add(new int[] { r, c });
				}
			}

			max = 0;
			visited = new boolean[N][N];
			for (int[] tmp : topList) {
				// 가장 높은 봉우리들 중에서 하나 골라서 가능한 등산로 탐색하기
				canGo = true;
				visited[tmp[0]][tmp[1]] = true;

				dfs(tmp[0], tmp[1], 1, false);
				
				for(boolean[] v : visited) {
					Arrays.fill(v, false);
				}
			}
			
			sb.append("#"+t+" "+max+"\n");

		}
		
		System.out.println(sb);

	}

	// r 좌표, c 좌표, 등산로 길이, 공사 여부
	static void dfs(int r, int c, int cnt, boolean use) {

		int currH = copy[r][c];

		// 일단 사방탐색 시작
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 경계조건 안맞으면 넘기기
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			// 방문한 적이 있는 곳이면 높이 상관 없이 어차피 더 못감
			if (visited[nr][nc]) {
				max = Math.max(max, cnt);
			}

			// 방문한 적 없는 곳이라면 높이에 따라 다르게 움직임
			else {

				// 다음이 지금 높이보다 더 낮으면 무조건 보내기
				if (copy[nr][nc] < currH) {
					visited[nr][nc] = true;
					dfs(nr, nc, cnt + 1, use);
					visited[nr][nc] = false;
				}

				// 다음이 지금 높이랑 같거나 더 높으면
				if (copy[nr][nc] >= currH) {
					
					// 공사 이미 한 적 있으면 더 갈 수 없음
					if (use)
						max = Math.max(max, cnt);
					
					// 아직 공사 한 적 없으면 공사 시도..?!
					else {
						
						// 근데 다음 - 지금이 K보다 작을 때만 공사 가능
						if(copy[nr][nc] - currH < K) {
							for(int k = copy[nr][nc]-currH+1; k<=K; k++) {
								int tmp = copy[nr][nc];
								copy[nr][nc] -= k;
								visited[nr][nc] = true;
								
								dfs(nr, nc, cnt+1, true);
								
								copy[nr][nc] = tmp;
								visited[nr][nc] = false;
							}
						}
						
						// 근데 지금이 이미 1 이고, 다음이 공사로 0이 될 수 있다면
						else if(currH == 1 && copy[nr][nc] <= K) {
							max = Math.max(max, cnt+1);
						}
					}
						
				}
			}

		}

	}

	static void copy() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				copy[r][c] = map[r][c];
			}
		}
	}

}
