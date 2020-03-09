# sw expert academy 1221번 GNS 자바(java)  풀이
- 난이도 : D3
- [sw expert academy 1221번 GNS](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14jJh6ACYCFAYD)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 0~9의 값이 다음과 같다
    ZRO, ONE, TWO, THR, FOR, FIV, SIX, SVN, EGT, NIN
2. 문자열이 주어질때 오름차순 정렬하여 출력하여라

# 문제풀이
처음에는 split을 이용하여 String 배열에 담아두고 처리하였는데 배열의 인덱스를 넘어가서 StringTokenizer를 이용하였습니다.  
이 문제는 ArrayList의 indexOf 함수와 Collections.sort 함수를 알면 쉽게 풀 수 있습니다.
0. 0~9에 해당하는 문자를 ArrayList에 담습니다.
1. 문자열을 StringTokenizer를 이용하여 parsing합니다.
2. 문자열을 ArrayList에 담을때 0에서 생성한 ArrayList에 문자열이 어느 인덱스에 있는지 참조하여(indexOf()) 그 인덱스를 새로운 ArrayList에 넣습니다.
3. Collections.sort를 이용하여 오름차순 정렬합니다.
4. 정렬한 list에서 하나씩 꺼내서 number list의 index로 사용하여 문자를 하나씩 출력합니다.