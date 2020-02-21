import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.io.IOException;

class Solution {	
    static int vertexNum, edgeNum;
    static int a, b;
    static ArrayList<ArrayList<Integer>> tree;
    static boolean[] visited;
    static ArrayList<Integer> left;
    static ArrayList<Integer> right;
    static boolean flag, flag2;
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
            
            tree = new ArrayList<ArrayList<Integer>>();
            for(int i=0; i<vertexNum+1; i++)
            	tree.add(new ArrayList<Integer>());

            String[] temp2 = br.readLine().split(" ");
            for(int i=0; i<edgeNum*2-1; i+=2){
                int a = Integer.parseInt(temp2[i]);
                int b = Integer.parseInt(temp2[i+1]);
                tree.get(a).add(b);
            }
            flag = false;
            flag2 = false;

            // 두 노드 찾기
            visited = new boolean[vertexNum+1];
            ArrayList<Integer> list = new ArrayList<>();
            dfs(1, a, b, list);

            // 공통 조상 찾기
            int same = 1;
            for(int i=0; i<left.size(); i++){
                for(int j=0; j<right.size(); j++){
                    if(left.get(i) == right.get(j)){
                        same = left.get(i);
                        break;
                    }
                }
            }

            // 공통조상의 자식 개수 찾기
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
            visited[t] = true;
            for(int i=0; i<tree.get(t).size(); i++) {
                // 연결된 점 방문
                if(!visited[tree.get(t).get(i)]){
                    stack.add(tree.get(t).get(i));
                    ans++;
                }
            }
        }
        // 출발점 추가
        return ans+1;
    }

    static void dfs(int k, int find, int find2, ArrayList<Integer> list){
        if(k==find){
        	left = deepCopy(list);
        	flag = true;
            return;
        }
        else if(k==find2) {
            right = deepCopy(list);
            
            flag2 = true;
        	return;
        }
        for(int i=0; i<tree.get(k).size(); i++){
        	int next = tree.get(k).get(i);
            if(!visited[next]){
                ArrayList<Integer> temp = deepCopy(list);
                temp.add(next);
                dfs(next, find, find2, temp);
                if(flag && flag2)
                    return;
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