import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Info{
	int x;
	int y;
	int n;
	
	Info(int x, int y, int n){
		this.x = x;
		this.y = y;
		this.n = n;
	}
}

class Solution {
	static int[][] bricks;
	static int N, W, H;
	static int max;
	static int[] selected;
	// 상, 하, 좌, 우
	static int[] xdir = {-1,1,0,0};
	static int[] ydir = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine());
    	
    	for(int tc=1; tc<=T; tc++) {
    		max = Integer.MIN_VALUE;
    		int num=0;
    		String[] temp = br.readLine().split(" ");
    		N = Integer.parseInt(temp[0]);
    		W = Integer.parseInt(temp[1]);
    		H = Integer.parseInt(temp[2]);
    		
    		// 벽돌 정보
    		bricks = new int[H][W];
    		for(int i=0; i<H; i++) {
    			temp = br.readLine().split(" ");
    			for(int j=0; j<W; j++) {
    				bricks[i][j] = Integer.parseInt(temp[j]);
    				if(bricks[i][j] != 0)
    					num++;
    			}
    		}
    		
    		selected = new int[N];
    		// 모든 경우를 따져주어야 한다. 중복 순열
    		perm(0);
    		System.out.println("#" + tc + " " + (num - max));
    	}
    }
    
    public static void perm(int start) {
    	if(start == N) {
    		Queue<Info> q = new LinkedList<>();
    		int[][] copy = new int[H][W];
			int broken=0;
			
			// 벽돌 상태 복사
    		for(int i=0; i<copy.length; i++)
    			System.arraycopy(bricks[i], 0, copy[i], 0, bricks[i].length);
    		
    		for(int j=0; j<N; j++) {
    			for(int i=0; i<H; i++) {
					// 선택한 열에서 맨 위 블록 찾기
    				if(copy[i][selected[j]] != 0) {
						// 연쇄적으로 블록을 부시기 위해 queue에 담음
    					q.offer(new Info(i,selected[j], copy[i][selected[j]]));
    					copy[i][selected[j]] = 0;
    					broken++;
						
						// 연쇄 뿌시기 시작!!
    					while(!q.isEmpty()) {
    						Info item = q.poll();
    						int n = item.n - 1;	// 벽돌에 써있는 수 -1길이 까지 탐색
    						int x = item.x;
    						int y = item.y;
							
    						for(int k=1; k<=n; k++) {
								// 상, 하, 좌, 우 4방향 탐색
    							for(int tt=0; tt<4; tt++) {
    								int dx = x + xdir[tt]*k;
									int dy = y + ydir[tt]*k;
									// 유효한 범위이고 0과 1이 아닌 수의 경우 연쇄 반응하기 위해 queue에 담음
									// 1은 연쇄가 없기 때문에 담지 않아도 됨
    								if(isValidPosition(dx, dy) && copy[dx][dy] != 0) {
    									if(copy[dx][dy] != 1)
    										q.offer(new Info(dx, dy, copy[dx][dy]));
        								copy[dx][dy] = 0;
        								broken++;
    								}
    							}
    						}
    					}
    					
						// 정리
						// 깨진 블록들로 인해 빈 공간을 메꾼다
    					if(bricks[i][selected[j]] != 0 && broken != 1) {
    						for(int t=0; t<W; t++) {
								// 열 단위로 검사하기
								// 0이 아닌 수를 찾아 재정렬을 위해 queue에 담기
    							Queue<Integer> temp = new LinkedList<>();
    							for(int k=H-1; k>=0; k--) {
    								if(copy[k][t] != 0) {
    									temp.offer(copy[k][t]);
    									copy[k][t] = 0;
    								}
    							}
    							
    							// 밑에 줄부터 쌓기
    							int tt = H-1;
    							while(!temp.isEmpty()) {
    								copy[tt--][t] = temp.poll();
    							}
    						}    						
    					}
    					break;
    				}
    			}
    		}
    		
    		max = max < broken ? broken : max;
    		return;
    	}
    	
    	for(int i=0; i<W; i++) {
			selected[start] = i;
			perm(start+1);
    	}
    }
    
    public static boolean isValidPosition(int x, int y) {
    	if(x < 0 || y < 0 || x >= H || y >= W) return false;
    	return true;
    }
}