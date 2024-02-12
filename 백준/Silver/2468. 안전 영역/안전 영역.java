import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N,max, count, rain;
	static int[][] hightArr;
	static int[][] visited;
	static ArrayList<Integer> safeNums = new ArrayList<>();
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		hightArr = new int[N+1][N+1];
		visited = new int[N+1][N+1];
		max = 1;
		
		// 높이 정보를 받으면서 가장 높은 높이를 찾음 -> 빗물 범위 정하기 위해
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				int n = Integer.parseInt(st.nextToken());
				hightArr[i][j] = n;
				if(n > max) max = n;
			}
		}
		
		// 가장 높은 지역보다 1 작을때까지 비온다고 가정 (max까지 오면 어차피 안전영역은 0이니까)
		// 각 비 높이마다 안전영역을 세야하니까...
		for(rain=0 ; rain <= max-1; rain++) {
			visited = new int[N+1][N+1]; // 비 높이 바뀔 때마다 방문체크도 새로 갱신
			count = 0; // 비 높이마다 안전영역 세줄거니까 카운트도 갱신
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					// 지역이 비 높이보다 높고 방문한 적 없으면 인접 지역 더 있는지 탐색 시작
					if(hightArr[i][j] > rain && visited[i][j]==0) {
						DFS(i, j);
						count++; // 인접 지역 다 탐색 끝났으면 카운트 1 증가
					}
				}
			}
			// 지금 빗물 높이에서 모든 지역 다 탐색 끝났으니까 
			// 다 세어준 카운트를 리스트에 추가
			safeNums.add(count);
		}
		
		// 리스트 오름차순으로 정렬
		Collections.sort(safeNums);
		
		// 리스트 마지막 인덱스 값 출력
		System.out.println(safeNums.get(safeNums.size()-1));
		
	}
	
	public static void DFS(int r, int c) {
		// 현재 지역 방문 체크
		visited[r][c] = 1;
		// 사방탐색하면서 인접 지역도 안전영역인지 체크
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 안정영역이면 그 지역으로부터 다시 탐색 시작
			if(1<=nr && nr<=N && 1<=nc && nc<=N && hightArr[nr][nc] > rain && visited[nr][nc]==0) {
				DFS(nr,nc);
			}
		}
	}

}
