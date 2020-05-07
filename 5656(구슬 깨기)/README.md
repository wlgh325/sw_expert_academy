# <span style="color: orange; font-weight:bold; font-size:17pt">sw expert academy 5656 구슬 깨기 자바(java)  풀이</span>
- 모의 SW 역량 테스트
- [sw expert academy 5656 구슬 깨기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. 구슬을 쏘아 벽돌을 깨드리려 한다. 벽돌이 있는 공간의 크기는 WxH이다.
2. 구슬은 좌우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
3. 벽돌은 숫자 1~9로 표현되며, 구슬이 명중한 벽돌은 상하좌우로(벽돌에 적힌 숫자-1)칸 만큼 같이 제거된다.
4. 빈 공간이 있을 경우 벽돌은 밑으로 떨어지게 된다.
5. N개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거하려고 한다. 벽돌을 떨어트린 후 남은 벽돌의 개수를 구하여라.
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
1. 모든 경우를 따져주기 위해서 중복 순열을 구해야 합니다. N이 3인 경우 000 ~ 999 (W=10)까지 모두 시뮬레이션 해봐야 하기 때문입니다.
2. 매번 시뮬레이션 하기 위해 원래 블록 상태를 유지해야 합니다. 그래서 배열을 복사하여 사용합니다.
3. 선택한 열에 맨 위의 블록을 찾아 큐에 넣습니다.
4. 그리고 bfs 방식으로 계속 연쇄적으로 뿌셔 나갑니다. 상하좌우로 1칸씩 늘려가며 써있는 수-1 만큼 탐색해 나갑니다.
5. 벽돌을 모두다 부셨다면 비어있는 공간을 제거하기 위해 아래에서 위로 0이 아닌 수를 탐색하여 queue에 넣습니다.
6. queue에 넣은 수를 그 열의 아래부터 쌓습니다.
7. 그리고 중복순열로 선택한 N개의 열들에 대해 반복합니다.
8. 제일 적은 수의 블록을 남기기 위해서는 제일 많이 부셔야 합니다. 그래서 깨트린 블록 수를 최대가 되도록 update 해 나갑니다.
9. 총 블록 수에서 제일 크게 뿌신 경우의 max 값을 빼서 답을 구합니다.    