import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Stack;
 
class Pos {
    int x;
    int y;
 
    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}
 
class Solution {
    static int N, K;
    static int[][] map;
    static int max;
    static ArrayList<Pos> maxHeightPos;
    // 위,아래, 왼쪽, 오른쪽
    static int[] xdir = {-1,1,0,0};
    static int[] ydir = {0,0,-1,1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
         
        for(int test=1; test<=testNum; test++){
            // N과 K 입력받기
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            K = Integer.parseInt(temp[1]);
            map = new int[N][N];
 
            // 부지 중 최대 높이
            int maxHeight = 0;
            
            // 부지 입력받기
            for(int i=0; i<N; i++){
                temp = br.readLine().split(" ");
                for(int j=0; j<N; j++){
                    int t = Integer.parseInt(temp[j]);
                    maxHeight = maxHeight < t ? t : maxHeight;
                    map[i][j] = t;
                }
            }
 
            maxHeightPos = new ArrayList<>();
            findMaxHeightPos(maxHeight);
            max = 0;
            // 최대 k까지 뺀다
            for(int k=1; k<=K; k++){
                // map의 모든 위치를 빼나간다.
                for(int i=0; i<N; i++){
                    for(int j=0; j<N; j++){
                        map[i][j] -= k;
                        // 모든 봉우리에서 dfs
                        for(int w=0; w<maxHeightPos.size(); w++){
                            Pos p = maxHeightPos.get(w);
                            visited = new boolean[N][N];
                            visited[p.x][p.y] = true;
                            dfs(p.x, p.y, 1);
                        }
                        // 원상 복구
                        map[i][j] += k;
                    }
                }
            }
 
            // 부지를 깎지 않았을 경우 dfs
            for(int i=0; i<maxHeightPos.size(); i++){
                Pos p = maxHeightPos.get(i);
                visited = new boolean[N][N];
                visited[p.x][p.y] = true;
                dfs(p.x, p.y, 1);
            }
 
            System.out.println("#" + test + " " + max);
        }
        br.close();
    }
 
    public static void dfs(int x, int y, int len){
        for(int i=0; i<4; i++){
            int dx = x + xdir[i];
            int dy = y + ydir[i];
            // 유요한 위치이고 방문하지 않았을 경우
            if(isValidPosition(dx, dy) && !visited[dx][dy]){
                // 더 작은 부지 일 경우 방문
                if(map[x][y] > map[dx][dy]){
                    visited[dx][dy] = true;
                    dfs(dx, dy, len+1);
                    visited[dx][dy] = false;
                }
            }
        }
        max = max < len ? len : max;
    }
 
    public static void findMaxHeightPos(int maxHeight){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == maxHeight)
                    maxHeightPos.add(new Pos(i, j));
            }
        }
    }
 
    public static boolean isValidPosition(int x, int y){
        if(x < 0 || x >= N || y < 0 || y >= N) return false;
        return true;
    }
}