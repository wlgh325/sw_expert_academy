import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
class Solution2 {
    static int[][] map;
    static int N, M;
    static int[][] homeposxy;
    static int homecnt;
    static int max;
    static final int operationCosts[] = { 0, 1, 5, 13, 25, 41, 61, 85, 113, 145, 181, 221, 265, 313, 365, 421, 481, 545, 613, 685, 761, 841};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        homeposxy = new int[400][2];
         
        for(int tc=1; tc<=T; tc++) {
        	homecnt = 0;
            max = 0;
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
             
            // 도시 정보
            map = new int[N][N];
            for(int i=0; i<N; i++) {
                temp = br.readLine().split(" ");
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(temp[j]);
                    if(map[i][j] != 0) {
                    	homeposxy[homecnt][0] = i;
                    	homeposxy[homecnt++][1] = j;
                    }
                }
            }
            
            for(int k=N+1; k>0; k--) {
                // 마름모 모양 탐색
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        search(i,j,k);
                    }
                }
 
                if(max != 0)
                	break;
            }
             
            System.out.println("#" + tc + " " + max);
        }
    }
     
    public static void search(int x, int y, int k) {
        int cnt = 0;
        for(int idx=0; idx<homecnt; idx++) {
        	int ii = homeposxy[idx][0];
        	int jj = homeposxy[idx][1];
        	int dist = Math.abs(x-ii) + Math.abs(y-jj);
        	if(dist <= k-1)
        		cnt++;
        }
         
        int cost = cnt*M;
        if(cost >= operationCosts[k])
            max = max < cnt ? cnt : max;
    }
     
    public static boolean isValidPosition(int x, int y) {
        if(x < 0 || y < 0 || x >= N || y >= N) return false;
        return true;
    }
}