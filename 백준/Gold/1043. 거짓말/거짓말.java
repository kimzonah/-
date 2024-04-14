import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> knowList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		for(int i=0; i<k; i++) {
			knowList.add(Integer.parseInt(st.nextToken()));
		}
		
		ArrayList[] party = new ArrayList[M];
		for(int i=0; i<M; i++) {
			party[i] = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int j=0; j<n; j++) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 각자의 대표를 자기 자신으로 셋팅
		p = new int[N+1];
		for(int i=1; i<=N; i++) {
			p[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			ArrayList<Integer> list = party[i];
			// 각 파티 사람들 끼리 같은 집합에 속하게 함 (일단 대표는 무조건 첫번쨰 원소로)
			for(int j=0; j<list.size(); j++) {
				union(list.get(0), list.get(j));
			}
		}
		
//		System.out.println(findset(party[0].get(0)));
		
		int result = 0;
		
		for(int i=0; i<M; i++) {
			ArrayList<Integer> tmp = party[i];
			boolean same = true;
//			int leader = party[i].get(0);
			// 비밀을 알고 있는 사람들과 대표가 만난적 있는지 체크
			for(int j=0; j<knowList.size(); j++) {
				if(findset(knowList.get(j)) == findset(tmp.get(0))) {
					same = false;
					break;
				}
			}
			
			if(same) {
				result++;
			}
			
			
		}
		
		System.out.println(result);
		
	}
	
	static int findset(int n) {
		if(p[n] != n) {
			p[n] = findset(p[n]);
		}
		return p[n];
	}
	
	static void union(int a, int b) {
		p[findset(b)] = findset(a);
	}

}
