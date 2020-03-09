import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Solution {
    static ArrayList<String> number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int testNum = Integer.parseInt(br.readLine());
        init();

        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            int length = Integer.parseInt(temp[1]);

            st = new StringTokenizer(br.readLine(), " ");
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0; i<length; i++){
                list.add(number.indexOf(st.nextToken()));
            }
            
            Collections.sort(list);
            System.out.println("#" + test);
            for(int n: list){
                System.out.print(number.get(n) + " "); 
            }
            System.out.println();
        }

		br.close();
    }

    private static void init(){
        number = new ArrayList<>();
        number.add("ZRO"); number.add("ONE"); number.add("TWO"); number.add("THR"); number.add("FOR");
        number.add("FIV"); number.add("SIX"); number.add("SVN"); number.add("EGT"); number.add("NIN");
    }
}