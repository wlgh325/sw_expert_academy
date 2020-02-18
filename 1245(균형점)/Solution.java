import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.IOException;

class Solution {	
    static int n;
    static int[] pos, weight;    
    static final int G = 1;
    static ArrayList<Double> arrList;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testNum = Integer.parseInt(br.readLine());
        
        for(int test=1; test<=testNum; test++){
            n = Integer.parseInt(br.readLine());
            pos = new int[n];
            weight = new int[n];

            // x좌표
            String[] temp = br.readLine().split(" ");
            int sum = 0;
            for(int i=0; i<n; i++){
                int t = Integer.parseInt(temp[i]);
                pos[i] = t;
                sum += t;
            }
            
            // 무게
            for(int i=n; i<n*2; i++){
                weight[i-n] = Integer.parseInt(temp[i]);
            }
            arrList = new ArrayList<>();
            solve();
            
            bw.write("#" + test + " ");
            for(int i=0; i<arrList.size(); i++){
                bw.write(arrList.get(i) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
		br.close();
    }

    public static void solve(){
        // 점들 사이에 있는 n-1의 경우 모두 다 따져보기
        for(int i=0; i<n-1; i++){
            double result = (Math.sqrt(weight[i]) * (pos[i+1] - pos[i]) ) / (Math.sqrt(weight[i]) + Math.sqrt(weight[i+1]) );
            arrList.add(result + pos[i]);
        }
    }

    public static double calForce(int m1, int m2, int d){
        return G*m1*m2/(d*d);
    }

    public static boolean isPossible(){
        return true;
    }
}