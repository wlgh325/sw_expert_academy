import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;

class Solution{	
    static ArrayList<ArrayList<Integer>> list;
    static int n;
    static String cmd;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
        
        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            n = Integer.parseInt(temp[0]);
            cmd = temp[1];
            
            list = initList();
            for(int i=0; i<n; i++){
                String[] temp2 = br.readLine().split(" ");
                for(int j=0; j<n; j++)
                    list.get(i).add(Integer.parseInt(temp2[j]));
                
            }
            
            move();
            bw.write("#" + test);
            bw.newLine();
            print();
        }
        bw.flush();
        bw.close();
		br.close();
    }
    
    public static void move(){
        switch(cmd){
            case "left":
                left(list);
                break;
            case "right":
                right(list);
                break;
            case "up":
                ArrayList<ArrayList<Integer>> reverse = rotateClockwise(list);
                right(reverse);
                list = rotateCounterClockwise(reverse);
                break;
            case "down":
                ArrayList<ArrayList<Integer>> reverse2 = rotateClockwise(list);
                left(reverse2);
                list = rotateCounterClockwise(reverse2);
        }
    }

    public static void left(ArrayList<ArrayList<Integer>> list){
        // 왼쪽에서 부터 확인
        for(int i=0; i<n; i++){
            for(int k=0; k<list.get(i).size()-1; k++)
                if(list.get(i).get(k) == 0){
                    list.get(i).remove(k);
                    k--;
                }
            int j = 0;
            while(j < list.get(i).size()-1){
                int left = list.get(i).get(j);
                int idx = j+1;
                
                int right = list.get(i).get(idx);

                // 값이 갖다면 합친다.
                if(left == right){
                    list.get(i).set(j, right*2);
                    list.get(i).remove(idx);
                    list.get(i).add(0);
                }
                
                j = idx;
            }

            // 길이가 n이 될때까지 0을 왼쪽에 채움
            while(list.get(i).size() != n)
                list.get(i).add(0);
        }
    }

    public static void right(ArrayList<ArrayList<Integer>> list){
        // 위에부터 확인
        // 오른쪽에서 부터 확인

        
        for(int i=0; i<n; i++){
            // 0을 다 없앰
            for(int k=0; k<=list.get(i).size()-1; k++)
                if(list.get(i).get(k) == 0){
                    list.get(i).remove(k);
                    k--;
                }

            int j = list.get(i).size() - 1;
            while(j > 0){
                int right = list.get(i).get(j);
                int idx = j-1;
                
                int left = list.get(i).get(idx);

                // 값이 갖다면 합친다.
                if(left == right){
                    list.get(i).set(j, right*2);
                    list.get(i).remove(idx);
                    list.get(i).add(0, 0);
                }
                

                j = idx;
            }

            // 길이가 n이 될때까지 0을 왼쪽에 채움
            while(list.get(i).size() != n)
                list.get(i).add(0,0);
        }
    }

    public static ArrayList<ArrayList<Integer>> rotateClockwise(ArrayList<ArrayList<Integer>> list){        
        ArrayList<ArrayList<Integer>> reverse = initList();
        for(int i=0; i<n; i++){
            for(int j=n-1; j>=0; j--){
                reverse.get(i).add(list.get(j).get(i));
            }
        }
        return reverse;
    }

    public static ArrayList<ArrayList<Integer>> rotateCounterClockwise(ArrayList<ArrayList<Integer>> list){        
        ArrayList<ArrayList<Integer>> reverse = initList();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                reverse.get(i).add(list.get(j).get(n-1-i));
            }
        }
        return reverse;
    }

    public static ArrayList<ArrayList<Integer>> initList(){
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        for(int i=0; i<n; i++){
            board.add(new ArrayList<Integer>());
        }
        return board;
    }

    public static void print() throws IOException{
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                bw.write(list.get(i).get(j) + "");
                // 마지막에 띄어쓰기 방지
                if(j != n-1)
                    bw.write(" ");
            }
            bw.newLine();
        }
    }
}