import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M;
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			sb.append("#"+t+" ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 대표원소 담아주는 배열 p에 자기 자신으로 초기 세팅
			p = new int[N+1];
			for(int i=1; i<=N; i++) {
				p[i] = i;
			}
			
			for(int m=0; m<M; m++) {
				
				st = new StringTokenizer(br.readLine());
				int cal = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(cal == 0) {
					// b가 속한 집합의 대표원소를 a의 대표원소로 바꿔줌
					p[findset(b)] = findset(a);
				}
				
				else {
					// a와 b 각각 속한 집합의 대표원소가 같다면 1
					if(findset(a) == findset(b)) sb.append("1");
					else sb.append("0");
				}
				
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}

	static int findset(int a) {
		// a가 속한 집합이 자기자신이 대표가 아니라면
		if(p[a] != a) {
			p[a] = findset(p[a]);
			return p[a];
		}
		else {
			return a;
		}
	}
}
