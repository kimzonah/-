import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Prob1860 {
    public static void main(String[] args) throws Exception{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
         
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            // N명의 손님 오는 시간 담아줄 배열
            int[] customTime = new int[N];

            // 한 줄로 시간들 입력 받기 
            st = new StringTokenizer(br.readLine());
             
            // 공백 기준으로 쪼개서 배열에 담아줌
            for(int i=0; i<N ; i++) {
                customTime[i] = Integer.parseInt(st.nextToken());
            }
            // 온 시간 순서대로 손님 정렬
            Arrays.sort(customTime);
            
            String res = "Possible";
            
            // 손님 순서대로 붕어빵 남은 수 확인
            for(int i=0; i<N; i++) {
            	
	        	// 남은 붕어빵 수 = (붕어빵 싸이클 횟수 * 싸이클 하나 당 만든 수) - 지금까지 온 손님 수
	    		if( (customTime[i]/M)*K - i < 1) {
	    			res = "Impossible";
	    			break;
	    		}
	    		
            }
            System.out.println("#"+t+" "+res);
   
        }
    }
 
}