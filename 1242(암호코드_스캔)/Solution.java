import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Solution {
    static char[][] map;
    static int width, height;
    static int[][][] num;
    static int ans;
    
    static int hcode[][] = {
        { 0, 0, 0, 0 }, //0
        { 0, 0, 0, 1 }, //1
        { 0, 0, 1, 0 }, //2
        { 0, 0, 1, 1 }, //3
        { 0, 1, 0, 0 }, //4
        { 0, 1, 0, 1 }, //5
        { 0, 1, 1, 0 }, //6
        { 0, 1, 1, 1 }, //7
        { 1, 0, 0, 0 }, //8
        { 1, 0, 0, 1 }, //9
        { 1, 0, 1, 0 }, //A
        { 1, 0, 1, 1 }, //B
        { 1, 1, 0, 0 }, //C
        { 1, 1, 0, 1 }, //D
        { 1, 1, 1, 0 }, //E
        { 1, 1, 1, 1 } //F
    };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());

        for(int test=1; test<=testNum; test++){
            String[] temp = br.readLine().split(" ");
            height = Integer.parseInt(temp[0]);
            width = Integer.parseInt(temp[1]);
            
            num = new int[4][3][4];
            map = new char[height][width*4];
            initCode();

            int codeLen = 7;
            int[] code = new int[8];
            ans = 0;
            
            // 암호
            String before = "";
            for(int i=0; i<height; i++){
            	String input = br.readLine();
                
                // 첫 줄 or 이전 줄과 똑같을 경우 탐색 X
                if(input.equals(before) || i == 0) {
                	before = input;
                	continue;
                }
                
                // map 만들기
                String[] temp2 = input.split("");
                makeMap(before.split(""), i-1);
                makeMap(temp2, i);
                
                // 암호코드 탐색
                for(int j=width*4-1; j>0; j--){
                    // 바코드의 우상단인 경우
                	if(map[i][j] != '\0' && map[i-1][j] == '\0'){
	                    int x,y,z;
	                    x=y=z=0;
	                    // x, y, z
	                    // 1/0/1
	
	                    // 1의 개수
	                    while(map[i][j] != '\0'){
	                        z++;
	                        j--;
	                    }
	                    // 0의 개수
	                    while(map[i][j] == '\0'){
	                        y++;
	                        j--;
	                    }
	                    // 1의 개수
	                    while(map[i][j] != '\0'){
	                        x++;
	                        j--;
	                    }
	                    // 0의 개수
	                    while(map[i][j] == '\0'){
                            j--;
                            // 맨 왼쪽은 쭉 null 문자만 나오므로 인덱스가 0에 도달하게 되면 탈출
	                        if(j < 0)
	                            break;
	                    }
	                    j++;
                        
                        // 제일 작은 값 찾아서 나누기
	                    int min = x;
	                    if(min > y)
	                        min = y;
	                    if(min > z)
	                        min = z;
	                    x /= min;
	                    y /= min;
	                    z /= min;
    
                        // 찾은 숫자 쓰기
	                    code[codeLen--] = num[x-1][y-1][z-1];
	                    
	                    // 7개를 모두 찾음
	                    if(codeLen == -1){
	                        // 검증 코드 구하기
	                        int odd = 0;
	                        int even = 0;
	                        
	                        for(int t=0; t<code.length; t++){
	                            if(t % 2 == 0)
	                                even += code[t];
	                            else
	                                odd += code[t];
	                        }
	                        
	                        int verificationCode = even*3 + odd;
	                        if(verificationCode % 10 == 0)
	                            ans += odd + even;
	                        codeLen = 7;
	                    }
                	}
                }
                before = input;
            }
            
            System.out.println("#" + test + " " + ans);
        }
		br.close();
    }

    public static void initCode(){
        for(int i=0; i<4; i++)
            for(int j=0; j<3; j++)
                for(int k=0; k<4; k++)
                    num[i][j][k] = -1;
        num[1][0][0] = 0;
        num[1][1][0] = 1;
        num[0][1][1] = 2;
        num[3][0][0] = 3;
        num[0][2][1] = 4;
        num[1][2][0] = 5;
        num[0][0][3] = 6;
        num[2][0][1] = 7;
        num[1][0][2] = 8;
        num[0][0][1] = 9;
    }

    public static int hexToDec(char ch){
        int num = 0;
        switch(ch){
            case 'A':
                num = 10;
                break;
            case 'B':
                num = 11;
                break;
            case 'C':
                num = 12;
                break;
            case 'D':
                num = 13;
                break;
            case 'E':
                num = 14;
                break;
            case 'F':
                num = 15;
                break;
            default:
                num = Character.getNumericValue(ch);
        }
        return num;
    }
    
    public static void makeMap(String[] temp2, int w) {
        for(int q=0; q<width; q++){
            int t = hexToDec(temp2[q].charAt(0));
            
            for(int k=0; k<4; k++)
                map[w][q*4 + k] = (char)hcode[t][k];
        }
    }
}
