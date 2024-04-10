import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	
	static int N, result, sr, sc;
	static int[][] map;
	static boolean[] visited;
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = -1;
			
			for(int r=0; r<N-2; r++) {
				for(int c=1; c<N-1; c++) {
					visited = new boolean[101];
					visited[map[r][c]] = true; // 시작점 방문 처리
					sr = r;
					sc = c;
					tour(r, c, 0, 1);
				}
			}
			
			sb.append("#"+t+" "+result+"\n");
			
		}
		
		System.out.println(sb);
		
	}
	
	
	static void tour(int r, int c, int d, int cnt) {
		
		// 현재방향으로 보내주거나 다음방향으로 보내줄거임
		for(int i=0; i<2; i++) {
			int nd = d + i;
			
			// 다음 방향이 4면 이 방향으로는 안감
			if(nd==4) continue;
			
			int nr = r + dr[nd];
			int nc = c + dc[nd];
			
			// 다음으로 갈 칸이 시작점이면 result 갱신하고 for문 더 안 봄
			if(nr==sr && nc==sc) {
				result = Math.max(result, cnt);
				continue;
			}
			
			// 다음으로 갈 칸이 경계를 넘거나 방문한 숫자면 중단
			if(nr<0 || nr>=N || nc<0 || nc>=N || visited[map[nr][nc]])
				continue;
			
			// 중단할 이유가 없다면 이동
			visited[map[nr][nc]] = true;
			tour(nr, nc, nd, cnt+1);
			visited[map[nr][nc]] = false;
			
		}
		
	}
	
}