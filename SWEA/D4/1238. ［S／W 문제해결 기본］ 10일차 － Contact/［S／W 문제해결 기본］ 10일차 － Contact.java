import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution {

	static Map<Integer, List<Integer>> infoMap;
	static Map<Integer, Boolean> visited;
	static Map<Integer, List<Integer>> received;
	static int lastTime, lastNum;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 10; t++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			infoMap = new HashMap<>();
			visited = new HashMap<>();

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int key = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());

				// 현재 key값이 이미 있다면 리스트에 바로 추가
				if (infoMap.keySet().contains(key)) {
					infoMap.get(key).add(val);
				}

				// 현재 key값이 없다면 해당 key값을 갖는 리스트 생성해주고 추가
				else {
					infoMap.put(key, new ArrayList<>());
					infoMap.get(key).add(val);
				}

				// 언급된 모든 번호에 대한 방문 배열 만들어줌
				visited.put(key, false);
				visited.put(val, false);
			}

//			for (int key : infoMap.keySet()) {
//				System.out.println(key + " " + infoMap.get(key));
//			}

//			System.out.println(visited.values());

			received = new TreeMap<>();
			call(start);
			
			lastTime = 0;
			for(int time : received.keySet()) {
				if(lastTime < time) lastTime = time;
			}
			
			lastNum = 0;
			for(int num : received.get(lastTime)) {
				if(lastNum < num) lastNum = num;
			}
			
			

			sb.append("#" + t + " " + lastNum+"\n");

		}
		System.out.println(sb);

	}

	static void call(int start) {

		// 큐에 내가 몇번째에 연락을 받았는지 정보도 함께 넣어줌 [key, 순서]
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { start, 0 });
		visited.put(start, true);

		while (!que.isEmpty()) {

			int[] curr = que.poll();
			int currKey = curr[0]; // 현재 번호
			int turn = curr[1]; // 현재 몇번째인지
//			System.out.println(currKey);
			// currKey가 키값인 리스트 하나씩 조회
			if (infoMap.keySet().contains(currKey)) {

				for (int nextKey : infoMap.get(currKey)) {
//					System.out.println(nextKey + " " + visited.get(nextKey));
					if (!visited.get(nextKey)) {
						visited.put(nextKey, true);
						
						// 받은 시간 시간별로 맵핑하여 기록
						if(received.keySet().contains(turn+1)) {
							received.get(turn+1).add(nextKey);
						}
						else {
							received.put(turn+1, new ArrayList<>());
							received.get(turn+1).add(nextKey);
						}
						
						que.add(new int[] { nextKey, turn + 1 });

					}
				}
			}

		}

	}

}
