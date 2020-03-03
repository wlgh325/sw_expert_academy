import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Pos {
	int x;
	int y;

	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Solution {
	static int width, height;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] aVisited;
	static final int ALPHABET_NUM = 26;
	static int max;
	// 상하좌우
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
			// 섬의 크기
        	String[] temp = br.readLine().split(" ");
        	height = Integer.parseInt(temp[0]);
			width = Integer.parseInt(temp[1]);
			map = new char[height][width];

			// 섬의 명물 정보
			for(int i=0; i<height; i++){
				temp = br.readLine().split("");
				for(int j=0; j<width; j++){
					map[i][j] = temp[j].charAt(0);
				}
			}

			visited = new boolean[height][width];
			aVisited = new boolean[ALPHABET_NUM];
			max = 0;
			visited[0][0] = true;
			aVisited[map[0][0] - 'A'] = true;
			dfs(0,0,1);
			System.out.println("#" + test + " " + max);
        }
        			  
		br.close();
	}
	
	public static void dfs(int x, int y, int k){
		// 상하좌우 탐색
		for(int i=0; i<4; i++){
			int dx = x + xdir[i];
			int dy = y + ydir[i];
			// 유효한 위치 && 방문하지 않은 곳
			if(isValidPosition(dx, dy) && !visited[dx][dy]){
				// 같은 알파벳을 같지 않는 곳
				if(!aVisited[map[dx][dy] - 'A']){
					visited[dx][dy] = true;
					aVisited[map[dx][dy] - 'A'] = true;
					dfs(dx, dy, k+1);
					aVisited[map[dx][dy] - 'A'] = false;
					visited[dx][dy] = false;
				}
			}
		}

		max = max < k ? k : max;
	}

	public static boolean isValidPosition(int x, int y){
		if(x < 0 || x >= height || y < 0 || y >= width) return false;
		return true;
	}
}