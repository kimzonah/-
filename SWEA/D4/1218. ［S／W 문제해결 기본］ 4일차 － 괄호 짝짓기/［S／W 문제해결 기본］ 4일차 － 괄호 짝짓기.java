import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 전체 사이클 10번 반복
		for(int t=1; t<11; t++) {
			// 몇개의 괄호를 입력 받을건지 개수 정하기
			int N = Integer.parseInt(br.readLine());
			// String으로 한번에 받고,
			String textStr = br.readLine();
			Stack<Character> stack = new Stack<>();
			
			int res = 1;
			
			for(int i=0; i<N; i++) {
				// charAt으로 형변환
				char text = textStr.charAt(i);
				
				// 여는 괄호면 스택에 쌓아주기
				if(text == '(' || text == '{' || text == '[' || text == '<') {
					stack.push(text);
				}
				// 닫는 괄호면 스택 가장 위에 있는 것과 한 쌍일 때만 유효,
				// 아니면 res를 0으로 초기화 후 반복문 탈출
				else {
					if(text == ')' && close(stack,'(')) {
						res=0;
						break;
					}	
					else if(text == '}' && close(stack,'{')) {
						res=0;
						break;
					}
					else if(text == ']' && close(stack,'[')) {
						res=0;
						break;
					}
					else if(text == '>' && close(stack,'<')) {
						res=0;
						break;
					}
				}
				
			}
			
			if(!stack.isEmpty())
				res = 0;
			
			System.out.println("#"+t+" "+res);
		}
		
	}
	
	// 닫는 괄호 확인 메서드
	public static boolean close(Stack<Character> stack, char compText) {
		// 현재 스택 최상단 원소가 비교하려는 문자와 같다면
		// 뽑아줌
		if(stack.peek()==compText) {
			stack.pop();
			return false;
		}
		// 최상단 원소와 다르다면 뽑지 않고 그냥 true 반환
		// 위에 논리연산자로 true면 break 하기 위해
		return true;
	}
}