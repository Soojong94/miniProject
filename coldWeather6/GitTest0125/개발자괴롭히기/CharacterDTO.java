package 개발자괴롭히기;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;

public class CharacterDTO {

	Random ran = new Random();
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	// 필드
	private String id;
	private String name;
	private int hp;
	private int intell;
	private int fp;
	private int mp;
	private int ment;
	private int salary;

	public CharacterDTO(String name, int hp, int intell, int fp, int mp, int ment, int salary) {
		super();
		this.name = name;
		this.hp = hp;
		this.intell = intell;
		this.fp = fp;
		this.mp = mp;
		this.ment = ment;
		this.salary = salary;
	}

//      name 메소드
	public CharacterDTO(String name) {
		super();
		this.name = name;
	}

	// state 메소드

	public CharacterDTO(int hp, int intell, int fp, int mp, int ment) {
		super();
		this.hp = hp;
		this.intell = intell;
		this.fp = fp;
		this.mp = mp;
		this.ment = ment;

	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getIntell() {
		return intell;
	}

	public int getFp() {
		return fp;
	}

	public int getMp() {
		return mp;
	}

	public int getMent() {
		return ment;
	}

	public int getSalary() {
		return salary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setIntell(int intell) {
		this.intell = intell;
	}

	public void setFp(int fp) {
		this.fp = fp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public void setMent(int ment) {
		this.ment = ment;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int normalAttack() {
		Random rand = new Random();
		int damage = this.intell + rand.nextInt(50) + 1;
		int miss = rand.nextInt(100) + 1;
		boolean isCritical = (rand.nextInt(100) < (10 + this.ment * 2));
		if (isCritical) {
			damage *= 1 * (1 + 0.05 * this.ment);
			System.out.println(this.name + "의 일반 공격에 치명타가 발동되었습니다!");
		} else if (miss < 20) {
			damage = 0;

			System.out.println();
		}
		return damage;
	}

	public int specialAttack() {
		Random rand = new Random();
		int damage = 30 + this.mp * 2;
		boolean isCritical = (rand.nextInt(100) < (10 + this.ment * 2));
		int miss = rand.nextInt(100) + 1;
		if (isCritical) {
			damage *= 1 * (1 + 0.05 * this.ment);
			System.out.println(this.name + "의 특수 공격에 치명타가 발동되었습니다!");
		} else if (miss < 20) {
			damage = 0;
		}
		return damage;
	}

	public int defend() {
		int defensePower = 50 + this.fp * 2;
		return defensePower;
	}

	public static int getStatInput(Scanner sc, String statName, int statTotal) {
		System.out.println("남은 스탯 포인트: " + statTotal);
		System.out.print(statName + "을 입력하세요: ");
		int stat = sc.nextInt();
		while (stat > statTotal) {
			System.out.print("스탯 포인트가 부족합니다. 다시 입력하세요: ");
			stat = sc.nextInt();
		}
		statTotal -= stat;
		return stat;
	}

}