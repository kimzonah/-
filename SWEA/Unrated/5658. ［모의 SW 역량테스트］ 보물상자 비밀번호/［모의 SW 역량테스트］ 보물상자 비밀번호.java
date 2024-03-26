import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	static int N, K;
	static Set<Integer> set;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			String str = br.readLine();
			// N개의 숫자 que에 뒤로 넣어주기
			Deque<Character> que = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				que.addLast(str.charAt(i));
			}

			int div = N / 4;
			set = new HashSet<>();
			int rotation = 0;
			while (rotation < div) {
				
				// 현재의 배열로 생성 가능한 수 구하기
				for (int i = 0; i < N; i += div) {
					// div개씩 묶어서
					String tmp = "";
					for (int j = 0; j < div; j++) {
						// que에 맨 처음 숫자부터 빼주고 뒤로 다시 넣어주기
						char first = que.pollFirst();
						tmp += first;
						que.addLast(first);
					}
					set.add(Integer.parseInt(tmp, 16));
				}
				
				// 회전해주기 : 맨 뒤 원소 맨 앞으로 보내주기
				que.addFirst(que.pollLast());
				rotation++;

			}
			
			list = new ArrayList<>(set);
			Collections.sort(list, Collections.reverseOrder());
			System.out.println("#" + t+ " "+ list.get(K-1));
			

		}

	}

}
