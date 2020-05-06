import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
	static int[][] map;
	static int N, M;
	static int[] operationCosts;
	static int max;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	operationCosts = new int[22];
    	
    	// k에 따른 운영비용 구하기
    	for(int K=1; K<=21; K++)
    		operationCosts[K] = K*K + (K - 1) * (K - 1);
    	
    	for(int tc=1; tc<=T; tc++) {
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
    			}
    		}
            
            // k=1 ~ N+1 까지 탐색
            // N+1까지 탐색해야 NxN의 도시를 모두 덮는 마름모 크기의 경우를 탐색할 수 있다
    		for(int k=1; k<=N+1; k++) {
                // 모든 점 탐색
    			for(int i=0; i<N; i++) {
            		for(int j=0; j<N; j++) {
                        // 마름모 모양 탐색
            			search(i,j,k);
            		}
            	}

    		}
    		
    		System.out.println("#" + tc + " " + max);
    	}
    }
    
    public static void search(int x, int y, int k) {
    	int cnt = 0;
    	// 거리가 k-1인 모든 곳 탐색
    	for(int i=x-k+1; i<x+k; i++) {
    		for(int j=y-k+1; j<y+k; j++) {
    			int dist = Math.abs(x-i) + Math.abs(y-j);
    			// 맨해튼 거리가 k-1 이하인 곳을 확인
    			if(dist <= k-1) {
                    // 유효한 위치이고 집이 있는 경우 cnt 증가
    				if(isValidPosition(i,j) && map[i][j] == 1)
    					cnt++;
    			}
    		}
    	}
        
        // 손해가 아닌 경우 max와 비교해서 update
		int cost = cnt*M;
		if(cost >= operationCosts[k])
			max = max < cnt ? cnt : max;
    }
    
    public static boolean isValidPosition(int x, int y) {
    	if(x < 0 || y < 0 || x >= N || y >= N) return false;
    	return true;
    }
}