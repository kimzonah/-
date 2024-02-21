import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{
	int num;
	int parent;
	
	Node(int num){
		this.num = num;
	}
}

public class Main {
	
	static int N; // 노드의 수
	static ArrayList<Integer>[] linkInfo; // 연결 정점 정보
	static Node[] nodes; // 노드들을 담아줄 배열
	static int[] visited; // 방문 처리
	static int linkSize, oneChSize;
	static ArrayList<Integer> linkList;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		linkInfo = new ArrayList[N+1];
		nodes = new Node[N+1];
		for(int i=0; i<N+1; i++) {
			linkInfo[i] = new ArrayList<>();
			nodes[i] = new Node(i);
		}
				
		
		// N-1번 연결된 정점 입력 받기
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 1이면 연결된 노드의 부모 자리에 넣어주고 1의 연결된 노드 정보에만 자식을 추가한다.
			if(a==1 || b==1) {
				if(a==1) {
					nodes[b].parent = a;
					linkInfo[a].add(b);
				}
				else {
					nodes[a].parent = b;
					linkInfo[b].add(a);
				}
			}
			// 1이 아닐땐 둘 다 서로의 연결 노드 정보에 추가
			else {
				linkInfo[a].add(b);
				linkInfo[b].add(a);
			}
		}
		
		visited = new int[N+1];
		// 1번부터 시작
		for(int curr : linkInfo[1]) {
			if(visited[curr]==0) {
				DFS(curr);
			}
		}
		
		for(int i=2; i<N+1; i++) {
			System.out.println(nodes[i].parent);
		}
		
	}
	
	static void DFS(int curr) {
		visited[curr] = 1; // 일단 방문처리
		linkList = linkInfo[curr];
		
		// 연결된 노드 리스트를 돌면서
		for(int next: linkList) {
			// 방문한적 없는 노드라면 -> 아직 자식이 된 적이 없다는 뜻
			if(visited[next]==0) {
				// 부모 노드에 현재 노드를 연결
				nodes[next].parent = curr;
				DFS(next);
			}
		}
		
	}

}
