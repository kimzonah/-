import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	
	static int N,M;
	static int[] count; // 칭찬 지수
	static ArrayList<Integer>[] senior; // 직속 상사 번호
	
	static int seniorNum, juniorNum;
	static int i, w;
	static int result;
	
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		senior = new ArrayList[N+1];
		for(int i=1; i<N+1; i++) {
			senior[i] = new ArrayList<>();
		}
		count = new int[N+1];
		
		// i 직원의 직속 상사 번호 i 인덱스 리스트 배열에 추가
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<N+1; i++) {
			seniorNum = Integer.parseInt(st.nextToken());
			senior[i].add(seniorNum);
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			count[i] += w; // i 직원이 받은 칭찬 지수 i 인덱스에 받아주기
		}
		
		System.out.print(count[1]+" ");
		for(int i=2; i<N+1; i++) {
			// i 직원의 직속상사 칭찬지수들을 더해준다.
			for(int sNum : senior[i]) {
				count[i] += count[sNum];
			}
			System.out.print(count[i]+" ");
		}
		
		
		
		
	}
	


}
