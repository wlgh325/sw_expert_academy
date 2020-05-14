import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;

class Info implements Comparable<Info>{
    int idx;
    int charge;

    Info(int idx, int charge){
        this.idx = idx;
        this.charge = charge;
    }

    @Override
    public int compareTo(Info a){
        if(this.charge < a.charge)
            return 1;
        else if(this.charge > a.charge)
            return -1;
        else
            return 0;
    }
}

class Solution {
    static int M, A;
    static int[][] track;
    static int[][] bc;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
         
        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            M = Integer.parseInt(temp[0]);  // 총 이동시간
            A = Integer.parseInt(temp[1]);  // BC의 개수

            track = new int[2][M+1];
            track[0][0] = 0; track[1][0] = 0;   // 처음 위치
            // 사용자 A, B의 이동정보
            for(int i=0; i<2; i++){
                temp = br.readLine().split(" ");
                for(int j=1; j<=M; j++){
                    track[i][j] = Integer.parseInt(temp[j-1]);
                }
            }

            bc = new int[A][4];
            // bc의 정보 (x,y), 범위, 충전 양
            for(int i=0; i<A; i++){
                temp = br.readLine().split(" ");
                bc[i][0] = Integer.parseInt(temp[1]) - 1;   // x
                bc[i][1] = Integer.parseInt(temp[0]) - 1;   // y
                bc[i][2] = Integer.parseInt(temp[2]);   // range
                bc[i][3] = Integer.parseInt(temp[3]);   // charge
            }

            System.out.println("#" + test + " " + move());
        }
        br.close();
    }

    public static int move(){
        // X, 상, 우, 하, 좌
        int[] xdir = {0,-1,0,1,0};
        int[] ydir = {0,0,1,0,-1};

        int[] apos = new int[2];    // apos[0]: x좌표, apos[1]: y좌표
        apos[0] = 0; apos[1] = 0;

        int[] bpos = new int[2];
        bpos[0] = 9; bpos[1] = 9;

        int sum = 0;
        // 이동
        for(int i=0; i<=M; i++){
            int adir = track[0][i];
            int bdir = track[1][i];

            // 사용자 A의 이동한 위치
            apos[0] += xdir[adir];
            apos[1] += ydir[adir];

            // 사용자 B의 이동한 위치
            bpos[0] += xdir[bdir];
            bpos[1] += ydir[bdir];            

            // A 사용자가 사용가능한 bc 선택
            PriorityQueue<Info> pqa = new PriorityQueue<>();
            for(int j=0; j<A; j++){
                // bc와의 거리 구하기
                int dist = Math.abs(bc[j][0] - apos[0]) + Math.abs(bc[j][1] - apos[1]);
                // 거리가 range 이하이면 queue에 넣기
                if(dist <= bc[j][2])
                    pqa.offer(new Info(j,bc[j][3]));
            }
            
            // B 사용자가 사용가능한 bc 선택
            PriorityQueue<Info> pqb = new PriorityQueue<>();
            for(int j=0; j<A; j++){
                // bc와의 거리 구하기
                int dist = Math.abs(bc[j][0] - bpos[0]) + Math.abs(bc[j][1] - bpos[1]);
                // 거리가 range 이하이면 queue에 넣기
                if(dist <= bc[j][2])
                    pqb.offer(new Info(j, bc[j][3]));
            }

            // 둘다 가능한게 있으면 겹치는게 있는지 확인
            if(pqa.size() != 0 && pqb.size() != 0){
                Info infoa = pqa.peek();
                Info infob = pqb.peek();
    
                // 가능한 bc 중에 가장 큰 것을 각각 선택했을 때 겹치지 않는다면 그걸로 결정!
                if(infoa.idx != infob.idx){
                    sum += infoa.charge;
                    sum += infob.charge;
                }
                else{
                    // 둘다 그거 하나 밖에 없다면 나눠씀
                    // 겹친다면 다른 가능한게 있는 사람은 다른걸 선택
                    if(pqa.size() == 1 && pqb.size() == 1){
                        sum += infoa.charge;
                    }
                    else if(pqa.size() != 1 && pqb.size() != 1){
                        // 서로 다른 BC의 charge 값이 같을 수 있으므로 check
                        // 100 100
                        // 100 50
                        // 이 경우 B가 공통 BC를 이용해야 함
                        int a1 = pqa.poll().charge;
                        int a2 = pqa.poll().charge;

                        int b1 = pqb.poll().charge;
                        int b2 = pqb.poll().charge;

                        if(a1 + b2 <= a2 + b1)
                            sum += a2 + b1;
                        else
                            sum += a1 + b2;
                    }
                    else if(pqa.size() != 1){
                        // 두번째 BC를 이용하기 위해 맨 앞의 BC를 큐에서 뺌
                        pqa.poll();
                        sum += pqa.poll().charge + pqb.poll().charge;
                    }
                    else if(pqb.size() != 1){
                        pqb.poll();
                        sum += pqa.poll().charge + pqb.poll().charge;
                    }
                }
            }
            else if(pqa.size() != 0)
                sum += pqa.poll().charge;
            else if(pqb.size() != 0)
                sum += pqb.poll().charge;
        }

        return sum;
    }
}