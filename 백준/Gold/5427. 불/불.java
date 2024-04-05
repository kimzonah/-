
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int W, H;
	static char[][] map;
	static boolean[][] visitedSang;
	static Queue<int[]> queFire;
	static Queue<int[]> queSang;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int time;
	static boolean exit;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			visitedSang = new boolean[H][W];
			
			queFire = new LinkedList<>();
			queSang = new LinkedList<>();
			
			for(int r=0; r<H; r++) {
				String tmp = br.readLine();
				for(int c=0; c<W; c++) {
					map[r][c] = tmp.charAt(c);
					if(map[r][c] == '@') { // 상근이 위치면 상근이 큐에 넣고 방문처리
						queSang.add(new int[] {r, c});
						visitedSang[r][c] = true;
					}
					
					if(map[r][c] == '*') { // 불 위치면 불 큐에 넣기
						queFire.add(new int[] {r, c});
					}
					
				}
			}
			
			time = 0;
			exit = false;
			
			bfs();
			
			if(exit) System.out.println(time+1);
			else System.out.println("IMPOSSIBLE");
			
		}// 테스트 케이스
		
	}// main
	
	static void bfs() {
		
		int sSize = 0;
		int fSize = 0;
		
		bfs :while(!queSang.isEmpty()) {
			
			// 일단 현재 상근이 자리 중에 출구가 있으면
			sSize = queSang.size();
			for(int i=0; i<sSize; i++) {
				int[] now = queSang.poll();
				int r = now[0];
				int c = now[1];
				
				if(r==0 || r==H-1 || c==0 || c==W-1) {
					exit = true;
					break bfs;
				}
				else {
					queSang.add(now);
				}
			}
			
			time++;
			
			// 불 먼저 이동시키기
			fSize = queFire.size(); // 현재 시간에 만들어진 불만큼만 체크
			for(int i=0; i<fSize; i++) {
				int[] nowF = queFire.poll();
				int r = nowF[0];
				int c = nowF[1];
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 경계조건 넘으면 제껴~
					if(nr<0 || nr >=H || nc<0 || nc >=W)
						continue;
					
					// 가려는 곳이 벽도 아니고 이미 불이 아닌 곳이라면
					if(map[nr][nc]!='#' && map[nr][nc]!='*') {
						map[nr][nc] = '*'; 
						queFire.add(new int[] {nr, nc});
					}
				}
			}
			
			// 상근이 이동시키기
			sSize = queSang.size();
			for(int i=0; i<sSize; i++) {
				int[] nowS = queSang.poll();
				int r = nowS[0];
				int c = nowS[1];
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					// 경계조건 넘으면 제껴~
					if(nr<0 || nr >=H || nc<0 || nc >=W)
						continue;
					
					// 가려는 곳이 벽이 아니고, 불도 아니고, 상근이가 간 적 없는 곳이라면
					if(map[nr][nc]!='#' && map[nr][nc]!='*' && !visitedSang[nr][nc]) {
						
						visitedSang[nr][nc] = true;
						queSang.add(new int[] {nr, nc});
						
						
					}
				}
			}
			
			
		}
		
	}

}
