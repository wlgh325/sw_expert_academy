import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

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
    static int[][] dp;
    static int[][] multiple;
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

            /* ------------------부분 행렬 찾기------------------ */
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(map[i][j] != 0){
                        list.add(search(i,j));
                    }
                }
            }

            /* ------------------행렬 곱 순서 찾기------------------ */
            int size = list.size();
            multiple = new int[size][2];
            findOrder(list);

            dp = new int[size][size];
            for(int i=0; i<size; i++)
                Arrays.fill(dp[i], -1);

            /* ------------------최소 행렬 곱셈 횟수 구하기------------------ */
            System.out.println("#" + test + " " + calMinMult(0,size-1));
        }
    }

    private static int calMinMult(int left, int right){
        if(left == right) return 0;
        if(dp[left][right] != -1) return dp[left][right];
        
        int min = Integer.MAX_VALUE;
        for(int i=left; i<right; i++){
            int leftResult = calMinMult(left, i);
            int rightResult = calMinMult(i+1, right);
            int num = multiple[left][0] * multiple[i][1] * multiple[right][1];
            min = Math.min(leftResult + rightResult + num, min);
        }
        
        return dp[left][right] = min;
    }

    private static void findOrder(ArrayList<Matrix> list){
        int len = list.size();
        int pos = 0;
        boolean flag;

        // 수나사의 숫자가 암나사 쪽에 안나오는 나사 찾기
        for(int i=0; i<len; i++){
            flag = false;
            for(int j=0; j<len; j++){
                if(i == j)
                    continue;
                // 수나사와 암나사가 같은 경우
                if(list.get(i).row == list.get(j).col){
                    flag = true;
                    break;
                }
            }
            if(!flag)
                pos = i;
        }
        multiple[0][0] = list.get(pos).row;
        multiple[0][1] = list.get(pos).col;

        // 암나사의 값을 수나사의 값으로 갖는 나사를 찾아가며 쓴다.
        int cur = 0;
        int cnt = 1;
        while(len > 1){
            if(cur == pos){
                cur++;
                continue;
            }
            
            // 연결된 나사중 제일 끝의 암나사의 값과 연결하지 않은 다른 나사의 수나사의 값이 같은 경우 연결
            if(list.get(pos).col == list.get(cur).row){
                multiple[cnt][0] = list.get(cur).row;
                multiple[cnt++][1] = list.get(cur).col;
                len--;
                pos = cur;
                cur = 0;    // 모든 나사 탐색을 위해 다시 index 0으로 초기화
            }
            else
                cur++;
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
                map[i][j] = 0;
            }
        }
        return new Matrix(row, col);
    }

    private static boolean isValidPosition(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        return true;
    }
}