import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

class Solution {
    static int N;
    static Set<Integer> result;
    static int[] num;
    static int[] op;
    static Stack<Integer> select;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
        	N = Integer.parseInt(br.readLine());
        	
        	// initialize
        	result = new HashSet<>();
        	num = new int[N];
        	op = new int[4];
        	
        	// 연산자 입력받기
        	String[] temp = br.readLine().split(" ");
        	for(int i=0; i<4; i++) {
        		op[i] = Integer.parseInt(temp[i]);
        	}
        	
        	// 숫자 입력받기
        	temp = br.readLine().split(" ");
        	for(int i=0; i<N; i++) {
        		num[i] = Integer.parseInt(temp[i]);
        	}
        	
        	select = new Stack<>();
        	dfs(0);
        	
        	int min = Collections.min(result);
        	int max = Collections.max(result);
        	System.out.println("#" + test + " " + (max-min));
        }
        			  
		br.close();
    }
	
	public static void dfs(int k) {
		if(k == N-1) {
			result.add(solve());
		}
		
		for(int i=0; i<4; i++) {
			if(op[i] > 0) {
				select.push(i);
				op[i] -= 1;
				dfs(k+1);
				op[i] += 1;
				select.pop();
			}
		}

	}
	
	public static int solve() {
		int result = num[0];
		
		for(int i=0; i<select.size(); i++) {
			switch(select.get(i)) {
				case 0:
					result += num[i+1];
					break;
				case 1:
					result -= num[i+1];
					break;
				case 2:
					result *= num[i+1];
					break;
				case 3:
					result /= num[i+1];
					break;
			}
		}
		return result;
	}
}