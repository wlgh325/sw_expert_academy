# <span style="color: orange; font-weight:bold; font-size:17pt">sw expert academy 2117번 홈 방법 서비스 자바(java)  풀이</span>
- 모의 SW 역량 테스트
- [sw expert academy 2117번 홈 방범 서비스](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu&)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. 홈 방범 서비스는 마름모 모양으로만 제공된다.
2. 서비스 영역의 크기 K가 커질 수록 운영비용이 커진다.
3. 운영 비용 = K x K + (K - 1) x (K - 1) / K=2 일때 운영 비용은 2 x 2 + 1 * 1 = 5 이다. 이는 서비스 영역의 면적과 동일하다
4. 도시를 벗어난 영역에 서비스를 제공해도 운영 비용은 변경되지 않는다.
5. 홈 방범 서비스를 제공받는 집들은 각각 M의 비용을 지불 할 수 있다.
6. 손해를 보지 않는 한에서 최대한 많은 집에 홈방법 서비스를 제공하려고 한다.
7. 가장 많은 집들에 제공하는 서비스 영역을 찾고, 그 때의 홈방법 서비스를 제공받는 집들의 수를 출력하여라
8. 도시의 정보에서 집이 있는 위치는 1, 나머지는 0이다.
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
k=1 부터 N+1까지 모든 마름모 크기의 영역에 대해서 시뮬레이션 합니다 시뮬레이션 하면서 이익이 0보다 작은 경우는 넘어 갑니다. 그리고 제공받는 집들의 Max 값을 업데이트 해갑니다.  
마름모 모양 탐색은 현 위치에서의 맨해튼 거리를 기준으로 탐색하면 됩니다.  
k가 2인 경우 맨해튼 거리가 k-1=1 보다 작은 모든 곳을 탐색하면 됩니다.  
1. 우선 k에 따른 운영비용을 모두 구합니다
2. 도시 정보를 map 이차원 배열에 담습니다.
3. 마름모 크기가 k~ N+1일 때의 모든 점에서 탐색합니다.
    예를들어 k=2일때 모든 점에서 k=2인 마름모 모양으로 탐색을 합니다.
4. 마름모 모양 탐색은 위에서도 이야기했듯이 맨해튼 거리를 이용합니다. 거리가 k-1이하이고 유효한 위치이며 map의 값이 1인 경우 cnt를 증가합니다.
5. 수익을 계산하고 운영비용과 비교하여 수익이 크거나 같은 경우에 max 값과 cnt값을 비교하여 udpate 해줍니다.
