import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Prob1225 {
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		for(int t=1; t<11; t++) {
			
			int N = sc.nextInt();
			Queue<Integer> numQue = new LinkedList<>();
			
			for(int i=0; i<8; i++) {
				numQue.add(sc.nextInt());
			}
			
			int d = 1;
			while(true) {
				int lastNum = numQue.poll()-d;
				// 뒤에 올 숫자가 0이하면 0 넣고 탈출
				if(lastNum <= 0) {
					numQue.add(0);
					break;
				}
				// 아니면 그냥 값 삽입
				else
					numQue.add(lastNum);
				// d갱신. d는 1~5만 반복해야함.
				d = (d%5)+1;
			}
			
			
			System.out.print("#"+t);
			for(int i=0; i<8; i++) {
				System.out.print(" " + numQue.poll());
			}
			System.out.println();
			
			
			
		}
	}

}
