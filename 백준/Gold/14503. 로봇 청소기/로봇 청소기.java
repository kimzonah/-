import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, count;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0}; //위 왼 아 오
	static int[] dc = {0, 1, 0, -1};
	static int num=2;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		int sd = Integer.parseInt(st.nextToken());
		
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		clean(sr, sc, sd);
		
		System.out.println(count);
		
	}
	
	static void clean(int r, int c, int d) {
		
		// 현재 칸이 청소가 안된 경우 청소하기
		if(map[r][c]==0) {
			map[r][c] = num++;
			count++;
		}
		
		boolean isEmpty = false;
		int nr=-1;
		int nc=-1;
		int nd=-1;
		// 반시계 방향으로 돌면서 현재 칸 주변 4칸에 청소되지 않은 칸 있는지 확인
		for(int i=1; i<=4; i++) {
			nd = (d + (3*i)) % 4;
			nr = r + dr[nd];
			nc = c + dc[nd];
			
			
			// 찾으면 true 처리 해주고 탐색 중단
			if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==0) {
				isEmpty = true;
				break;
			}
			else 
				continue;
		}
		
		// 주변 4칸 청소되지 않은 칸 있으면
		if(isEmpty) {
			clean(nr, nc, nd);
		}
		
		// 주변 4칸 청소되지 않은 칸 못 찾았으면 뒤쪽 칸 탐색
		else {
			nd = (d+2)%4;
			nr = r + dr[nd];
			nc = c + dc[nd];
			
			if(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc]==1) return;
			else {
				clean(nr, nc, d);
			}
		}
		
	}

}
