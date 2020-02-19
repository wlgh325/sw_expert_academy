import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.io.IOException;

class Main {	
    static int vertexNum, edgeNum;
    static int a, b;
    static int[][] adj;
    static boolean[] visited;
    static ArrayList<Integer> left;
    static ArrayList<Integer> right;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testNum = Integer.parseInt(br.readLine());
        
        for(int test=1; test<=testNum; test++){
            // 입력받기
            String[] temp = br.readLine().split(" ");
            vertexNum = Integer.parseInt(temp[0]);
            edgeNum = Integer.parseInt(temp[1]);
            a = Integer.parseInt(temp[2]);
            b = Integer.parseInt(temp[3]);
            adj = new int[vertexNum+1][vertexNum+1];

            String[] temp2 = br.readLine().split(" ");
            for(int i=0; i<edgeNum*2-1; i+=2){
                int a = Integer.parseInt(temp2[i]);
                int b = Integer.parseInt(temp2[i+1]);
                adj[a][b] = 1;
                adj[a][0]++;
            }

            // 첫번째 노드 찾기
            visited = new boolean[vertexNum+1];
            ArrayList<Integer> list = new ArrayList<>();
            dfs(1, a, b, list);

            int same = 1;
            for(int i=0; i<left.size(); i++){
                for(int j=0; j<right.size(); j++){
                    if(left.get(i) == right.get(j)){
                        same = left.get(i);
                        break;
                    }
                }
            }

            visited = new boolean[vertexNum+1];
            bw.write("#" + test + " " + same + " " + dfs2(same));
            bw.newLine();
        }
              
        bw.flush();
        bw.close();
		br.close();
    }

    static int dfs2(int k){
        Stack<Integer> stack = new Stack<>();
        stack.add(k);

        visited[k] = true;
        
        int ans = 0;
        while(!stack.isEmpty()){
            int t = stack.pop();
            int cnt = 0;
            visited[t] = true;
            for(int i=1; i<=vertexNum; i++){
                // 모두 방문 하였을 경우, 방문할 점이 없는 경우
                if(cnt == adj[t][0])
                    break;

                // 연결된 점 방문
                if(adj[t][i] == 1){
                    cnt++;
                    if(!visited[i]){
                        stack.add(i);
                        ans++;
                    }
                }

            }
        }
        // 출발점 추가
        return ans+1;
    }

    static void dfs(int k, int find, int find2, ArrayList<Integer> list){
        int cnt = 0;
        if(k==find){
        	left = deepCopy(list);
            return;
        }
        else if(k==find2) {
        	right = deepCopy(list);
        	return;
        }
        for(int i=1; i<=vertexNum; i++){
            // 연결된 점이 없으면
            if(adj[k][0] == 0)
                break;
            if(adj[k][i] == 1){
                cnt++;
                if(!visited[i]){
                    ArrayList<Integer> temp = deepCopy(list);
                    temp.add(i);
                    dfs(i, find, find2, temp);
                }
                if(cnt == adj[k][0])
                    break;
            }
        }
    }

    static ArrayList<Integer> deepCopy(ArrayList<Integer> src){
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i=0; i<src.size(); i++){
            temp.add(src.get(i));
        }
        return temp;
    }
}