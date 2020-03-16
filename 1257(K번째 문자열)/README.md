# sw expert academy 1257번 K번째 문자열 자바(java)  풀이
- 난이도 : D6
- [sw expert academy 1257번 K번째 문자열](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18KWf6ItECFAZN)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 문자열의 부분 문자열을 구해서 사전 순서로 정렬한다.
2. 사전 순서대로 나열했을때 K번째에 오는 문자열을 구하여라.
3. 문자열의 최대 길이는 400이다.
4. k번째 문자열을 찾을 수 없다면 none을 출력해야 한다.

# 문제풀이
1. naive하게 모든 문자열 구해서 오름차순 정렬하기
    부분 문자열을 모두 set에 넣고(중복 방지) 배열로 변환합니다.
    배열의 k-1번째 수를 출력합니다.
2. trie 이용하기
    부분 문자열을 모두 trie에 넣고 sorting하여 k번째 문자열을 뽑습니다.
이 문제는 1번의 방법으로 풀어도 금방 풀 수 있으며 오히려 1번이 빠릅니다.  
왜냐하면 트라이당 쿼리를 한번 씩만 던집니다. 그렇기 때문에 그냥 naive하게 가는게 더 빠릅니다.  
만약 트라이를 만들고 수 많은 쿼리를 받아서 처리해야 한다면 trie가 더 빠를 것입니다.