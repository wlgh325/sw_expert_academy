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

class Solution {
    static int height, width, R, C, L;
    static int[][] map;
    static boolean[][] visited;
    // 2,3,4,5,6,7번 구조물 이동
    static int[][] xdir = {{-1,1},{0,0},{-1,0},{1,0},{1,0},{-1,0}};
    static int[][] ydir = {{0,0},{-1,1},{0,1},{0,1},{0,-1},{0,-1}};
    // 1번 구조물 이동
    static int[] xdir2 = {-1,1,0,0};
    static int[] ydir2 = {0,0,-1,1};
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
                    map[i][j] = Integer.parseInt(temp[j]);
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
            int structNum = map[x][y];
            if(structNum != 1){
                structNum -= 2;
                for(int i=0; i<2; i++){
                    int dx = x + xdir[structNum][i];
                    int dy = y + ydir[structNum][i];
                    // 유효한 위치 && 방문하지 않은 경우
                    if(isValidPosition(dx, dy) && !visited[dx][dy]){
                        // 연결된 경우에만 이동 가능
                        if(isConnected(x, y, dx, dy, i)){
                            visited[dx][dy] = true;
                            q.offer(new Pos(dx, dy, time+1));
                        }
                    }
                }
            }
            else{
                for(int i=0; i<4; i++){
                    int dx = x + xdir2[i];
                    int dy = y + ydir2[i];
                    if(isValidPosition(dx, dy) && !visited[dx][dy]){
                        if(isConnected(x,y,dx,dy,i)){
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

    public static boolean isConnected(int x, int y, int dx, int dy, int i){
        switch(map[x][y]){
            case 1:
                switch(map[dx][dy]){
                    case 2:
                        if(i == 2 || i == 3)
                            return false;
                        break;
                    case 3:
                        if(i == 0 || i == 1)
                            return false;
                        break;
                    case 4:
                        if(i == 0 || i == 3)
                            return false;
                        break;
                    case 5:
                        if(i == 1 || i == 3)
                            return false;
                        break;
                    case 6:
                        if(i == 1 || i == 2)
                            return false;
                        break;
                    case 7:
                        if(i == 0 || i == 2)
                            return false;
                        break;
                }
                break;
            case 2:
                switch(map[dx][dy]){
                    case 3:
                        return false;
                    case 4:
                        if(i==0) return false;
                        break;
                    case 5:
                        if(i == 1) return false;
                        break;
                    case 6:
                        if(i == 1) return false;
                        break;
                    case 7:
                        if(i == 0) return false;
                        break;
                }
                break;
            case 3:
                switch(map[dx][dy]){
                    case 2:
                        return false;
                    case 4:
                        if(i==1) return false;
                        break;
                    case 5:
                        if(i==1) return false;
                        break;
                    case 6:
                        if(i==0) return false;
                        break;
                    case 7:
                        if(i==0) return false;
                        break;
                }
                break;
            case 4:
                switch(map[dx][dy]){
                    case 2:
                        if(i==1) return false;
                        break;
                    case 3:
                        if(i==0) return false;
                        break;
                    case 4:
                        return false;
                    case 5:
                        if(i==1) return false;
                        break;
                    case 7:
                        if(i==0) return false;
                        break;
                }
                break;
            case 5:
                switch(map[dx][dy]){
                    case 2:
                        if(i==1) return false;
                        break;
                    case 3:
                        if(i==0) return false;
                        break;
                    case 4:
                        if(i==1) return false;
                        break;
                    case 5:
                        return false;
                    case 6:
                        if(i==0) return false;
                        break;
                }
                break;
            case 6:
                switch(map[dx][dy]){
                    case 2:
                        if(i==1) return false;
                        break;
                    case 3:
                        if(i==0) return false;
                        break;
                    case 5:
                        if(i==0) return false;
                        break;
                    case 6:
                        return false;
                    case 7:
                        if(i==1) return false;
                        break;
                }
                break;
            case 7:
                switch(map[dx][dy]){
                    case 2:
                        if(i==1) return false;
                        break;
                    case 3:
                        if(i==0) return false;
                        break;
                    case 4:
                        if(i==0) return false;
                        break;
                    case 6:
                        if(i==1) return false;
                        break;
                    case 7:
                        return false;
                }
        }
        return true;
    }
}