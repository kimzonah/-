
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int count;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		for(int r=1; r<N+1; r++) {
			String str = br.readLine();
			for(int c=1; c<M+1; c++) {
				char ch = str.charAt(c-1);
				if(ch=='1') map[r][c] = 1;
				else map[r][c] = 0;
			}
		}
		
		visited[1][1] = true;
		count = 0;
		BFS(1,1);
		
		System.out.println(map[N][M]);
	}
	
	static void BFS(int sr, int sc) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {sr, sc});
		
		Loop: while(!que.isEmpty()) {
			
			int[] point = que.poll();
			int r = point[0];
			int c = point[1];
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(1<=nr && nr<=N && 1<=nc && nc<=M && map[nr][nc]==1 && visited[nr][nc]==false) {
					visited[nr][nc] = true;
					que.offer(new int[] {nr, nc});
					map[nr][nc] = map[r][c]+1;
				}
			}
			
		}
		
	}

}
