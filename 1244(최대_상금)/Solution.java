import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.io.IOException;

class Solution {	
    static int num, move;
    static int[] arr;
    static String str = "";
    static int result;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testNum = Integer.parseInt(br.readLine());
        
        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            move = Integer.parseInt(temp[1]);
            
            arr = new int[temp[0].length()];
            String[] temp2 = temp[0].split("");
            

            for(int i=0; i<arr.length; i++){
                int t = Integer.parseInt(temp2[i]);
                arr[i] = t;
            }
            result = 0;
            dfs(0,0);
            bw.write("#" + test + " "+ result);
            bw.newLine();
        }
        bw.flush();
        bw.close();
		br.close();
    }

    public static void dfs(int k, int cnt){
        int t;
        if(cnt == move){
            str = "";
            Arrays.stream(arr).forEach(x -> str += String.valueOf(x));
            result = Math.max(result, Integer.parseInt(str));
            return;
        }
        for(int i=k; i<arr.length; i++){
            for(int j=i+1; j<arr.length; j++){
                if(arr[i] <= arr[j]){
                    t = arr[i]; arr[i] = arr[j]; arr[j] = t;
                    dfs(i, cnt + 1);
                    t = arr[i]; arr[i] = arr[j]; arr[j] = t;

                }
            }
        }
    }
}