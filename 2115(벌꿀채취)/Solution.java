import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.LinkedList;

class Pos{
	// x: 시작위치(행 index)
	// y: 시작위치(열 index)
	// sum: 최대수익
	int x;
	int y;
	int sum;
	
	Pos(int x, int y, int sum){
		this.x = x;
		this.y = y;
		this.sum = sum;
	}
}

class Solution {
	static int N, M, C;
	static int[][] honey;
	static List<Pos> list;
	static boolean[] visited;
	static int max;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
			String[] temp = br.readLine().split(" ");
			N = Integer.parseInt(temp[0]);
			M = Integer.parseInt(temp[1]);
			C = Integer.parseInt(temp[2]);

			honey = new int[N][N];
			for(int i=0; i<N; i++){
				temp = br.readLine().split(" ");
				for(int j=0; j<N; j++){
					honey[i][j] = Integer.parseInt(temp[j]);
				}
			}

			max = 0;
			list = new LinkedList<>();

			// 모든 가능한 경우 탐색
			// 가능한 최대 합을 모두 구해놓기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// M개의 벌꿀통을 고를 수 있는지 확인 (가로로 연속해서만 고를 수 있기 때문)
					if(isValid(j))
						getMax(i, j);					
				}
			}
			// 가능한 조합을 가지고 최대값 구하기
			System.out.println("#" + test + " " + solve());
        }
		br.close();
	}
	
	public static void getMax(int x, int y){
		int sum = 0;
		int profit = 0;
		// 시작 위치 (x,y)로 부터 M개의 벌꿀 채취할 경우 벌꿀 양의 합과 수익 구하기
		for(int i=0; i<M; i++) {
			sum += honey[x][y+i];
			profit += honey[x][y+i]*honey[x][y+i];
		}
		
		// M개 모두 골랐을때 벌꿀의 합이 sum보다 작으면 그것이 최대 수익
		if(sum <= C)
			list.add(new Pos(x, y, profit));
		else {
			visited = new boolean[M];
			// 1~M-1개를 고르는 경우 모두 check
			// 최대 수익을 구한다.
			for(int i=1; i<M; i++)
				dfs(x,y,0,i,0,0,0);
			list.add(new Pos(x, y, max));
			max = 0;
		}
	}

	public static boolean isValid(int j){
		if(j + M  > N) return false;
		return true;
	}
	
	public static void dfs(int x, int y, int k, int N, int sum, int profit, int start){
		if(k == N){
			max = max < profit ? profit : max;
			return;
		}

		for(int i=start; i<M; i++){
			if(!visited[i] && sum + honey[x][y+i] <= C){
				visited[i] = true;
				dfs(x, y, k+1, N, sum + honey[x][y+i], profit + honey[x][y+i]*honey[x][y+i], i+1);
				visited[i] = false;
			}
		}
	}
	
	public static int solve() {
		int max = list.get(0).sum;
		
		// 가능한 경우 중 2개를 골라 겹치지 않으면
		// 수익의 합을 구해 max profit을 update 해준다.
		for(int i=0; i<list.size(); i++) {

			int sum = list.get(i).sum;
			int x = list.get(i).x;
			int y = list.get(i).y;
			
			for(int j=i+1; j<list.size(); j++) {
				int x2 = list.get(j).x;
				int y2 = list.get(j).y;
				// 같은 꿀통을 딸 수 없음. 겹치면 안됨
				if(x == x2 && y+M > y2)
					continue;
				
				int sum2 = list.get(j).sum;
				int tot = sum + sum2;
				max = max < tot ? tot : max;
			}
		}
		return max;
	}
}