# sw expert academy 1242번 최적 경로 자바(java)  풀이
- [sw expert academy 1242번 최적 경로](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것을 찾는다.
2. 모든 좌표는 다르게 주어진다.
3. 문제에 쓰여있듯이 모든 경우를 따져주면 된다.

# 문제풀이
0. 좌표를 편하게 입력받기 위해 x,y 좌표를 저장할 Pos 클래스를 선언한다.
1. 입력을 받아 집, 회사 좌표를 따로 저장한다.
2. 고객들의 좌표를 배열에 저장한다.
3. dfs를 통해 고객들의 좌표의 나열 가능한 모든 경우를 구한다(순열)
4. 이동한 거리를 재귀를 통해 넘길때 현재 선택한 집과 이전 집의 거리를 더해서 넘긴다.
5. N개의 집을 모두 선택해 나열했다면 마지막으로 선택한 집과 회사와의 거리를 더해주고 min 보다 작으면 갱신해준다.