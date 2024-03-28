import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

	static int N, pSize, min;
	static int[][] map;
	static ArrayList<int[]> pPoint, sPoint;
	static int[] selStair;
	static int stair0R, stair0C, stair0T, stair1R, stair1C, stair1T, time0, time1;
	static ArrayList<Integer> stair0;
	static ArrayList<Integer> stair1;
	static boolean[] visited0;
	static boolean[] visited1;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			map = new int[N + 1][N + 1];

			pPoint = new ArrayList<>();
			sPoint = new ArrayList<>();

			for (int r = 1; r <= N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= N; c++) {
					int tmp = Integer.parseInt(st.nextToken());
					map[r][c] = tmp;
					if (tmp == 1) {
						pPoint.add(new int[] { r, c });
					} else if (tmp >= 2) {
						sPoint.add(new int[] { r, c, tmp });
					}
				}
			}
			stair0R = sPoint.get(0)[0];
			stair0C = sPoint.get(0)[1];
			stair0T = sPoint.get(0)[2];
			stair1R = sPoint.get(1)[0];
			stair1C = sPoint.get(1)[1];
			stair1T = sPoint.get(1)[2];

			pSize = pPoint.size();
			selStair = new int[pSize];

			min = Integer.MAX_VALUE;
			select(0);

			sb.append("#" + t + " " + min + "\n");

		}
		System.out.println(sb);

	}

	static void select(int idx) {

		if (idx == pSize) {
			// 조합 다 끝났으면 계단 내려가기 시작
			downSet(selStair);
			return;
		}

		for (int i = 0; i < 2; i++) {
			selStair[idx] = i;
			select(idx + 1);
		}
	}

	static void downSet(int[] selStair) {

		stair0 = new ArrayList<>();
		stair1 = new ArrayList<>();

		// 각 사람이 선택한 계단 내려가기를 시작할 수 있는 시간 넣어줌 (입구 도착시간 + 1분)
		for (int i = 0; i < pSize; i++) {
			if (selStair[i] == 0)
				stair0.add(Math.abs(pPoint.get(i)[0] - stair0R) + Math.abs(pPoint.get(i)[1] - stair0C) + 1);
			else
				stair1.add(Math.abs(pPoint.get(i)[0] - stair1R) + Math.abs(pPoint.get(i)[1] - stair1C) + 1);
		}

		// 각 계단 사람들의 도착시간 오름차순으로 정렬
		if (!stair0.isEmpty())
			Collections.sort(stair0);
		if (!stair1.isEmpty())
			Collections.sort(stair1);

//		System.out.println(stair0);
//		System.out.println(stair1);

		if (!stair0.isEmpty()) {
			visited0 = new boolean[stair0.size()];
			downStair00(stair0.get(0), 0, 0);
		}
		if (!stair1.isEmpty()) {
			visited1 = new boolean[stair1.size()];
			downStair11(stair1.get(0), 0, 0);
		}

		if (stair0.isEmpty())
			min = Math.min(min, time1);
		else if (stair1.isEmpty())
			min = Math.min(min, time0);
		else
			min = Math.min(min, Math.max(time0, time1));

//		System.out.println(min);

	}

	static void downStair00(int time, int idx, int cnt) {

//		System.out.println("time : " + time + " idx : " + idx + " cnt : " + cnt);
		if (idx >= stair0.size()) {
//			System.out.println("time0 : " + time);
			time0 = stair0.get(stair0.size() - 1);
			return;
		}

		// 현재 내 위치에서 cnt만큼 앞에 있는 사람들만 확인해서 계단 내려가기 끝날 시간이면 cnt 1 감소
		for (int i = idx - cnt; i < idx; i++) {
			if (i >= 0 && i < stair0.size() && visited0[i] && stair0.get(i) <= time) {
				cnt--;
			} else
				break;
		}

		int tmpc = 0;
		// 현재 내 위치부터 계단 남은 만큼만 확인해서
		for (int i = idx; i < idx + 3 - cnt; i++) {

			// 지금 계단 내려갈 수 있으면 계단 태우고, 도착할 시간으로 값 변경해줌
			if (i >= 0 && i < stair0.size() && !visited0[i] && stair0.get(i) <= time) {
				visited0[i] = true;
				tmpc++;
				stair0.set(i, time + stair0T);
			}

			// 지금 계단 내려갈 수 없다면 그 뒤를 더 볼 필요 없으므로 중단
			else
				break;

		}

//		System.out.println(stair0);
		// 1분후, 내가 확인해준 인덱스 다음으로 넘겨줌
		downStair00(time + 1, idx + tmpc, cnt + tmpc);

	}

	static void downStair11(int time, int idx, int cnt) {

//		System.out.println("time : " + time + " idx : " + idx + " cnt : " + cnt);
		if (idx >= stair1.size()) {
//			System.out.println("time1 : " + time);
			time1 = stair1.get(stair1.size() - 1);
			return;
		}

		// 현재 내 위치에서 cnt만큼 앞에 있는 사람들만 확인해서 계단 내려가기 끝날 시간이면 cnt 1 감소
		for (int i = idx - cnt; i < idx; i++) {
			if (i >= 0 && i < stair1.size() && visited1[i] && stair1.get(i) <= time) {
				cnt--;
			} else
				break;
		}

		int tmpc = 0;
		// 현재 내 위치부터 계단 남은 만큼만 확인해서
		for (int i = idx; i < idx + 3 - cnt; i++) {

			// 지금 계단 내려갈 수 있으면 계단 태우고, 도착할 시간으로 값 변경해줌
			if (i >= 0 && i < stair1.size() && !visited1[i] && stair1.get(i) <= time) {
				visited1[i] = true;
				tmpc++;
				stair1.set(i, time + stair1T);
			}

			// 지금 계단 내려갈 수 없다면 그 뒤를 더 볼 필요 없으므로 중단
			else
				break;

		}

//		System.out.println(stair1);
		// 1분후, 내가 확인해준 인덱스 다음으로 넘겨줌
		downStair11(time + 1, idx + tmpc, cnt + tmpc);

	}

}