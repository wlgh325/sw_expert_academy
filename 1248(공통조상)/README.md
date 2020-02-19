# sw expert academy 1248 공통조상 자바(java)  풀이
- 난이도 : D5
- sw expert academy 1248 공통조상 문제 자바 풀이
- [sw expert academy 1248 공통조상](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15PTkqAPYCFAYD)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 두 정점의 공통 조상 중 가장 가까운 것을 찾아라.
2. 공통 조상을 루트로 하는 서브트리의 크기를 알아내라
3. 간선은 주어질때 '부모 자식'순으로 주어진다.

# 문제풀이
인접행렬로 그래프를 만든다. 그래프를 dfs로 순회하며 찾아야하는 두 값을 찾는다.  
찾으면서 list에 방문하는 점들을 저장하고 두 list를 비교하여 왼쪽부터 공통된 값을 찾는다.