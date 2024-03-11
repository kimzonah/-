import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, year, group;
	static int[][] map, melt;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
//	static ArrayList<int[]> ice = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		melt = new int[N][M];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				int hight = Integer.parseInt(st.nextToken());
				map[r][c] = hight;
//				if(hight != 0) ice.add(new int[] {r,c,0});
			}
		}
		
		year = 1;
		while(true) {
			ArrayList<int[]> ice = new ArrayList<>();
			group = 0;
			
			// 각 빙하의 사방에 있는 0 구역 수 구하기 (1년동안 녹는 높이)
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[r][c]!=0) { // 빙하가 있다면
						ice.add(new int[] {r, c});
						meltH(r, c);
					}
				}
			}
			
			// 빙하가 더이상 없다면 break
			if(ice.size()==0) break;
			
			// 구해준 높이만큼 녹이기
			for(int[] point : ice) {
				int r = point[0];
				int c = point[1];
				int now = map[r][c];
				int m = melt[r][c];
				if(now > m ) map[r][c] = now - m;
				else map[r][c] = 0;
			}
			
//			for(int r=0; r<N; r++) {
//				for(int c=0; c<M; c++) {
//					System.out.print(map[r][c]+" ");
//				}
//				System.out.println();
//			}
			
			// 몇덩어리인지 구하기
			visited = new boolean[N][M];
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[r][c] != 0 && visited[r][c]==false) {
						visited[r][c] = true;
						DFS(r, c);
						group++;
					}
				}
			}
			
			if(group >= 2) break;
			else year++;
			
		}
		
		if(group==0)System.out.println("0");
		else System.out.println(year);
		
		
	}
	
	static void DFS(int r, int c) {
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]!=0 && visited[nr][nc]==false) {
				visited[nr][nc] = true;
				DFS(nr,nc);
			}
		}
	}
	
	static void meltH(int r, int c) {
		int zero = 0;
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(0<=nr && nr<N && 0<=nc && nc<M && map[nr][nc]==0) zero++;
		}
		melt[r][c] = zero;
	}
	
}
