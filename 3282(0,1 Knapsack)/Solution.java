import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  
class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int test=1; test<=testNum; test++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
             
            int[][] object = new int[n+1][2];
            int[][] dp = new int[n+1][k+1];
            for(int i=1; i<=n; i++){
                st = new StringTokenizer(br.readLine(), " ");
                object[i][0] = Integer.parseInt(st.nextToken());    // 물건의 부피
                object[i][1] = Integer.parseInt(st.nextToken());    // 물건의 가치
            }
 
            // 아무것도 넣지 않은 경우
            for(int i=0; i<=k; i++){
                dp[0][i] = 0;
            }
 
            // 첫 번째 물건 부터 물건 하나씩 넣어봄
            for(int i=1; i<=n; i++){
                // 가방 무게에 따라서 해봄
                for(int j=0; j<=k; j++){
                    // 가방에 넣을 수 없는 경우
                    if(object[i][0] > j)
                        dp[i][j] = dp[i-1][j];
                    else
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - object[i][0]] + object[i][1]);
                }
            }
            System.out.println("#" + test + " " + dp[n][k]);
        }
    }
}