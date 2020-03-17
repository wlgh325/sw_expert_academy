import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class Pos {
    int x;
    int y;

    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Matrix{
    int row;
    int col;

    Matrix(int row, int col){
        this.row = row;
        this.col = col;
    }
}

class Solution {
    static int[][] map;
    static int n;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int testNum = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int test=1; test<=testNum; test++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            ArrayList<Matrix> list = new ArrayList<>();

            // NxN 행렬
            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(!visited[i][j] && map[i][j] != 0){
                        list.add(search(i,j));
                    }
                }
            }
            // 개수, 행 열, 행 열...
            // 행렬은 행과 열을 곱한 값이 작은 순서대로 출력
            // 크기가 같은 경우 행이 작은 순 출력
            Collections.sort(list, new Comparator<Matrix>() {
                @Override
                public int compare(Matrix a, Matrix b){
                    int aa = a.col*a.row;
                    int bb = b.col*b.row;
                    if(aa > bb)
                        return 1;
                    else if(aa < bb)
                        return -1;
                    else{
                        if(a.row > b.row)
                            return 1;
                        else if(a.row < b.row)
                            return -1;
                        return 0;
                    }
                }
            });
            System.out.print("#" + test + " " + list.size() + " ");
            for(Matrix m : list)
                System.out.print(m.row + " " + m.col + " ");
            System.out.println();
        }
    }

    private static Matrix search(int a, int b){
        // 오른쪽, 아래
        int[][] dir = {{0,1}, {1,0}};        

        int row = 1;
        int col = 1;
        int x = a;
        int y = b;
        // 오른쪽
        while(true){
            int dx = x + dir[0][0];
            int dy = y + dir[0][1];
            if(isValidPosition(dx, dy) && map[dx][dy] != 0){
                col++;
                x = dx;
                y = dy;
            }
            else
                break;
        }
        // 아래
        while(true){
            int dx = x + dir[1][0];
            int dy = y + dir[1][1];
            if(isValidPosition(dx, dy) && map[dx][dy] != 0){
                row++;
                x = dx;
                y = dy;
            }
            else
                break;
        }

        for(int i=a; i<a+row; i++){
            for(int j=b; j<b+col; j++){
                visited[i][j] = true;
            }
        }
        return new Matrix(row, col);
    }

    private static boolean isValidPosition(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        return true;
    }
}
