import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

class Pos{
    int x;
    int y;
    int on;
    int floor;

    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }

    Pos(int x, int y, int floor, int on){
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.on = on;
    }
}

class Solution {
    static int min;
    static int N;
    static Queue<Pos> queue;
    static ArrayList<Pos> stairs;
    static boolean[] visited;
    static int[] floors;
    static boolean[] checks;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
         
        for(int test=1; test<=testNum; test++){
            N = Integer.parseInt(br.readLine());
            queue = new LinkedList<>();
            stairs = new ArrayList<>();

            for(int i=0; i<N; i++){
                String[] temp = br.readLine().split(" ");
                for(int j=0; j<N; j++){
                    int t = Integer.parseInt(temp[j]);
                    if(t == 1)
                        queue.offer(new Pos(i,j));
                    else if( t >= 1)
                        stairs.add(new Pos(i, j, t+1, 0));
                }
            }

            min = Integer.MAX_VALUE;
            for(int i=0; i<=queue.size()/2; i++){
                visited = new boolean[queue.size()];
                comb(0,i,queue.size());
            }

            System.out.println("#" + test + " " + min);
        }
        br.close();
    }

    public static void comb(int start, int r, int num){
        if(r == 0){
            solve(num);
            return;
        }
        for(int i=start; i<num; i++){
            visited[i] = true;
            comb(i+1, r-1, num);
            visited[i] = false;
        }
    }

    public static void solve(int num){
        int time = 0;
        floors = new int[num];
        checks = new boolean[num];
        int cnt = 0;

        // 원본 유지하기 위해서 복사 queue 만들기
        Pos[] temp = queue.toArray(new Pos[queue.size()]);
        Queue<Pos> q = new LinkedList<>();
        for(int i=0; i<temp.length; i++)
            q.add(new Pos(temp[i].x, temp[i].y));

        // 내려가야 하는 층수
        for(int i=0; i<num; i++){
            if(visited[i])
                floors[i] = stairs.get(0).floor;        
            else
                floors[i] = stairs.get(1).floor;
        }

        stairs.get(0).on = 0;
        stairs.get(1).on = 0;

        while(true){
            for(int i=0; i<num; i++){
                Pos p = q.poll();

                // 계단1
                if(visited[i])
                    usingStair(0, p, i);            
                // 계단 2
                else if(!visited[i])
                   usingStair(1, p, i);
                
                // 계단을 모두 내려온 경우
                if(floors[i] == 0){
                    cnt ++;
                    if(visited[i])
                        stairs.get(0).on--;
                    else
                        stairs.get(1).on--;
                    checks[i] = false;
                }
                q.offer(new Pos(p.x, p.y));
            }
            time++;

            if(cnt == num)
                break;
        }
        min = min > time ? time : min;

        // 바꿔서 계단 이동
        time = 0;
        floors = new int[num];
        checks = new boolean[num];
        cnt = 0;
        q.clear();
        for(int i=0; i<temp.length; i++)
            q.add(new Pos(temp[i].x, temp[i].y));

        // 내려가야 하는 층수
        for(int i=0; i<num; i++){
            if(!visited[i])
                floors[i] = stairs.get(0).floor;        
            else
                floors[i] = stairs.get(1).floor;
        }

        stairs.get(0).on = 0;
        stairs.get(1).on = 0;

        while(true){
            for(int i=0; i<num; i++){
                Pos p = q.poll();

                // 계단1
                if(!visited[i])
                    usingStair(0, p, i);            
                // 계단 2
                else if(visited[i])
                   usingStair(1, p, i);
                
                // 계단을 모두 내려온 경우
                if(floors[i] == 0){
                    cnt ++;
                    if(!visited[i])
                        stairs.get(0).on--;
                    else
                        stairs.get(1).on--;
                }
                q.offer(new Pos(p.x, p.y));
            }
            time++;

            if(cnt == num)
                break;
        }
        min = min > time ? time : min;
    }

    public static void usingStair(int stairNum, Pos p, int i){
        Pos stair = stairs.get(stairNum);
        // 계단에 도착한 경우 대기인이 3명이 아닌 경우에만 내려감
        if((stair.y == p.y && stair.x == p.x && stair.on < 3) || checks[i]){
            if(!checks[i]){
                stairs.get(stairNum).on++;
                checks[i] = true;
            }
            floors[i]--;
        }
        else{
            // y좌표를 맞춘다.
            if(p.y != stair.y){
                if(p.y < stair.y)
                    p.y++;
                else
                    p.y--;
            }
            // y맞추고 x좌표를 맞춤
            else if(p.x != stair.x){
                if(p.x < stair.x)
                    p.x++;
                else
                    p.x--;
            }
        }
    }
}