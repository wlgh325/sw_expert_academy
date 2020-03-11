import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
  
class Solution4 {
    // 위, 아래, 왼쪽, 오른쪽
    static int[] xdir = {-1,1,0,0};
    static int[] ydir = {0,0,-1,1};
    static int n;
    static int[][] map;
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
            
            dijkstra(test,0,0);           
        }
  
        br.close();
    }
  
    private static void dijkstra(int test, int x, int y){
        boolean[][] visited;
        int[][] distance;
        
        visited = new boolean[n][n];
        distance = new int[n][n];

        // distance 초기화
        for(int i=0; i<n; i++)
            Arrays.fill(distance[i], Integer.MAX_VALUE);

        // 시작 노드 초기화
        distance[x][y] = 0;
        visited[x][y] = true;
        
        // 연결노드 distance 갱신
        for(int i=0; i<4; i++){
            int dx = x + xdir[i];
            int dy = y + ydir[i];
            if(isValidPosition(dx, dy) && !visited[dx][dy])
                distance[dx][dy] = map[dx][dy];            
        }

        // 모든 점 check
        for(int a=0; a<n*n-2; a++){
            int min = Integer.MAX_VALUE;
            int minX = -1;
            int minY = -1;

            // 최소값 찾기
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(!visited[i][j] && distance[i][j] != Integer.MAX_VALUE){
                        if(distance[i][j] < min){
                            min = distance[i][j];
                            minX = i;
                            minY = j;
                        }
                    }
                }
            }

            visited[minX][minY] = true;
            // minX, minY와 연결되면서 방문하지 않은 노드들에 대해 check
            for(int i=0; i<4; i++){
                int dx = minX + xdir[i];
                int dy = minY + ydir[i];
                if(isValidPosition(dx, dy) && !visited[dx][dy]){
                    if(distance[dx][dy] > distance[minX][minY] + map[dx][dy]){
                        distance[dx][dy] = distance[minX][minY] + map[dx][dy];
                    }
                }                
            }
        }

        System.out.println("#" + test + " " + distance[n-1][n-1]);
    }
  
    private static boolean isValidPosition(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n)
            return false;
        return true;
    }
}