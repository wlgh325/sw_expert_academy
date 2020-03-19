import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution2 {
    static int n;
    static int[][] adj;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            adj = new int[n][n];  // 거리 저장

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    adj[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int min = Integer.MAX_VALUE;
            // 모든 점에 대해서 구한다.
            for(int i=0; i<n; i++){
                int sum = djikstra(i);
                min = min > sum ? sum : min;
            }
            System.out.println("#" + tc + " " + min);
        }
    }

    private static int djikstra(int x){
        boolean[] visited;
        int[] distance;

        visited = new boolean[n];
        distance = new int[n];

        // ditance 초기화
        for(int i=0; i<n; i++)
            distance[i] = Integer.MAX_VALUE;
        // 시작점 초기화
        distance[x] = 0;
        visited[x] = true;

        // 시작점과 연결된 노드 distance 갱신
        for(int i=0; i<n; i++){
            if(adj[x][i] != 0 && !visited[i])
                distance[i] = adj[x][i];
        }

        // 모든 점에 대해서
        for(int a=0; a<n-1; a++){
            int min = Integer.MAX_VALUE;
            int minPos = -1;

            // 방문하지 않은 노드 중 최소 값 찾기
            for(int i=0; i<n; i++){
                if(!visited[i] && distance[i] != Integer.MAX_VALUE){
                    if(distance[i] < min){
                        min = distance[i];
                        minPos = i;
                    }
                }
            }

            visited[minPos] = true;
            // mixX와 연결 되면서 방문하지 않은 노드들 check
            for(int i=0; i<n; i++){
                if(adj[minPos][i] != 0 && !visited[i]){
                    if(distance[i] > distance[minPos] + adj[minPos][i])
                        distance[i] = distance[minPos] + adj[minPos][i];
                }
            }
        }

        int sum = 0;
        for(int i=0; i<n; i++)
            sum += distance[i];
        return sum;
    }
}
