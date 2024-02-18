import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int student [][] = new int[2][6];
		
		for(int i=0; i<N; i++) {
			int gender = sc.nextInt();
			int grade = sc.nextInt();
			
			student[gender][grade-1]+=1;
		}
		
		int room = 0;
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<6; j++) {
				room += (student[i][j])/K;
				if(student[i][j]%K != 0) {
					room += 1;
				}
			}
		}
		System.out.println(room);
		
	}
			
	}
