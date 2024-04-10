import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, Anum, Bnum, result;
	static boolean[][] adjArr, copyAdj;
	static boolean[] visited;
	static boolean able;
	static int[] peopleNum, sel;
	static ArrayList<Integer> AList, BList;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		peopleNum = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			peopleNum[i] = Integer.parseInt(st.nextToken());
		}

		adjArr = new boolean[N + 1][N + 1];
		copyAdj = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int n = Integer.parseInt(st.nextToken());
				adjArr[i][n] = true;
			}
		}

		// 0은 A, 1은 B
		result = Integer.MAX_VALUE;
		sel = new int[N + 1];
		select(1); // 구역 나누기
		
		if(result == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(result);

	}

	static void select(int num) {

		// 모든 구역이 선거구가 정해지면
		if (num == N + 1) {
			AList = new ArrayList<>();
			Anum = 0;
			BList = new ArrayList<>();
			Bnum = 0;
			// 0은 A, 1은 B
			for (int i = 1; i <= N; i++) {
				if (sel[i] == 0) {
					Anum += peopleNum[i];
					AList.add(i);
				} else {
					Bnum += peopleNum[i];
					BList.add(i);
				}
			}
			// 인구수가 0인 경우가 있으면 리턴
			if (AList.size() == 0 || BList.size() == 0)
				return;

			// 선거구 소속에 맞게 연결 정보 재연결
			changeAdj();
			
			// 두 선거구 모두 가능한 경우면 result 갱신
			if (checkA() && checkB()) {
				result = Math.min(result, Math.abs(Anum - Bnum));
			}
			return;

		}

		// A구역에도 넣어보고
		sel[num] = 0;
		select(num + 1);

		// B구역에도 넣어보기
		sel[num] = 1;
		select(num + 1);

	}

	static void check(int n) {

		for (int i = 1; i <= N; i++) {
			// n과 연결되어 있으면서 방문한 적 없으면
			if (copyAdj[n][i] && !visited[i]) {
				visited[i] = true;
				check(i);
			}
		}

	}

	// A 선거구 확인
	static boolean checkA() {

		visited = new boolean[N + 1];
		// A선거구 아무 구역부터 시작해서 연결된 구역 방문처리 모두 해주기
		visited[AList.get(0)] = true;
		check(AList.get(0));
		for (int i : AList) {
			// A리스트 중 방문 못한 곳이 있다면 false
			if (!visited[i]) {
				return false;
			}
		}

		return true;
	}

	// B 선거구 확인
	static boolean checkB() {

		visited = new boolean[N + 1];
		// B선거구 아무 구역부터 시작해서 연결된 구역 방문처리 모두 해주기
		visited[BList.get(0)] = true;
		check(BList.get(0));
		for (int i : BList) {
			// A리스트 중 방문 못한 곳이 있다면 false
			if (!visited[i]) {
				return false;
			}
		}

		return true;
	}

	static void changeAdj() {

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				// 원래 연결된 구역이었는데 현재는 다른 선거구라면 연결 끊어주기
				if (adjArr[r][c] && sel[r] != sel[c]) {
					copyAdj[r][c] = false;
				}

				// 나머지 경우는 그대로 카피
				else {
					copyAdj[r][c] = adjArr[r][c];
				}
			}
		}

	}

}
