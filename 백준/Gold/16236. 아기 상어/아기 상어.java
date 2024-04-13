import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, time, distance;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static int[][] map;
    static boolean[][] visited;
    static BabyShark baby;
    static ArrayList<Shark> canEat;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        baby = new BabyShark(0, 0);
        
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                int n = Integer.parseInt(st.nextToken());
                if(n==9) {
                    baby.r = r;
                    baby.c = c;
                    map[r][c] = 0;
                }
                else if(n==0) {
                    map[r][c] = 0;
                }
                else {
                    map[r][c] = n;
                }
            }
        }
        
        time = 0;
        while(true) {
            
            if(!check()) {
                System.out.println(time);
                break;
            }
            
            else {
                eat();
                time += distance;
            }
            
        }
        
    }

    static void eat() {
        canEat.sort(new sortShark());
        int er = canEat.get(0).r;
        int ec = canEat.get(0).c;
        int num = map[er][ec];
        
        // 먹어줌!
        baby.r = er;
        baby.c = ec;
        baby.eat++;
        
        // 먹인 물고기는 없애줌
        map[er][ec] = 0;
        
        // 먹은 물고기 수가 크기와 같아지면 eat초기화 하고 size 증가
        if(baby.eat == baby.size) {
            baby.eat = 0;
            baby.size++;
        }
        
        
        
    }

    static boolean check() {

        Queue<int[]> que = new LinkedList<>();
        visited = new boolean[N][N];

        que.add(new int[] { baby.r, baby.c });
        visited[baby.r][baby.c] = true;

        distance = 1;

        Loop1: while (!que.isEmpty()) {

            canEat = new ArrayList<>();

            // 같은 거리인 칸들만 탐색해볼겡
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int[] now = que.poll();
                int r = now[0];
                int c = now[1];
                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // 아예 못 가는 위치인 경우 : 나보다 큰 상어가 있거나, 이미 간 곳일 때
                    if (nr<0 || nr>=N || nc<0 || nc>=N || map[nr][nc] > baby.size || visited[nr][nc])
                        continue;

                    // 갈 수는 있으면서 걔가 나보다 작다면 먹을 수 있는 상어 리스트에 추가
                    if (map[nr][nc] < baby.size && map[nr][nc]!=0) {
                        canEat.add(new Shark(nr, nc));
                    }

                    // 나랑 같은 사이즈면 일단 거기에서 다시 이동할 곳 찾아줄거임
                    if (map[nr][nc] == baby.size || map[nr][nc] == 0) {
                        que.add(new int[] { nr, nc });
                        visited[nr][nc] = true;
                    }
                }
            }

            // 같은 거리 중 먹을 수 있는 애들 하나라도 있다면 더 이상 que 돌지 않기
            if (canEat.size() != 0) {
                return true;
            }

            // 0이면 다음 거리
            distance++;
        }

        return false;

    }

}

class sortShark implements Comparator<Shark> {

    @Override
    public int compare(Shark o1, Shark o2) {
        if (o1.r != o2.r) {
            return o1.r - o2.r;
        }
        return o1.c - o2.c;
    }

}

class BabyShark {
    int size;
    int eat;
    int r;
    int c;

    BabyShark(int r, int c) {
        this.size = 2;
        this.eat = 0;
        this.r = r;
        this.c = c;
    }

    @Override
    public String toString() {
        return "BabyShark [size=" + size + ", eat=" + eat + ", r=" + r + ", c=" + c + "]";
    }

}

class Shark {
    int r;
    int c;

    Shark(int r, int c) {
        this.r = r;
        this.c = c;
    }

}