import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] timeArr = new int[N];
			int max = 0;
			
			st = new StringTokenizer(br.readLine());
			
			// 손님이 오는 시간들을 배열에 담아줌
			// 입력 받은 값이 max보다 크면 max를 갱신해줌
			for(int i=0; i<N ; i++) {
				timeArr[i] = Integer.parseInt(st.nextToken());
				if(timeArr[i] > max) {
					max = timeArr[i];
				}
			}
			
			// 붕어빵 수
			int breadCnt = 0;
			String res = "Possible";
			
			// 흐르는 시간을 세는 for문
			Time : 
			for(int current=0; current<=max; current++) {
				// 현재 시간이 M의 배수면 붕어빵을 K개 구움
				if(current!=0 && current%M == 0) {
					breadCnt += K;
				}
				// 반복문 돌면서 손님오는 시간들 하나씩 현재 시간이랑 비교
				for(int person : timeArr) {
					// 현재 시간이 손님오는 시간 중 하나면
					if(current==person) {
						// 그런데 지금 붕어빵이 없으면 impossible
						if(breadCnt==0) {
							res = "Impossible";
							break Time;
						}
						// 그게 아니면 붕어빵 수 1 감소
						else {
							breadCnt -= 1;
						}
					}
				}
			}
			
			System.out.println("#"+t+" "+res);
			
		}
	}

}
