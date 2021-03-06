# sw expert academy 1240 단순 2진 암호코드 자바(java)  풀이
- 난이도 : D3
- sw expert academy 1240 단순 2진 암호코드 문제 자바 풀이
- [sw expert academy 1240 단순 2진 암호코드](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15FZuqAL4CFAYD)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 암호는 총 8개의 숫자로 이루어져있다.
2. 앞 7자리는 상품 고유의 번호를, 마지막 자리는 검증코드를 나타낸다.
3. 검증코드 계산은 다음과 같이 한다.
    - (홀수 자리의 합 * 3 ) + 짝수 자리의 합 + 검증 코드는 10의 배수가 되어야 한다.
    

# 문제풀이
1. 한 줄씩 읽어나가며 그 줄에 1이 하나도 없다면 암호가 없는 줄이므로 continue로 스킵한다.
    * 암호코드가 있는 줄이 여러 줄 반복되므로 한 줄만 읽도록 암호를 한 번 읽었다면 스킵한다.
2. 암호가 7칸을 간격으로 일정하게 있는 것이 아니므로 왼쪽 index부터 하나씩 증가시키며 차례로 탐색한다.
    * 이때 0~9까지의 숫자중에 매치되는 숫자가 있더라도 뒤의 숫자가 암호코드에 매치가 되지 않을 수도 있다. 그전에 인식 된 수는 다른 수로 인식될 수 있다는 뜻이다. 이 경우에는 이전 index로 돌아가 다시 index를 증가시키며 다른 수와 비교하며 탐색해나간다.
3. 8개의 숫자를 모두 읽었다면 while문을 빠져나간다.
4. 인덱스에 따라서 홀수의 합과 짝수의 합을 구하고 문제의 조건에 따라 계산하여 10의 배수가 되는지 확인한다.
5. 10의 배수가 맞다면 홀수+짝수를 출력하고 아니면 0을 출력한다.

## 문제풀이 2번의 예시
테스트케이스 7번을 살펴보자. 한 줄만 띄어서 보면 다음과 같다.
```
0000000000000000000000010111100011010111011001100101110110111011011101100110010000000000000000000000
이때 맨 왼쪽 부터 차례대로 검사하면서 제일 먼저 매칭되는 수는 9인 "0001011"이다.
그러나 그 다음 수인 "1100011"과 매치되는 숫자가 없다. 즉 이 전에 읽은 0001011이 아니라는 뜻이다.
그래서 다시 9를 읽었던 index로 돌아가 다시 탐색한다.
그러면 "0101111"이라는 숫자를 매칭시킬 수 있다. 그 다음 계속 읽어가보면 모두 숫자에 매칭됨을 알 수 있다.
첫 번째 숫자는 9가 아니라 6이었다.
```