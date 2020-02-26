import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Solution {
    static int[] fee;
    static int[] months;
    static int min;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
        
        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            fee = new int[4];
            // 이용권 요금
            for(int i=0; i<temp.length; i++)
                fee[i] = Integer.parseInt(temp[i]);
            
            // 월별 이용 계획
            months = new int[12];
            temp = br.readLine().split(" ");
            for(int i=0; i<temp.length; i++)
                months[i] = Integer.parseInt(temp[i]);
            
            min = Integer.MAX_VALUE;
            dfs(0, 0);  // 완전 탐색
            min = min > fee[3] ? fee[3] : min;  // 1년 이용권 사용금액과 비교
            System.out.println("#" + test + " " + min);
        }
		br.close();
    }

    public static void dfs(int cnt, int sum){
        if(cnt >= 12){
            min = min > sum ? sum : min;
            return;
        }

        // 이용일 수 0인 달은 비용을 더하지 않는다
        if(months[cnt] == 0){
            dfs(cnt+1, sum);
        }
        else{
            dfs(cnt+1, sum + (months[cnt]*fee[0])); // 하루 이용권 사용
            dfs(cnt+1, sum + fee[1]);   // 한달 이용권 사용
            dfs(cnt+3, sum + fee[2]);   // 세달 이용권 사용
        }
    }
}