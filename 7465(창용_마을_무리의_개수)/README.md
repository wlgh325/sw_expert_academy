# <span style="color: orange; font-weight:bold; font-size:17pt">sw expert academy 7465번 창용 마을 무리의 개수 자바(java)  풀이</span>
- 난이도 : D4
- [sw expert academy 7465번 창용 마을 무리의 개수](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. 마을에 N명의 사람이 살고 있다.
2. 사람은 1~N번 까지 번호가 붙어있다.
3. 두 사람이 서로 아는 사이거나, 몇 사람을 거쳐 알 수 있다면 이들을 모두 묶어 무리라 한다.
4. 이 마을에는 몇개의 무리가 있는지 계산하여라.
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
union-find 기법을 이용하여 disjoint set을 만들어서 disjoint set의 개수를 구합니다.
1. 부모에 대한 정보를 저장하는 parent 배열을 초기화 합니다.
2. 연결정보 값을 입력 받아서 부모가 같지 않은 경우 union 합니다.
3. 모든 점에 대해서 union을 수행했다면 disjoint set이 완성 되었습니다.
4. 각 disjoint set에서 최상위 부모의 개수를 구하면 됩니다. 그러기 위해 모든 점들에 대해 부모를 구하여 set에 담아서 중복을 없앱니다. 그러면 disjoint set의 개수를 구할 수 있습니다.
    예를 들어 (1,2), (3,4)이고
    parent[1] = 1, parent[2] = 1, parent[3] = 3, parent[4] = 3 이라면
    1의 부모를 찾아서 1을 set에 넣고 2의 부모인 1을 찾아서 set에 넣지만 이미 넣었기 때문에 제거 됩니다.
    그리고 3의 부모인 3을 넣고 4의 부모인 4를 넣지만 중복되서 제거 됩니다.
    그리하여 set에는 1과3 2개가 들어가 있으므로 disjoint set은 2개임을 알 수 있습니다.