# sw expert academy 1808번 지희의 고장난 계산기 자바(java)  풀이
- 난이도 : D4
- [sw expert academy 1808번 지희의 고장난 계산기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4yC3pqCegDFAUx)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 숫자 X를 계산하기 위해 눌러야 하는 최소 버튼 수를 구하고 싶다.
2. i번째 정수는 계산기에서 i-1을 누를 수 있는 버튼의 상태를 나타낸다. (1이면 동작, 0이면 동작하지 않음)
3. 숫자버튼, 곱하기 버튼, 계산버튼이 있다.
4. 타겟 숫자를 만들기 위한 최소 버튼 클릭 수를 출력한다.(만들 수 없는 경우 -1 출력)
5. 가능한 숫자중 같은 숫자를 여러 번 누를 수 있다.
6. 곱하기를 여러번 사용할 수 있다.

# 문제풀이
이 문제는 두 수의 곱으로 이루어지는 것이 아닌 여러 수의 곱으로 답이 나올 수도 있습니다.  
그러므로 타겟 수보다 작은 수들로 나누어 가면서 약수들을 찾아야 합니다.  
만약 만들 수 없는 수라면 더 잘게 나눠서 만들 수 있는 수인지 확인합니다. 이를 재귀로 구현합니다.  
1. 만들 수 있다면 각 수의 길이를 구해 더해서 현 context의 result 보다 작다면 result 값을 갱신합니다.  
2. 그리고 그 result값이 min 값 보다 작고 target값이 입력으로 주어진 target이라면 min 값을 갱신합니다.  
3. 이와 같은 과정을 반복하여 모든 경우를 조사하여 최소값을 찾습니다.  
4. 만약 min 값이 변하지 않고 초기의 값 그대로라면 만들 수 없는 경우이므로 -1을 출력합니다.

# 모든 경우 탐색
버튼을 한 번만 누를 수 있는 것이 아니라 여러번 누를 수 있기 때문에 모든 자리 수를 따져야 합니다.  
예를 들어 2,3 두 수만 누를 수 있다면 두 수의 곱만 따진다면 다음과 같이 가능합니다.  
```
2 * 2, 2*3, 22*2, 22*3, 22*22, 22*23, 22*32, 22*33...
```
구하는 수보다 작은 범위 안에서 모든 경우를 따져보아야 합니다.

# 숫자를 바로 만들 수 있는 경우
이 테스트 케이스를 보고 답이 왜 4인지 이해가 가지 않았습니다.
```
1 1 1 1 1 1 1 1 1 1
128
답: 4
```
이는 '128 ='을 입력하면 바로 128이 나오기 때문에 최소 버튼 클릭 수가 4가 됩니다.  
즉 바로 숫자를 만들 수 있는 경우 이때 최소가 되기 때문에 이를 먼저 check해주어야 합니다.

# 만들 수 있는 숫자인지 판단
가장 기본적인 방법으롤 접근하였습니다. 10으로 나눠가며 나머지를 구해서 자리수 별로 하나씩 구했습니다.  
그렇게 숫자를 하나씩 빼서 그 숫자가 누를 수 있는 숫자인지 검사합니다. 그리고 그와 동시에 나눈 수를 가지고 주어진 숫자의 길이도 구할 수 있습니다.

# 구하는 수의 루트까지
구하는 수보다 작은 수 모두로 나누어 볼 필요는 없습니다. 구하는 수가 t라면 Math.sqrt(t)까지만 따져보면 됩니다.  
10의 약수를 구해보겠습니다.
```
1 2 5 10
```
루트10은 3.xx 정도 됩니다. 즉 2까지만 나누어 봐도 됩니다. 뒤의 수는 반복이기 때문입니다.