# sw expert academy 1242 암호코드 스캔 자바(java)  풀이
- 난이도 : D5
- sw expert academy 1242 암호코드 스캔 문제 자바 풀이
- [sw expert academy 1242 암호코드 스캔](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15JEKKAM8CFAYD)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 암호는 총 8개의 숫자로 이루어져있다.
2. 앞 7자리는 상품 고유의 번호를, 마지막 자리는 검증코드를 나타낸다.
3. 검증코드 계산은 다음과 같이 한다.
    - (홀수 자리의 합 * 3 ) + 짝수 자리의 합 + 검증 코드는 10의 배수가 되어야 한다.
4. 암호코드 숫자 하나의 길이는 최소7이며 7의 배수로 늘어날 수 있다. (각 숫자는 흰색과 파란색의 넓이 비로 표현된다)
    - 원래 9인 "0001011"의 비는 3:1:1:2이다. 2배로 늘어나게 되면 "00000011001111"이 된다. 비율은 같으므로 이도 9를 표현한다.
    

# 문제풀이