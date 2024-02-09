import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int switchSize; // 스위치 개수
	static int[] switchArr; // 스위치 상태 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 스위치 개수 입력
		switchSize = Integer.parseInt(br.readLine());

		// 스위치 개수+1개 크기의 스위치 배열 생성. 인덱스 번호를 스위치 번호랑 같게 하기 위해
		switchArr = new int[switchSize + 1];

		// 스위치 상태 입력 후 배열에 인덱스 1부터 넣어주기
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= switchSize; i++) {
			switchArr[i] = Integer.parseInt(st.nextToken());
		}

		// 학생 수 입력
		int stuNum = Integer.parseInt(br.readLine());

		// 학생 수 만큼 성별, 받은 수 입력 받으면서 조건에 맞게 스위치 켜고 끄기 반복
		for (int i = 0; i < stuNum; i++) {
			st = new StringTokenizer(br.readLine());

			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (gender == 1)
				Male(num);

			else
				Female(num);

		}

		for (int i = 1; i <= switchSize; i++) {
			System.out.print(switchArr[i] + " ");
			if (i % 20 == 0)
				System.out.println();
		}

	}

	// 남자일 때 수행할 메서드
	public static void Male(int num) {
		int n = num; // n 초기값을 num으로
		// n이 스위치 마지막 번호보다 작거나 같아질 때까지
		while (n <= switchSize) {
			Toggle(n); // n 번째 스위치 토글
			n += num; // n에 받은 수만큼 더해서 갱신(배수)
		}
	}

	// 여자일 때 수행할 메서드
	public static void Female(int num) {
		// 일단 받은수번째 스위치는 무조건 토글
		Toggle(num);
		int d = 1;
		// 받은 수번째 스위치에서 양옆 한칸씩 비교하면서 토글
		// 양옆으로 이동한 인덱스가 1~스위치개수 사이어야 함
		while (num - d >= 1 && num + d <= switchSize) {
			// 만약에 양옆 한칸이 같다면 각각 토글해주고 한칸씩 더 이동해서 다시 비교
			if (switchArr[num - d] == switchArr[num + d]) {
				Toggle(num - d);
				Toggle(num + d);
				d += 1;
			} else
				break;
		}
	}

	public static void Toggle(int n) {
		if (switchArr[n] == 0)
			switchArr[n] = 1;
		else
			switchArr[n] = 0;
	}

}
