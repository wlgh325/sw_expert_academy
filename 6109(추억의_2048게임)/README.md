# sw expert academy 6109번 추억의 2048게임문제 자바(java)  풀이
- 난이도 : D4
- sw expert academy 6109번 추억의 2048게임 문제 자바 풀이
- [sw expert academy 6109번 추억의 2048게임](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWbrg9uabZsDFAWQ)

## [티스토리 주소](https://hoho325.tistory.com/)

# 문제정리
이는 2048 게임을 직접 해보는 것이 더 빠르게 이해할 수 있을 것 같다.  
간단히 이야기 하자면 2의 배수로만 이루어진 타일들이 있는데  
같은 숫자의 타일이 겹쳐지면 더해지는 게임이며 최종적으로 2048이라는 숫자를 가지는 타일을 만들면 끝나느 게임입니다.  
[2048게임 해보기](https://play2048.co/)

# 문제풀이
ArrayList를 중첩하여 이용하여 2차원 배열처럼 사용하여 게임판을 표현하였습니다.
전체적인 로직은 다음과 같습니다.
```
우선 ArrayList를 중첩하여 2차원 배열 처럼 사용할 수 있도록 초기화 합니다(initList)
그 다음 move 함수를 통해 움직입니다.
이때 방향에 따라 switch문을 통해 나누었습니다.
제가 생각하는 이 문제의 핵심은 오른쪽==위로, 왼쪽==아래로 라는 것입니다.
오른쪽으로 이동하는 로직이랑 위로 이동시키는 로직이 같습니다.
위로 이동시킨다면 시계방향으로 맵을 회전 시켜서 오른쪽으로 이동시키는 것과 같습니다.
  
이를 이용하여 위나 아래로 이동하는 경우 list를 시계방향으로 뒤집고 로직을 수행한다음
다시 반시계 방향으로 뒤집어 원래대로 돌려주었습니다.
```

## 오른쪽으로 이동 로직
왼쪽과 오른쪽으로 이동의 로직은 같기 때문에 오른쪽으로 이동할때를 기준으로 설명드리겠습니다.  
삽질을 많이 하다가 존재하는 0을 모두 제거하고 오른쪽에서 왼쪽으로 index를 이동하면서 확인한다면 된다는 것을 알게되었습니다. (정확히 기억 안나지만 3시간 정도는 한듯한...)  
1. 첫 행에 숫자가 0202와 같이 있다면 0을 제거합니다(22로 변함)
2. 그 다음 오른쪽에서 왼쪽으로 이동하며 같은 숫자인지 확인합니다.
3. 같은 숫자이면 값을 2배로 해주고 하나를 제거 합니다.
4. 그리고 게임판의 크기 만큼 나머지를 0으로 채워줍니다. 오른쪽으로 이동이니 왼쪽에 0을 채웁니다(0004가 됨)
