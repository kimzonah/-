import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N, M;
		String str;
		
		int[][] chess; // 8*8 보드 정보 받아줄 1차원 배열
		
		ArrayList<Integer> countList = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //10
		M = Integer.parseInt(st.nextToken()); //13
		
		int board[][] = new int[N][M];
		
		// 보드 정보 받아주기
		for(int r=0; r<N; r++) {
			str = br.readLine();
			for(int c=0; c<M; c++) {
				if (str.charAt(c) == 'B') board[r][c] = 1;
				else board[r][c] = 0;
			}
		}
		
		int boardRow = N - 7; 
		int boardCol = M - 7; 
		
		// 일단 8*8 구역 하나씩을 고정해줌
		for(int i=0; i<boardRow; i++) {
			for(int j=0; j<boardCol; j++) {
				// 위 이중 for문 돌면 구역 하나 정해진 것
				// 구역이 정해지면 모든 값을 초기화
				chess = new int[8][8];
				
				// 구역 안에 모든 행과 열 순회 하면서 값들을 chess 배열에 넣어줌
				for(int r=0; r<8; r++) {
					for(int c=0; c<8; c++) {
						chess[r][c] = board[r+i][c+j];
					}
				}
				
				// 첫 시작을 1로 재정렬 or 0으로 재정렬 하는 두가지 경우 고려
				
				// 1. 첫 시작을 1로 하는 체스판으로 만들때 다시 칠하는 횟수 구하기
				int cntStartOne = 0;
				for(int r=0; r<8; r++) {
					for(int c=0; c<8; c++) {
						
						// 짝수번째 행이고
						if(r%2==0) {
							// 짝수번째 열이면서 1이 아니면 다시 칠하기
							if(c%2 == 0 && chess[r][c] != 1) {
								cntStartOne++;
							}
							// 홀수번째 열이면서 0이 아니면 다시 칠하기
							else if(c%2 != 0 && chess[r][c] != 0) {
								cntStartOne++;
							}
						}
						// 홀수번째 행이고
						else {
							// 짝수번째 열이면서 0이 아니면 다시 칠하기
							if(c%2 == 0 && chess[r][c] != 0) {
								cntStartOne++;
							}
							// 홀수번째 열이면서 1이 아니면 다시 칠하기
							else if(c%2 != 0 && chess[r][c] != 1) {
								cntStartOne++;
							}
						}
						
					}
				}
				
				// 2. 첫 시작을 0으로 하는 체스판으로 만들때 다시 칠하는 횟수 구하기
				int cntStartZero = 0;
				for(int r=0; r<8; r++) {
					for(int c=0; c<8; c++) {
						
						// 짝수번째 행이고
						if(r%2==0) {
							// 짝수번째 열이면서 0이 아니면 다시 칠하기
							if(c%2 == 0 && chess[r][c] != 0) {
								cntStartZero++;
							}
							// 홀수번째 열이면서 1이 아니면 다시 칠하기
							else if(c%2 != 0 && chess[r][c] != 1) {
								cntStartZero++;
							}
						}
						// 홀수번째 행이고
						else {
							// 짝수번째 열이면서 1이 아니면 다시 칠하기
							if(c%2 == 0 && chess[r][c] != 1) {
								cntStartZero++;
							}
							// 홀수번째 열이면서 0이 아니면 다시 칠하기
							else if(c%2 != 0 && chess[r][c] != 0) {
								cntStartZero++;
							}
						}
						
					}
				}
				countList.add(Math.min(cntStartZero, cntStartOne));
				
				
				
			}
		}
		
		Collections.sort(countList);
		System.out.println(countList.get(0));
		
	}
	

}
