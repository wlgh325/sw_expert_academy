import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
 
class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
            int k = Integer.parseInt(br.readLine());
            String str = br.readLine();
            int len = str.length();
            HashSet<String> set = new HashSet<>();
            for(int i=0; i<len; i++) {
                for(int j=i+1; j<=len; j++) {
                    set.add(str.substring(i, j));
                }
            }
            Object[] arr = set.toArray();
            Arrays.sort(arr);
            if(arr.length < k)
                System.out.println("#" + test + " none");
            else
                System.out.println("#" + test + " " + arr[k-1]);
        }
    }
}
