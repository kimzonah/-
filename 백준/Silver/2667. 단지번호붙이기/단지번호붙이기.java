import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	static int N;
	static int[][] mapArr, visited;
	static int count;
	static ArrayList<Integer> cluster = new ArrayList<>();
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		mapArr = new int[N+1][N+1];
		visited = new int[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			String nums = br.readLine();
			for(int j=1; j<N+1; j++) {
				mapArr[i][j] = nums.charAt(j-1) - '0';
			}
		}
		
		// 지도 한 칸씩 순회하면서
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				// 만약에 지도 상에 1이면서 아직 방문한 적 없다면 새 단지 시작
				if(mapArr[i][j]==1 && visited[i][j]==0) {
					count = 1; // 해당 집과 같은 단지에 있는 집 찾기 위해 카운트 시작
					DFS(i, j); // 단지에 속한 집 착기 시작
					cluster.add(count); // 단지에 속한 집 다 찾았으면 카운드 리스트에 추가
				}
			}
		}
		
		// 오름차순으로 정렬
		Collections.sort(cluster);
		
		System.out.println(cluster.size()); // 총 단지 수 출력
		for(int i=0; i<cluster.size(); i++) {
			System.out.println(cluster.get(i));
		}
		
	}
	
	public static void DFS(int r, int c) {
		visited[r][c] = 1; // 현재 집 방문 처리 해주기
		for(int d=0; d<4; d++) { // 사방탐색하면서...
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 만약 사방탐색 중 경계를 넘지 않고, 1이면서 방문한적 없다면?
			if(1<=nr && nr<=N && 1<=nc && nc<=N && mapArr[nr][nc]==1 && visited[nr][nc]==0) {
				count++; // 같은 단지이므로 카운드 1 더함
				DFS(nr, nc); // 그 집을 기준으로 다시 탐색 시작
			}
		}
	}

}
