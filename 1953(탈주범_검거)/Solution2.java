import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pos {
    int x;
    int y;
    int time;

    Pos(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

class Solution2 {
    static int height, width, R, C, L;
    static int[][] map;
    static boolean[][] visited;
    // 상하좌우
    static int[] xdir = {-1,1,0,0};
    static int[] ydir = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
            String[] temp = br.readLine().split(" ");
            // 지하터널의 세로, 기로크기, 맨홀의 세로, 가로 위치, 탈출 후 소요된 시간
            height = Integer.parseInt(temp[0]);
            width = Integer.parseInt(temp[1]);
            R = Integer.parseInt(temp[2]);
            C = Integer.parseInt(temp[3]);
            L = Integer.parseInt(temp[4]);

            // 지하터널 정보
            map = new int[height][width];
            for(int i=0; i<height; i++){
                temp = br.readLine().split(" ");
                for(int j=0; j<width; j++){
                    map[i][j] = getBit(Integer.parseInt(temp[j]));
                }
            }

            visited = new boolean[height][width];
			System.out.println("#" + test + " " + bfs(R,C));
        }
        			  
		br.close();
    }
    
    public static int bfs(int a, int b){
        Queue<Pos> q = new LinkedList<>();
        int num = 0;

        q.offer(new Pos(a,b, 0));
        visited[a][b] = true;
        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int time = p.time;
            
            if(time == L)
                break;
            
            num++;
            for(int i=0; i<4; i++){
                if((map[x][y] & (1<<(3-i)) ) != 0){
                    int dx = x + xdir[i];
                    int dy = y + ydir[i];
                    // 유효한 위치 && 방문하지 않은 경우
                    if(isValidPosition(dx, dy) && !visited[dx][dy]){
                        if(isConnected(map[dx][dy], i)){
                            visited[dx][dy] = true;
                            q.offer(new Pos(dx, dy, time+1));
                        }
                    }
                }
            }
        }

        return num;
    }

    public static boolean isValidPosition(int x, int y){
        if(x < 0 || x >= height || y < 0 || y >= width) return false;
        if(map[x][y] == 0) return false;
        return true;
    }
    
    public static int getBit(int structNum){
        // 상: 왼쪽 첫 번째
        // 하 : 왼쪽 두 번째
        // 좌 : 왼쪽 세 번째
        // 우 : 왼쪽 네 번째
        // ex) 1번: 상하좌우 이동 가능 : 1111(2진수) => 15(10진수)
        switch(structNum){
            case 1:
                return 15;  // 상하좌우 1111
            case 2:
                return 12;  // 상하 1100
            case 3:
                return 3;   // 좌우 0011
            case 4:
                return 9;   // 상우 1001
            case 5:
                return 5;   // 하우 0101
            case 6:
                return 6;   // 하좌 0110
            case 7:
                return 10;  // 상좌 1010
        }
        return 0;
    }

    public static boolean isConnected(int next, int i){
        // 연결된 경우에만 이동 가능
        // 상으로 이동인 경우 && 하에 연결 (0100)
        // 하로 이동인 경우 && 상에 연결 (1000)
        // 좌로 이동 && 우에 연결 (0001)
        // 우로 이동 && 좌에 연결 (0010)
        int connect = 0;
        switch(i){
            case 0:
                connect = 4;
                break;
            case 1:
                connect = 8;
                break;
            case 2:
                connect = 1;
                break;
            case 3:
                connect = 2;
        }

        if((next & connect) == connect)
            return true;
        return false;
    }
}