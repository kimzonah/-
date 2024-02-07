import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // 한줄로 값을 받을 때 string으로 쪼개줌
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<T+1; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int max = 0;
			
			// 카드세트 2개 만들어줌
			Queue<Integer> setOne = new LinkedList<>();
			Queue<Integer> setTwo = new LinkedList<>();
			for(int n=1; n<N+1; n++) {
				setOne.add(n);
			}
			for(int m=1; m<M+1; m++) {
				setTwo.add(m);
			}
			
			// 합 카운드 해줄 배열 생성. 배열 크기는 가능한 합의 최대
			int[] sumArr = new int[N+M+1];
			
			
			while(!setTwo.isEmpty()) { // 카드세트2 빌 때까지 반복
				
				for(int n=0; n<N; n++) { // 카드세트1 수만큼 반복
					int num = setOne.poll(); // head 값 저장해놓고
					int sum = num + setTwo.peek(); // 세트2 head랑 더해줌
					sumArr[sum]++; // 더한 값이 인덱스인 원소 1증가
					if(sumArr[sum] > max) { // 카운트가 현 max보다 더 크면 max 갱신 
						max = sumArr[sum];
					}
					setOne.add(num);	// 뺀 값 다시 채워주기
				}
				
				setTwo.poll(); // 세트1 한 사이클 끝나면 세트2 head도 버리기
			}
			
			
			System.out.print("#"+t);
			for(int i=0; i<sumArr.length; i++) {
				if(sumArr[i]==max) {
					System.out.print(" "+i);
				}
			}
			System.out.println();

			
		}
		
	}


}
