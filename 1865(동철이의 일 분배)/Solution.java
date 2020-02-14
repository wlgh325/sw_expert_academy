import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

class Solution {	
    static double[][] arr;
    static int n;
    static double max;
    static int[] cols;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testNum = Integer.parseInt(br.readLine());

        for(int test=1; test<=testNum; test++){
            // init for test case
            n = Integer.parseInt(br.readLine());
            arr = new double[n+1][n+1];
            cols = new int[n+1];
            max = 0.0;

            // 입력받기
            // arr[j][k] = j번 사람이 k번째 일을 성공할 확률 (%)
            for(int j=1; j<=n; j++){
                // j: N개의 testcase
                String[] temp = br.readLine().split(" ");
                // k : N개의 수
                for(int k=1; k<=n; k++){
                    arr[j][k] = Double.parseDouble(temp[k-1]) / 100.0;
                }
            }

            comb(1, 1.0);
            max *= 100;
            bw.write("#" + test + " " + String.format("%.6f", max));
            bw.newLine();
        }

        bw.flush();
        bw.close();
		br.close();
    }

    public static void comb(int row, double percent){
        if(row == n+1){
            max = max < percent ? percent : max;
            return;
        }

        // col배열이 0으로 초기화해서 col 인덱스를 1~N까지
        for(int col=1; col<=n; col++){
            if(isValid(col)){
                if(percent * arr[row][col] == 0)
                    continue;
                if(percent * arr[row][col] < max)
                    continue;
                cols[row] = col;
                comb(row+1, percent* arr[row][col]);
                cols[row] = 0;
            }
        }
    }

    public static boolean isValid(int y){
        for(int i=1; i<=n; i++){
            if(cols[i] == y)
                return false;
        }
        return true;
    }
}