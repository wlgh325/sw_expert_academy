# <span style="color: orange; font-weight:bold; font-size:17pt">sw expert academy 1258번 행렬찾기 자바(java)  풀이</span>
- 난이도 : D4
- [sw expert academy 1258번 행렬찾기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18LoAqItcCFAZN)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. 창고에 n*n 배열 형태로 화학 물질들이 있다.
2. 빈 용기는 0
3. 화학 물질이 들어 있는 용기는 화학 물질의 종류에 따라 1~9 사이의 값을 가짐
4. 화학물질이 담긴 용기들이 사각형을 이루고 있다. 사각형 내부에는 빈 용기가 없다.
5. 화학 물질이 담긴 용기들로 이루어진 사각형들은 각각 차원이 다르다. (열과 행의 개수가 서로 다르다)
6. 2개의 화학 물질이 담긴 용기들로 이루어진 사각형들 사이에는 빈 용기들이 있다. (대각선 상에는 빈 용기가 없을수도 있다.)
7. n은 100이하 이다.
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
처음에는 bfs로 풀어야 되나 하고 bfs 함수를 다 작성하였다가 깨달았습니다.  
행과 열의 크기를 알아야하기 때문에 bfs는 맞지 않다고 생각했습니다.  
그래서 다음과 같이 구현하였습니다.

- <span style="color: red; font-weight:bold; font-size:13pt">search</span>
0. 방문하지 않았고 map에 쓰여진 값이 0이 아니라면 탐색을 시작합니다.
1. 우선 오른쪽으로 이동하며 column의 개수를 잽니다. 유효하지 않은 인덱스거나 0이 아니면 탐색을 그만합니다
2. 이제 아래쪽으로 내려가며 행의 크기를 search합니다. 이도 위와 같은 방식으로 동작합니다.
3. 행의 크기와 열의 크기를 구했으니 그 영역을 방문함으로 표시해줍니다.
4. 행과 열을 가진 Matrix 객체를 반환합니다.
<br><br>

- <span style="color: red; font-weight:bold; font-size:13pt">sorting</span>
comparator를 구현합니다. 오름차순으로 정렬합니다.
1. 왼쪽 인자의 행*열의 값이 크다면 1을 반환, 작다면 -1을 반환합니다.
2. 같은 경우 행을 기준으로 구분합니다. 왼쪽 인자의 행이 더 크다면 1, 작다면 -1 같다면 0을 반환하여 행*열의 값 기준 오름차순, 같다면 행 기준 오름차순으로 정렬합니다.