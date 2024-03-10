import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, minute, result;
	static int[] dx = {-1, 1, 2};
	static Queue<int[]> que = new LinkedList<>();
	static boolean[] visited = new boolean[100001];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N==K) result = 0;
		else BFS(N, 0);
		
		System.out.println(result);
		
	}
	
	static void BFS(int point, int minute) {
		int[] start = {point, minute};
		visited[point] = true;
		que.add(start);
		
		Loop: while(!que.isEmpty()) {
			int[] now = que.poll();
			int nextM = now[1]+1; // 현재 몇초인지
			
			for(int d=0; d<3; d++) {
				int nextP;
				if(d==2) nextP = now[0]*dx[d];
				else nextP = now[0] + dx[d];
							
				// 만약 동생이 있는 좌표를 만나면 흐른시간을 result로 받고 반복문 종료
				if(nextP == K) {
					result = nextM;
					break Loop;
				}
				
				if(0<=nextP && nextP<=100000 && visited[nextP]==false) {
					visited[nextP] = true;
					que.add(new int[] {nextP, nextM});
				}
			}
		}
	}

}
