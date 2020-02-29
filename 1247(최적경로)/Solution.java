import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Pos {
    int x;
    int y;

    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static int N;
    static Pos home;
    static Pos office;
    static Pos[] customer;
    static int min;
    static boolean[] visited;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
        
        for(int test=1; test<=testNum; test++){
            N = Integer.parseInt(br.readLine());
            
            // 회사, 집, N명의 고객의 좌표
            String[] temp = br.readLine().split(" ");
            home = new Pos(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            office = new Pos(Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
            customer = new Pos[N];

            // 고객의 집의 좌표 저장
            int idx = 0;
            for(int i=4; i<4+N*2; i+=2)
                customer[idx++] = new Pos(Integer.parseInt(temp[i]), Integer.parseInt(temp[i+1]));
            
            min = Integer.MAX_VALUE;
            visited = new boolean[N];
            // dfs를 통해 모든 경우 탐색(순열)
            dfs(0,home.x, home.y,0);
            System.out.println("#" + test + " " + min);
        }
		br.close();
    }
    
    public static void dfs(int k, int x, int y, int dist){
        if(k == N){
            // 마지막 방문한 집과 회사의 거리를 더해준다.
            dist += getDistance(x, y, office.x, office.y);
            // min 값 보다 작을 경우 갱신
            min = min > dist ? dist : min;
            return;
        }

        for(int i=0; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                // 선택한 고객의 집의 customer[i].x, customer[i].y 좌표를 넘긴다.
                // 이전 집의 x,y좌표와 현재 선택한 집의 cusromer[i].x, customer[i].y 좌표와의 거리를 계산하여 넘긴다.
                dfs(k+1, customer[i].x, customer[i].y, dist + getDistance(x, y, customer[i].x, customer[i].y));
                visited[i] = false;
            }
        }
    }

    public static int getDistance(int x, int y, int x2, int y2){
        return Math.abs(x-x2) + Math.abs(y-y2);
    }
}