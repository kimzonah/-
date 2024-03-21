import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main{
	
	static int N, M, cityD, homeD, result;
	static int[][] map, sel;
	static ArrayList<int[]> home = new ArrayList<>();
	static ArrayList<int[]> chicken = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		
		for(int r=1; r<N+1; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<N+1; c++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[r][c] = tmp;
				if(tmp == 1) home.add(new int[] {r, c});
				else if(tmp == 2) chicken.add(new int[] {r, c});
			}
		}
		
		sel = new int[M][2]; // M개의 선택된 치킨집 담아주는 배열
		result = Integer.MAX_VALUE; // 최종 출력값 : 가장 작은 값을 구해야함
		combination(0,0); // 후보군 0번째 인덱스부터 살피면서 M개 고르고, sel에 0번째 인덱스를 채워줄거임
		System.out.println(result);
	}
	
	static void combination(int idx, int sidx) {
		
		// M개 다 채웠으면 이제 치킨거리 구하기 !
		if(sidx==M) {
			cityD = 0;
			for(int i=0; i<home.size(); i++) {
				int homeR = home.get(i)[0];
				int homeC = home.get(i)[1];
				
				homeD = Integer.MAX_VALUE;
				
				for(int j=0; j<M; j++) {
					int chickenR = sel[j][0];
					int chickenC = sel[j][1];
					
					int tmpD = Math.abs(homeR-chickenR) + Math.abs(homeC-chickenC);
					if(tmpD < homeD) homeD = tmpD;
				}
				
				cityD += homeD;
				
			}
			
			if(cityD < result) result = cityD;
			
			return;
		}
		
		// 일단 여기부터 수행됨
		for(int i=idx; i<=chicken.size()-M+sidx; i++) {
			// 후보군에서 idx부터 sel의 sidx에 넣어주기 
			sel[sidx] = chicken.get(i);
			// 다음 조합을 하러가자!
			// 후보군에서 넣어준 놈 다음것부터만 넣을지 말지 고민할거고, sidx 다음 인덱스 채워주러 갈거야
			combination(i+1, sidx+1);
		}
		
	}
	
}