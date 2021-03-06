# sw expert academy 2105번 디저트 카페 자바(java)  풀이
- 모의 SW 역량 테스트
- [sw expert academy 2105번 디저트 카페](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 한 변의 길이가 N인 정사각형 모양을 가진 지역이 있다.
2. 숫자는 디저트 카페에서 팔고 있는 디저트의 종류 수를 의미한다.
3. 카페들 사이를 대각선 방향으로 움직일 수 있으며 사각형 모양으로 움직이고 출발지로 되돌아와야 한다.
4. 이동 중에 같은 숫자의 디저트 종류를 가지는 카페에 가면 안된다.
5. 하나의 카페에만 방문해서는 안된다.
6. 왔던 길을 다시 돌아가도 안된다.
7. 디저트를 가장 많이 먹을 수 있는 경로를 찾고 그때 경유 가능한 카페 수를 출력한다.
* 먹을 수 없는 경우는 -1을 출력한다.
8. 디저트 번호는 1~100까지 이다.

# 문제풀이
이동 가능한 경우는 다음과 같이 정합니다.
1. 이전에 오른쪽 아래로 이동한 경우: 오른쪽 아래와 왼쪽 아래로 이동 가능
2. 이전에 왼쪽 아래로 이동한 경우: 왼쪽 아래와 왼쪽 위로 이동 가능
3. 이전에 왼쪽 위로 이동한 경우: 왼쪽 위와 오른쪽 위로 이동 가능
4. 이전에 오른쪽 위로 이동한 경우: 오른쪽 위와 오른쪽 아래로 이동 가능
이렇게 위와 같이 이동함으로써 이동가능한 디저트 카페 코스를 따져줍니다.

# dfs
모든 경우를 따져주기 위해서 dfs를 순회하였습니다.  
순회하면서 다음과 같은 정보들을 저장하여 넘겨줍니다.
1. 현 x좌표
2. 현 y좌표
    1,2번은 현재 위치한 x,y 좌표를 나타냅니다.  
3. 이동한 횟수
    경유한 디저트 카페의 수를 기억합니다.
4. 이전에 이동한 방향
    그리고 다음에 이동할 방향을 정하기 위해서 이전에 이동했던 방향을 넘겨줍니다. 이동 방향은 다음과 같이 하였습니다.
    <방향>
    ```
    0: 오른쪽 아래
    1: 왼쪽 아래
    2: 왼쪽 위
    3: 오른쪽 위
    ```
    <xdir[i][j]>
    ```
    i: 이전에 이동한 방향
    j: 다음에 이동할 방향
    ```
    예를 들어 전에 오른쪽 아래로 이동했다면 다음에는 오른쪽 아래나 왼쪽 아래로 이동할 수 있습니다. 다음과 같이 나타내집니다.
    오른쪽 아래는 xdir[0][0] = 1, ydir[0][0] = 1 (x 증가, y증가)
    왼쪽 아래는 xdir[0][1] = 1, ydir[0][1] = -1 (x 증가, y감소)
    나머지 방향들에 대해서도 위와 같은 방식으로 나타내었습니다.
5. 방향을 튼 횟수
    사각형이 만들어지도록 움직인것이 맞는지 확인하기 위해서 방향을 꺾은 횟수도 저장해야 합니다. 총 3번 방향을 꺾은뒤 시작 위치로 돌아오면 사각형이 완성된 것입니다.
x,y과 시작점의 x,y좌표와 같고 3번 꺾어서 시작점에 도착한 경우 사각형을 만든 것으로 여깁니다.  
이때 k != 0 을 넣은 이유는 이제 이동을 시작한 경우를 걸러내기 위해서 입니다. (이동을 시작하자마자 dfs를 들어오면 바로 시작위치와 같기 때문에 이를 방지)

# 이전에 경유한 카페의 디저트 수
처음에는 이동한 곳의 디저트 수를 모두 list에 담아서 조사하였습니다. 하지만 이는 시간이 오래 걸리게 됩니다.  
list를 이용하지 않고, 방문한 카페의 디저트 수와 같은지 조사하기 위해서 boolean 1차원 배열을 만들어 관리합니다.  
디저트 수가 2인 곳을 방문했다면 nVisited[2] = true가 됩니다. 이를 이용하여 같은 디저트 수를 갖는 곳은 피할 수 있습니다.

# 가지치기
다음 부분이 가지치기를 해낸 부분입니다.
1. 시작 위치에서 오른쪽 아래로만 탐색
```java
    if(k==0 && i == 1)
        continue;
```
위 코드를 집어 넣지 않았더니 시간초과가 발생했지만 집어 넣으므로써 통과할 수 있었습니다.  
위 코드의 의미는 출발점에서 오른쪽 아래로 이동해서 사각형을 만드는 경우만 따짐으로써 중복 검색을 방지하는 것입니다.  
오른쪽 아래로만 돌아서 사각형을 만드는 경우만 따져 주면 모든 경우를 검사할 수 있기 때문입니다.  
위에 수는 코드를 적용하지 않았을 경우 dfs 함수가 불려진 횟수 이고 밑에는 코드를 적용했을 경우 불려진 횟수입니다.  
2배가 넘게 차이가 나는 것을 볼 수 있습니다.  
- 2452521
-  962482

2. 사각형을 만들 수 없는 경우
크게 신경쓰지 않아도 되는 부분입니다.  
맨 밑의 두 행, 맨 왼쪽 열, 맨 오른쪽 열에서는 사각형을 만들 수 없다. 이 경우는 탐색하지 않습니다.  