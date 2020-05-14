import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Pos{
	int x;
	int y;
	
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Solution2 {
	static int max;
	static int N, K;
	static int[][] map;
	static ArrayList<Pos> start;

	// 상, 하, 좌, 우
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
	static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
        	String[] temp = br.readLine().split(" ");
        	N = Integer.parseInt(temp[0]);
        	K = Integer.parseInt(temp[1]);
        	
        	map = new int[N][N];
        	start = new ArrayList<>();
        	
        	int maxHeight = 0;
        	for(int i=0; i<N; i++) {
        		temp = br.readLine().split(" ");
        		for(int j=0; j<N; j++) {
        			map[i][j] = Integer.parseInt(temp[j]);
        			maxHeight = maxHeight < map[i][j] ? map[i][j] : maxHeight;
        		}
        	}
        	
        	// 시작점 찾기
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<N; j++) {
        			if(map[i][j] == maxHeight)
        				start.add(new Pos(i,j));
        		}
        	}
        	
        	max = 0;
        	visited = new boolean[N][N];
        	for(int i=0; i<start.size(); i++) {
        		Pos p = start.get(i);
        		visited[p.x][p.y] = true; 
        		dfs(p.x, p.y, 1, false);
        		visited[p.x][p.y] = false;
        	}
        	System.out.println("#" + tc + " " + max);
        }
    }
    
    public static void dfs(int x, int y, int cnt, boolean isCut) {
    	int check = 0;
    	
    	for(int i=0; i<4; i++) {
    		int dx = x + xdir[i];
    		int dy = y + ydir[i];
    		
    		if(isValidPosition(dx, dy) && !visited[dx][dy]) {
    			// 아직 방문 안 했지만, 이미 잘랐고 다음 이동지가 더 큰 경우 더 이상 이동X
    			if(isCut && map[x][y] <= map[dx][dy]) {
    				check++;
    			}
    			else if(isCut && map[x][y] > map[dx][dy]) {
    				// 이미 잘랐지만 다음 이동지가 더 작아서 이동가능
    				visited[dx][dy] = true;
    				dfs(dx, dy, cnt+1, true);
    				visited[dx][dy] = false;
    			}
    			else if(!isCut) {
    				// 자르지 않았고 다음에도 또 자르지 않고 이동가능하면 이동
    				if(map[x][y] > map[dx][dy]) {
    					visited[dx][dy] = true;
    					dfs(dx, dy, cnt+1, false);
    					visited[dx][dy] = false;    					
    				}
    				
    				// 자르지 않은 경우 1부터 K까지 잘라서 이동해봄
    				for(int j=1; j<=K; j++) {
        				visited[dx][dy] = true;
        				map[dx][dy] -= j;

        				// 잘라서 이동가능한 경우만 이동
        				if(map[x][y] > map[dx][dy])
        					dfs(dx, dy, cnt+1, true);
        				else
        					check++;
        				map[dx][dy] += j;
        				visited[dx][dy] = false;
        			}
				}
    		}
			else
				check++;
    	}
    	
    	// 4 방향 모두 이동할 수 없으면
    	if(check == 4)
    		max = max < cnt ? cnt : max;
    }
    
    public static boolean isValidPosition(int x, int y) {
    	if(x < 0 || y < 0 || x >= N || y >= N) return false;
    	return true;
    }
}