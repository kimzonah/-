import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T;
	static int[][] sudoku;
	static int[] visitedHor;
	static int [] visitedVer;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			// 스도쿠 숫자 입력 받기
			sudoku = new int[9][9];
			for(int i=0; i<9; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<9; j++) {
					sudoku[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int res = 1;
				
			// 1. 가로세로 탐색
			// i행 또는 i열 고정
			FirstCheck:
			for(int i=0; i<9; i++) {
				
				// 고정된 줄 방문 체크를 위한 배열 초기화
				visitedHor = new int[10];
				visitedVer = new int[10];
				
				for(int j=0; j<9; j++) {
					int hor = sudoku[i][j]; // i행 j를 한칸식 이동하며 가로로 탐색
					int ver = sudoku[j][i]; // i열 j를 한칸씩 이동하며 세로로 탐색
					
					// 가로줄에서 hor이 이미 만난 숫자거나, 새로줄에서 ver이 이미 만난 숫자라면 res를 0으로 바꾸고 탐색 중지
					if(visitedHor[hor]!= 0 || visitedVer[ver]!=0) {
						res = 0;
						break FirstCheck;
					}
					// 위 경우가 아니라면 방문 처리
					else {
						visitedHor[hor] = 1;
						visitedVer[ver] = 1;
					}
				}
			}
				
			// 2. 3*3씩 탐색
			// 가로세로 탐색이 끝났는데 여전이 res가 1이라면 3*3 탐색도 수행
			if(res == 1) {
				
				SecondCheck:
				for(int startR=0; startR<=6; startR+=3) {
					for(int startC=0; startC<=6; startC+=3) {
						visitedHor = new int[10];
						
						for(int r=0; r<3; r++) {
							int R = startR+r;
							
							for(int c=0; c<3; c++) {
								int num = sudoku[R][startC+c];
								
								if(visitedHor[num] != 0) {
									res = 0;
									break SecondCheck;
								}
								else {
									visitedHor[num] = 1;
								}
							}
						}
					}
				}
				
			}
			
			System.out.println("#"+t+" "+res);	
			
		}
		
	}
	

}
