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
            int n = Integer.parseInt(br.readLine());            
            int[][] screw = new int[n][2];
            st = new StringTokenizer(br.readLine(), " ");
             
            // 나사 정보 입력받기
            for(int i=0; i<n; i++){
                screw[i][0] = Integer.parseInt(st.nextToken());   // 수나사
                screw[i][1] = Integer.parseInt(st.nextToken());   // 암나사
            }
             
            // 수나사의 숫자가 암나사 쪽에 안나오는 나사 찾기
            int pos = 0;
            boolean flag;
            for(int i=0; i<n; i++){
                flag = false;
                for(int j=0; j<n; j++){
                    if(i == j)
                        continue;
                    // 수나사와 암나사가 같은 경우
                    if(screw[i][0] == screw[j][1]){
                        flag = true;
                        break;
                    }
                }
                if(!flag)
                    pos = i;
            }
             
            StringBuilder sb = new StringBuilder();
            sb.append(screw[pos][0] + " " + screw[pos][1] + " ");
            // 암나사의 값을 수나사의 값으로 갖는 나사를 찾아가며 쓴다.
            int cur = 0;
            while(n > 1){
                if(cur == pos){
                    cur++;
                    continue;
                }
                
                // 연결된 나사중 제일 끝의 암나사의 값과 연결하지 않은 다른 나사의 수나사의 값이 같은 경우 연결
                if(screw[pos][1] == screw[cur][0]){
                    sb.append(screw[cur][0] + " " + screw[cur][1] + " ");
                    n--;
                    pos = cur;
                    cur = 0;    // 모든 나사 탐색을 위해 다시 index 0으로 초기화
                }
                else
                    cur++;
            }
            System.out.println("#" + test + " " + sb);
        }
    }
}