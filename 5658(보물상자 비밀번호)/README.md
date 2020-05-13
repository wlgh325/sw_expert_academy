# <span style="color:orange; font-size:17pt; font-weight:bold">sw expert academy 5658번 보물상자 비밀번호 자바(java)  풀이</span>
- 모의 SW 역량 테스트
- [sw expert academy 5658번 보물상자 비밀번호](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo)
<br><br>

# <span style="color:orange; font-size:15pt">문제 정리</span>
1. 각 변에 16진수 숫자(0~F)가 적혀있는 보물상자가 있다.
2. 보물상자의 뚜껑은 시계방향으로 돌릴 수 있고, 한 번 돌릴 때마다 숫자가 시계방향으로 한 칸씩 회전한다
3. 각 변에는 동일한 개수의 숫자가 있고, 시계방향 순으로 높은 자리 숫자에 해당한다 => 하나의 수를 나타냄
4. 보물상자에는 자물쇠가 걸려있는데, 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진 수로 만든 수이다.
5. N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀번호를 출력해라
6. N은 4의배수, 8~28사이, N개의 숫자는 각각 0~F (A~F는 대문자)
<br><br>

# <span style="color:orange; font-size:15pt">문제 접근</span>
1. 회전의 구현: 회전은 queue를 이용하여 구현하려했습니다. 하지만 끝에 있는 값이 앞으로 와야하기 때문에 쉽게하기 위해 deque를 이용하는 것으로 바꾸었습니다.
2. 숫자 중 k번째로 큰수: 숫자들을 sorting 하여 구하기 위해 treeset을 이용하기로 하였습니다. treeset은 중복을 허용하지 않으며 크기순으로 정렬해주기 때문입니다.
3. 16진수 -> 10진수: 문자를 하나씩 꺼내서 바꿔주려고 했습니다.
<br><br>

# <span style="color:orange; font-size:15pt">문제 풀이</span>
1. 우선 주어진 숫자들을 deque에 모두 넣습니다.
2. 그리고 회전 시킵니다. 회전은 N/4만큼 회전 시킵니다.
3. 원래 문자를 담은 deque를 복사합니다
4. 그리고 회전 수 만큼 deque의 뒤에서 빼서 앞에다 붙여넣습니다.
5. 그리고 N/4개의 숫자씩 잘라서 숫자 하나를 만듭니다. 이게 한 변에 써있는 숫자가 됩니다.
6. treeset에 16진수를 10진수로 변환하여 넣습니다.
7. 모두 회전 후 set의 iterator를 내림 차순으로 하여 뽑습니다. 그리고 K 번째 숫자를 찾으면 됩니다.