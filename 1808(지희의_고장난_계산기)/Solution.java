import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Solution {
	static int[] nums;
	static int target;
	static int min;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int testNum = Integer.parseInt(br.readLine());
        for(int test=1; test<=testNum; test++) {
			String[] temp = br.readLine().split(" ");
			nums = new int[10];
			min = Integer.MAX_VALUE;
			for(int i=0; i<10; i++)
				nums[i] = Integer.parseInt(temp[i]);

			target = Integer.parseInt(br.readLine());
			
			// 타겟 숫자를 바로 만들 수 있는 경우
			int b = check(target);
			if(b != 0){
				b += 1;
				System.out.println("#" + test + " " + b);
				continue;
			}

			solve(target, 0);

			// 만들 수 없는 경우는 -1
			// 만들 수 있으면 계산 버튼 추가(+1)
			if(min == Integer.MAX_VALUE)
				min = -1;
			else
				min += 1;
			System.out.println("#" + test + " " + min);	
        }
        			  
		br.close();
	}

	// 2 * 2 * 15
	public static int solve(int target, int depth){
		int result = Integer.MAX_VALUE;
		int b = check(target);
		
		// target 수를 만들 수 있으면 길이 반환
		if(b != 0)
			return b;

		for(int i=2, end=(int)Math.sqrt(target)+1; i< end; i++){
			// 약수이면
			if(target % i == 0){
				int a = check(i);
				// i를 만들 수 있으면
				if(a != 0){
					a = a + 1;	// 곱하기 버튼 추가(+1)
					b = solve(target/i, depth+1);
					// target/i를 만들 수 있는 경우
					if(b != Integer.MAX_VALUE){
						int r = a+b;
						if(r < result)
							result = r;
						// 최소값보다 작고 target이 원하는 수인 경우 min값 업데이트
						if(result < min && target == Solution.target)
							min = result;
					}
				}
			}
		}
		return result;
	}

	public static int check(int num){
		int t = 0;
		int len = 1;	// 길이

		// 10으로 나눠가며 자리수 check
		if(num >= 10){
			while(true){
				t = num % 10;
				num /= 10;
				len++;
				if(nums[t] == 0)
					return 0;
				if(num < 10)
					break;
			}
		}
		
		// 10보다 작은 경부 바로 쓸 수 있는 버튼인지 check
		if(nums[num] == 0) return 0;

		return len;
	}
}