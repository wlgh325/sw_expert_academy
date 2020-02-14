# sw expert academy 1865 동철이의 일 분배 자바(java)  풀이
- 난이도 : D4
- sw expert academy 1865 동철이의 일 분배 문제 자바 풀이
- [sw expert academy 1865 동철이의 일 분배](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LuHfqDz8DFAXc)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. N명의 직원, N개의 일 ( 한 사람당 하나의 일을 맡아서 진행 )
2. 인덱스 : 1~N
3. 주어진 일을 모두 성공할 확률의 최댓값 구하기

# 문제풀이
1. 모든 경우를 계산한다. 가능한 조합을 재귀로 해서 구한다.
    * 이때 cols[row]를 이용하여 각 row에서 선택한 col을 저장해두고 서로 다른 일을 할 수 있도록 한다.
2. 하지만 그대로 모든 경우를 구한다면 시간초과가 뜬다.
3. 그러므로 가지치기를 한다.
    * 곱하는 값은 1보다 작은 소수이므로 곱하면 곱할수록 작아진다. 그러므로 곱해서 커지지 않는다면 계산을 하지 않는다.
    * 그리고 곱해서 0이 나오는 경우 더 커질 수가 없다. 그러므로 이 경우도 가지로 쳐내준다.