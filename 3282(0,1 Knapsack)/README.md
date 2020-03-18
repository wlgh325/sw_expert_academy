# <span style="color: orange; font-weight:bold; font-size:17pt">sw expert academy 3282번 0/1 Knapsack 문제 자바(java)  풀이</span>
- 난이도 : D3
- [sw expert academy 3282번 0/1 Knapsack](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJAVpqrzQDFAWr&categoryId=AWBJAVpqrzQDFAWr&categoryType=CODE)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. 1~N번 까지 번호를 부여 받은 N개의 물건과 최대 K 부피 만큼 물건을 넣을 수 있는 가방이 있다.
2. 각 물건은 부피 Vi와 가치 Ci를 가지고 있다 (각 값은 최대 100)
3. 물건들 중 몇개를 넣어서 가방에 들어간 물건들 가치의 합을 최대가 되게 하려고 한다. (이때 부피의 합이 K를 초과하면 안된다)
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
점화식을 세워서 문제를 풀 수 있습니다. 즉 DP 테이블을 만들 것입니다.  
object의 첫번째 열[i][0]은 물건의 부피를 두번째 열[i][1]은 가치를 나타냅니다.
1. 행 i는 i번째 물건을 넣는경우, 열 j는 가방의 무게를 (i,j)의 값은 i물건을 j 무게의 가방에 넣었을때의 최대 가치 입니다.
    예를 들어 dp[1][5] = 5라면 1번째 물건을 넣고 가방의 무게가 5일때 최대 담을 수 있는 가치는 5임을 의미합니다.
2. 가방에 넣을 수 없는 경우에는 이전 물건을 넣었을때 그 가방에서의 최대 가치를 가져옵니다.
    dp[i][j] = dp[i-1][j]
3. 가방에 넣을 수 있다면 두 가지 경우를 고려합니다.
    - 넣을 수 있지만 i번째 물건을 넣는 경우 : dp[i-1][j]
    - 물건을 넣는 경우 : dp[i-1][j - object[i][0]] + object[i][1]
    물건을 넣는 경우는 i번째 물건을 넣을 자리를 만들어서(j-object[i][0]) 물건을 넣었을때의 가치( + object[i][1] )를 계산합니다.
    dp[i-1][j - object[i][0]] : 이번에 넣을 i번째의 물건 만큼의 공간을 만들었을때 그 때 무게에서의 가치의 최대값
    object[i][0] : 넣을 물건의 가치

    위의 두 가지 중 더 큰값을 최대값으로 갱신합니다.
4. 위와 같은 방법으로 dp 테이블을 작성후 dp[n][k]를 출력하면 모든 물건을 넣어봤을때 k무게에 담을 수 있는 최대 가치를 출력할 수 있습니다.

# <span style="color: red; font-weight:bold; font-size:15pt">DP 테이블</span>
다음 과 같은 입력 예제가 있습니다.
```
1
4 5
1 2
3 2
4 4
2 3
```
이때 만들어지는 dp 테이블은 다음과 같습니다.

| `v/k` | `0` | `1` | `2` | `3` | `4` | `5` |
|---|:---:|:---:|:---:|:---:|:---:|:---:|
| `0` | `0` | `0` | `0` | `0` | `0` | `0` |
| `1` | `0` | `2` | `2` | `2` | `2` | `2` |
| `2` | `0` | `2` | `2` | `2` | `4` | `4` |
| `3` | `0` | `2` | `2` | `2` | `4` | `6` |
| `4` | `0` | `2` | `3` | `5` | `5` | `6` |