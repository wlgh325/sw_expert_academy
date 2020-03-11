import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Pos implements Comparable<Pos>{
    int x;
    int y;
    int time;

    Pos(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }
    
    // 오름차순 정렬
    @Override
    public int compareTo(Pos p){
        if(this.time > p.time)
            return 1;
        else if(this.time < p.time)
            return -1;
        return 0;
    }
}

class Solution2 {
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
        PriorityQueue<Pos> q = new PriorityQueue<>();

        q.offer(new Pos(x,y,0));
        visited[x][y] = true;
        while(!q.isEmpty()){
            Pos p = q.poll();
            int a = p.x;
            int b = p.y;
            int time = p.time;

            // 우 하단 도착지에 도착한 경우
            // 최소값과 비교하여 더 작다면 갱신한다.
            if(a == n-1 && b == n-1)
                min = min > time ? time : min;
            // 가지치기
            if(min <= time)
                continue;
            for(int i=0; i<4; i++){
                int dx = a + xdir[i];
                int dy = b + ydir[i];
                if(isValidPosition(dx, dy)){
                    int dtime = time + map[dx][dy];
                    if(!visited[dx][dy] || dtime < ans[dx][dy]){
                        visited[dx][dy] = true;
                        ans[dx][dy] = dtime;
                        q.offer(new Pos(dx, dy, dtime));
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