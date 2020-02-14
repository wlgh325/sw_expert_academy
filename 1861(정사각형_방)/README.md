# sw expert academy 1861 정사각형 방 자바(java)  풀이
- 난이도 : D4
- sw expert academy 1861 정사각형 방 문제 자바 풀이
- [sw expert academy 1861 정사각형 방 분배](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LtJYKDzsDFAXc)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. N<sup>2</sup>의 방이 존재 (최대 1000x1000 방이 존재)
2. i번째 줄 왼쪽에서 j번째 방에는 A<sub>i,j</sub>가 적혀있다. (방 마다 모두 다르다)
3. 상하좌우로 방을 이동할 수 있다. ( 현재 방에 적힌 숫자보다 1 큰 경우만)
* **어떤 방에서 출발**해야 가장 많이 이동할 수 있는지, 그렇다면 얼마만큼 이동 가능한지 출력!

# 문제풀이
* 이동할 수 있는 방의 개수가 최대인 방이 여러개 있다면, **적힌 수가 가장 작은 것** 출력
1. dfs를 이용한다. 모든 점(i,j)에서 dfs 탐색을 한다.
    * visited는 i,j에 쓰여있는 숫자인 방번호로 방문했는지 안했는지 1차원 배열로 표현하여 관리한다.
    * visited[9]=true라면 방번호가 9인 방을 이미 방문함
2. max값이 더 크다면 max 값과 start값을 모두 update해주고, 같다면 시작점의 수를 비교해서 더 작다면 start를 update한다.