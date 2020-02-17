import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

class Solution {	
    static int width, height;
    static int[] arr;
    static final String[] num = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testNum = Integer.parseInt(br.readLine());

        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            height = Integer.parseInt(temp[0]);
            width = Integer.parseInt(temp[1]);

            arr = new int[9];
            boolean flag2 = false; 
            for(int k=0; k<height; k++){
                String temp2 = br.readLine();

                // 0만 있는거 제외
                if(!temp2.contains("1"))
                    continue;
                // 한 줄 읽은 뒤는 스킵
                if(flag2)
                    continue;

                int i=0;  
                int idx = 1;
                boolean flag = false;
                boolean cont = false;
                while(true){
                    // 숫자 몇을 나타내나?
                    for(int j=0; j<10; j++){
                        if(num[j].equals(temp2.substring(i, i+7))){
                            arr[idx++] = j;
                            flag = true;
                            break;
                        }
                        if(cont && j == 9){
                            i -= 7;
                            flag = false;
                            cont = false;
                            arr = new int[9];
                            idx = 1;
                        }
                    }
                    
                    if(!flag)
                        i++;
                    else{
                        i += 7;
                        cont = true;
                    }

                    if(idx == 9){
                        flag2 = true;
                        break;
                    }                     
                }
            }

            int odd = 0;
            int even = 0;
            for(int i=1; i<8; i++){
                if(i % 2 != 0)
                    odd += arr[i];
                else
                    even += arr[i];
            }

            boolean result = (odd*3 + even + arr[8]) % 10 == 0 ?  true : false;
            if(result)
                bw.write("#" + test + " " + (odd+even+arr[8]));
            else
                bw.write("#" + test + " 0");
                
            bw.newLine();
        }

        bw.flush();
        bw.close();
		br.close();
    }
}