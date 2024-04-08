
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static char map[][];
	static boolean visited[][];
	static int normal, abnormal;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
 	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for(int r=0; r<N; r++) {
			String str = br.readLine();
			map[r] = str.toCharArray();
		}
		
		// 정상인이 보는 그림 체크
		normal = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(!visited[r][c]) {
					noDFS(r, c);
					normal++;
				}
			}
		}
		
		// 적록색약이 보는 그림 체크
		visited = new boolean[N][N];
		abnormal = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(!visited[r][c]) {
					abDFS(r, c);
					abnormal++;
				}
			}
		}
		
		System.out.println(normal + " "+ abnormal);
		
	}
	
	static void noDFS(int r, int c) {
		
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
			
			// 현재랑 같은 색일때만 같게 봄
			if(map[nr][nc]==map[r][c] && !visited[nr][nc]) {
				visited[nr][nc] = true;
				noDFS(nr,nc);
			}
		}
	}
	
	static void abDFS(int r, int c) {
		
		// 현재 파란색이면 파란색일때만 같게 봄
		if(map[r][c]=='B') {
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				
				if(map[nr][nc]=='B' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					abDFS(nr,nc);
				}
			}
		}
		// 현재 파란색이 아닌 빨강, 초록이면 빨강, 초록이기만 해도 같게 봄
		else {
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
				
				if((map[nr][nc]=='R' || map[nr][nc]=='G') && !visited[nr][nc]) {
					visited[nr][nc] = true;
					abDFS(nr,nc);
				}
			}
		}
	}

}
