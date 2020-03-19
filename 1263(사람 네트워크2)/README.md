# <span style="color: orange; font-weight:bold; font-size:17pt">sw expert academy 1263번 사람 네트워크2 자바(java)  풀이</span>
- 난이도 : D6
- [sw expert academy 1263번 사람 네트워크2](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18P2B6Iu8CFAZN)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. Closeness Centrality(CC) : 네트워크 상에서 한 사용자가 다른 모든 사람에게 얼마나 가까운가?
2. 사용자 i의 CC(i)는 다음과 같이 계산한다.
    모든 dist(i,j)의 합 (단 dist(i,j)는 노드 i로부터 노드 j까지의 최단 거리)
3. N은 1000 이하이다.
4. 사람들의 CC 값중 최소값을 출력하여라.
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
모든 노드에서의 최단 경로를 구하여야 합니다. 즉 All pairs shortest path problem이 됩니다.  
이는 플로이드 워셜 알고리즘을 이용하여 구할 수 있습니다.  

# <span style="color: red; font-weight:bold; font-size:15pt">1. 플로이드 워셜 알고리즘(Solution.java)</span>
한 정점에서 모든 정점으로 가는 최단 경로를 구합니다.  
예를 들어 1번 정점에서 다른 정점으로 가는 최단 경로가 다음과 같이 구해졌다면 1번 정점에서 다른 모든 정점까지의 최단 거리는 이들의 합과 같습니다.
```
0 1 1 2 2 => 0 + 1 + 1 + 2 + 2 = 6
```
모든 점에 대해서 합을 구하고, 그 중에서 제일 작은 값을 구하면 됩니다.

# <span style="color: red; font-weight:bold; font-size:15pt">2. 다익스트라 알고리즘 (Solution2.java)</span>
플로이드 워셜은 모든 점에 대해서 구하지만 다익스트라는 한 점에 대해서 최단 거리를 구할 수 있습니다.  
즉 이 알고리즘을 모든 점에 대하여 적용하면 모든 점에서 다른 점들에 대한 최단거리 들을 구할 수 있습니다.  
다익스트라 알고리즘을 n번 반복하여 나온 값들 중 제일 작은 값을 구하면 됩니다.