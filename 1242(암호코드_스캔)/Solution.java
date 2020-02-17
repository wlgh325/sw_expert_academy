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
            
            String beforeCode = "";
            ArrayList<String> codeList = new ArrayList<>();
            ArrayList<String> allCode = new ArrayList<>();
            
            for(int k=0; k<height; k++){
                String temp2 = br.readLine();
                String[] tt = temp2.split("0");
                
                // 0만 있는거 제외
                if(tt.length == 0)
                    continue;
                
                int i=0;
                int before_idx = 0;
                int idx = 1;
                int len = 7;
                boolean isSameCode = false;
                boolean allFlag = false;
                boolean flag = false;
                boolean cont = false;
                String twoRadix = toTwoRadix(temp2);
                arr = new int[9];
                int[] idx_arr = new int[9];
                
                // 이전 줄의 code와 같으면 continue
                if(twoRadix.equals(beforeCode))
                    continue;
                for(int j=0; j<allCode.size(); j++) {
                	if(temp2.equals(allCode.get(j))) {
                		allFlag = true;
                		break;
                	}
                }
                allCode.add(temp2);
                
                if(!allFlag) {
                    while(true){
                        int[] code = check(twoRadix.substring(i, i+len));
                        // 숫자 몇을 나타내나?
                        for(int j=0; j<10; j++){
                            int cnt = 0;
                            
                            if(code.length == 4){
                                for(int l=0; l<4; l++){
                                    if(num[j][l] == code[l])
                                        cnt++;
                                }
                            }
                            
                            // 같으면
                            if(cnt == 4){
                                arr[idx] = j;
                                idx_arr[idx] = i;
                                flag = true;
                                idx++;
                                break;
                            }

                            // 0~9까지의 어느 숫자하고도 매치되지 않음
                            if(cont && j == 9){
                                i = idx_arr[1];
                                flag = false;
                                cont = false;
                                arr = new int[9];
                                idx_arr = new int[9];
                                idx = 1;
                            }
                        }
                        
                        // 매치되는 숫자가 없으면 index를 하나씩 증가
                        if(!flag)
                            i++;
                        // 있다면 코드의 길이만큼 오른쪽으로 이동
                        else{
                            i += len;
                            cont = true;
                        }

                        // 길이 len으로 맞는 숫자가 없을 경우 길이 증가
                        // 처음부터 다시 찾기
                        //System.out.println("i+len: " + (i+len) + ", len: " + len);
                        if(i + len >= twoRadix.length()){
                            len += 7;
                            i = before_idx;
                        }
                        
                        if(len >= twoRadix.length())
                        	break;
                        
                        // 8개의 숫자를 모두 찾았다면 break
                        if(idx == 9) {
                        	String str = twoRadix.substring(idx_arr[1], idx_arr[8] + len);
                            for(int j=0; j<codeList.size(); j++) {
                            	if(codeList.get(j).equals(str)) {
                            		isSameCode = true;
                            		break;
                            	}
                            }
                            if(!isSameCode) {
                            	cal();
                            	codeList.add(str);
                            }
                            
                            if(idx_arr[8] + len < twoRadix.length()) {
                            	if(!twoRadix.substring(idx_arr[8]+len, twoRadix.length()-1).contains("1"))
                            		break;	
                            }
                            i = idx_arr[8] + len;
                            before_idx = i;
                            
                            // initialize
                        	len = 7;
                        	cont = false;
                        	flag = false;
                        	isSameCode = false;
                        	allFlag = false;
                        	arr = new int[9];
                        	idx_arr = new int[9];
                        	idx = 1;
                        }
                    }
                }

                beforeCode = twoRadix;
            }

            bw.write("#" + test + " " + sum);
            bw.newLine();
        }

        bw.flush();
        bw.close();
		br.close();
    }
	public static void cal() {
        int odd = 0;
        int even = 0;
        
        for(int j=1; j<8; j++){
            if(j % 2 != 0)
                odd += arr[j];
            else
                even += arr[j];
        }

        boolean result = (odd*3 + even + arr[8]) % 10 == 0 ?  true : false;
        if(result)
            sum += odd + even + arr[8];
	}
	
    public static boolean isSame(){
        boolean result = true;

        return result;
    }
    public static String toTwoRadix(String str){
        String result = "";
        for(int i=0; i<str.length(); i++){
            String temp = str.substring(i, i+1);
            switch(temp){
                case "A":
                    temp = "10";
                    break;
                case "B":
                    temp = "11";
                    break;
                case "C":
                    temp = "12";
                    break;
                case "D":
                    temp = "13";
                    break;
                case "E":
                    temp = "14";
                    break;
                case "F":
                    temp = "15";
            }
            result += two[Integer.parseInt(temp)];
        }
        return result;
    }

    public static int[] check(String str){
        int one = 0;
        int zero = 0;
        ArrayList<Integer> list = new ArrayList<>();

        boolean flag = false;
        boolean flag2 = false;
        for(int i=0; i<str.length(); i++){
            if(str.substring(i, i+1).equals("0")){
                zero++;
                flag = true;
                if(flag2){
                    list.add(one);
                    flag2 = false;
                    one = 0;
                }
            }
            else{
                one++;
                flag2 = true;
                if(flag){
                    list.add(zero);
                    flag = false;
                    zero = 0;
                }
            }

        }

        if(flag2)
            list.add(one);
        else if(flag)
            list.add(zero);

        ArrayList<Integer> gcd = new ArrayList<>();
        
        // 모든 수에 대하여 최소 공배수를 구해서 나눈다.
        for(int i=0; i<list.size()-1; i++){
            int a = list.get(i);
            int b = list.get(i+1);
            int r = 1;
            
            if(list.get(i) != 0 && list.get(i+1) != 0){
                if(a < b){
                    int temp = a;
                    a = b;
                    b = temp;
                }
        
                while(r>0){
                    r = a % b;
                    a = b;
                    b = r;
                }
                
                gcd.add(a);                
            }
        }
        
        boolean isDivide = true;
        for(int i=0; i<gcd.size(); i++){
            if(gcd.get(i) == 1 || gcd.get(i) == 0){
                isDivide = false;
                break;
            }
        }


        // 최소공배수로 나누기
        if(isDivide && gcd.size() != 0){
            int min = Integer.MAX_VALUE;
            for(int i=0; i<gcd.size(); i++){
                if(min > gcd.get(i))
                    min = gcd.get(i);
            }

            for(int i=0; i<list.size(); i++){
                int num = list.get(i);
                list.set(i, num/min);
            }
        }

        int[] arr = new int[list.size()];
        for(int i=0; i<arr.length; i++)
            arr[i] = list.get(i);

        return arr;
    }
}
