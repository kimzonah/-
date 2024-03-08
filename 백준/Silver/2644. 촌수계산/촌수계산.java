

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, p1, p2, M, x, y;
	static int[][] person;
	static int[] visited, chon;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		person = new int[N+1][N+1];
		
		st = new StringTokenizer(br.readLine());
		p1 = Integer.parseInt(st.nextToken());
		p2 = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			person[a][b] = 1;
			person[b][a] = 1;
		}
		
		visited = new int[N+1]; // 방문처리
		chon = new int[N+1]; // 각 사람마다 p1과의 촌수들을 담아줌
		BFS(p1);
		
		int result = chon[p2];
		if(result != 0) System.out.println(result);
		else System.out.println("-1");
	}
	
	static void BFS(int p1) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(p1);
		visited[p1] = 1;
		
		while(!que.isEmpty()) {
			int now = que.poll();
			int ch = chon[now]; // now의 촌수를 받아놓음
			
			// now와 직접 연결된 모든 사람들은 now와 1촌 관계임
			for(int i=1; i<=N; i++) {
				if(person[now][i]==1 && visited[i]==0) {
					que.add(i);
					chon[i] = ch+1;
					visited[i] = 1;
				}
			}
			
		}
	}

}
