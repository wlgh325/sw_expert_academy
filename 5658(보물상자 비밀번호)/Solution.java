import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
         
        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            TreeSet<Integer> set = new TreeSet<>();
            Deque<Character> dq = new LinkedList<>();
            N = Integer.parseInt(temp[0]);
            K = Integer.parseInt(temp[1]);

            String str = br.readLine();
            for(int i=0; i<N; i++)
                dq.offer(str.charAt(i));
            
            // N/4번 회전
            for(int i=0; i<(N/4); i++){
                // deque 복사
                Deque<Character> copy = new LinkedList<>();
                copy.addAll(dq);

                // 회전
                for(int d=0; d<i+1; d++){
                    copy.addFirst(copy.pollLast());
                }

                // 변 마다 숫자 추출
                for(int j=0; j<4; j++){
                    String hex = "";
                    for(int k=0; k<(N/4); k++){
                        hex += copy.poll();
                    }
                    set.add(hexToDecimal(hex));
                }
            }
            // 내림차순 정렬
            Iterator<Integer> iter = set.descendingIterator();
            int idx = 1;
            
            // K번째 수 뽑기
            while(true){
                int num = iter.next();
                if(idx == K){
                    System.out.println("#" + test + " " + num);
                    break;
                }
                idx++;
            }
        }
 
        br.close();
    }

    public static int hexToDecimal(String hex){
        int dec = 0;
        int po = N/4 - 1;   // 16의 몇승?

        for(int i=0; i<N/4; i++){
            char c = hex.charAt(i);
            // 48 ~ 57 : 0 ~ 9
            if(c >= 48 && c <= 57)
                dec += Math.pow(16, (po-i))*(c-48);
            else
                dec += Math.pow(16, (po-i))*(c-55);  // 65 ~ 70 : A ~ F
        }

        return dec;
    }
}