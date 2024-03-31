import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int D, W, K, result, min;
	static int[][][] film;
	static boolean isPass;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			film = new int[D][W][2];
			
			for(int r=0; r<D; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<W; c++) {
					film[r][c][0] = Integer.parseInt(st.nextToken());
					film[r][c][1] = film[r][c][0];
				}
			}
			
			result = Integer.MAX_VALUE;
			change(0, 0);
			sb.append("#"+t+" "+result+"\n");
		}
		System.out.println(sb);
		
	}
	
	static void change(int r, int cnt) {
		
		if(cnt >= result) return;
		
		if(r==D) {
			isPass = true;
			for(int c=0; c<W; c++) {
				check(1, c, 1);
				if(!isPass) break;
			} // 모든 열에서 통과해야 isPass가 true로 유지
			
			if(!isPass) return;
			else {
				result = Math.min(result, cnt);
				return;
			}
			
		}
		
		// 약품 투입 안하기
		change(r+1, cnt);
		
		// 약품A 투입하기
		for(int c=0; c<W; c++) {
			film[r][c][1] = 0;
		}
		change(r+1, cnt+1);
		
		// 약품B 투입하기
		for(int c=0; c<W; c++) {
			film[r][c][1] = 1;
		}
		change(r+1, cnt+1);
		
		// 다 해도 통과 못했으면 다시 원복
		for(int c=0; c<W; c++) {
			film[r][c][1] = film[r][c][0];
		}
		
	}
	
	static void check(int r, int c, int cntK) {
		
		// 같은 속성 연속 카운트가 K이상이면 중단
		if(cntK >= K) {
			return;
		}
		
		// 끝까지 다 봤으면 false 처리 하고 중단
		if(r==D) {
			isPass = false;
			return;
		}
		
		if(film[r][c][1] == film[r-1][c][1]) {
			check(r+1, c, cntK+1);
		}
		
		else {
			check(r+1, c, 1);
		}
		
	}
	
}