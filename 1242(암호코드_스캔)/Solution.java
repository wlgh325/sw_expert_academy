import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Main {	
    static int width, height;
    static int[] arr;
    static final int[][] num = {{3,2,1,1},{2,2,2,1},{2,1,2,2},{1,4,1,1},{1,1,3,2},{1,2,3,1},{1,1,1,4},{1,3,1,2},{1,2,1,3},{3,1,1,2}};
    static final String[] two = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    static int sum;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testNum = Integer.parseInt(br.readLine());

        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            height = Integer.parseInt(temp[0]);
            width = Integer.parseInt(temp[1]);
            sum = 0;
            
            bw.write("#" + test + " " + sum);
            bw.newLine();
        }

        bw.flush();
        bw.close();
		br.close();
    }
}
