import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.io.IOException;
import java.util.Arrays;

class Solution {
    static int D, W, K;
    static int min;
    static int[][] film;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
        
        // 그냥하면 3^D * D * W 로 시간안에 안나옴
        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            D = Integer.parseInt(temp[0]);  // 두께
            W = Integer.parseInt(temp[1]);  // 가로 길이
            K = Integer.parseInt(temp[2]);  // 합격 기준

            min = Integer.MAX_VALUE;
            film = new int[D][W];
            for(int i=0; i<D; i++){
                temp = br.readLine().split(" ");
                for(int j=0; j<W; j++){
                    film[i][j] = Integer.parseInt(temp[j]);
                }
            }

            if(K==1){
                System.out.println("#" + test + " " + 0);
                continue;
            }

            dfs(0,0);

            System.out.println("#" + test + " " + min);
        }
        br.close();
        return;
    }

    public static void dfs(int row, int cnt){
        // 최소값 보다 이미 더 많이 뿌린 경우 탐색 X
        if(min <= cnt)
            return;
        // 마지막 행까지 도달한 경우 성능검사
        if(row == D){
            check(cnt);
            return;
        }

        // 안 뿌림
        dfs(row+1, cnt);

        // 원본 저장
        int[] copy = new int[W];
        System.arraycopy(film[row], 0, copy, 0, W);

        // A 뿌림
        Arrays.fill(film[row], 0);
        dfs(row+1, cnt+1);
        System.arraycopy(copy, 0, film[row], 0, W);

        // B 뿌림
        Arrays.fill(film[row], 1);
        dfs(row+1, cnt+1);
        System.arraycopy(copy, 0, film[row], 0, W);
    }

    public static void check(int cnt){
        // 열 별로 검사
        for(int i=0; i<W; i++){
            int before = film[0][i];
            int len = 1;
            int max = 0;
             
            for(int j=1; j<D; j++){
                if(film[j][i] == before){
                    len++;
                }
                else{
                    max = max < len ? len : max;
                    // 이미 연속된 특성이 K개가 넘은 경우 다음 열 탐색
                    if(max >= K)
                        break;

                    len = 1;
                    before = film[j][i];
                }

                if(len >= K)
                    break;

                // 더 탐색해도 K이상 나올 수 없음
                if(len + (D-j+1) < K)
                    return;
            }

            max = max < len ? len : max;
            // 한 열이라도 연속된 수의 길이가 K 이상이 아니면 return
            if(max < K)
                return;
        }

        min = min > cnt ? cnt : min;
        return;
    }    
}