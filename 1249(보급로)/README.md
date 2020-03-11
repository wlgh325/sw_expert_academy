# <span style="color:orange; font-size:17pt; font-weight:bold">sw expert academy 1249번 보급로 자바(java)  풀이</span>
- 난이도 : D4
- sw expert academy 1249번 보급로 문제 자바 풀이
- [sw expert academy 1249번 보급로](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD)
<br><br>

# <span style="color:orange; font-size:15pt">문제 정리</span>
1. 출발지에서 도착지까지 가는 경로 중에 도로 복구를 하려고 한다.
2. 출발지에서 도착지까지 가는 경로 중에 복구 시간이 가장 짧은 경로에 대한 총 복구시간을 구하여라.
3. 도로가 파여진 깊이에 비례해서 복구 시간은 증가한다.
4. 깊이가 1이면 복구에 드는 시간은 1이다.
5. 지도 정보는 2차원 배열 형태로 주어진다.
6. 출발지: 좌상단, 도착지: 우하단
7. 상하좌우 방향으로 한 칸씩 움직일 수 있다.
<br><br>

# <span style="color:orange; font-size:15pt">문제 접근</span>
간단합니다. <span stlye="color: red">bfs로 모든 경우를 탐색</span>합니다. 그 위치에서의 최대값을 갱신해나갑니다.  우하단의 값을 가지고 최소값을 update해나가면 됩니다. 이 외에 다른 방법으로도 풀어 보았습니다.
1. bfs
2. bfs(우선 순위 큐)
3. dfs
4. 다익스트라
<br><br>

# <span style="color:orange; font-size:15pt">bfs (Solution.java)</span>
bfs를 구현하기 위해서 queue를 사용하였습니다.  
4번에서 방문하였더라도 그 길로 오는 것이 복구시간이 더 작은 경우 update 해주고 그 길로 이동하도록 하여야 합니다.
1. 시작 위치를 방문했다고 check하고 queue에 넣습니다.
2. 위, 아래, 왼쪽, 오른쪽으로 모두 이동합니다.
    이 때 isValidPosition을 통해 이동 가능한 경우만 queue에 넣을 수 있도록 합니다.
3. 이동 가능하고 방문하지 않았고 이 길로 가는 것이 더 적은 시간이 걸린다면 queue에 넣고 방문 처리합니다.
    그리고 현 위치에서의 값에 이동할 위치의 복구시간을 더해서 update해줍니다.
    ex) 0,0에서 1,0으로 이동가능. 이 경우 1,0까지 오는데 필요한 복구시간은 ans[0][0] + map[1][0] = 3
    <span style="color: red; font-weight:bold">현 위치까지 오는데의 복구시간(ans[a][b])에 다음 이동할 위치의 복구시간(map[dx][dy])가 다른 경로로 왔을때의 복구시간(ans[dx][dy])보다 작으면 이 길로 가는 것이 더 복구시간이 더 적다는 뜻이됩니다.</span>
5. 위와 같은 과정을 우 하단에 도착할때 까지 반복하여 최소 값을 갱신해 나갑니다.
    이럻게 하면 우 하단에 오기까지 모든 경우중 가장 작은 값을 구할 수 있습니다.
<br><br>

# <span style="color:orange; font-size:15pt">bsf 우선순위 큐 이용(Solution2.java)</span>
일반 큐 대신 <span style="color: red">우선순위 큐를 이용</span>하여 시간이 덜 걸리는 곳 먼저 꺼내서 탐색 합니다.  
우선 순위 큐는 다음과 같이 선언합니다.
```java
import java.util.PriorityQueue;
PriorityQueue<Pos> q = new PriorityQueue<>();
```
그리고 <span stlye="color: red">큐에 담는 Pos 객체의 Comparable을 구현</span>해야 합니다.  시간이 작은 것을 먼저 빼야 하기 때문에 <span stlye="color: blue">오름차순 정렬</span>합니다.
```java
class Pos implements Comparable<Pos>{
    int x;
    int y;
    int time;

    Pos(int x, int y, int time){
        this.x = x;
        this.y = y;
        this.time = time;
    }
    
    // 오름차순 정렬
    @Override
    public int compareTo(Pos p){
        if(this.time > p.time)
            return 1;
        else if(this.time < p.time)
            return -1;
        return 0;
    }
}
```
<br><br>

# <span style="color:orange; font-size:15pt">dfs (Solution3.java)</span>
이러한 문제 유형은 bfs로 푸는것이 맞습니다. 하지만 <span style="color: red">dfs에 가지치기</span>를 잘 하면 dfs로 문제를 통과할 수 있습니다. 로직은 bfs와 같습니다.
1. ans 배열에 현재 그 위치까지 오는데 걸리는 최소 시간을 저장해 갑니다.  
2. 만약 이동하려는 위치의 ans값이 min보다 작으면 탐색하지 않습니다.  
3. 또한 이동하려는 위치의 ans값(ans[dx][dy])이 현재까지 오는데 필요한 시간(ans[a][b]) 에 이동하려는 칸에서 걸리는 시간을 더한 것 보다 크면 최소가 될 수 없기에 이동할 필요가 없으므로 큐에 담지 않습니다. 
```java
if(!visited[dx][dy] || ans[dx][dy] > ans[a][b] + map[dx][dy]){
    visited[dx][dy] = true;
    ans[dx][dy] = ans[a][b] + map[dx][dy];
    stack.push(new Pos(dx, dy));
}
```
<br><br>

# <span style="color:orange; font-size:15pt">dijkstra(다익스트라) 알고리즘 이용(Solution4.java)</span>
정점 A에서 정점 B까지 최단 경로를 찾는 문제와 같기 때문에 다익스트라 알고리즘을 이용하여 풀 수도 있습니다.
0. 거리를 저장할 distance 2차원 배열(int)을 모두 MAX 값으로 초기화 합니다. 방문 여부를 저장할 visited 2차원 배열(boolean)도 필요합니다.
1. 시작 노드를 초기화 합니다. (distance=0, 방문 체크)
2. 시작 노드와 연결된 노드의 distance를 갱신해 줍니다.
3. distance중 최소값을 가지는 index를 찾습니다.(minX, minY)
4. 최소 거리를 가지는 정점을 방문처리 합니다.
5. 방금 방문한 정점과 연결되었으며 방문하지 않은 노드들에 대해 더 짧은 거리가 있다면 갱신합니다.
6. 3-5번은 모든 점에 대하여 check 합니다.(시작, 끝 점 제외 n*n-2개)
<br><br>