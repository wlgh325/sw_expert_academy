import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
	int x;
	int y;
	int time;
	
	Pos(int x, int y, int time){
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

class Solution {
	static int[][] map;
	static int N, M, L;
	static boolean[][] visited;
	static ArrayList<int[][]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        list = new ArrayList<>();
        list.add(new int[][] {{-1,0},{1,0},{0,-1},{0,1}});	// 1: 상하좌우
        list.add(new int[][] {{-1,0},{1,0}});	// 2: 상, 하
        list.add(new int[][] {{0,-1},{0,1}});	// 3: 좌, 우
        list.add(new int[][] {{-1,0},{0,1}});	// 4: 상, 우
        list.add(new int[][] {{1,0},{0,1}});	// 5: 하, 우
        list.add(new int[][] {{1,0},{0,-1}});	// 6: 하, 좌
        list.add(new int[][] {{-1,0},{0,-1}});	// 7: 상, 좌

        for(int tc=1; tc<=T; tc++) {
        	String[] temp = br.readLine().split(" ");
        	int holex, holey;
        	N = Integer.parseInt(temp[0]);
        	M = Integer.parseInt(temp[1]);
        	holex = Integer.parseInt(temp[2]);
        	holey = Integer.parseInt(temp[3]);
        	L = Integer.parseInt(temp[4]);
        	map = new int[N][M];
        
        	for(int i=0; i<N; i++) {
        		temp = br.readLine().split(" ");
        		for(int j=0; j<M; j++) {
        			map[i][j] = Integer.parseInt(temp[j]);
        		}
        	}
        	
        	visited = new boolean[N][M];
        	int cnt = bfs(holex, holey);
            System.out.println("#" + tc + " " + cnt);
        }
    }
    
    public static int bfs(int a, int b) {
    	Queue<Pos> q = new LinkedList<>();
    	q.offer(new Pos(a,b,1));
    	visited[a][b] = true;
    	
    	int cnt = 1;
    	while(!q.isEmpty()) {
    		Pos p = q.poll();
    		int x = p.x;
    		int y = p.y;
    		int time = p.time;
    		
    		if(time == L)
    			break;
    		
    		int[][] dir = list.get(map[x][y]-1);
    		for(int i=0; i<dir.length; i++) {
    			int dx = x + dir[i][0];
    			int dy = y + dir[i][1];
    			if(isValidPosition(dx, dy) && !visited[dx][dy] && map[dx][dy] != 0) {
    	    		// 0이 아니더라도 이어져 있어야 함
    				if(map[x][y] == 1) {
						if(map[dx][dy] == 2 && (i == 2 || i==3))
							continue;
						else if(map[dx][dy] == 3 && (i==0 || i==1))
							continue;
						else if(map[dx][dy] == 4 && (i==0 || i==3))
							continue;
						else if(map[dx][dy] == 5 && (i==1 || i==3))
							continue;
						else if(map[dx][dy] == 6 && (i==1 || i==2))
							continue;
						else if(map[dx][dy] == 7 && (i==0 || i==2))
							continue;
    				}
    				else if(map[x][y] == 2) {
    					if(map[dx][dy] == 3)
    						continue;
    					else if(map[dx][dy] == 4 && i==0)
    						continue;
    					else if(map[dx][dy] == 5 && i==1)
    						continue;
    					else if(map[dx][dy] == 6 && i==1)
    						continue;
    					else if(map[dx][dy] == 7 && i==0)
    						continue;
    				}
    				else if(map[x][y] == 3) {
    					if(map[dx][dy] == 2)
    						continue;
    					else if(map[dx][dy] == 4 && i==1)
    						continue;
    					else if(map[dx][dy] == 5 && i==1)
    						continue;
    					else if(map[dx][dy] == 6 && i==0)
    						continue;
    					else if(map[dx][dy] == 7 && i==0)
    						continue;
    				}
    				else if(map[x][y] == 4) {
    					if(map[dx][dy] == 2 && i==1)
    						continue;
    					else if(map[dx][dy] == 3 && i==0)
    						continue;
    					else if(map[dx][dy] == 4)
    						continue;
    					else if(map[dx][dy] == 5 && i==1)
    						continue;
    					else if(map[dx][dy] == 7 && i==0)
    						continue;
    				}
    				else if(map[x][y] == 5) {
    					if(map[dx][dy] == 2 && i==1)
    						continue;
    					else if(map[dx][dy] == 3 && i==0)
    						continue;
    					else if(map[dx][dy] == 4 && i==1)
    						continue;
    					else if(map[dx][dy] == 5)
    						continue;
    					else if(map[dx][dy] == 6 && i==0)
    						continue;
    				}
    				else if(map[x][y] == 6) {
    					if(map[dx][dy] == 2 && i==1)
    						continue;
    					else if(map[dx][dy] == 3 && i==0)
    						continue;
    					else if(map[dx][dy] == 5 && i==0)
    						continue;
    					else if(map[dx][dy] == 6)
    						continue;
    					else if(map[dx][dy] == 7 && i==1)
    						continue;
    				}
    				else if(map[x][y] == 7) {
    					if(map[dx][dy] == 2 && i==1)
    						continue;
    					else if(map[dx][dy] == 3 && i==0)
    						continue;
    					else if(map[dx][dy] == 4 && i==0)
    						continue;
    					else if(map[dx][dy] == 6 && i==1)
    						continue;
    					else if(map[dx][dy] == 7)
    						continue;
    				}
    				
    				visited[dx][dy] = true;
					q.offer(new Pos(dx, dy, time+1));
					cnt++;
    			}
    		}
    	}
    	return cnt;
    }
    
    public static boolean isValidPosition(int x, int y) {
    	if(x < 0 || y < 0 || x >= N || y >= M) return false;
    	return true;
    }
}