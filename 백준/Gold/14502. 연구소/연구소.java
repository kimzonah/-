import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    
    static int N, M;
    static int[][] lab;
    static int[][] copyLab;
    static int[][] visited;
    static int[] zeroPoint; // 0이되는 점
    static ArrayList<int[]> ableMap; // [r,c], [r,c], ...
    static int count;
    static int max = -1;
    
    static int[] dr = {-1, 1, 0 ,0};
    static int[] dc = {0, 0, -1 ,1};
    
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        ableMap = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        lab = new int[N][M];
        
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                int num = Integer.parseInt(st.nextToken());
                // 연구소 지도 값 입력
                lab[r][c] = num;
                // 0이면 벽을 세울 수 있으므로 리스트에 좌표값을 담아줌
                if(num == 0) {
                    zeroPoint = new int[2];
                    zeroPoint[0] = r;
                    zeroPoint[1] = c;
                    ableMap.add(zeroPoint); // 0인 영역의 인덱스
                    
                }
            }
        }
        
        
        // 세울 3개의 벽 정하기
        int size = ableMap.size();
        for(int i=0; i<size-2; i++) { // 첫번째 벽 세우기
            for(int j=i+1; j<size-1; j++) { // 두번째 벽 세우기
                for(int k=j+1; k<size; k++) { // 세번째 벽 세우기
                    copyLab = new int[lab.length][lab[0].length];
                    copyArr(lab, copyLab);
                    int[] Map1 = ableMap.get(i);
                    copyLab[Map1[0]][Map1[1]] = 1;
                    int[] Map2 = ableMap.get(j);
                    copyLab[Map2[0]][Map2[1]] = 1;
                    int[] Map3 = ableMap.get(k);
                    copyLab[Map3[0]][Map3[1]] = 1;
                    
                    // copyLab으로 탐색 시작
                    visited = new int[N][M];
                    count = size-3; // 원래 0이던 영역 수 - 새로운 벽 3개
                    
                    // 연구소 모든 칸 순회 하다가...
                    for(int r=0; r<N; r++) {
                        for(int c=0; c<M; c++) {
                            // 바이러스가 있고, 탐색한 적 없는 위치를 만나면 dfs탐색 시작
                            if(copyLab[r][c]==2 && visited[r][c]==0) {
                            	virus(r, c);
                            }
                        }
                    }

                    if(count > max) {
                        max = count;
                    }
                }
            }
        }
        
        System.out.println(max);
        
        
        
    }
    
    static void virus(int r, int c) {
        visited[r][c] = 1; // 방문처리
        
        // 사방탐색
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            // 경계조건 안에 있고 방문한 적 없으면서 0인 영역이면 바이러스 확산
            if(0<=nr && nr<N && 0<=nc && nc<M && visited[nr][nc]==0 && copyLab[nr][nc]==0) {
                copyLab[nr][nc] = 2;
            	count--; // 안전영역 1 감소
                virus(nr, nc); // 새로운 바이러스 감염 영억에서부터 다시 탐색
            }
        }
    }
    
    static void copyArr(int[][] lab, int[][] copyLab) {
        for(int i=0; i<lab.length; i++) {
            for(int j=0; j<lab[i].length; j++) {
                copyLab[i][j] = lab[i][j];
            }
        }
    }

}