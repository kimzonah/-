import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// 직사각형 크기 값 입력 받기
		int col = sc.nextInt();
		int row = sc.nextInt();
		
		// 몇 번 자를건지 값 입력 받기
		int num = sc.nextInt();
		
		// 방향 별 자를 횟수 카운드 변수 생성하기
		int hor = 0;
		int ver = 0;
		
		// [ [방향,줄] , [방향,줄], ..] 꼴로 자를 점선 받기 위해 배열 생성
		int[][] cut = new int[num][2];
		for(int i=0; i<num; i++) {
			cut[i][0] = sc.nextInt();
			cut[i][1] = sc.nextInt();
			if(cut[i][0] == 0) {
				hor++;
			}else
				ver++;
		}
		
//		System.out.println(hor);
//		System.out.println(ver);
		
		// 방향별로 자를 줄 배열 다시 생성
		// 이때 입력받은 줄 외에 0번째 줄 row번째줄, col번째 줄도 취급한다.
		int [] horLine = new int [hor+2];
		int [] verLine = new int [ver+2];
		
		int horIndex = 0;
		int verIndex = 0;
		
		for(int i=0; i<num; i++) {
			if(cut[i][0]==0) {				
				horLine[horIndex++] = cut[i][1];
			}else
				verLine[verIndex++] = cut[i][1];
			
		}
		horLine[hor] = 0;
		horLine[hor+1] = row;
		verLine[ver] = 0;
		verLine[ver+1] = col;
		
		// 자르는 줄이 오름차순이 되도록
		Arrays.sort(horLine);
		Arrays.sort(verLine);
		
//		System.out.println(Arrays.toString(horLine));
//		System.out.println(Arrays.toString(verLine));
		
		// 방향별로 잘린 사각형 길이를 담는 배열 생성하기
		int[] horLen = new int[hor+1];
		int[] verLen = new int[ver+1];
		
		for(int i=0; i<hor+1; i++) {
			horLen[i] = horLine[i+1]-horLine[i];
		}
		
		for(int i=0; i<ver+1; i++) {
			verLen[i] = verLine[i+1]-verLine[i];
		}
		
//		System.out.println(Arrays.toString(horLen));
//		System.out.println(Arrays.toString(verLen));
		
		// 각 길이를 곱해서 최대 넓이 찾아내기
		int max = 0;
		
		for(int i=0; i<horLen.length; i++) {
			for(int j=0; j<verLen.length; j++) {
				max = Math.max(max, horLen[i]*verLen[j]);
			}	
		}
		
		System.out.println(max);
		
		
	}
}
