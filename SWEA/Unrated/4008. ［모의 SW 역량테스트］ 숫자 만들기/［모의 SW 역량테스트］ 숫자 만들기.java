import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N, min, max;
	static int[] oper, nums, sel;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		oper = new int[4];

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}

			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			sel = new int[N - 1];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			select(0);
			
			sb.append("#"+ t + " " + (max-min)+"\n");
		}
		System.out.println(sb);

	}

	static void select(int sidx) {
		
		if(sidx == N-1) {
			cal();
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(oper[i]>0) {
				sel[sidx] = i;
				oper[i] = oper[i]-1;
				select(sidx+1);
				oper[i] = oper[i]+1;
				
			}
		}

	}
	
	static void cal() {
//      System.out.println(Arrays.toString(sel));
      int ans = nums[0];
      int sidx = 0;
      for(int i=1; i<N; i++) {
          int tmp = sel[sidx++];
          if(tmp == 0) ans = ans + nums[i];
          else if(tmp == 1) ans = ans - nums[i];
          else if(tmp == 2) ans = ans * nums[i];
          else ans = ans/nums[i];
      }
      
//      System.out.println(ans);
      max = Math.max(ans, max);
      min = Math.min(ans, min);
      
  }

}
