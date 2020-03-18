# <span style="color: orange; font-weight:bold; font-size:17pt">sw expert academy 1259번 금속 막대 자바(java)  풀이</span>
- 난이도 : D5
- [sw expert academy 1259번 금속 막대](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18NaZqIt8CFAZN&categoryId=AV18NaZqIt8CFAZN&categoryType=CODE)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
1. 원형 금속 막대는 수,암나사로 이루어져 있다.
2. 수나사와 암나사는 굵기가 서로 다르다.
3. 원형 금속 막대를 연결하기 위해서는 수나사의 굵기와 암나사의 굵기가 서로 일치해야 한다.
4. 어떤 순서로 연결해야 가장 많이 연결할 수 있는지 찾아라.
+ 수나사의 값과 암나사의 값의 중복이 없다.
+ 최대 길이로 연결했을때 남는 나사가 없다. (모든 나사를 연결할 수 있다.)
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
1 2 / 5 1 / 2 4 / 4 3 을 예로 들어 보겠습니다.
1. 이어지게 된다면 5 1 1 2 2 4 4 3이 됩니다.
2. 맨 앞의 나사를 먼저 찾아야 합니다. 그러기 위해서는 수나사의 숫자가 암나사 쪽에 나오면 안됩니다.
    5 1 / 1 2를 보면 앞쪽의 수나사의 값이 (5, 1)이라는 나사의 암나사의 값과 같기 때문에 뒤로 이어지게 됩니다.
    그렇기 때문에 암나사의 값과 겹치지 않는 수나사 값이 있는 나사를 찾습니다.
3. 그러면 (5,1)나사를 찾게 됩니다. 5를 쓰고 그 뒤에 암나사를 쓰고 암나사의 값을 수나사의 값으로 갖는 나사를 찾아가면서 씁니다.
