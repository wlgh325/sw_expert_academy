import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution {
	static int width, height;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] aVisited;

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
			// 섬의 크기
        	String[] temp = br.readLine().split(" ");
        	height = Integer.parseInt(temp[0]);
			width = Integer.parseInt(temp[1]);
			map = new int[height][widht];

			// 섬의 명물 정보
			for(int i=0; i<height; i++){
				temp = br.readLine().split(" ");
				for(int j=0; j<width; j++){
					map[i][j] = Integer.parseInt(temp[j]);
				}
			}

			visited = new boolean[height][width];
			
        }
        			  
		br.close();
    }
}