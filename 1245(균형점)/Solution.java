import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

class Solution {	
    static int n;
    static double[] pos, weight;    
    static final int G = 1;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testNum = Integer.parseInt(br.readLine());
        
        for(int test=1; test<=testNum; test++){
            n = Integer.parseInt(br.readLine());
            pos = new double[n];
            weight = new double[n];

            // x좌표
            String[] temp = br.readLine().split(" ");
            for(int i=0; i<n; i++){
                pos[i] = Double.parseDouble(temp[i]);
            }
            
            // 무게
            for(int i=n; i<n*2; i++){
                weight[i-n] = Double.parseDouble(temp[i]);
            }
            
            System.out.print("#" + test + " ");
            // n-1개의 균형점 찾기
            // solve(idx, depth, mid, left, right)
            for(int i=0; i<n-1; i++) {
            	System.out.print(String.format("%.10f ", solve(i, 0, (pos[i] + pos[i+1])/2, pos[i], pos[i+1])));
            }
            
            System.out.println();
        }
              
        bw.flush();
        bw.close();
		br.close();
    }
	
	public static double solve(int idx, int depth, double mid, double left, double right) {
		if( depth == 100) return mid;
		double f = 0.0;
		double result = 0.0;
		
		f += calLeft(idx,mid);
		f -= calRight(idx, mid);
        
		// 오른쪽의 힘이 더 크므로 왼쪽으로 이동
        if(f < 0) {
        	result = solve(idx, depth+1, (left + mid)/2.0, left, mid);
        }
        // 오른쪽으로 더 이동
        else if ( f > 0) {
        	result = solve(idx,depth+1, (mid + right)/2.0, mid, right);
        }
        else {
        	result = mid;
        }
        return result;
	}
	
	public static double calLeft(int i, double t) {
    	double left = 0.0;
    	for(int j=0; j<i+1; j++) {
    		left += calForce(weight[j],1,t-pos[j]);
    	}
    	return left;
    }
	
    public static double calRight(int i, double t) {
    	double right = 0.0;
    	for(int j=i+1; j<n; j++) {
    		right += calForce(1,weight[j], pos[j] - t);
    	}
    	return right;
    }
    
    public static double calForce(double m1, double m2, double d){
        return G*m1*m2/(d*d);
    }
}