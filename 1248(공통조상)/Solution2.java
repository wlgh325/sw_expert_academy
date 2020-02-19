import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Main {	
    static int vertexNum, edgeNum;
    static int a, b;
    static int[] parent;
    static int[][] child;
    //static ArrayList<ArrayList<Integer>> child;
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
            
            child = new int[vertexNum+1][vertexNum+1];
//            child = new ArrayList<ArrayList<Integer>>();
//            for(int i=0; i<vertexNum; i++) {
//            	child.add(new ArrayList<Integer>());
//            }
            
            parent = new int[vertexNum+1];

            String[] temp2 = br.readLine().split(" ");
            for(int i=0; i<edgeNum*2-1; i+=2){
                int a = Integer.parseInt(temp2[i]);
                int b = Integer.parseInt(temp2[i+1]);
                parent[b] = a;
                
                child[a][b] = 1;
            }
            int a_depth=0, b_depth=0;
            a_depth = get_depth(a);
            b_depth = get_depth(b);

            int same = solve(a, a_depth, b, b_depth);
            int size = getSize(same);
            bw.write("#" + test + " " + same + " " + size);
            bw.newLine();
        }
              
        bw.flush();
        bw.close();
		br.close();
    }

    static int getSize(int same){
        int ret = 1;
        int cnt = 0;
        for(int i=0; i<child[same].length; i++) {
        	if(child[same][i] == 0)
        		cnt++;
        }

        if(cnt == child[same].length)
        	return 1;
        
        for(int i=0; i< child[same].length; i++){
            ret += getSize(child[same][i]);
        }

        return ret;
    }
    static int solve(int a, int a_depth, int b, int b_depth){
        if(a_depth > b_depth){
            while(a_depth != b_depth){
                a_depth--;
                a = parent[a];
            }
        }
        else if(a_depth < b_depth){
            while(a_depth != b_depth){
                b_depth--;
                b = parent[b];
            }
        }

        while(a != b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
    static int get_depth(int n){
       int ret = 0;
       int cur = n;
       while(cur != 1) {
           ret++;
           cur = parent[cur];
       }
       return ret;
    }
}