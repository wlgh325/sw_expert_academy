import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashSet;

class Solution {
    static int n, m;
    static int[] parent;    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // 각 사람의 부모의 정보를 담는 parent 배열 초기화
            parent = new int[n];
            for(int i=0; i<n; i++)
                parent[i] = i;

            // disjoint set 만들기
            for(int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a-1,b-1);
            }

            // 중복 되지 않는 부모의 개수를 구한다 => disjoint set의 개수
            HashSet<Integer> set = new HashSet<>();
            for(int i=0; i<n; i++){
                set.add(findParent(i));
            }

            System.out.println("#" + tc + " " + set.size());
        }
    }

    private static int findParent(int x){
        if(parent[x] == x) return x;
        else
            return parent[x] = findParent(parent[x]);
    }

    private static void union(int x, int y){
        x = findParent(x);
        y = findParent(y);

        // 부모가 같지 않으면 합친다
        if(x != y)
            parent[y] = x;
    }
}