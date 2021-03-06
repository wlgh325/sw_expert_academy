# <span style="color: orange; font-weight:bold; font-size:17pt">sw expert academy 1260번 화학물질2 문제 자바(java)  풀이</span>
- 난이도 : D6
- [sw expert academy 1260번 화학물질2](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV18OR16IuUCFAZN&categoryId=AV18OR16IuUCFAZN&categoryType=CODE)
<br><br>

## [티스토리 주소](https://hoho325.tistory.com/)
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제정리</span>
이 문제는 조건이 많으므로 잘 읽어보아야 합니다.  
그리고 이 문제를 풀기전에 행렬찾기(1258번) 문제와 금속막대(1259번) 문제를 풀어보시는 것을 추천드립니다.
1. 창고에 n*n 배열 형태로 화학 물질들이 있다.
2. 빈 용기는 0
3. 화학 물질이 들어 있는 용기는 화학 물질의 종류에 따라 1~9 사이의 값을 가짐
4. 화학물질이 담긴 용기들이 사각형을 이루고 있다. 사각형 내부에는 빈 용기가 없다.
5. 화학 물질이 담긴 용기들로 이루어진 사각형들은 각각 차원이 다르다. (열과 행의 개수가 서로 다르다)
6. 2개의 화학 물질이 담긴 용기들로 이루어진 사각형들 사이에는 빈 용기들이 있다. (대각선 상에는 빈 용기가 없을수도 있다.)
7. 행렬 간의 곱셈을 수행하는 방식으로 화학 물질을 섞는다.
8. 어떤 행렬을 먼저 곱하냐에 따라 행렬 원소간의 곱셈 수가 달라질 수 있다.
9. 찾아낸 행렬 간의 곱셈에 필요한 최소 원소간의 곱셈 수를 찾는 프로그램을 작성하여라.
10. n은 100 이하이다.
11. 부분 행렬의 열의 개수와 행의 개수 모두 서로 다르다.
12. 그래서 행렬을 곱셈 순서는 딱 한가지만 존재한다.
<br><br>

# <span style="color: red; font-weight:bold; font-size:15pt">문제 풀이</span>
우선 부분 행렬을 찾고 찾은 부분 행렬의 곱 순서를 찾아야 합니다.
- 부분 행렬 찾기: [행렬 찾기 문제 풀이](https://github.com/wlgh325/sw_expert_academy/tree/master/1258(%ED%96%89%EB%A0%AC%EC%B0%BE%EA%B8%B0))를 참고해주세요!!
- 행렬 곱 순서 찾기: [금속 막대 문제 풀이](https://github.com/wlgh325/sw_expert_academy/tree/master/1259(%EA%B8%88%EC%86%8D%EB%A7%89%EB%8C%80))를 참고해주세요!!
행렬간의 곱셈에 필요한 최소 곱셈 수를 찾는 과정은 재귀에 dp를 이용합니다.

# <span style="color: red; font-weight:bold; font-size:15pt">최소 곱셈 수 찾기</span>
문제에 제시된 예시를 들겠습니다.  
예시에서 부분 행렬을 찾고 순서를 찾으면 다음과 같습니다.
```
0번째 행렬: (2,3)
1번째 행렬: (3,4)
2번째 행렬: (4,5)
```
곱셈은 다음과 같이 2가지 방법이 존재합니다
- (0번 행렬x 1번 행렬) x 2번행렬 = 64
- 0번 행렬 x (1번 행렬 x 2번 행렬) = 90

즉 괄호를 어떻게 묶냐에 따라 곰셈 수가 달라집니다.  
괄호를 묶는 가능한 경우를 모두 따져서 곱셈 수를 계산해 최소 값을 찾아야 합니다.   
괄호를 묶기 위해서 인덱스를 재귀로 넘기며 왼쪽과 오른쪽을 따로 계산합니다.   
만약 행렬이 6개 였다면 A, (B, C, D, E, F)로 먼저 나뉘는데, (B, C, D, E, F)의 곱을 할때도 B, (C, D, E, F)로 나뉩니다.   
그리고 (C, D, E, F)는 또 C, (D, E, F)로   
(D, E, F)는 D, (E,F)로 나뉘어 갑니다. 물론 중간에 (B,C), (D,E,F)로 나누는 방법도 있을 것입니다.  
위 처럼 나뉘었을때 최소 곱의 수는 A의 행 x A의 열 x (B, C, D, E, F)에서 가능한 최소 곱을 가지는 행렬의 열이 됩니다.  

그렇기 때문에 재귀로 구현할 수 있습니다.
- 왼쪽 괄호로 묶은 행렬들의 곱의 수의 합 : calMinMult(left, i) / (left, left+1, left+2,... i)
- 오른쪽쪽 괄호로 묶은 행렬들의 곱의 수의 합 : calMinMult(i+1, right) / (i, i+1, i+2, ... right)
- left번째 행렬의 행 * i번째 행렬의 열 * right번째 행렬의 열
    i번째 행렬의 열을 곱하는 이유: (2, 3), (3,4) / (4,5)로 나누었다면 곱은 num을 계산할때 2x4 배열과 4x5 배열을 곱해야 합니다.
    (2,3), (3,4), (4,5) ....(4, a), (a, z)의 곱을 한다면 최종 행렬은 (2,z)가 되기 때문입니다.
    이때 z는 i번째 행렬의 열 값입니다.
이 모두를 더해 최소값을 갱신해 dp 배열에 저장해나가며 계산합니다.