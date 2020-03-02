import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Solution {
    static int max;
    static int N;
    static int[][] map;
    // 0: 오른쪽 아래, 1: 왼쪽 아래, 2: 왼쪽 위, 3: 오른쪽 위
    // 0, 1, 2, 3
    // {0,1}, {1,2}, {2,3}, {3,0}
    static int[][] xdir = {{1,1}, {1,-1}, {-1,-1}, {-1,1}};
    static int[][] ydir = {{1,-1},{-1,-1},{-1,1},{1,1}};
    static boolean[][] visited;
    static boolean[] nVisited;
    static int startX, startY;
    static int startNum;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
         
        for(int test=1; test<=testNum; test++){
            // 입력받기
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for(int i=0; i<N; i++){
                String[] temp = br.readLine().split(" ");
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(temp[j]);
                }
            }
            
            max = -1;
            visited = new boolean[N][N];
            nVisited = new boolean[101];
            // 모든 위치에서 dfs 탐색
            // 맨 아래 두행, 맨 왼쪽 열, 맨 오른쪽 열은 사각형 만들기 불가로 제외
            for(int i=0; i<N-2; i++){
                for(int j=1; j<N-1; j++){
                    // 시작 위치와 시작점의 디저트 수 저장
                    startX = i;
                    startY = j;
                    startNum = map[i][j];
                    dfs(i,j, 0, 0, 0);                    
                }
            }
            System.out.println("#" + test + " " + max);
        }
        br.close();
    }

    public static void dfs(int x, int y, int k, int dir, int rotate){
        // 시작 위치에 도착 & 사각형 완성
        if(k != 0 && startX == x && startY == y && rotate == 3){
            max = max < k ? k : max;    // 최대값 갱신
            return;
        }
        for(int i=0; i<2; i++){
            // 시작점에서 오른쪽 아래로 내려가는 경우만 따짐으로써
            // 중복 제거
            if(k==0 && i == 1)
                continue;
            int dx = x + xdir[dir][i];
            int dy = y + ydir[dir][i];
            // 유효한 위치이면서 방문하지 않은 곳
            if(isValidPosition(dx, dy) && !visited[dx][dy]){
                // 이전에 방문했던 곳(시작 점 포함)의 값들과 달라야 함
                // 시작점으로 돌아온 경우는 같을 수 밖에 없기 때문에 그 부분은 오른쪽 조건으로 예외처리
                if((!nVisited[map[dx][dy]] && startNum != map[dx][dy])|| ((dx == startX) && (dy == startY))){
                    visited[dx][dy] = true;
                    nVisited[map[dx][dy]]= true;
                    
                    if(rotate != (dir+i)%4)
                        dfs(dx, dy, k+1, (dir+i)%4, rotate+1);    // 같은 방향으로 가지 않기 때문에 방향을 꺾은 것으로 판단
                    else
                        dfs(dx, dy, k+1, (dir+i)%4, rotate);  // 같은 방향으로 가는 경우는 꺾지 않은 것이므로 rotate 그대로 전달
                    
                    nVisited[map[dx][dy]]= false;
                    visited[dx][dy] = false;
                }
            }
        }
    }

    public static boolean isValidPosition(int x, int y){
        if(x < 0 || x >= N || y < 0 || y >= N) return false;
        return true;
    }
}