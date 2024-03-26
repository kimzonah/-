import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[][] map;
	static int N;
	static int count, max;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {-0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
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
			
			int day = 0;
			max = 0;
			while(day<=100) {
				// day일째
				
				// day랑 같은 맛있는 정도면 먹기
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						if(map[r][c] == day) map[r][c] = 0;
					}
				}
				
				// 몇 덩어리인지 세기
				count = 0;
				visited = new boolean[N][N];
				for(int r=0; r<N; r++) {
					for(int c=0; c<N; c++) {
						if(map[r][c]!=0 && !visited[r][c]) {
							DFS(r, c);
							count++;
						}
					}
				}
				
				if(max < count) max = count;
				
				day++;
				
			}
			
			System.out.println("#"+t+" "+max);
			
		}
		
	}
	
	static void DFS(int r, int c) {
		
		for(int d=0; d<4; d++) {
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 먹은 부분이 아니고 방문한 적 없다면
			if(nr>=0 && nr<N && nc>=0 && nc<N && map[nr][nc]!=0 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				DFS(nr,nc);
			}
		}
		
	}

}
