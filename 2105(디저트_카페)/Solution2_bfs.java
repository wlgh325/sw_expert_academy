import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Pos{
	int x;
	int y;
	int dir;
	HashSet<Integer> set;
	Pos(int x, int y, int dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.set = new HashSet<>();
	}
	
	Pos(int x, int y, int dir, HashSet<Integer> set){
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.set = set;
	}
}

class Solution2_bfs {

	/*
	 * 대각선 방향으로만 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 한다
	 * 또한, 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안된다
	 * 하나의 카페에서 디저트를 먹는 것도 안된다
	 * 왔던 길을 다시 돌아가도 안된다
	 * 디저트를 최대한 많이 먹으려고 한다
	 * 디저트를 먹을 수 없는 경우 -1
	 * 디저트의 종류를 나타내는 수는 1이상 100이하
	 * */
	static int max;
	static int N;
	static int[][] map;
	
	// 좌상, 우상, 좌하, 우하
	static int[] xdir = {-1,-1,1,1};
	static int[] ydir = {-1,1,-1,1};
	static ArrayList<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	
        	map = new int[N][N];
        	for(int i=0; i<N; i++) {
        		String[] temp = br.readLine().split(" ");
        		for(int j=0; j<N; j++) {        			
        			map[i][j] = Integer.parseInt(temp[j]);
        		}
        	}
        	
        	init();
        	max = 0;
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<N; j++) {
        			bfs(i,j);
        		}
        	}
        	
        	if(max != 0)
        		System.out.println("#" + tc + " " + max);
        	else
        		System.out.println("#" + tc + " " + -1);
        }
    }
    
    public static void bfs(int a, int b) {
    	Queue<Pos> q = new LinkedList<>();

    	//처음엔 무조건 우하로만 이동
    	int dx = a + xdir[3];
    	int dy = b + ydir[3];
    	if(isValidPosition(dx, dy) && map[a][b] != map[dx][dy]) {
    		q.offer(new Pos(dx, dy,3));
    		q.peek().set.add(map[dx][dy]);
    		q.peek().set.add(map[a][b]);
    	}
    	else
    		return;
    	
    	while(!q.isEmpty()) {
    		Pos p = q.poll();
    		int x = p.x;
    		int y = p.y;
    		int dir = p.dir;
    		int[] ddir = list.get(dir);
    		
    		for(int i=0, len=ddir.length; i<len; i++) {
    			dx = x + xdir[ddir[i]];
    			dy = y + ydir[ddir[i]];
    		
        		// 처음 위치로 돌아온 경우
        		if(dx == a && dy == b) {
        			int size = p.set.size();
        			max = max < size ? size : max;
        			continue;
        		}
        		
    			if(isValidPosition(dx, dy)) {
    				// 같은 숫자의 디저트가 아님
    				if(!p.set.contains(map[dx][dy])) {
    					HashSet<Integer> copy = new HashSet<>();
    					copy.addAll(p.set);
    					copy.add(map[dx][dy]);
    					q.offer(new Pos(dx, dy, ddir[i], copy));
    				}
    			}
    		}
    	}
    }
    
    public static void init() {
    	list = new ArrayList<>();
    	// 좌상, 우상, 좌하, 우하 (0,1,2,3)
    	list.add(new int[] {0,1});	// 좌상이동의 경우 다음 가능한 이동 경로: 좌상, 우상
    	list.add(new int[] {1});	// 우상 이동의 경우 다음 가능한 이동 경로: 우상
    	list.add(new int[] {2,0});	// 좌하 이동의 경우 다음 가능한 이동 경로: 좌하,좌상
    	list.add(new int[] {2,3});	// 우하 이동의 경우 다음 가능한 이동 경로: 우하,좌하
    }
    
    public static boolean isValidPosition(int x, int y) {
    	if(x < 0 || y < 0 || x >= N || y >= N) return false;
    	return true;
    }
}