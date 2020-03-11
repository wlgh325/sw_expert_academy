import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
 
class Pos {
    int x;
    int y;
 
    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}
 
class Solution {
    // 위, 아래, 왼쪽, 오른쪽
    static int[] xdir = {-1,1,0,0};
    static int[] ydir = {0,0,-1,1};
    static int n;
    static int[][] map;
    static int min;
    static boolean[][] visited;
    static int[][] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
         
        for(int test=1; test<=testNum; test++){
            n = Integer.parseInt(br.readLine());
 
            map = new int[n][n];
            // map 입력받기
            for(int i=0; i<n; i++){
                String[] temp = br.readLine().split("");
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(temp[j]);
                }
            }
 
            min = Integer.MAX_VALUE;
            visited = new boolean[n][n];
             
            ans = new int[n][n];
            for(int i=0; i<n; i++)
                Arrays.fill(ans[i], Integer.MAX_VALUE);
            ans[0][0] = 0;
 
            bfs(0,0);
            System.out.println("#" + test + " " + min);
        }
 
        br.close();
    }
 
    private static void bfs(int x, int y){
        Queue<Pos> q = new LinkedList<>();
 
        q.offer(new Pos(x,y));
        visited[x][y] = true;
        while(!q.isEmpty()){
            Pos p = q.poll();
            int a = p.x;
            int b = p.y;
 
            // 우 하단 도착지에 도착한 경우
            // 최소값과 비교하여 더 작다면 갱신한다.
            if(a == n-1 && b == n-1)
                min = min > ans[n-1][n-1] ? ans[n-1][n-1] : min;
            // 가지치기
            if(min <= ans[a][b])
                continue;
            for(int i=0; i<4; i++){
                int dx = a + xdir[i];
                int dy = b + ydir[i];
                if(isValidPosition(dx, dy)){
                    if(!visited[dx][dy] || ans[dx][dy] > ans[a][b] + map[dx][dy]){
                        visited[dx][dy] = true;
                        ans[dx][dy] = ans[a][b] + map[dx][dy];
                        q.offer(new Pos(dx, dy));
                    }
                }
            }
        }
    }
 
    private static boolean isValidPosition(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n)
            return false;
        return true;
    }
}