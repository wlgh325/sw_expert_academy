# sw expert academy 1245 균형점 문제 자바(java)  풀이
- 난이도 : D5
- sw expert academy 1245 균형점 문제 자바 풀이
- [sw expert academy 1245 균형점](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15MeBKAOgCFAYD)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
1. 자성체들의 중심점이 자성체의 위치, 즉 공간 좌표(x,y,z)이다.
2. n개의 자성체들은 x좌표만 다르다. (일직선상에 존재한다)
3. 자성체들이 위치한 직선에 물체가 존재하면 자성체로부터 인력을 받는다.
4. 양쪽의 힘이 같은 지점에 물체가 있다면 움직이지 않고 정지한다.
* 양쪽의 힘이 같은 지점을 찾자. n개의 자성체가 있다면 n-1개의 균형점이 존재한다.

# 문제풀이
존재하는 n-1개의 균형점을 모두 찾는다. 둘 사이의 균형점만을 각각 찾아나가면 된다.