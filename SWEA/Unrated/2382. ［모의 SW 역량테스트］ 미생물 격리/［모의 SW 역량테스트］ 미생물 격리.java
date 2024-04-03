import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, K;
	static int[][] map;
	static Group[] micro;
	static boolean[] visited;
	static ArrayList<Integer> deadMicro;
	static ArrayList<Integer> sameList;
	static int[] dr = { 0, -1, 1, 0, 0 }; // 1상 2하 3좌 4우
	static int[] dc = { 0, 0, 0, -1, 1 };
	static int sum;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 셀의 개수
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 군집의 개수

			map = new int[N][N];
			micro = new Group[K + 1];

			for (int i = 1; i <= K; i++) { // K개의 군집 정보 입력받기
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int direc = Integer.parseInt(st.nextToken());
				micro[i] = new Group(r, c, num, direc);
				map[r][c] = i; // r,c 좌표에 i번째 군집 놓기
			}

			deadMicro = new ArrayList<>(); // 죽은 군집 넣어주기

			int nowTime = 1;
			while (nowTime <= M) {

				// 군집 배열에서 하나씩 꺼내서 이동시켜주기
				for (int i = 1; i <= K; i++) {
					Group g = micro[i];
					// 죽은 군집이 아닐때만
					if (g.alive) {
						g.r = g.r + dr[g.direc];
						g.c = g.c + dc[g.direc];
					}
				}

				visited = new boolean[K + 1];

				// 위치에 따라 크기, 방향 변경
				for (int i = 1; i <= K; i++) {
					Group g = micro[i];
					
					// 이미 확인했거나 죽은 군집이라면 넘어가기
					if (visited[i] || !g.alive)
						continue;
					
					visited[i] = true;

					// 이동해온 위치가 약품 셀이라면
					if (g.r == 0 || g.r == N - 1 || g.c == 0 || g.c == N - 1) {
						if (g.num == 1) { // 크기가 1이라면 바로 사라짐
							g.alive = false;
						} else {
							g.num = g.num / 2;
							g.direc = reverse(g.direc);
						}
					}
					
					// 약품 셀이 아니라면 동시에 온 군집이 있는 지 확인
					else {
						sameList = new ArrayList<>();
						sum = g.num;
						
						checkSame(i, g.r, g.c);
						
						// 동시에 온 군집이 있으면
						if(sameList.size()>0) {
							int maxIdx = i; // 시작은 현재 군집부터
							
							for(int idx : sameList) {
								Group other = micro[idx];
								// 현재 최대값보다 other가 더 크면 갱신하고 현재 최대군집 죽이기
								if(other.num > micro[maxIdx].num) {
									micro[maxIdx].alive = false;
									maxIdx = idx;
								}
								// 아니면 other만 죽이기
								else {
									other.alive = false;
								}
							}
							
							micro[maxIdx].num = sum;
							
						}
						
						
					}
				}
				
				nowTime++;

			}
			
			int total = 0;
            for(int i=1; i<=K; i++) {
                if(micro[i].alive) {
                    total += micro[i].num;
                }
            }
            
            sb.append("#"+t+" "+total+"\n");

		}
		
		System.out.println(sb);

	}
	
	static void pickBig() {
		// 큰 것만 살려놓고 작은 건 다 죽이기
	}

	static void checkSame(int idx, int r, int c) {
		for(int i=idx+1; i<=K; i++) {
			Group g = micro[i];
			if(visited[i] || !g.alive) continue;
			if(g.r==r && g.c==c) {
				visited[i] = true;
				sum += g.num;
				sameList.add(i);
			}
		}
	}

	static int reverse(int d) {
		if (d == 1)
			return 2;
		else if (d == 2)
			return 1;
		else if (d == 3)
			return 4;
		else
			return 3;
	}

}

class Group {

	int r;
	int c;
	int num;
	int direc;
	int time;
	boolean alive;

	Group(int r, int c, int num, int direc) {
		this.r = r;
		this.c = c;
		this.num = num;
		this.direc = direc;
		alive = true;
		time = 0;
	}

}