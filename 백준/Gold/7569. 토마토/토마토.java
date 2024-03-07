import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N, H, ripen, day;
	static int[][][] box;
	static int[][][] visited;
	static int[] dr = {-1, 1, 0, 0, 0, 0}; // 상하좌우앞뒤
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, 1, -1};
	static Queue<int[]> que = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		box = new int[N][M][H];
		visited = new int[N][M][H];
		
		int total = 0;
		for(int h=0; h<H; h++) {
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<M; c++) {
					int t = Integer.parseInt(st.nextToken());
					box[r][c][h] = t;
					if(t==1 || t==0) {
						total++; // 전체 토마토 수
						if(t==1) {
							// 맨 처음 익어있던 토마토들을 먼저 큐에 넣어줌
							que.add(new int[] {r, c, h});
							visited[r][c][h] = 1;
							box[r][c][h] = 0;
							ripen++; // 익은 토마토 수
						}
					}
					
				}
			}
		}
		
		day = 0;
		BFS();
		
//		for(int h=0; h<H; h++) {
//			for(int r=0; r<N; r++) {
//				for(int c=0; c<M; c++) {
//					System.out.print(box[r][c][h]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		if(ripen == total) System.out.println(day);
		else System.out.println("-1");
		
	}
	
	static void BFS() {
		while(!que.isEmpty()) {
			int point[] = que.poll();
			int r = point[0];
			int c = point[1];
			int h = point[2];
			
			for(int d=0; d<6; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nh = h + dh[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=M || nh<0 || nh>=H) continue;
				
				if(box[nr][nc][nh]==0 && visited[nr][nc][nh]==0) {
					day = box[r][c][h]+1;
					box[nr][nc][nh] = day;
					visited[nr][nc][nh] = 1;
					ripen++;
					que.add(new int[] {nr, nc, nh});
				}
			}
		}
		
	}

}
