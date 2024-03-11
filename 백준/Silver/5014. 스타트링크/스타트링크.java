
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int F, S, G, U, D, result, visitCnt;
    static boolean[] visited;
    static Queue<int[]> que = new LinkedList<>();
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        
        if(G == S) System.out.println("0");
        else {
            result = -1;
            visited = new boolean[F+1];
            BFS(S, 0);
            if(result == -1) System.out.println("use the stairs");
            else System.out.println(result);
        }
        
    }
    
    static void BFS(int start, int num) {
        int[] move = {U, -D};
        
        que.add(new int[] {S, 0});
        visited[S]=true;
        
        Loop: while(!que.isEmpty()) {
            
            int[] now = que.poll();
            
            for(int i=0; i<2; i++) {
                int next = now[0]+move[i];
                
                if(next == G) {
                    result = now[1]+1;
                    break Loop;
                }
                
                if(1<=next && next<=F && visited[next]==false) {
                    visited[next] = true;
                    que.add(new int[] {next, now[1]+1});
                }
            }
            
        }
        
    }

}