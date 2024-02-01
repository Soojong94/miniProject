package 개발자괴롭히기;

import java.util.Scanner;

public class CharacterCreate {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// 캐릭터 이름 입력 받기
		System.out.print("캐릭터 이름을 입력하세요:");
		String Name = scanner.nextLine();

		// 능력치 입력 받기
		System.out.println("능력치를 배분하세요.(전체 총합이 100이 되어야 합니다)");

		int h = 200;

		// 각 능력치 입력 받기
		int hp = getStat("체력", 100);
		int intell = getStat("지능력", 100 - hp);
		int fp = getStat("신앙력", 100 - hp - intell);
		int mp = getStat("마력", 100 - hp - intell - fp);
		int ment = getStat("정신력", 100 - hp - intell - fp - mp);

		// 데이터베이스에 연결하고 캐릭터 정보 저장 (이 부분은 데이터베이스 대신에 출력으로 대체)
		System.out.println("캐릭터 정보:");
		System.out.println("이름: " + Name);
		System.out.println("체력: " + (hp + h));
		System.out.println("지능: " + intell);
		System.out.println("신앙력: " + fp);
		System.out.println("마력: " + mp);
		System.out.println("정신력: " + ment);

		// 리소스 정리
		scanner.close();
	}

	// 능력치 입력을 받는 메서드
	private static int getStat(String statName, int remainingPoints) {
		Scanner scanner = new Scanner(System.in);
		int statValue;

		while (true) {
			System.out.print(statName + " 입력 (남은 포인트: " + remainingPoints + "): ");
			statValue = scanner.nextInt();

			if (statValue >= 0 && statValue <= remainingPoints) {
				break;
			} else {
				System.out.println("유효한 범위 내에서 입력하세요.");
			}
		}

		return statValue;

	}

}
